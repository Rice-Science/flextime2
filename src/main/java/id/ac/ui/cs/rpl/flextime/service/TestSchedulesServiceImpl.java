package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.ClassSchedules;
import id.ac.ui.cs.rpl.flextime.model.TestSchedules;
import id.ac.ui.cs.rpl.flextime.repository.TestSchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestSchedulesServiceImpl implements TestSchedulesService{

    @Autowired
    private TestSchedulesRepository testRepository;

    @Override
    public void create(TestSchedules testSchedules) {
        testRepository.save(testSchedules);
    }

    @Override
    public List<TestSchedules> findAll() {
        return testRepository.findAll();
    }

    @Override
    public void delete(String id){
        testRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Optional<TestSchedules> findById(String id) {
        return testRepository.findById(UUID.fromString(id));
    }

    @Override
    public void update(String id, TestSchedules updatedClass) {
        TestSchedules classSchedules = testRepository.findById(UUID.fromString(id)).orElse(null);

        if (classSchedules != null) {
            classSchedules.setTestSchedulesTitle(updatedClass.getTestSchedulesTitle());
            classSchedules.setTestSchedulesDuration(updatedClass.getTestSchedulesDuration());
            classSchedules.setTestSchedulesDate(updatedClass.getTestSchedulesDate());
            testRepository.save(classSchedules);
        }
    }

    @Override
    public List<TestSchedules> findTestByCustomerId(String customerId) {
        return testRepository.findTestByCustomer_Id(UUID.fromString(customerId));
    }
}