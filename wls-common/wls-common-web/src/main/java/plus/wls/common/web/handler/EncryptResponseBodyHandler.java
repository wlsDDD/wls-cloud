package plus.wls.common.web.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import plus.wls.common.web.pojo.properties.Secret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Nullable;

/**
 * <p>接口出参加密</p>
 * RequestBodyAdvice可以理解为在@RequestBody之前需要进行的 操作，<br/>
 * ResponseBodyAdvice可以理解为在@ResponseBody之后进行的操作;<br/>
 * 所以当接口需要加解密时，在使用@RequestBody接收前台参数之前可以先在RequestBodyAdvice的实现类中进行参数的解密，<br/>
 * 当操作结束需要返回数据时，可以在@ResponseBody之后进入ResponseBodyAdvice的实现类中进行参数的加密。<br/>
 *
 * @author wls
 * @date 2022-06-21 14:07:29
 * @since 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class EncryptResponseBodyHandler implements ResponseBodyAdvice<Object> {
    
    /**
     * 该方法用于判断当前请求的返回值，是否要执行beforeBodyWrite方法
     *
     * @param methodParameter handler方法的参数对象
     * @param converterType   将会使用到的Http消息转换器类类型
     *
     * @return 返回true则会执行beforeBodyWrite
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        Secret secret = SpringUtil.getBean(Secret.class);
        if (secret.getEncryptionEnable()) {
            return true;
        }
        // todo 获取租户信息 判断是否需要解密
        return false;
    }
    
    /**
     * 在Http消息转换器执转换，之前执行
     *
     * @param body               服务端的响应数据
     * @param methodParameter    handler方法的参数对象
     * @param mediaType          响应的ContentType
     * @param converterType      将会使用到的Http消息转换器类类型
     * @param serverHttpRequest  serverHttpRequest
     * @param serverHttpResponse serverHttpResponse
     *
     * @return 返回 一个自定义的HttpInputMessage，可以为null，表示没有任何响应
     */
    @Override
    @Nullable
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> converterType,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (ObjectUtil.isNull(body)) {
            return body;
        }
        // 获取请求数据
        String srcData = new JSONObject(body).toJSONString(0);
        // 加密
        byte[] decrypt;
        // todo 从租户信息中获取私钥
        String privateKey = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQg8DLSZ2SmfqEuF+v0Vrv0iRJHYYd3koVWsFnE0iPXlZKgCgYIKoEcz1UBgi2hRANCAAQp/KklBiuPDyG2lx8p8VF0yeLfzMlXjUPQUqH/TqsQsNbhsBjC80rwsUpru8Kh7B6J58rNh2ILWrlG4XpqMBza";
        try {
            decrypt = SmUtil.sm2(privateKey, null).encrypt(srcData, KeyType.PrivateKey);
        } catch (Exception e) {
            log.error("出参加密失败 返回原来参数 {}", body, e);
            return body;
        }
        return new JSONObject(decrypt);
    }
    
}
