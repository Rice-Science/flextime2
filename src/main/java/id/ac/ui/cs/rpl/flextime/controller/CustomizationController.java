package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.Customization;
import id.ac.ui.cs.rpl.flextime.model.SessionPlan;
import id.ac.ui.cs.rpl.flextime.model.Training;
import id.ac.ui.cs.rpl.flextime.service.CustomizationService;
import id.ac.ui.cs.rpl.flextime.service.SessionPlanService;
import id.ac.ui.cs.rpl.flextime.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customization")
public class CustomizationController {
    @Autowired
    private CustomizationService customizationService;
    @Autowired
    private SessionPlanService sessionPlanService;
    @Autowired
    private TrainingService trainingService;

    @GetMapping("/{sessionPlanId}/{trainingId}")
    public String createCustomizationPage(@PathVariable String sessionPlanId, @PathVariable String trainingId, Model model) {
        Customization customization = new Customization();
        model.addAttribute(customization);
        return "customization/create";
    }

    @PostMapping("/{sessionPlanId}/{trainingId}")
    public String createCustomizationPost(@PathVariable String sessionPlanId, @PathVariable String trainingId, @ModelAttribute("customization") Customization customization ) {
        Training training  = trainingService.getTrainingById(trainingId).orElseThrow();
        SessionPlan sessionPlan = sessionPlanService.getSessionPlanById(sessionPlanId).orElseThrow();
        customization.setSessionPlan(sessionPlan);
        customization.setTraining(training);
        customizationService.saveCustomization(customization);
        return "redirect:/session-training";
    }
}
