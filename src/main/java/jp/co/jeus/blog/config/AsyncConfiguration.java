package jp.co.jeus.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class AsyncConfiguration extends WebMvcConfigurationSupport {

//    @Override
//    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//        configurer.setDefaultTimeout(5000);
//        configurer.setTaskExecutor(mvcTaskExecutor());
//    }
//
//    @Bean("AsyncProcess")
//    public ThreadPoolTaskExecutor mvcTaskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(20);
//        executor.setThreadNamePrefix("Asynchronous Process-");
//        executor.initialize();
//        return executor;
//    }
}
