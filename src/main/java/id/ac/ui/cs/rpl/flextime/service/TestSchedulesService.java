package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.ClassSchedules;
import id.ac.ui.cs.rpl.flextime.model.TestSchedules;

import java.util.List;
import java.util.Optional;

public interface TestSchedulesService {
    void create(TestSchedules testSchedules);
    public List<TestSchedules> findAll();
    public void delete(String id);
    Optional<TestSchedules> findById(String id);
    public void update(String id, TestSchedules testSchedules);
    List<TestSchedules> findTestByCustomerId(String customerId);
}