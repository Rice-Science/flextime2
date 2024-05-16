package id.ac.ui.cs.rpl.flextime.controller;
import id.ac.ui.cs.rpl.flextime.model.AssignmentSchedules;
import id.ac.ui.cs.rpl.flextime.model.ClassSchedules;
import id.ac.ui.cs.rpl.flextime.model.TestSchedules;
import id.ac.ui.cs.rpl.flextime.service.AssignmentSchedulesService;
import id.ac.ui.cs.rpl.flextime.service.ClassSchedulesService;
import id.ac.ui.cs.rpl.flextime.service.TestSchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import id.ac.ui.cs.rpl.flextime.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/course-plan")
public class CoursePlanController {

    @Autowired
    private AssignmentSchedulesService assignmentService;
    @Autowired
    private ClassSchedulesService classService;
    @Autowired
    private TestSchedulesService testService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String assignmentListPage (Model model) {
        List<AssignmentSchedules> allAssignments = assignmentService.findAssignmentByCustomerId(
                userService.findByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()).getId().toString()
        );
        List<ClassSchedules> allClass = classService.findClassByCustomerId(
                userService.findByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()).getId().toString()
        );
        List<TestSchedules> allTest = testService.findTestByCustomerId(
                userService.findByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()).getId().toString()
        );

        model.addAttribute("assignments", allAssignments);
        model.addAttribute("class", allClass);
        model.addAttribute("test", allTest);

        return "CoursePlan/CoursePlan";
    }

    @GetMapping("/create-assignments")
    public String createAssignmentPage(Model model){
        AssignmentSchedules assignment = new AssignmentSchedules();
        model.addAttribute("assignment", assignment);
        return "CoursePlan/CreateAssignment";
    }

    @PostMapping("/create-assignments")
    public String createAssignmentPost(@ModelAttribute AssignmentSchedules assignment) {
        assignment.setCustomer(userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()));
        assignmentService.create(assignment);
        return "redirect:course-plan/list";
    }

    @GetMapping("/create-class")
    public String createClassPage(Model model){
        ClassSchedules classSchedules = new ClassSchedules();
        model.addAttribute("class", classSchedules);
        return "CoursePlan/CreateClass";
    }

    @PostMapping("/create-class")
    public String createClassPost(@ModelAttribute ClassSchedules classSchedules) {
        classSchedules.setCustomer(
                userService.findByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName())
        );
        classService.create(classSchedules);
        return "redirect:course-plan/list";
    }

    @GetMapping("/create-test")
    public String createTestPage(Model model){
        TestSchedules test = new TestSchedules();
        model.addAttribute("test", test);
        return "CoursePlan/CreateTest";
    }

    @PostMapping("/create-test")
    public String createTestPost(@ModelAttribute TestSchedules testSchedules) {
        testSchedules.setCustomer(
                userService.findByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName())
        );
        testService.create(testSchedules);
        return "redirect:course-plan/list";
    }

    @DeleteMapping("/delete-assignments/{id}")
    public String deleteAssignment(@PathVariable String id) {
        assignmentService.delete(id);
        return "redirect:/course-plan/list";
    }

    @DeleteMapping("/delete-class/{id}")
    public String deleteClass(@PathVariable String id) {
        classService.delete(id);
        return "redirect:/course-plan/list";
    }

    @DeleteMapping("/delete-test/{id}")
    public String deleteTest(@PathVariable String id) {
        testService.delete(id);
        return "redirect:/course-plan/list";
    }

    @GetMapping("/edit-assignment/{id}")
    public String editAssignmentPage(@PathVariable String id, Model model) {
        Optional<AssignmentSchedules> assignment = assignmentService.findById(id);
        model.addAttribute("assignment", assignment);
        return "CoursePlan/EditAssignment";
    }

    @PostMapping("/update-assignment/{id}")
    public String updateAssignment(@PathVariable String id, @RequestBody AssignmentSchedules updatedAssignment) {
        assignmentService.update(id, updatedAssignment);
        return "redirect:/course-plan/list";
    }

    @GetMapping("/edit-class/{id}")
    public String editClassPage(@PathVariable String id, Model model) {
        Optional<ClassSchedules> classSchedules = classService.findById(id);
        model.addAttribute("class", classSchedules );
        return "CoursePlan/EditClass";
    }

    @PostMapping("/update-class/{id}")
    public String updateClass(@PathVariable String id, @RequestBody ClassSchedules updatedClass) {
        classService.update(id, updatedClass);
        return "redirect:/course-plan/list";
    }

    @GetMapping("/edit-test/{id}")
    public String editTestPage(@PathVariable String id, Model model) {
        Optional<TestSchedules> test = testService.findById(id);
        model.addAttribute("class", test );
        return "CoursePlan/EditTest";
    }

    @PostMapping("/update-test/{id}")
    public String updateTest(@PathVariable String id, @RequestBody TestSchedules updatedTest) {
        testService.update(id, updatedTest);
        return "redirect:/course-plan/list";
    }
}