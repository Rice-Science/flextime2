package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.Training;
import id.ac.ui.cs.rpl.flextime.service.TrainingService;
import id.ac.ui.cs.rpl.flextime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String trainingPage(Model model) {
        List<Training> trainings = trainingService.getAllTrainings();
        model.addAttribute("trainings", trainings);
        model.addAttribute("admin", userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()));
        return "training/index";
    }

    @GetMapping("/add")
    public String addTrainingPage(Model model) {
        Training training = new Training();
        model.addAttribute("training", training);
        model.addAttribute("admin", userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()));
        return "training/addTraining";
    }

    @PostMapping("/add")
    public String addTrainingPost(@ModelAttribute Training training) throws IOException, NoSuchAlgorithmException {
        training.setAdmin(userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()));
        trainingService.saveTraining(training);
        return "redirect:/training";
    }

    @PostMapping("/delete/{trainingId}")
    public String deleteTraining(@PathVariable String trainingId) {
        trainingService.deleteTrainingById(trainingId);
        return "redirect:/training";
    }

    @GetMapping("/edit/{trainingId}")
    public String editTrainingPage(@PathVariable String trainingId, Model model) {
        Optional<Training> training = trainingService.getTrainingById(trainingId);
        model.addAttribute("training", training);
        model.addAttribute("admin", userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()));
        return "training/editTraining";
    }

    @PostMapping("/edit/{trainingId}")
    public String editTrainingPost(@PathVariable String trainingId, @ModelAttribute Training training) {
        training.setAdmin(userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()));
        trainingService.saveTraining(training);
        return "redirect:/training";
    }
}
