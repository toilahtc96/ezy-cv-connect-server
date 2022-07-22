package com.ezyfox.cvconnect;

import com.tvd12.ezyhttp.server.boot.EzyHttpApplicationBootstrap;
import com.tvd12.ezyhttp.server.core.asm.RequestHandlerImplementer;

public class CurVitaeConnectApplication {

    public static void main(String[] args) throws Exception {
        RequestHandlerImplementer.setDebug(true);
        EzyHttpApplicationBootstrap.start(CurVitaeConnectApplication.class);
    }
}
