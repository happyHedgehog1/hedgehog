package com.hedgehog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /* a라는 url은 로컬에 있는 폴더 위치에서 불러온 값들을 이용한다.
     * 이때 원본파일 경로와 원본파일 이름, 변환파일 경로와 변환파일 이름은
     * 데이터베이스에 저장되고 Controller 에서 파일 자체를 저 C드라이브 로컬 위치에 저장해야 한다. */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/", "classpath:/static/");

        // 이미지 불러올 수 있도록 세팅
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///c:/hedgehog/upload/");

        registry.addResourceHandler("/thumbPath/**")
                .addResourceLocations("file:///c:/hedgehog/img/upload/thumbnail/");
        registry.addResourceHandler("/imagePath/**")
                .addResourceLocations("file:///c:/hedgehog/img/upload/original/");
//file:///C:/hedgehog/img/upload/original/0a83a8fc-0b1e-44d9-8e3b-e6afc34f5a37_%ED%9A%8C%EC%9B%90%EC%A0%95%EB%B3%B4%20%EC%88%98%EC%A0%95.png
//file:///C:/hedgehog/img/upload/original/0a83a8fc-0b1e-44d9-8e3b-e6afc34f5a37_%ED%9A%8C%EC%9B%90%EC%A0%95%EB%B3%B4%20%EC%88%98%EC%A0%95.png
    }

}
