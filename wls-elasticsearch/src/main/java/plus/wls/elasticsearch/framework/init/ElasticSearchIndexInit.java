// package plus.wls.elasticsearch.framework.init;
//
// import plus.wls.common.core.exception.BusinessException;
// import plus.wls.common.core.pojo.ApiLog;
// import plus.wls.elasticsearch.framework.pojo.EsData;
// import plus.wls.elasticsearch.framework.properties.ElasticSearchProperties;
// import plus.wls.elasticsearch.framework.util.EsUtil;
// import lombok.extern.slf4j.Slf4j;
// import org.elasticsearch.action.admin.indices.alias.Alias;
// import org.elasticsearch.client.RequestOptions;
// import org.elasticsearch.client.indices.CreateIndexRequest;
// import org.elasticsearch.client.indices.CreateIndexResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
//
// import javax.annotation.PostConstruct;
// import java.util.LinkedList;
// import java.util.List;
//
///**
// * es搜索引擎索引初始化
// *
// * @author wls
// * @since 2021/10/18 16:00:04
// */
//@Slf4j
//@Component
// public class ElasticSearchIndexInit {
//
//    @Autowired ElasticSearchProperties elasticSearchProperties;
//    @Autowired EsUtil es;
//
//    @PostConstruct
//    public void indexInit() throws Exception {
//        String index = elasticSearchProperties.getIndex();
//        log.info("[搜索] 开始创建搜索索引:{}", index);
//        // 如果存在就不创建了
//        if (es.indexExist(index)) {
//            log.info("[搜索] {}索引库已经存在!", index);
//            return;
//        }
//        // 开始创建库
//        CreateIndexRequest request = new CreateIndexRequest(index);
//        // 配置文件
////        ClassPathResource seResource = new ClassPathResource("mapper/setting.json");
////        InputStream seInputStream = seResource.getInputStream();
////        String seJson = IoUtil.read(seInputStream, "UTF-8");
////        IoUtil.close(seInputStream);
//        // 映射文件
////        ClassPathResource mpResource = new ClassPathResource("mapper/label-mapping.json");
////        InputStream mpInputStream = mpResource.getInputStream();
////        String mpJson = IoUtil.read(mpInputStream, "UTF-8");
////        IoUtil.close(mpInputStream);
////        request.settings(seJson, XContentType.JSON);
////        request.mapping(mpJson, XContentType.JSON);
//        // 设置别名
//        request.alias(new Alias(index + "_alias"));
//        CreateIndexResponse createIndexResponse = es.esClient.indices().create(request, RequestOptions.DEFAULT);
//        if (!createIndexResponse.isAcknowledged()) {
//            log.warn("[搜索] 搜索索引{}创建失败", index);
//            throw new BusinessException("初始化失败");
//        }
//        // 加载数据
//        List<EsData<ApiLog>> list = new LinkedList<>();
//        list.add(new EsData<ApiLog>().setId("1").setData(new ApiLog().setIp("125")));
//        list.add(new EsData<ApiLog>().setId("2").setData(new ApiLog().setIp("127")));
//        es.insertBatch(index, list);
//        log.info("[搜索] 搜索索引{}创建成功", index);
//    }
//
//}
