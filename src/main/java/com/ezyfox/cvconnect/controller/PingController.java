package com.ezyfox.cvconnect.controller;

import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;

@Controller("/api/v1/ping")
public class PingController {

    @DoGet("/")
    public ResponseEntity ping(){
        return ResponseEntity.ok("Ping");
    }
}
