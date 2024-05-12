package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.SessionPlan;

import java.util.List;
import java.util.Optional;

public interface SessionPlanService {
    public SessionPlan saveSessionPlan(SessionPlan sessionPlan);
    public Optional<SessionPlan> getSessionPlanById(String id);
    public List<SessionPlan> getAllSessionPlansByFitnessPlan(String id);
    public void deleteSessionPlanById(String id);
    public List<SessionPlan> getAllSessionPlansByUser(String username);
}
