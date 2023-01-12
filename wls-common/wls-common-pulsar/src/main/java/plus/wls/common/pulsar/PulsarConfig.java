package plus.wls.common.pulsar;

import io.github.majusko.pulsar.producer.ProducerFactory;
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
    @Bean
    public ProducerFactory producerFactory() {
        return new ProducerFactory()
                .addProducer("bootTopic", String.class)
                .addProducer("stringTopic", String.class)
                .addProducer("wls", String.class)
                ;
    }
    
}
