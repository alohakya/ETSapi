package com.project.etsapi.controller;

import com.project.etsapi.service.TakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/take")
public class TakeController {
    @Autowired
    TakeService takeService;



}
