package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.CustomerTraining;
import id.ac.ui.cs.rpl.flextime.model.Customization;
import id.ac.ui.cs.rpl.flextime.model.SessionPlan;
import id.ac.ui.cs.rpl.flextime.model.Training;
import id.ac.ui.cs.rpl.flextime.service.CustomerTrainingService;
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
    private TrainingService trainingService;
    @Autowired
    private SessionPlanService sessionPlanService;
    @Autowired
    private CustomerTrainingService customerTrainingService;

    @GetMapping("/save/{sessionPlanId}/{trainingId}")
    public String createCustomizationPage(@PathVariable String sessionPlanId, @PathVariable String trainingId, Model model) {
        CustomerTraining customerTraining = new CustomerTraining();
        Training training = trainingService.getTrainingById(trainingId).orElseThrow();
        model.addAttribute("customerTraining", customerTraining);
        model.addAttribute("customization", new Customization());
        model.addAttribute("sessionPlanId", sessionPlanId);
        model.addAttribute("training", training);
        return "customization/create";
    }

    @PostMapping("/save/{sessionPlanId}/{trainingId}")
    public String createCustomizationPost(@PathVariable String sessionPlanId, @PathVariable String trainingId, @ModelAttribute("customization") Customization customization ) {
        SessionPlan sessionPlan = sessionPlanService.getSessionPlanById(sessionPlanId).orElseThrow();
        Training training = trainingService.getTrainingById(trainingId).orElseThrow();

        customizationService.saveCustomization(customization);

        CustomerTraining customerTraining = new CustomerTraining();
        customerTraining.setSessionPlan(sessionPlan);
        customerTraining.setTraining(training);
        customerTraining.setCustomization(customization);
        customerTrainingService.save(customerTraining);

        return "redirect:/session-training/" + customerTraining.getSessionPlan().getId().toString();
    }

    @GetMapping("/edit/{customerTrainingId}")
    public String editCustomizationPage(@PathVariable String customerTrainingId, Model model) {
        CustomerTraining customerTraining = customerTrainingService.getCustomerTrainingById(customerTrainingId);
        Customization customization = customerTraining.getCustomization();
        model.addAttribute("training", customerTraining);
        model.addAttribute("customization", customization);
        return "customization/edit";
    }

    @PostMapping("/edit/{customerTrainingId}")
    public String editCustomizationPost(@PathVariable String customerTrainingId, @ModelAttribute("customization") Customization customization) {
        CustomerTraining customerTraining = customerTrainingService.getCustomerTrainingById(customerTrainingId);
        customizationService.saveCustomization(customization);
        customerTraining.setCustomization(customization);
        customerTrainingService.save(customerTraining);
        return "redirect:/session-training/" + customerTraining.getSessionPlan().getId().toString();
    }
}
