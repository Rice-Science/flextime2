package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.SessionSchedule;
import id.ac.ui.cs.rpl.flextime.model.User;

import java.time.LocalDateTime;
import java.util.*;

public interface ActivityPlanService {
    SessionSchedule checkSessionPlan(User user, SessionSchedule sessionSchedule) throws Exception;
    public void deleteSessionSchedules(UUID id);
    public void createSessionSchedules(User user, SessionSchedule sessionSchedule) throws Exception;
    public List<SessionSchedule> findSessionSchedulesByUser_Id(UUID user_id);

}
