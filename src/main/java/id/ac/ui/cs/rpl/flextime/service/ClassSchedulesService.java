package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.ClassSchedules;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ClassSchedulesService {
    void create(ClassSchedules classSchedules) throws Exception;
    public List<ClassSchedules> findAll();
    public void delete(String id);
    Optional<ClassSchedules> findById(String id);
    public ClassSchedules update(ClassSchedules classSchedules) throws Exception;
    List<ClassSchedules> findClassByCustomerId(String customerId);
    public boolean checkOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2);
    public List<ClassSchedules> findClassByDay(String day);
}