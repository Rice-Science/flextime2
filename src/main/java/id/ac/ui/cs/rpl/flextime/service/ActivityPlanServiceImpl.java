package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.*;
import id.ac.ui.cs.rpl.flextime.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<TestSchedules> testSchedules = testSchedulesRepository.findTestByCustomer_Id(user.getId());
        List<CustomerTraining> customerTrainings = customerTrainingRepository.findCustomerTrainingsBySessionPlan_Id(sessionSchedule.getSessionPlan().getId());
        int totalSession = 0;
        int totalDuration = sessionSchedule.getEnd().getSecond() - sessionSchedule.getStart().getSecond();

        for (CustomerTraining customerTraining: customerTrainings){
            totalSession += customerTraining.getDurationInSeconds();

        }

        if (totalSession > totalDuration){
            throw new Exception("You have exceeded the total duration of the session");
        }

        for (ClassSchedules classSchedule : classSchedules) {

            if (classSchedule.getStartDate().isBefore(sessionSchedule.getStart()) && classSchedule.getEndDate().isAfter(sessionSchedule.getEnd())) {
                throw new Exception("You have class at that time");
            }
        }

        for (TestSchedules testSchedule: testSchedules){
            if (testSchedule.getStartDate().isBefore(sessionSchedule.getStart()) && testSchedule.getEndDate().isAfter(sessionSchedule.getEnd())) {
                throw new Exception("You have test at that time");
            }
        }

        return sessionSchedule;
    }
    public void deleteSessionSchedules(UUID id) {
        sessionScheduleRepository.deleteById(id);
    }

    public void createSessionSchedules(User user, SessionSchedule sessionSchedule) throws Exception {
        checkSessionPlan(user, sessionSchedule);
        sessionScheduleRepository.save(sessionSchedule);
    }

    public List<SessionSchedule> findSessionSchedulesByUser_Id(UUID user_id) {

        FitnessPlan customerFitnessPlan = fitnessPlanRepository.findFitnessPlanByCustomer_Id(user_id);
        List<SessionPlan> sessionPlans = sessionPlanRepository.findSessionPlansByFitnessPlan_Id(customerFitnessPlan.getId());

        return sessionScheduleRepository.findSessionSchedulesBySessionPlanIn(sessionPlans);

    }






}
