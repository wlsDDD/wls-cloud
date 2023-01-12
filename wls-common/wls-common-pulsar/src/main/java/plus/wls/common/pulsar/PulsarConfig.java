package plus.wls.common.pulsar;

import io.github.majusko.pulsar.producer.ProducerFactory;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * pulsar配置
 *
 * @author wls
 * @since 2023-01-11
 */
@Configuration
public class PulsarConfig {
    
    private PulsarClient pulsarClient;
    
    @Bean
    public ProducerFactory producerFactory() {
        return new ProducerFactory()
                .addProducer("bootTopic", String.class)
                .addProducer("stringTopic", String.class)
                .addProducer("wls", String.class)
                ;
    }
    
    @Bean
    public Consumer configProducer(PulsarClient pulsarClient) throws PulsarClientException {
        return pulsarClient.newConsumer()
                           .topic("wls")
                           .consumerName("wls-blue")
                           // .subscriptionName("wls-desc")
                           .negativeAckRedeliveryDelay(10, TimeUnit.SECONDS)
                           .subscribe();
        
    }
    
}
