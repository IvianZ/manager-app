package org.example.managerapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {

    @GetMapping("/projects")
    public String viewAllProjects(Model model) {
        return "projects.html";
    }

}
