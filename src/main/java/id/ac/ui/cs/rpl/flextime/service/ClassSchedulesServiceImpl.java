package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.ClassSchedules;
import id.ac.ui.cs.rpl.flextime.model.TestSchedules;
import id.ac.ui.cs.rpl.flextime.repository.ClassSchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class ClassSchedulesServiceImpl implements ClassSchedulesService{

    @Autowired
    private ClassSchedulesRepository classRepository;

    @Autowired
    private UserService userService;

    @Override
    public void create(ClassSchedules classSchedules) throws Exception{
        LocalTime testStart = classSchedules.getClassSchedulesStart();
        LocalTime testEnd = classSchedules.getClassSchedulesEnd();

        List<ClassSchedules> allSchedules = findClassByCustomerId(
                userService.findByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()).getId().toString()
        );

        if (testStart.isAfter(testEnd)) {
            throw new Exception("Overlap with between start and end times!");
        }

        for (ClassSchedules existingSchedule : allSchedules) {
            if (Objects.equals(existingSchedule.getClassSchedulesDay(), classSchedules.getClassSchedulesDay())){
                LocalTime existingStart = existingSchedule.getClassSchedulesStart();
                LocalTime existingEnd = existingSchedule.getClassSchedulesEnd();
                if (checkOverlap(testStart, testEnd, existingStart, existingEnd)) {
                    throw new Exception("Overlap with existing test schedule detected!");
                }
            }
        }

        classRepository.save(classSchedules);
    }

    @Override
    public boolean checkOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2){
        return start1.isBefore(end2) && end1.isAfter(start2);
    }

    @Override
    public List<ClassSchedules> findAll() {
        return classRepository.findAll();
    }

    @Override
    public void delete(String id){
        classRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Optional<ClassSchedules> findById(String id) {
        return classRepository.findById(UUID.fromString(id));
    }

    @Override
    public ClassSchedules update(ClassSchedules updatedClass) throws Exception{
        LocalTime testStart = updatedClass.getClassSchedulesStart();
        LocalTime testEnd = updatedClass.getClassSchedulesEnd();

        List<ClassSchedules> allSchedules = findClassByCustomerId(
                userService.findByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()).getId().toString()
        );

        if (testStart.isAfter(testEnd)) {
            throw new Exception("Overlap with between start and end times!");
        }

        for (ClassSchedules existingSchedule : allSchedules) {
            if (Objects.equals(existingSchedule.getClassSchedulesDay(), updatedClass.getClassSchedulesDay())){
                LocalTime existingStart = existingSchedule.getClassSchedulesStart();
                LocalTime existingEnd = existingSchedule.getClassSchedulesEnd();
                if (checkOverlap(testStart, testEnd, existingStart, existingEnd)) {
                    throw new Exception("Overlap with existing test schedule detected!");
                }
            }
        }

        return classRepository.save(updatedClass);
    }

    @Override
    public List<ClassSchedules> findClassByCustomerId(String customerId) {
        return classRepository.findClassByCustomer_Id(UUID.fromString(customerId));
    }

    @Override
    public List<ClassSchedules> findClassByDay(String day) {
        return classRepository.findAllByClassSchedulesDay(day);
    }
}