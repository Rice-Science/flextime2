package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.AssignmentSchedules;
import id.ac.ui.cs.rpl.flextime.repository.AssignmentSchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AssignmentSchedulesServiceImpl implements AssignmentSchedulesService{

    @Autowired
    private AssignmentSchedulesRepository assignmentRepository;

    @Override
    public void create(AssignmentSchedules assignment) {
        assignmentRepository.save(assignment);
    }

    @Override
    public List<AssignmentSchedules> findAll() {
        return assignmentRepository.findAll();
    }

    @Override
    public void delete(String id){
        assignmentRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Optional<AssignmentSchedules> findById(String id) {
        return assignmentRepository.findById(UUID.fromString(id));
    }

    @Override
    public AssignmentSchedules update(AssignmentSchedules updatedAssignment) {
        return assignmentRepository.save(updatedAssignment);
    }

    @Override
    public List<AssignmentSchedules> findAssignmentByCustomerId(String customerId) {
        return assignmentRepository.findAssignmentByCustomer_Id(UUID.fromString(customerId));
    }
}