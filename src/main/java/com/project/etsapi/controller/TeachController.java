package com.project.etsapi.controller;

import com.project.etsapi.service.TeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teach")
public class TeachController {
    @Autowired
    TeachService teachService;
}
