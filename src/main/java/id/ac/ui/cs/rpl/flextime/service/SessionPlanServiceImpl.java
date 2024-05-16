package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.CustomerTraining;
import id.ac.ui.cs.rpl.flextime.model.SessionPlan;
import id.ac.ui.cs.rpl.flextime.repository.CustomerTrainingRepository;
import id.ac.ui.cs.rpl.flextime.repository.SessionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SessionPlanServiceImpl implements SessionPlanService{
    @Autowired
    private SessionPlanRepository sessionPlanRepository;
    @Autowired
    private CustomerTrainingRepository customerTrainingRepository;

    @Override
    public void deleteSessionPlanById(String id) {
        sessionPlanRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public SessionPlan saveSessionPlan(SessionPlan sessionPlan) {
        return sessionPlanRepository.save(sessionPlan);
    }

    @Override
    public Optional<SessionPlan> getSessionPlanById(String id) {
        return sessionPlanRepository.findById(UUID.fromString(id));
    }

    @Override
    public List<SessionPlan> getAllSessionPlansByFitnessPlan(String id) {
        return sessionPlanRepository.findSessionPlansByFitnessPlan_Id(UUID.fromString(id));
    }

    @Override
    public int getTrainingCount(String id) {
        List<CustomerTraining> customerTrainings = customerTrainingRepository.findCustomerTrainingsBySessionPlan_Id(UUID.fromString(id));
        return customerTrainings.size();
    }
}