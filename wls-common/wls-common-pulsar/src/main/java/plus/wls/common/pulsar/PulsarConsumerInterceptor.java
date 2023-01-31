// package plus.wls.common.pulsar;
//
// import io.github.majusko.pulsar.consumer.DefaultConsumerInterceptor;
// import lombok.extern.slf4j.Slf4j;
// import org.apache.pulsar.client.api.Consumer;
// import org.apache.pulsar.client.api.Message;
// import org.apache.pulsar.client.api.PulsarClientException;
// import org.springframework.stereotype.Component;
//
// import java.util.concurrent.TimeUnit;
//
// /**
//  * pulsar消费者拦截器
//  *
//  * @author wls
//  * @since 2023-01-13
//  */
// @Component
// @Slf4j
// public class PulsarConsumerInterceptor extends DefaultConsumerInterceptor<Object> {
//     // @Override
//     // public Message beforeConsume(Consumer<Object> consumer, Message message) {
//     //     System.out.println("do something");
//     //     return super.beforeConsume(consumer, message);
//     // }
//
//
//     @Override
//     public Message<Object> beforeConsume(Consumer<Object> consumer, Message<Object> message) {
//         try {
//             log.info("----执行了-----");
//             consumer.reconsumeLater(message, 5, TimeUnit.SECONDS);
//         } catch (PulsarClientException e) {
//             log.error("error", e);
//             return super.beforeConsume(consumer, message);
//         }
//         return super.beforeConsume(consumer, message);
//     }
//
// }