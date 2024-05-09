package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.Training;
import id.ac.ui.cs.rpl.flextime.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @GetMapping("")
    public String trainingPage(Model model) {
        List<Training> trainings = trainingService.getAllTrainings();
        model.addAttribute("trainings", trainings);
        return "training";
    }

    @GetMapping("/add")
    public String addTrainingPage(Model model) {
        Training training = new Training();
        model.addAttribute("training", training);
        return "addTraining";
    }

//    @PostMapping("/add")
//    public String addTrainingPost(@ModelAttribute Training training) {
//        training.setAdmin();
//        return "redirect:/training";
//    }
}
