package com.cyberguard.cyberguard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberguard.cyberguard.entity.LinkRequest;
import com.cyberguard.cyberguard.entity.LinkResponse;
import com.cyberguard.cyberguard.service.LinkService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/links")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/verificar")
    public ResponseEntity<LinkResponse> verificarLink(
            @RequestBody LinkRequest request
    ) {

        LinkResponse response =
                linkService.verificarLink(request.getUrl());

        return ResponseEntity.ok(response);
    }
}