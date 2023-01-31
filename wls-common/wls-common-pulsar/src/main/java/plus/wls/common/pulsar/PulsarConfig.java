package plus.wls.common.pulsar;

import io.github.majusko.pulsar.error.exception.ClientInitException;
import io.github.majusko.pulsar.producer.ProducerFactory;
import io.github.majusko.pulsar.reactor.FluxConsumer;
import io.github.majusko.pulsar.reactor.FluxConsumerFactory;
import io.github.majusko.pulsar.reactor.PulsarFluxConsumer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * pulsar配置
 *
 * @author wls
 * @since 2023-01-11
 */
@Configuration
public class PulsarConfig {
    
    
    @Autowired
    private FluxConsumerFactory fluxConsumerFactory;
    
    @Bean
    public ProducerFactory producerFactory() {
        return new ProducerFactory().addProducer("bootTopic", String.class)
                                    .addProducer("stringTopic", String.class)
                                    // .addProducer("wls-topic", String.class)
                                    .addProducer("wls", String.class);
    }
    
    @Bean
    public FluxConsumer<String> wlsConsumer() throws ClientInitException, PulsarClientException {
        return fluxConsumerFactory.newConsumer(
                PulsarFluxConsumer.builder()
                                  .setTopic("wls")
                                  .setConsumerName("wls-consumer")
                                  .setSubscriptionName("wls-subscription")
                                  .setSimple(false)
                                  .setMessageClass(String.class)
                                  .build());
    }
    // @Bean
    // public Consumer<byte[]> wlsConsumer(PulsarClient pulsarClient) throws PulsarClientException {
    //     return pulsarClient.newConsumer()
    //                        .topic("wls")
    //                        .consumerName("wls-blue")
    //                        .subscriptionName("wls-desc")
    //                        .enableRetry(true)
    //                        .negativeAckRedeliveryDelay(10, TimeUnit.SECONDS)
    //                        .subscribe();
    //
    // }
    
}
