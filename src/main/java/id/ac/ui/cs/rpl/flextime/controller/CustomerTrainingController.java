package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.SessionPlan;
import id.ac.ui.cs.rpl.flextime.model.Training;
import id.ac.ui.cs.rpl.flextime.service.SessionPlanService;
import id.ac.ui.cs.rpl.flextime.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/session-training")
public class CustomerTrainingController {
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private SessionPlanService sessionPlanService;

    @GetMapping("/{sessionPlanId}")
    public String index(@PathVariable String sessionPlanId, Model model) {
        SessionPlan sessionPlan =  sessionPlanService.getSessionPlanById(sessionPlanId).orElseThrow();
        List<Training> trainings = trainingService.getAllTrainingsByTrainingType(sessionPlan.getTrainingType());
        model.addAttribute("trainings", trainings);
        model.addAttribute("sessionPlan", sessionPlan);
        return "training/customer/index";
    }

    @PostMapping("/{sessionPlanId}/add-training/{trainingId}")
    public String addTraining(@PathVariable String sessionPlanId, @PathVariable String trainingId) {
        SessionPlan sessionPlan = sessionPlanService.getSessionPlanById(sessionPlanId).orElseThrow();
        Training training = trainingService.getTrainingById(trainingId).orElseThrow();
        sessionPlan.addTraining(training);
        sessionPlanService.saveSessionPlan(sessionPlan);
        return "redirect:/customization/" + sessionPlanId + "/" + trainingId;
    }
}
