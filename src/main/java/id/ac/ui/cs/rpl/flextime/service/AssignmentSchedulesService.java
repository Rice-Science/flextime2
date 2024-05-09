package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.AssignmentSchedules;
import java.util.List;

public interface AssignmentSchedulesService {
    public AssignmentSchedules create(AssignmentSchedules assignment);
    public List<AssignmentSchedules> findAll();
    public void delete(String id);
    AssignmentSchedules findById(String id);
    void update(AssignmentSchedules assignment);
}