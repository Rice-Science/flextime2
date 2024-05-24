package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.*;
import id.ac.ui.cs.rpl.flextime.service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private ActivityPlanService activityPlanService;

    @GetMapping("")
    public String home() {
        return "home";
    }

//    @GetMapping("/")
//    public String getActivityPlan(Model model){
//        User user = userService.findByUsername(SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName());
//
//        if (user == null) {
//            return "redirect:/login";
//        }
//        if (activityPlanService.getActivityPlanByUser_Id(user.getId()) == null) {
//            return "homePage/noActivityPlan";
//        }
//
//        ActivityPlan activityPlan = activityPlanService.createActivityPlan(user);
//        Map<String, List<Object>> activities = activityPlanService.groupActivitiesByDate(user);
//
//
//        model.addAttribute("activityPlan", activityPlan);
//        model.addAttribute("groupedActivities", activities);
//        return "home";
//
//    }
//
//    @PostMapping("/add-session")
//    public String addSessionPlanPost(@ModelAttribute SessionPlan sessionPlan, @ModelAttribute Date sessionSchedules) {
//        UUID userId = userService.findByUsername(SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName()).getId();
//        activityPlanService.addSession(sessionPlan.getId(), sessionSchedules, activityPlanService.getActivityPlanByUser_Id(userId));
//
//        return "homePage/addSession";
//    }
//
//    @GetMapping("/add-session")
//    public String addSessionPlanPage(Model model){
//        SessionPlan sessionPlan = new SessionPlan();
//        Date date = new Date();
//
//        return "homePage/addSession";
//    }
//
//    @PostMapping("/delete-session/{sessionSchedules}")
//    public String deleteSessionPlanPost(@PathVariable  Date sessionSchedules) {
//        UUID userId = userService.findByUsername(SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName()).getId();
//
//        activityPlanService.removeSession(sessionSchedules, activityPlanService.getActivityPlanByUser_Id(userId));
//
//
//        return "redirect:/";
//    }
}


