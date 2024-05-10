package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.Customization;
import id.ac.ui.cs.rpl.flextime.model.SessionPlan;
import id.ac.ui.cs.rpl.flextime.model.Training;
import id.ac.ui.cs.rpl.flextime.service.CustomizationService;
import id.ac.ui.cs.rpl.flextime.service.SessionPlanService;
import id.ac.ui.cs.rpl.flextime.service.TrainingService;
import id.ac.ui.cs.rpl.flextime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@RequestMapping("/session-plan")
@Controller
public class SessionPlanController {
    @Autowired
    private UserService userService;
    @Autowired
    private SessionPlanService sessionPlanService;
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private CustomizationService customizationService;
    @GetMapping("")
    public String index(Model model) {
        List<SessionPlan> sessionPlans = sessionPlanService.getAllSessionPlansByUser(
            userService.findByUsername(SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getName()).getUsername()
        );
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
    public String pickTrainingTypePost(@ModelAttribute SessionPlan sessionPlan, @Param("trainingType") String trainingType, RedirectAttributes redirectAttributes) {
        sessionPlan.setTrainingType(trainingType);
        redirectAttributes.addFlashAttribute("sessionPlan", sessionPlan);
        return "redirect:/session-plan/pick-training";
    }

    @GetMapping("/pick-training")
    public String pickTrainingPage(Model model) {
        // get the session plan from the redirectAttributes
        SessionPlan sessionPlan = (SessionPlan) model.getAttribute("sessionPlan");
        List<Training> trainings = trainingService.getAllTrainings();
        model.addAttribute("sessionPlan", sessionPlan);
        model.addAttribute("trainings", trainings);
        return "session-plan/pickTraining";
    }

    @PostMapping("/pick-training")
    public String pickTrainingPost(@ModelAttribute SessionPlan sessionPlan, @Param("trainingId") String trainingId, RedirectAttributes redirectAttributes) {
        Training training = trainingService.getTrainingById(trainingId).get();
        sessionPlan.addTraining(training);
        sessionPlanService.saveSessionPlan(sessionPlan);
        redirectAttributes.addFlashAttribute("sessionPlan", sessionPlan);
        redirectAttributes.addFlashAttribute("training", training);
        return "redirect:/session-plan/pick-customization";
    }

    @GetMapping("/pick-customization")
    public String pickCustomizationPage(Model model) {
        Training training = (Training) model.getAttribute("training");
        Customization customization = new Customization();
        SessionPlan sessionPlan = (SessionPlan) model.getAttribute("sessionPlan");
        model.addAttribute("customization", customization);
        model.addAttribute("sessionPlan", sessionPlan);
        model.addAttribute("training", training);
        return "session-plan/pickCustomization";
    }

    @PostMapping("/pick-customization")
    public String pickCustomizationPost(@ModelAttribute Customization customization, @ModelAttribute SessionPlan sessionPlan, @ModelAttribute Training training) {
        customization.setSessionPlan(sessionPlan);
        customization.setTraining(training);
        customizationService.saveCustomization(customization);
        return "redirect:/session-plan";
    }

}
