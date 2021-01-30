package com.razzolim.junit.controllers;

import com.razzolim.junit.fauxspring.Model;
import com.razzolim.junit.services.VetService;

public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    public String listVets(Model model){

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
