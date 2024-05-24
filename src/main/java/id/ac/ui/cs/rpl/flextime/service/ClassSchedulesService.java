package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.ClassSchedules;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClassSchedulesService {
    void create(ClassSchedules classSchedules);
    public List<ClassSchedules> findAll();
    public void delete(String id);
    Optional<ClassSchedules> findById(String id);
    public ClassSchedules update(ClassSchedules classSchedules);
    List<ClassSchedules> findClassByCustomerId(String customerId);
    public boolean checkOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2);
}