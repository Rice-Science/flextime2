package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.*;
import id.ac.ui.cs.rpl.flextime.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
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
    @Autowired
    private CustomerTrainingService customerTrainingService;

    @GetMapping("")
    public String index(Model model) {
        User user = userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        FitnessPlan fitnessPlan = fitnessPlanService.getFitnessPlanByCustomerId(user.getId().toString());
        List<SessionPlan> sessionPlanList = sessionPlanService.getAllSessionPlansByFitnessPlan(fitnessPlan.getId().toString());

        if (!sessionPlanList.isEmpty()) {
            for (SessionPlan sessionPlan : sessionPlanList) {
                boolean isNoTraining = customerTrainingService.getCustomerTrainingsBySessionPlanId(sessionPlan.getId().toString()).isEmpty();
                if (isNoTraining) {
                    sessionPlanService.deleteSessionPlanById(sessionPlan.getId().toString());
                }
            }
            List<SessionPlan> sessionPlans = sessionPlanService.getAllSessionPlansByFitnessPlan(fitnessPlan.getId().toString());
            model.addAttribute("sessionPlans", sessionPlans);
            model.addAttribute("service", sessionPlanService);
            return "session-plan/index";
        }
        return "session-plan/indexNoSession";
    }

    @GetMapping("/pick-training-type")
    public String pickTrainingTypePage(Model model) {
        SessionPlan sessionPlan = new SessionPlan();
        model.addAttribute("sessionPlan", sessionPlan);
        return "session-plan/pickTrainingType";
    }

    @PostMapping("/pick-training-type")
    public String pickTrainingTypePost(@ModelAttribute("sessionPlan") SessionPlan sessionPlan, @RequestParam("trainingType") String trainingType) {
        User user = userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        FitnessPlan fitnessPlan = fitnessPlanService.getFitnessPlanByCustomerId(user.getId().toString());
        sessionPlan.setFitnessPlan(fitnessPlan);
        sessionPlan.setTrainingType(trainingType);
        sessionPlanService.saveSessionPlan(sessionPlan);
        return "redirect:/session-training/" + sessionPlan.getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteSessionPlan(@PathVariable String id) {
        sessionPlanService.deleteSessionPlanById(id);
        return "redirect:/session-plan";
    }

}
