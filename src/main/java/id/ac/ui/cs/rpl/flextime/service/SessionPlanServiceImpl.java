package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.SessionPlan;
import id.ac.ui.cs.rpl.flextime.repository.SessionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionPlanServiceImpl implements SessionPlanService{
    @Autowired
    private SessionPlanRepository sessionPlanRepository;

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
}
