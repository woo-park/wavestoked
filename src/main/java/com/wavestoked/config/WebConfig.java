package com.wavestoked.config;

import com.wavestoked.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// @Slf4j
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }

//     @Bean
//     public MappingJackson2HttpMessageConverter jsonEscapeConverter() {
//         ObjectMapper copy = objectMapper.copy();
//         copy.getFactory().setCharacterEscapes(new HtmlCharacterEscapes());
//         return new MappingJackson2HttpMessageConverter(copy);
//     }
}


