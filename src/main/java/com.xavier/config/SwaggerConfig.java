package com.xavier.config;

import com.xavier.common.jwt.JWTVars;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/**
 * Swagger 配置
 *
 * @author NewGr8Player
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket customDocket() {
		AuthorizationScope[] authorizationScopes = {new AuthorizationScope("global", "accessEverything")};

		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("ElasticSearch")
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(false)
				.select()
				.apis((input) -> input.isAnnotatedWith(ApiOperation.class))/* 注解了ApiOperation的方法 */
				.paths(PathSelectors.any())//过滤的接口
				.build()
				.securitySchemes(
						Arrays.asList(
								new ApiKey(JWTVars.HEADER_NAME, JWTVars.HEADER_NAME, "header")
						)
				)
				.securityContexts(
						Arrays.asList(
								SecurityContext.builder()
										.securityReferences(
												Arrays.asList(
														new SecurityReference(JWTVars.HEADER_NAME, authorizationScopes)
												)
										)
										.forPaths(PathSelectors.any())
										.build()
						)
				)
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("SpringBoot_MyBatis-Plus_Demo")
				.description("Alexstrasza's API ")
				.contact(new Contact("公司名称", "http://newgr8player.github.io/", ""))
				.version("0.0.1-RELEASE")
				.license("MIT License")
				.licenseUrl("https://github.com/NewGr8Player/SpringBoot-Demo/blob/master/LICENSE")
				.build();
	}

}
