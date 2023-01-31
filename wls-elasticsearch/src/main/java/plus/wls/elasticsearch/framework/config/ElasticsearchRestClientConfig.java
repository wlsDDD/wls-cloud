package plus.wls.elasticsearch.framework.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import plus.wls.elasticsearch.framework.properties.ElasticSearchProperties;
import plus.wls.elasticsearch.framework.util.EsUtil;

/**
 * 1、导入依赖
 * 2、编写配置,给容器中注入RestHighLevelClient对象
 * 3、操作Es
 *
 * @author wls
 * @since 2021/10/18 15:40:14
 */
@Configuration
public class ElasticsearchRestClientConfig {
    
    @Autowired
    ElasticSearchProperties elasticSearchProperties;
    
    @Bean
    public RestClientBuilder restClientBuilder() {
        return RestClient.builder(new HttpHost(elasticSearchProperties.getHost(), elasticSearchProperties.getPort(), elasticSearchProperties.getScheme()));
    }
    
    @Bean
    public RestHighLevelClient esClient(RestClientBuilder restClientBuilder) {
        restClientBuilder.setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder.setSocketTimeout(elasticSearchProperties.getTimeOut()));
        // 此处可以进行其它操作
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        EsUtil.esClient = restHighLevelClient;
        return restHighLevelClient;
    }
    
}
