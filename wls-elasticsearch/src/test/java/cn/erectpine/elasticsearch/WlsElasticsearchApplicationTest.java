package cn.erectpine.elasticsearch;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WlsElasticsearchApplicationTest {
    
    @Autowired RestHighLevelClient esClient;
    
    @Test
    public void test01() throws Exception {
        // 创建索引请求
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("index_wls");
        // 执行请求
        CreateIndexResponse createIndexResponse = esClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        
        
    }
    
}