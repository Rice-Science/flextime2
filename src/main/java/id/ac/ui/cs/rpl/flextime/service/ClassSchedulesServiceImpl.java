package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.AssignmentSchedules;
import id.ac.ui.cs.rpl.flextime.model.ClassSchedules;
import id.ac.ui.cs.rpl.flextime.repository.ClassSchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassSchedulesServiceImpl implements ClassSchedulesService{

    @Autowired
    private ClassSchedulesRepository classRepository;

    @Override
    public void create(ClassSchedules classSchedules) {
        classRepository.save(classSchedules);
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
    public void update(String id, ClassSchedules updatedClass) {
        ClassSchedules classSchedules = classRepository.findById(UUID.fromString(id)).orElse(null);

        if (classSchedules != null) {
            classSchedules.setClassSchedulesTitle(updatedClass.getClassSchedulesTitle());
            classSchedules.setClassSchedulesDuration(updatedClass.getClassSchedulesDuration());
            classSchedules.setClassSchedulesDate(updatedClass.getClassSchedulesDate());
            classRepository.save(classSchedules);
        }
    }

    @Override
    public List<ClassSchedules> findClassByCustomerId(String customerId) {
        return classRepository.findClassByCustomer_Id(UUID.fromString(customerId));
    }
}