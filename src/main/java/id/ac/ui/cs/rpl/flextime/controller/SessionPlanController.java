package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.*;
import id.ac.ui.cs.rpl.flextime.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/session-plan")
@Controller
public class SessionPlanController {
    @Autowired
    private UserService userService;
    @Autowired
    private SessionPlanService sessionPlanService;
    @Autowired
    private FitnessPlanService fitnessPlanService;

    @GetMapping("")
    public String index(Model model) {
        User user = userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        FitnessPlan fitnessPlan = fitnessPlanService.getFitnessPlanByCustomerId(user.getId().toString());
        List<SessionPlan> sessionPlans = sessionPlanService.getAllSessionPlansByFitnessPlan(fitnessPlan.getId().toString());
        if (sessionPlans.isEmpty()) {
            return "session-plan/indexNoSession";
        }
        model.addAttribute("sessionPlans", sessionPlans);
        return "session-plan/index";
    }

    @GetMapping("/pick-training-type")
    public String pickTrainingTypePage(Model model) {
        SessionPlan sessionPlan = new SessionPlan();
        model.addAttribute("sessionPlan", sessionPlan);
        return "session-plan/pickTrainingType";
    }

    @PostMapping("/pick-training-type")
    public String pickTrainingTypePost(@ModelAttribute("sessionPlan") SessionPlan sessionPlan) {
        User user = userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        FitnessPlan fitnessPlan = fitnessPlanService.getFitnessPlanByCustomerId(user.getId().toString());
        sessionPlan.setFitnessPlan(fitnessPlan);
        sessionPlanService.saveSessionPlan(sessionPlan);
        return "redirect:/session-training/" + sessionPlan.getId();
    }
}
