package com.ecommerce.ecommercebe.configuration;

import com.ecommerce.ecommercebe.configuration.filter.RequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistryConfiguration {
    @Autowired
    private RequestFilter requestFilterNew;

    @Bean
    public FilterRegistrationBean<RequestFilter> registerRequestFilter(){
        FilterRegistrationBean<RequestFilter> requestFilter = new FilterRegistrationBean<>();
        requestFilter.setFilter(requestFilterNew);
        requestFilter.addUrlPatterns("/products/*");
        requestFilter.addUrlPatterns("/features/*");
        requestFilter.addUrlPatterns("/logout");
        return requestFilter;
    }
}
