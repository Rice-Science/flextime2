package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.ClassSchedules;
import id.ac.ui.cs.rpl.flextime.repository.ClassSchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ClassSchedulesServiceImpl implements ClassSchedulesService{

    @Autowired
    private ClassSchedulesRepository classRepository;

    @Autowired
    private UserService userService;

    @Override
    public void create(ClassSchedules classSchedules){
        classRepository.save(classSchedules);
    }

    @Override
    public boolean checkOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2){
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
    public ClassSchedules update(ClassSchedules updatedClass) {
        return classRepository.save(updatedClass);
    }

    @Override
    public List<ClassSchedules> findClassByCustomerId(String customerId) {
        return classRepository.findClassByCustomer_Id(UUID.fromString(customerId));
    }
}