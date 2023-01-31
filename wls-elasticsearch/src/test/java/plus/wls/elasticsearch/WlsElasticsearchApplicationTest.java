package plus.wls.elasticsearch;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.wls.elasticsearch.framework.pojo.User;
import plus.wls.elasticsearch.framework.util.EsUtil;

@SpringBootTest
class WlsElasticsearchApplicationTest {
    
    @Autowired
    RestHighLevelClient esClient;
    
    @Test
    public void test01() throws Exception {
        boolean wlx_index = EsUtil.indexCreate("wls_index");
    }
    
    @Test
    public void test03() throws Exception {
        User wls1 = new User().setId(1L).setName("wls1").setAge(1);
        IndexRequest request = new IndexRequest("wls_index");
        request.id(wls1.getId() + "");
        request.source(JSON.toJSONString(wls1), XContentType.JSON);
        IndexResponse index = esClient.index(request, RequestOptions.DEFAULT);
    }
    
    @Test
    public void test02() {
        EsUtil.insertOrUpdateOne("wls_index", new User().setId(1L).setName("wls1").setAge(24).setPhone("17628082024"));
        EsUtil.insertOrUpdateOne("wls_index", new User().setId(1L).setName("wls1").setAge(24).setPhone("17628082024"));
        EsUtil.insertOrUpdateOne("wls_index_xx", new User().setId(1L).setName("wls1").setAge(24).setPhone("17628082024"));
    }
    
}
