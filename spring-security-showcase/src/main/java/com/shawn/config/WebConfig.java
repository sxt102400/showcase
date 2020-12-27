package com.shawn.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;


@ComponentScan(basePackages = "com.shawn",useDefaultFilters = false,
        includeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION,classes={Controller.class})})
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver());
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(engine);
        resolver.setCharacterEncoding("UTF-8");
        registry.viewResolver(resolver);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }

    public ServletContextTemplateResolver resolver() {
        ServletContextTemplateResolver str = new ServletContextTemplateResolver(this.getServletContext());
        str.setPrefix("/templates/");
        str.setSuffix(".html");
        str.setTemplateMode("HTML");
        str.setCharacterEncoding("UTF-8");
        return str;
    }
}
