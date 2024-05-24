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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

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

    @GetMapping("")
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
        return "redirect:/course-plan";
    }

    @GetMapping("/create-class")
    public String createClassPage(Model model){
        ClassSchedules classSchedules = new ClassSchedules();
        model.addAttribute("classSchedules", classSchedules);
        return "CoursePlan/CreateClass";
    }

    @PostMapping("/create-class")
    public String createClassPost(@ModelAttribute ClassSchedules classSchedules) {
        // Convert String time fields to localTime
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            classSchedules.setClassSchedulesStart(LocalTime.parse(classSchedules.getClassSchedulesStartString(), timeFormatter));
            classSchedules.setClassSchedulesEnd(LocalTime.parse(classSchedules.getClassSchedulesEndString(), timeFormatter));
        } catch (DateTimeParseException e) {
            // Handle parsing errors
            return "redirect:/course-plan/create-class?error=invalid_date_time_format";
        }

        classSchedules.setCustomer(
                userService.findByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName())
        );

        classService.create(classSchedules);

        return "redirect:/course-plan";
    }


    @GetMapping("/create-test")
    public String createTestPage(Model model){
        TestSchedules test = new TestSchedules();
        model.addAttribute("testSchedules", test);
        return "CoursePlan/CreateTest";
    }

    @PostMapping("/create-test")
    public String createTestPost(@ModelAttribute TestSchedules testSchedules) {
        // Convert String date and time fields to LocalDate and LocalTime
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            testSchedules.setTestSchedulesDate(LocalDate.parse(testSchedules.getTestSchedulesDateString(), dateFormatter));
            testSchedules.setTestSchedulesStart(LocalTime.parse(testSchedules.getTestSchedulesStartString(), timeFormatter));
            testSchedules.setTestSchedulesEnd(LocalTime.parse(testSchedules.getTestSchedulesEndString(), timeFormatter));
        } catch (DateTimeParseException e) {
            // Handle parsing errors
            return "redirect:/course-plan/create-test?error=invalid_date_time_format";
        }
        testSchedules.setCustomer(
                userService.findByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName())
        );

        try {
            testService.create(testSchedules);
        } catch (Exception e) {
            return "redirect:/course-plan/create-test?error=overlap_time";
        }

        return "redirect:/course-plan";
    }

    @PostMapping("/delete-assignments/{id}")
    public String deleteAssignment(@PathVariable String id) {
        assignmentService.delete(id);
        return "redirect:/course-plan";
    }

    @PostMapping("/delete-class/{id}")
    public String deleteClass(@PathVariable String id) {
        classService.delete(id);
        return "redirect:/course-plan";
    }

    @PostMapping("/delete-test/{id}")
    public String deleteTest(@PathVariable String id) {
        testService.delete(id);
        return "redirect:/course-plan";
    }

    @GetMapping("/edit-assignment/{id}")
    public String editAssignmentPage(@PathVariable String id, Model model) {
        AssignmentSchedules assignment = assignmentService.findById(id).orElseThrow(null);
        model.addAttribute("assignment", assignment);
        model.addAttribute("assignmentId", id);
        return "CoursePlan/EditAssignment";
    }

    @PostMapping("/update-assignment/{id}")
    public String updateAssignment(@PathVariable String id, @ModelAttribute("assignment") AssignmentSchedules updatedAssignment) {
        AssignmentSchedules assignment = assignmentService.findById(id).orElseThrow(null);
        updatedAssignment.setCustomer(userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()));
        updatedAssignment.setAssignmentSchedulesId(assignment.getAssignmentSchedulesId());
        assignmentService.update(updatedAssignment);
        return "redirect:/course-plan";
    }

    @GetMapping("/edit-class/{id}")
    public String editClassPage(@PathVariable String id, Model model) {
        ClassSchedules classSchedules = classService.findById(id).orElseThrow(null);

        classSchedules.setClassSchedulesStartString(classSchedules.getClassSchedulesStart().toString());
        classSchedules.setClassSchedulesEndString(classSchedules.getClassSchedulesEnd().toString());

        model.addAttribute("classId", id);
        model.addAttribute("classSchedules", classSchedules );
        return "CoursePlan/EditClass";
    }

    @PostMapping("/update-class/{id}")
    public String updateClass(@PathVariable String id, @ModelAttribute("classSchedules") ClassSchedules updatedClass) {
        ClassSchedules classSchedules = classService.findById(id).orElseThrow(null);

        // Convert String date and time fields to LocalDate and LocalTime
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            updatedClass.setClassSchedulesStart(LocalTime.parse(updatedClass.getClassSchedulesStartString(), timeFormatter));
            updatedClass.setClassSchedulesEnd(LocalTime.parse(updatedClass.getClassSchedulesEndString(), timeFormatter));
        } catch (DateTimeParseException e) {
            // Handle parsing errors
            return "redirect:/course-plan/update-class/" + id + "?error=invalid_date_time_format";
        }

        updatedClass.setCustomer(userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()));
        updatedClass.setClassSchedulesId(classSchedules.getClassSchedulesId());
        classService.update(updatedClass);
        return "redirect:/course-plan";
    }

    @GetMapping("/edit-test/{id}")
    public String editTestPage(@PathVariable String id, Model model) {
        TestSchedules testSchedules = testService.findById(id).orElseThrow(null);

        testSchedules.setTestSchedulesDateString(testSchedules.getTestSchedulesDate().toString());
        testSchedules.setTestSchedulesStartString(testSchedules.getTestSchedulesStart().toString());
        testSchedules.setTestSchedulesEndString(testSchedules.getTestSchedulesEnd().toString());

        model.addAttribute("testSchedules", testSchedules);
        model.addAttribute("testId", id);
        return "CoursePlan/EditTest";
    }

    @PostMapping("/update-test/{id}")
    public String updateTest(@PathVariable String id, @ModelAttribute("testSchedules") TestSchedules updatedTest) {
        TestSchedules testSchedules = testService.findById(id).orElseThrow(null);

        // Convert String date and time fields to LocalDate and LocalTime
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            updatedTest.setTestSchedulesDate(LocalDate.parse(updatedTest.getTestSchedulesDateString(), dateFormatter));
            updatedTest.setTestSchedulesStart(LocalTime.parse(updatedTest.getTestSchedulesStartString(), timeFormatter));
            updatedTest.setTestSchedulesEnd(LocalTime.parse(updatedTest.getTestSchedulesEndString(), timeFormatter));
        } catch (DateTimeParseException e) {
            // Handle parsing errors
            return "redirect:/course-plan/update-test/" + id +"error=invalid_date_time_format";
        }

        updatedTest.setCustomer(
                userService.findByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName())
        );
        updatedTest.setTestSchedulesId(testSchedules.getTestSchedulesId());
        testService.update(updatedTest);
        return "redirect:/course-plan";
    }
}