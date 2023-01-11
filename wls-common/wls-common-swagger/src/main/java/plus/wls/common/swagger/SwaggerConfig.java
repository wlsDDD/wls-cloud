package plus.wls.common.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(java.util.Date.class, String.class)
                .securitySchemes(security());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("wls-cloud")
                .description("接口文档")
                .termsOfServiceUrl("https://wls.plus/")
                .version("V1.0.0")
                .build();
    }
    
    private List<ApiKey> security() {
        ArrayList<ApiKey> list = new ArrayList<>();
        list.add(new ApiKey("Authorization", "Authorization", "header"));
        return list;
    }
    
}
