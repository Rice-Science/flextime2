package id.ac.ui.cs.rpl.flextime.controller;

import enums.Day;
import id.ac.ui.cs.rpl.flextime.dto.SessionScheduleDto;
import id.ac.ui.cs.rpl.flextime.model.*;
import id.ac.ui.cs.rpl.flextime.service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private ActivityPlanService activityPlanService;

    @Autowired
    private AssignmentSchedulesService assignmentService;

    @Autowired
    private ClassSchedulesService classService;

    @Autowired
    private TestSchedulesService testService;

    @Autowired
    private SessionPlanService sessionPlanService;

    @Autowired
    private UserService userService;

    @Autowired
    private FitnessPlanService fitnessPlanService;

    @GetMapping("")
    public String home(Model model) {
        FitnessPlan fitnessPlan = fitnessPlanService.getFitnessPlanByCustomerId(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId().toString());

        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<TestSchedules> testSchedules = testService.findTestByCustomerId(user.getId().toString());
        List<ClassSchedules> classSchedules = classService.findClassByCustomerId(user.getId().toString());
        List<AssignmentSchedules> assignmentSchedules = assignmentService.findAssignmentByCustomerId(user.getId().toString());

        if ( testSchedules.isEmpty() && classSchedules.isEmpty() && assignmentSchedules.isEmpty()) {
            return "homePage/noActivityPlan";
        }

        if (fitnessPlan == null) {
            return "redirect:/fitness-plan";
        }

        Map<String, List<ClassSchedules>> classSchedulesHashMap = new HashMap<>();
        Map<String, List<TestSchedules>> testSchedulesHashMap = new HashMap<>();
        Map<String, List<AssignmentSchedules>> assignmentSchedulesHashMap = new HashMap<>();
        Map<String, List<SessionSchedule>> sessionSchedulesHashMap = new HashMap<>();

        for (Day day : Day.values() ) {
            List<ClassSchedules> classSchedulesByDay = classService.findClassByDay(day.getValue());
            classSchedulesByDay = classSchedulesByDay.stream().sorted(Comparator.comparing(ClassSchedules::getClassSchedulesStart)).collect(Collectors.toList());

            classSchedulesHashMap.put(day.getValue(), classSchedulesByDay);

            List<TestSchedules> testSchedulesAdd = new ArrayList<>();
            for (TestSchedules test : testSchedules) {
                if (test.getDay().equals(day.getValue())) {
                    testSchedulesAdd.add(test);
                }
            }
            testSchedulesAdd = testSchedulesAdd.stream().sorted(Comparator.comparing(TestSchedules::getTestSchedulesStart)).collect(Collectors.toList());
            testSchedulesHashMap.put(day.getValue(), testSchedulesAdd);

            List<AssignmentSchedules> assignmentSchedulesList = assignmentService.findAssignmentByCustomerId(user.getId().toString());
            List<AssignmentSchedules> assignmentSchedulesAdd = new ArrayList<>();
            for (AssignmentSchedules assignment : assignmentSchedulesList) {
                if (assignment.getDay().equals(day.getValue())) {
                    assignmentSchedulesAdd.add(assignment);
                }
            }
            assignmentSchedulesAdd = assignmentSchedulesAdd.stream().sorted(Comparator.comparing(AssignmentSchedules::getAssignmentSchedulesDeadline)).collect(Collectors.toList());
            assignmentSchedulesHashMap.put(day.getValue(), assignmentSchedulesAdd);

            List<SessionSchedule> sessionSchedulesList = activityPlanService.findSessionSchedulesByDayAndByUser_Id(user.getId(), day.getValue());
            sessionSchedulesList = sessionSchedulesList.stream().sorted(Comparator.comparing(SessionSchedule::getStartTime)).collect(Collectors.toList());
            sessionSchedulesHashMap.put(day.getValue(), sessionSchedulesList);
        }

        model.addAttribute("classSchedulesMap", classSchedulesHashMap);
        model.addAttribute("testSchedules", testSchedulesHashMap);
        model.addAttribute("assignmentSchedules", assignmentSchedulesHashMap);
        model.addAttribute("sessionSchedules", sessionSchedulesHashMap);
        List<String> days = new ArrayList<>();
        for (Day day : Day.values()) {
            days.add(day.getValue());
        }
        model.addAttribute("days", days);

        return "home";
    }

    @GetMapping("/add-session-schedule")
    public String addSessionSchedule(Model model) {
        List<SessionPlan> availableSessionPlans2 = sessionPlanService.getAllSessionPlansByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<SessionSchedule> sessionSchedules = activityPlanService.findSessionSchedulesByUserId(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId().toString());

        List<SessionPlan >availableSessionPlans = availableSessionPlans2.stream().filter(sessionPlan -> sessionSchedules.stream().noneMatch(sessionSchedule -> sessionSchedule.getSessionPlan().getId().equals(sessionPlan.getId()))).collect(Collectors.toList());

        model.addAttribute("availableSessionPlans", availableSessionPlans);
        model.addAttribute("sessionSchedules", sessionSchedules);
        model.addAttribute("service", sessionPlanService);
        return availableSessionPlans2.isEmpty() ? "redirect:/session-plan" : "homePage/pickSession";
    }

    @GetMapping("/add-session-schedule/{sessionId}")
    public String addSessionToSchedule(@PathVariable String sessionId, Model model) {
        SessionScheduleDto sessionScheduleDto = new SessionScheduleDto();
        model.addAttribute("sessionPlan", sessionPlanService.getSessionPlanById(sessionId).orElseThrow());
        model.addAttribute("sessionScheduleDto", sessionScheduleDto);
        model.addAttribute("minTime", sessionPlanService.getTotalDurationInSeconds(sessionId));
        return "homePage/addSession";
    }

    @PostMapping("/add-session-schedule/{sessionId}")
    public String addSessionToSchedulePost(@PathVariable String sessionId, @ModelAttribute("sessionScheduleDto") SessionScheduleDto sessionScheduleDto) {
        LocalTime startTime = LocalTime.parse(sessionScheduleDto.getStartTime());
        LocalTime endTime = LocalTime.parse(sessionScheduleDto.getEndTime());
        SessionPlan sessionPlan = sessionPlanService.getSessionPlanById(sessionId).orElseThrow();
        SessionSchedule sessionSchedule = new SessionSchedule();
        sessionSchedule.setDay(sessionScheduleDto.getDay());
        sessionSchedule.setStartTime(startTime);
        sessionSchedule.setEndTime(endTime);
        sessionSchedule.setSessionPlan(sessionPlan);

        try {
            User user = userService.findByUsername((SecurityContextHolder.getContext().getAuthentication().getName()));
            activityPlanService.createSessionSchedules(user, sessionSchedule);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @PostMapping("/delete-session-schedule/{sessionId}")
    public String deleteSessionSchedule(@PathVariable String sessionId) {
        activityPlanService.deleteSessionSchedules(UUID.fromString(sessionId));
        return "redirect:/add-session-schedule";
    }
}


