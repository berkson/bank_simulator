package com.berkson.bank_simulator.web.services.framework;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class BeanService {
    private static ApplicationContext ctx;

    public BeanService() {
    }

    public static <T> T getBean(Class<T> beanClass) {
        return ctx.getBean(beanClass);
    }

    public static void setStaticContext(ApplicationContext ctx) {
        BeanService.ctx = ctx;
    }
}
