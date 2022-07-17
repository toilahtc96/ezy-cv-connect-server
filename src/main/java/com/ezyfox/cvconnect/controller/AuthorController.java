package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.Repositories.AuthorRepository;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller("api/v1/author")
@Slf4j
@AllArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @DoGet("/")
    public void getAuthor() {
        authorRepository.findById(1L);
        log.info("ok");
    }
}
