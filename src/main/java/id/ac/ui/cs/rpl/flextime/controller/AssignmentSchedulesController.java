package id.ac.ui.cs.rpl.flextime.controller;
import id.ac.ui.cs.rpl.flextime.model.AssignmentSchedules;
import id.ac.ui.cs.rpl.flextime.service.AssignmentSchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/assignment")
public class AssignmentSchedulesController {

    @Autowired
    private AssignmentSchedulesService service;

    @GetMapping("/list")
    public String assignmentListPage (Model model) {
        List<AssignmentSchedules> allAssignments = service.findAll();
        model.addAttribute("assignments", allAssignments);
        return "assignment";
    }

    @GetMapping("/create")
    public String createAssignmentPage(Model model){
        AssignmentSchedules assignment = new AssignmentSchedules();
        model.addAttribute("assignment", assignment);
        return "CreateAssignment";
    }

    @PostMapping("/create")
    public String createAssignmentPost(@ModelAttribute AssignmentSchedules assignment) {
        service.create(assignment);
        return "redirect:list";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAssignment(@PathVariable String id) {
        service.delete(id);
        return "redirect:/assignment/list";
    }

    @GetMapping("/edit/{id}")
    public String editAssignmentPage(@PathVariable String id, Model model) {
        AssignmentSchedules assignment = service.findById(id);
        model.addAttribute("assignment", assignment);
        return "EditAssignment";
    }

    @PostMapping("/update")
    public String updateVoucher(@ModelAttribute AssignmentSchedules assignment) {
        service.update(assignment);
        return "redirect:/assignment/list";
    }

}