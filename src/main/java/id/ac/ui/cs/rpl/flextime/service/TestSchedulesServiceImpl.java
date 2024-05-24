package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.ClassSchedules;
import id.ac.ui.cs.rpl.flextime.model.TestSchedules;
import id.ac.ui.cs.rpl.flextime.repository.TestSchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TestSchedulesServiceImpl implements TestSchedulesService{

    @Autowired
    private TestSchedulesRepository testRepository;
    @Autowired
    private UserService userService;

    @Override
    public void create(TestSchedules testSchedules) throws Exception{
        LocalDateTime testStart = testSchedules.getTestSchedulesDate().atTime(testSchedules.getTestSchedulesStart());
        LocalDateTime testEnd = testSchedules.getTestSchedulesDate().atTime(testSchedules.getTestSchedulesEnd());

        List<TestSchedules> allSchedules = findTestByCustomerId(
                userService.findByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()).getId().toString()
        );

        // if start conflict with end
        if (testStart.isAfter(testEnd)) {
            throw new Exception("Overlap with between start and end times!");
        }

        // if conflict with other schedule
        for (TestSchedules existingSchedule : allSchedules) {
            LocalDateTime existingStart = existingSchedule.getTestSchedulesDate().atTime(existingSchedule.getTestSchedulesStart());
            LocalDateTime existingEnd = existingSchedule.getTestSchedulesDate().atTime(existingSchedule.getTestSchedulesEnd());
            if (checkOverlap(testStart, testEnd, existingStart, existingEnd)) {
                throw new Exception("Overlap with existing test schedule detected!");
            }
        }

        testRepository.save(testSchedules);
    }

    @Override
    public boolean checkOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2){
        return start1.isBefore(end2) && end1.isAfter(start2);
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
    public TestSchedules update(TestSchedules updatedTest) throws Exception{
        LocalDateTime testStart = updatedTest.getTestSchedulesDate().atTime(updatedTest.getTestSchedulesStart());
        LocalDateTime testEnd = updatedTest.getTestSchedulesDate().atTime(updatedTest.getTestSchedulesEnd());

        List<TestSchedules> allSchedules = findTestByCustomerId(
                userService.findByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()).getId().toString()
        );

        // if start conflict with end
        if (testStart.isAfter(testEnd)) {
            throw new Exception("Overlap with between start and end times!");
        }

        // if conflict with other schedule
        for (TestSchedules existingSchedule : allSchedules) {
            LocalDateTime existingStart = existingSchedule.getTestSchedulesDate().atTime(existingSchedule.getTestSchedulesStart());
            LocalDateTime existingEnd = existingSchedule.getTestSchedulesDate().atTime(existingSchedule.getTestSchedulesEnd());
            if (checkOverlap(testStart, testEnd, existingStart, existingEnd)) {
                throw new Exception("Overlap with existing test schedule detected!");
            }
        }

        return testRepository.save(updatedTest);
    }

    @Override
    public List<TestSchedules> findTestByCustomerId(String customerId) {
        return testRepository.findTestByCustomer_Id(UUID.fromString(customerId));
    }
}