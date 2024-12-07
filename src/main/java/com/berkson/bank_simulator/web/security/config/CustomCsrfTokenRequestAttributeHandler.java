package com.berkson.bank_simulator.web.security.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.util.Assert;

import java.util.function.Supplier;

/**
 * Created By : Berkson Ximenes
 * Date : 07/12/2024
 **/

public class CustomCsrfTokenRequestAttributeHandler implements CsrfTokenRequestHandler {
    private String csrfRequestAttributeName = "_csrf";

    public CustomCsrfTokenRequestAttributeHandler() {
    }

    public final void setCsrfRequestAttributeName(String csrfRequestAttributeName) {
        this.csrfRequestAttributeName = csrfRequestAttributeName;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, Supplier<CsrfToken> deferredCsrfToken) {
        Assert.notNull(request, "request cannot be null");
        Assert.notNull(response, "response cannot be null");
        Assert.notNull(deferredCsrfToken, "deferredCsrfToken cannot be null");
        request.setAttribute(HttpServletResponse.class.getName(), response);
        CsrfToken csrfToken = deferredCsrfToken.get();
        request.setAttribute(CsrfToken.class.getName(), csrfToken);
        String csrfAttrName = this.csrfRequestAttributeName != null ? this.csrfRequestAttributeName : csrfToken.getParameterName();
        request.setAttribute(csrfAttrName, csrfToken);
    }
}
