package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.ClassSchedules;
import id.ac.ui.cs.rpl.flextime.model.TestSchedules;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TestSchedulesService {
    void create(TestSchedules testSchedules) throws Exception;
    public List<TestSchedules> findAll();
    public void delete(String id);
    Optional<TestSchedules> findById(String id);
    public TestSchedules update(TestSchedules testSchedules);
    List<TestSchedules> findTestByCustomerId(String customerId);
    public boolean checkOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2);
}