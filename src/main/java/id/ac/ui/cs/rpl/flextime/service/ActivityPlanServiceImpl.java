//package id.ac.ui.cs.rpl.flextime.service;
//
//import id.ac.ui.cs.rpl.flextime.model.*;
//import id.ac.ui.cs.rpl.flextime.repository.ActivityPlanRepository;
//import id.ac.ui.cs.rpl.flextime.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class ActivityPlanServiceImpl implements ActivityPlanService {
//
//    @Autowired
//    private ActivityPlanRepository activityPlanRepository;
//    @Autowired
//    private AssignmentSchedulesService assignmentSchedulesService;
//    @Autowired
//    private ClassSchedulesService classSchedulesService;
//    @Autowired
//    private TestSchedulesService testSchedulesService;
//
//
//    public ActivityPlan createActivityPlan(User user) {
//        UUID userId = user.getId();
//        List<AssignmentSchedules> assignmentSchedules = assignmentSchedulesService.findAssignmentByCustomerId(userId.toString());
//        List<ClassSchedules> classSchedules = classSchedulesService.findClassByCustomerId(userId.toString());
//        List<TestSchedules> testSchedules = testSchedulesService.findTestByCustomerId(userId.toString());
//
//        ActivityPlan  activityPlan = new ActivityPlan();
//
//        activityPlan.setUser(user);
//
//        for (AssignmentSchedules assignmentSchedule : assignmentSchedules) {
//            activityPlan.setAssignmentSchedules(assignmentSchedule);
//        }
//
//        for (ClassSchedules classSchedule : classSchedules){
//            activityPlan.setClassSchedules(classSchedule);
//        }
//
//        for (TestSchedules testSchedule : testSchedules){
//            activityPlan.setTestSchedules(testSchedule);
//        }
//
//        return activityPlan;
//    }
//
//    public void addSession(UUID sessionId, Date time, ActivityPlan activityPlan) {
//        Map<Date, UUID> sessionSchedules =  activityPlan.getSessionSchedules();
//        sessionSchedules.put(time, sessionId);
//    }
//
//    public ActivityPlan getActivityPlanByUser_Id(UUID userId) {
//        return activityPlanRepository.findActivityPlanByUser_Id(userId);
//    }
//
//    public void removeSession(Date time, ActivityPlan activityPlan) {
//        Map<Date, UUID> sessionSchedules = activityPlan.getSessionSchedules();
//        sessionSchedules.remove(time);
//    }
//
//    public Date calculateEndTimeSession( Date time, int duration) {
//        // Create a Calendar instance and set it to the given start time
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(time);
//
//        // Add the duration to the calendar (assuming duration is in seconds)
//        calendar.add(Calendar.SECOND, duration);
//
//        return calendar.getTime();
//    }
//
//    public Optional<ActivityPlan> getActivityPlanById(UUID id) {
//        return activityPlanRepository.findById(id);
//    }
//
//    public Map<String, List<Object>> groupActivitiesByDate(User user) {
//        List<AssignmentSchedules> assignmentSchedules = assignmentSchedulesService.findAssignmentByCustomerId(user.getId().toString());
//        List<TestSchedules> testSchedules = testSchedulesService.findTestByCustomerId(user.getId().toString());
//        List<ClassSchedules> classSchedules = classSchedulesService.findClassByCustomerId(user.getId().toString());
//        Map<Date, UUID> sessionSchedules = activityPlanRepository.findActivityPlanByUser_Id(user.getId()).getSessionSchedules();
//
//        List<DatedItem> datedItems = new ArrayList<>();
//
//        // Add all assignment schedules to the combined list
//        for (AssignmentSchedules assignment : assignmentSchedules) {
//            datedItems.add(new DatedItem(assignment, assignment.getAssignmentSchedulesDeadline()));
//        }
//
//        // Add all test schedules to the combined list
//        for (TestSchedules test : testSchedules) {
//            datedItems.add(new DatedItem(test, test.getTestSchedulesDate()));
//        }
//
//        // Add all class schedules to the combined list
//        for (ClassSchedules classSchedule : classSchedules) {
//            datedItems.add(new DatedItem(classSchedule, classSchedule.getClassSchedulesDate()));
//        }
//
//        // Add all session schedules to the combined list
//        for (Map.Entry<Date, UUID> entry : sessionSchedules.entrySet()) {
//            datedItems.add(new DatedItem(entry.getValue(), entry.getKey()));
//        }
//
//        // Sort the list by date
//        datedItems.sort(Comparator.comparing(DatedItem::getDate));
//
//        // Format the dates to group by day
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        // Group by day
//        Map<String, List<Object>> groupedByDay = datedItems.stream()
//                .collect(Collectors.groupingBy(
//                        item -> sdf.format(item.getDate()),
//                        Collectors.mapping(DatedItem::getItem, Collectors.toList())
//                ));
//
//        return groupedByDay;
//    }
//
//
//    //Wrapper class to keep the object and its date
//    public class DatedItem{
//        private Object item;
//        private Date date;
//
//        private DatedItem(Object item, Date date) {
//            this.item = item;
//            this.date = date;
//        }
//
//        public Object getItem() {
//            return item;
//        }
//
//        public Date getDate() {
//            return date;
//        }
//    }
//
//
//
//
//
//
//}
