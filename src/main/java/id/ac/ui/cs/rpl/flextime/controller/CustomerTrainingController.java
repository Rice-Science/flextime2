package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.CustomerTraining;
import id.ac.ui.cs.rpl.flextime.model.SessionPlan;
import id.ac.ui.cs.rpl.flextime.model.Training;
import id.ac.ui.cs.rpl.flextime.service.CustomerTrainingService;
import id.ac.ui.cs.rpl.flextime.service.SessionPlanService;
import id.ac.ui.cs.rpl.flextime.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/session-training")
public class CustomerTrainingController {
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private SessionPlanService sessionPlanService;
    @Autowired
    private CustomerTrainingService customerTrainingService;

    @GetMapping("/{sessionPlanId}")
    public String index(@PathVariable String sessionPlanId, Model model) {
        SessionPlan sessionPlan =  sessionPlanService.getSessionPlanById(sessionPlanId).orElseThrow();
        List<Training> trainings = trainingService.getAllTrainingsByTrainingType(sessionPlan.getTrainingType());
        List<CustomerTraining> customerTrainings = customerTrainingService.getCustomerTrainingsBySessionPlanId(sessionPlanId);
        model.addAttribute("trainings", trainings);
        model.addAttribute("sessionPlan", sessionPlan);
        model.addAttribute("customerTrainings", customerTrainings);
        return "training/customer/index";
    }

    @PostMapping("/{sessionPlanId}/add-training/{trainingId}")
    public String addTraining(@PathVariable String sessionPlanId, @PathVariable String trainingId, RedirectAttributes ra) {
        return "redirect:/customization/save/" + sessionPlanId + "/" + trainingId;
    }

    @PostMapping("/{sessionPlanId}/delete-training/{custTrainingId}")
    public String deleteTraining(@PathVariable String custTrainingId, @PathVariable String sessionPlanId) {
        customerTrainingService.deleteCustomerTrainingById(custTrainingId);
        return "redirect:/session-training/" + sessionPlanId;
    }
}
