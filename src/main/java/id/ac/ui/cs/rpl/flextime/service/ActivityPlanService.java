package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.ActivityPlan;
import id.ac.ui.cs.rpl.flextime.model.User;

import java.util.*;

public interface ActivityPlanService {
    public ActivityPlan createActivityPlan(User user);

    public void addSession(UUID sessionId, Date time, ActivityPlan activityPlan);
    public ActivityPlan getActivityPlanByUser_Id(UUID userId);
    public void removeSession(Date time, ActivityPlan activityPlan);
    public Date calculateEndTimeSession( Date time, int duration);
    public Optional<ActivityPlan> getActivityPlanById(UUID id);
    public Map<String, List<Object>> groupActivitiesByDate(User user);
}
