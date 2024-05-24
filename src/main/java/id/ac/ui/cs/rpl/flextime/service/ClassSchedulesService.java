package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.AssignmentSchedules;
import id.ac.ui.cs.rpl.flextime.model.ClassSchedules;

import java.util.List;
import java.util.Optional;

public interface ClassSchedulesService {
    void create(ClassSchedules classSchedules);
    public List<ClassSchedules> findAll();
    public void delete(String id);
    Optional<ClassSchedules> findById(String id);
//    public void update(String id, ClassSchedules classSchedules);
    List<ClassSchedules> findClassByCustomerId(String customerId);
}