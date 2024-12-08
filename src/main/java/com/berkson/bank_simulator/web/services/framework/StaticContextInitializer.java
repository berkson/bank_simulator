package com.berkson.bank_simulator.web.services.framework;

import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;

public class StaticContextInitializer {
    private final ApplicationContext ctx;

    public StaticContextInitializer(ApplicationContext ctx, WebApplicationContext webCtx, MessageSource messageSource) {
        this.ctx = ctx;
    }

    @PostConstruct
    private void load() {
        BeanService.setStaticContext(ctx);
    }
}
