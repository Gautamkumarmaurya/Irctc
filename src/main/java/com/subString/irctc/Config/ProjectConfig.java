package com.subString.irctc.Config;

import com.subString.irctc.Interceptors.MyCustomInterceptors;
import com.subString.irctc.Interceptors.TimeLoggerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    @Autowired
    private MyCustomInterceptors myCustomInterceptors;

    @Autowired
    private TimeLoggerInterceptor timeLoggerInterceptor;

  //  @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //  [  /trains/** , /station/** ] yaha par aap or Api dal sakte ho aap jis api lagana chahoge to usi ar cheleaga
//        registry.addInterceptor(myCustomInterceptors)
//                .addPathPatterns("/trains/**" , "/stations/**")
//                .excludePathPatterns("/trains/list");
//     }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(timeLoggerInterceptor).
                 addPathPatterns("/trains/**" , "/stations/**")
                 .excludePathPatterns("/trains/list");

    }
}
