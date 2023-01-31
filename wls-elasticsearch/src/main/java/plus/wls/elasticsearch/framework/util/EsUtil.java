package plus.wls.elasticsearch.framework.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import plus.wls.common.core.exception.EsIoException;
import plus.wls.elasticsearch.framework.pojo.EsData;
import plus.wls.elasticsearch.framework.pojo.EsId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * es搜索引擎工具类
 *
 * @author wls
 * @since 2021/10/18 17:16:19
 */
@Slf4j
public class EsUtil {
    
    public static RestHighLevelClient esClient;
    /*-------------------------------------------------------索引--------------------------------------------------*/
    
    /**
     * 创建索引
     * 仅当索引不存在时创建
     *
     * @param index 索引
     *
     * @return true 创建成功
     */
    public static boolean indexCreate(String index) {
        if (indexExist(index)) {
            return false;
        }
        try {
            return esClient.indices().create(new CreateIndexRequest(index), RequestOptions.DEFAULT).isAcknowledged();
        } catch (IOException e) {
            log.error("indexCreate 方法IO异常", e);
            throw new EsIoException("indexCreate 方法IO异常");
        }
    }
    
    /**
     * 判断某个index是否存在
     *
     * @param index index
     *
     * @return true 存在
     */
    public static boolean indexExist(String index) {
        GetIndexRequest request = new GetIndexRequest(index);
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
        try {
            return esClient.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("indexExist 方法IO异常", e);
            throw new EsIoException("indexExist 方法IO异常");
        }
    }
    
    /**
     * 删除索引
     * 仅当索引存在时删除
     *
     * @param index index
     *
     * @return true 删除成功
     *
     * @author wls
     */
    public static boolean indexDelete(String index) {
        if (indexExist(index)) {
            try {
                return esClient.indices().delete(new DeleteIndexRequest(index), RequestOptions.DEFAULT).isAcknowledged();
            } catch (IOException e) {
                log.error("indexDelete 方法IO异常", e);
                throw new EsIoException("indexDelete 方法IO异常");
            }
        }
        return false;
    }
    
    /*-------------------------------------------------------文档--------------------------------------------------*/
    
    /**
     * 插入/更新一条记录
     *
     * @param index index
     * @param data  对象
     *
     * @author fanxb
     * @date 2019/7/24 15:02
     */
    public static <T> IndexResponse insertOrUpdateOne(String index, T data) {
        IndexRequest request = new IndexRequest(index);
        if (data instanceof EsId) {
            request.id(((EsId) data).getId().toString());
        }
        request.source(JSON.toJSONString(data), XContentType.JSON);
        try {
            return esClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("insertOrUpdateOne 方法IO异常", e);
            throw new EsIoException("insertOrUpdateOne 方法IO异常");
        }
    }
    
    /**
     * Description: 批量插入数据
     *
     * @param index index
     * @param list  带插入列表
     *
     * @author fanxb
     * @date 2019/7/24 17:38
     */
    public static <T> void insertBatch(String index, List<EsData<T>> list) {
        BulkRequest request = new BulkRequest();
        list.forEach(item -> request.add(new IndexRequest(index).id(item.getId()).source(JSON.toJSONString(item.getData()), XContentType.JSON)));
        try {
            esClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Description: 批量删除
     *
     * @param index  index
     * @param idList 待删除列表
     *
     * @author fanxb
     * @date 2019/7/25 14:24
     */
    public static <T> void deleteBatch(String index, Collection<T> idList) {
        BulkRequest request = new BulkRequest();
        idList.forEach(item -> request.add(new DeleteRequest(index, item.toString())));
        try {
            esClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Description: 搜索
     *
     * @param index   index
     * @param builder 查询参数
     * @param c       结果类对象
     *
     * @return java.util.ArrayList
     *
     * @author fanxb
     * @date 2019/7/25 13:46
     */
    public static <T> List<T> search(String index, SearchSourceBuilder builder, Class<T> c) {
        SearchRequest request = new SearchRequest(index);
        request.source(builder);
        try {
            SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            List<T> res = new ArrayList<>(hits.length);
            for (SearchHit hit : hits) {
                res.add(JSON.parseObject(hit.getSourceAsString(), c));
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Description: delete by query
     *
     * @param index   index
     * @param builder builder
     *
     * @author fanxb
     * @date 2019/7/26 15:16
     */
    public static void deleteByQuery(String index, QueryBuilder builder) {
        DeleteByQueryRequest request = new DeleteByQueryRequest(index);
        request.setQuery(builder);
        // 设置批量操作数量,最大为10000
        request.setBatchSize(10000);
        request.setConflicts("proceed");
        try {
            esClient.deleteByQuery(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
