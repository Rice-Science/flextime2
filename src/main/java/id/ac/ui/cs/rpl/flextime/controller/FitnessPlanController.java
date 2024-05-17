package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.FitnessPlan;
import id.ac.ui.cs.rpl.flextime.model.SessionPlan;
import id.ac.ui.cs.rpl.flextime.model.User;
import id.ac.ui.cs.rpl.flextime.service.FitnessPlanService;
import id.ac.ui.cs.rpl.flextime.service.SessionPlanService;
import id.ac.ui.cs.rpl.flextime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/fitness-plan")
@Controller
public class FitnessPlanController {
    @Autowired
    private FitnessPlanService fitnessPlanService;
    @Autowired
    private UserService userService;
    @PostMapping("/add")
    public String addFitnessPlan() {
        FitnessPlan fitnessPlan = new FitnessPlan();
        fitnessPlan.setCustomer(userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()));
        fitnessPlanService.saveFitnessPlan(fitnessPlan);
        return "redirect:/fitness-plan";
    }

    @GetMapping("")
    public String index() {
        User user  = userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        if (user == null) {
            return "redirect:/auth/login";
        }

        FitnessPlan fitnessPlan = fitnessPlanService.getFitnessPlanByCustomerId(
            userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()).getId().toString()
        );
        if (fitnessPlan == null) {
            return "fitness-plan/index";
        }

        return "redirect:/session-plan";
    }
}
