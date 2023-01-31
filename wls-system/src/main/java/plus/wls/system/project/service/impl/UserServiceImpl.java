package plus.wls.system.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.majusko.pulsar.annotation.PulsarConsumer;
import io.github.majusko.pulsar.producer.PulsarTemplate;
import io.github.majusko.pulsar.reactor.FluxConsumer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.stereotype.Service;
import plus.wls.common.core.enums.CodeInfoEnum;
import plus.wls.common.core.exception.BusinessException;
import plus.wls.common.core.util.Asserts;
import plus.wls.common.web.util.PageUtil;
import plus.wls.system.project.entity.User;
import plus.wls.system.project.mapper.UserMapper;
import plus.wls.system.project.service.IUserService;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author wls
 * @since 2021-11-19
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    
    private UserMapper userMapper;
    private PulsarTemplate<String> pulsar;
    private FluxConsumer<String> wlsConsumer;
    
    /**
     * 用户信息-列表
     *
     * @param user 查询条件
     *
     * @return 分页列表
     */
    @Override
    public IPage<User> pageUser(User user) {
        return page(PageUtil.getPlusPage(user), Wrappers.lambdaQuery(user));
    }
    
    /**
     * 根据id获取用户信息表详情
     *
     * @param id id
     *
     * @return {@link User}
     */
    @Override
    public User getUserById(Long id) {
        return getById(id);
    }
    
    /**
     * 新增-用户信息
     *
     * @param user 用户信息
     */
    @Override
    public void insertUser(User user) throws PulsarClientException {
        MessageId send = pulsar.createMessage("wls", user.toString()).deliverAfter(5, TimeUnit.SECONDS).send();
        log.info("消息发送成功 wls 消息id {}", send);
    }
    
    // Consumer<byte[]> consumer = pulsar.newConsumer()
    //                                         .topic("persistent://pulsar-****")
    //                                         .subscriptionName("sub1")
    //                                         .subscriptionType(SubscriptionType.Shared)
    //                                         .enableRetry(true)//开启重试消费
    //                                         .deadLetterPolicy(DeadLetterPolicy.builder()
    //                                                                           .maxRedeliverCount(maxRedeliveryCount)//可以指定最大重试次数
    //                                                                           .retryLetterTopic("persistent://my-property/my-ns/sub1-retry")//可以指定重试队列
    //                                                                           .deadLetterTopic("persistent://my-property/my-ns/sub1-dlq")//可以指定死信队列
    //                                                                           .build())
    //                                         .subscribe();
    
    /**
     * 修改-用户信息
     *
     * @param user 用户信息
     */
    @Override
    public void updateUser(User user) {
        Asserts.isTrue(updateById(user), () -> new BusinessException(CodeInfoEnum.DATA_UPDATE_ERROR));
    }
    
    /**
     * 删除-用户信息
     *
     * @param ids ids
     */
    @Override
    public void deleteUser(List<Long> ids) {
        Asserts.isTrue(removeByIds(ids), () -> new BusinessException(CodeInfoEnum.DATA_DELETE_ERROR));
    }
    
    // @EventListener(ApplicationReadyEvent.class)
    // public void subscribe() {
    //     wlsConsumer.asFlux().subscribe(msg -> {
    //         Consumer<?> consumer = msg.getConsumer();
    //         try {
    //
    //             log.info("消息重新投递");
    //         } catch (Exception e) {
    //             log.error("error", e);
    //             consumer.reconsumeLater(msg.getMessage(), 5, TimeUnit.SECONDS);
    //             // throw new RuntimeException(e);
    //         }
    //         log.info("消费成功 {}", msg);
    //     });
    // }
    
    @PulsarConsumer(topic = "wls")
    public void sss(String msg) {
    
    }
    
}
