package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.AssignmentSchedules;

import java.util.List;
import java.util.Optional;

public interface AssignmentSchedulesService {
    void create(AssignmentSchedules assignment);
    public List<AssignmentSchedules> findAll();
    public void delete(String id);
    Optional<AssignmentSchedules> findById(String id);
    public AssignmentSchedules update(AssignmentSchedules assignment);
    List<AssignmentSchedules> findAssignmentByCustomerId(String customerId);
}