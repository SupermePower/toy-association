package cn.toy.www.config;

import com.google.common.base.Predicate;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaobingyu
 * @Date 2017/10/30 9:21
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.declaringClass();
                if (declaringClass == BasicErrorController.class){
                    return false;
                }

                if(declaringClass.isAnnotationPresent(RestController.class)) {
                    return true;
                }

                if(input.isAnnotatedWith(ResponseBody.class)) {
                    return true;
                }
                return false;
            }
        };
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(predicate)
                .build()
                .globalOperationParameters(setHeaderToken())
                .globalOperationParameters(setApiVersion());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("云豆天眼系统")
                .description("系统接口")
                // 版本
                .version("v1")
                .build();
    }


    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("authorization").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }



    private List<Parameter> setApiVersion() {
        ParameterBuilder apiVersion = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        apiVersion.name("version").description("版本号").modelRef(new ModelRef("string")).parameterType("path").required(false).build();
        pars.add(apiVersion.build());
        return pars;
    }

}
