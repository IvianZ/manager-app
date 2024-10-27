package org.example.managerapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TechController {

    @GetMapping("/techs")
    public String viewAllTechs(Model model) {
        return "techs.html";
    }

}
