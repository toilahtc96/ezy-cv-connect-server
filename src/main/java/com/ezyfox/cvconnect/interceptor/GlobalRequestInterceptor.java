package com.ezyfox.cvconnect.interceptor;

import com.ezyfox.cvconnect.annotation.UserId;
import com.ezyfox.cvconnect.exception.TokenNotFoundException;
import com.ezyfox.cvconnect.services.IAuthenticationService;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.core.annotation.Interceptor;
import com.tvd12.ezyhttp.core.constant.HttpMethod;
import com.tvd12.ezyhttp.server.core.interceptor.RequestInterceptor;
import com.tvd12.ezyhttp.server.core.manager.RequestURIManager;
import com.tvd12.ezyhttp.server.core.request.RequestArguments;

import java.lang.reflect.Method;

@Interceptor
public class GlobalRequestInterceptor
        extends EzyLoggable
        implements RequestInterceptor {

    @EzyAutoBind
    private RequestURIManager requestUriManager;
    @EzyAutoBind
    private IAuthenticationService authenticationService;

    @Override
    public boolean preHandle(RequestArguments arguments, Method handler) throws Exception {
        logger.info("request uri: {}", arguments.getRequest().getRequestURI());
        final HttpMethod method = arguments.getMethod();
        final String uri = arguments.getUriTemplate();

        if (!requestUriManager.isAuthenticatedURI(method, uri)) {
            return true;
        }
        String accessToken = arguments.getParameter("accessToken");
        if (accessToken == null) {
            accessToken = arguments.getHeader("accessToken");
        }
        if (accessToken == null) {
            accessToken = arguments.getCookieValue("accessToken");
        }
        if (accessToken == null) {
            throw new TokenNotFoundException("Can not get accessToken from cookie");
        }
        final long userId = authenticationService.verifyAccessToken(accessToken);
        arguments.setArgument(UserId.class, userId);
        return true;
    }

    @Override
    public void postHandle(RequestArguments arguments, Method handler) {
        logger.info(
                "request: {}, response: {}",
                arguments.getRequest().getRequestURI(),
                arguments.getResponse().getStatus()
        );
    }
}
