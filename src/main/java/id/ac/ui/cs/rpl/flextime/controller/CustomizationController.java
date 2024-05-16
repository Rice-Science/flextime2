package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.CustomerTraining;
import id.ac.ui.cs.rpl.flextime.model.Customization;
import id.ac.ui.cs.rpl.flextime.service.CustomerTrainingService;
import id.ac.ui.cs.rpl.flextime.service.CustomizationService;
import id.ac.ui.cs.rpl.flextime.service.SessionPlanService;
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
    private CustomerTrainingService customerTrainingService;

    @GetMapping("/add/{customerTrainingId}")
    public String createCustomizationPage(@PathVariable String customerTrainingId, Model model) {
        Customization customization = new Customization();
        model.addAttribute(customization);
        return "customization/create";
    }

    @PostMapping("/add/{customerTrainingId}")
    public String createCustomizationPost(@PathVariable String customerTrainingId, @ModelAttribute("customization") Customization customization ) {
        CustomerTraining customerTraining = customerTrainingService.getCustomerTrainingById(customerTrainingId);

        customization.setTraining(customerTraining);
        customizationService.saveCustomization(customization);

        return "redirect:/session-training/" + customerTraining.getSessionPlan().getId().toString();
    }

    @GetMapping("/edit/{customerTrainingId}")
    public String editCustomizationPage(@PathVariable String customerTrainingId, Model model) {
        Customization customization = customizationService.getCustomizationByTrainingId(customerTrainingId);
        model.addAttribute("customization", customization);
        return "customization/edit";
    }

    @PostMapping("/edit/{customerTrainingId}")
    public String editCustomizationPost(@ModelAttribute("customization") Customization customization, @PathVariable String customerTrainingId) {
        CustomerTraining customerTraining = customerTrainingService.getCustomerTrainingById(customerTrainingId);
        customization.setTraining(customerTraining);
        customizationService.saveCustomization(customization);
        return "redirect:/session-training/" + customerTraining.getSessionPlan().getId().toString();
    }
}
