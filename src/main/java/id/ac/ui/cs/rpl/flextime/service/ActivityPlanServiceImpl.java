package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.*;
import id.ac.ui.cs.rpl.flextime.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ActivityPlanServiceImpl implements ActivityPlanService {
    @Autowired
    private ClassSchedulesRepository classSchedulesRepository;

    @Autowired
    private FitnessPlanRepository fitnessPlanRepository;

    @Autowired
    private TestSchedulesRepository testSchedulesRepository;

    @Autowired
    private SessionScheduleRepository sessionScheduleRepository;

    @Autowired
    private SessionPlanRepository sessionPlanRepository;

    @Autowired
    private CustomerTrainingRepository customerTrainingRepository;

    public SessionSchedule checkSessionPlan(User user, SessionSchedule sessionSchedule) throws Exception {
        List<ClassSchedules> classSchedules = classSchedulesRepository.findClassByCustomer_Id(user.getId());
        List<CustomerTraining> customerTrainings = customerTrainingRepository.findCustomerTrainingsBySessionPlan_Id(sessionSchedule.getSessionPlan().getId());
        int totalSession = 0;
        int totalDuration = sessionSchedule.getEndTime().getSecond() - sessionSchedule.getStartTime().getSecond();

        for (CustomerTraining customerTraining: customerTrainings){
            totalSession += customerTraining.getDurationInSeconds();

        }

        if (totalSession < totalDuration){
            throw new Exception("You don't have enough session to do this plan");
        }

        for (ClassSchedules classSchedule : classSchedules) {

            if (classSchedule.getClassSchedulesStart().isBefore(sessionSchedule.getStartTime()) && classSchedule.getClassSchedulesEnd().isAfter(sessionSchedule.getEndTime()) &&
            classSchedule.getClassSchedulesDay().equals(sessionSchedule.getDay())) {
                throw new Exception("You have class at that time");
            }
        }

        return sessionSchedule;
    }
    public void deleteSessionSchedules(UUID id) {
        sessionScheduleRepository.deleteById(id);
    }

    public void createSessionSchedules(User user, SessionSchedule sessionSchedule) throws Exception {
        sessionScheduleRepository.save(checkSessionPlan(user, sessionSchedule));
    }

    public List<SessionSchedule> findSessionSchedulesByDayAndByUser_Id(UUID user_id, String day) {

        FitnessPlan customerFitnessPlan = fitnessPlanRepository.findFitnessPlanByCustomer_Id(user_id);
        List<SessionPlan> sessionPlans = sessionPlanRepository.findSessionPlansByFitnessPlan_Id(customerFitnessPlan.getId());

        return sessionScheduleRepository.findSessionSchedulesByDayAndSessionPlanIn(day, sessionPlans);

    }

    public List<SessionSchedule> findSessionSchedulesByUserId(String userId) {
        return sessionScheduleRepository.findSessionSchedulesBySessionPlan_FitnessPlan_Customer_Id(UUID.fromString(userId));
    }






}
