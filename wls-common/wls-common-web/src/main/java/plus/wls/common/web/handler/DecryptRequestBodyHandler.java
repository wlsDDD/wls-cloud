package plus.wls.common.web.handler;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import plus.wls.common.web.yml.SecretYml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * <p>接口入参解密</p>
 * RequestBodyAdvice可以理解为在@RequestBody之前需要进行的 操作，<br/>
 * ResponseBodyAdvice可以理解为在@ResponseBody之后进行的操作;<br/>
 * 所以当接口需要加解密时，在使用@RequestBody接收前台参数之前可以先在RequestBodyAdvice的实现类中进行参数的解密，<br/>
 * 当操作结束需要返回数据时，可以在@ResponseBody之后进入ResponseBodyAdvice的实现类中进行参数的加密。<br/>
 *
 * @author wls
 * @date 2022-06-21 14:07:25
 * @since 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class DecryptRequestBodyHandler extends RequestBodyAdviceAdapter {
    
    /**
     * 该方法用于判断当前请求，是否要执行beforeBodyRead方法
     *
     * @param methodParameter handler方法的参数对象
     * @param targetType      handler方法的参数类型
     * @param converterType   将会使用到的Http消息转换器类类型
     *
     * @return 返回true则会执行beforeBodyRead
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        SecretYml secretYml = SpringUtil.getBean(SecretYml.class);
        if (secretYml.getDecryptEnable()) {
            return true;
        }
        // todo 获取租户信息 判断是否需要解密
        return false;
    }
    
    /**
     * 在Http消息转换器执转换，之前执行
     *
     * @param inputMessage    客户端的请求数据
     * @param methodParameter handler方法的参数对象
     * @param targetType      handler方法的参数类型
     * @param converterType   将会使用到的Http消息转换器类类型
     *
     * @return 返回 一个自定义的HttpInputMessage
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter methodParameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        // 读取加密的请求体
        HttpHeaders headers = inputMessage.getHeaders();
        headers.remove("Content-Length");
        // todo 从租户信息中获取私钥
        String privateKey = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQg8DLSZ2SmfqEuF+v0Vrv0iRJHYYd3koVWsFnE0iPXlZKgCgYIKoEcz1UBgi2hRANCAAQp/KklBiuPDyG2lx8p8VF0yeLfzMlXjUPQUqH/TqsQsNbhsBjC80rwsUpru8Kh7B6J58rNh2ILWrlG4XpqMBza";
        byte[] decrypt;
        InputStream body = inputMessage.getBody();
        if (ObjectUtil.isNotNull(body)) {
            String xx = new JSONObject(IoUtil.read(body).toString("utf-8")).getStr("xx");
            try {
                decrypt = SmUtil.sm2(privateKey, null).decrypt(xx, KeyType.PrivateKey);
            } catch (Exception e) {
                log.error("入参解密失败 原参数 {}", body, e);
                return inputMessage;
            }
            // 使用解密后的数据，构造新的读取流
            return new HttpInputMessage() {
                @Override
                public HttpHeaders getHeaders() {
                    return inputMessage.getHeaders();
                }
                
                @Override
                public InputStream getBody() {
                    return new ByteArrayInputStream(decrypt);
                }
            };
        }
        return inputMessage;
    }
    
}
