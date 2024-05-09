package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.AssignmentSchedules;
import id.ac.ui.cs.rpl.flextime.repository.AssignmentSchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AssignmentSchedulesServiceImpl implements AssignmentSchedulesService{

    @Autowired
    private AssignmentSchedulesRepository assignmentRepository;

    @Override
    public AssignmentSchedules create(AssignmentSchedules assignment) {
        assignmentRepository.create(assignment);
        return assignment;
    }

    @Override
    public List<AssignmentSchedules> findAll() {
        Iterator<AssignmentSchedules> productIterator = assignmentRepository.findAll();
        List<AssignmentSchedules> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public void delete(String id){
        assignmentRepository.delete(id);
    }

    @Override
    public AssignmentSchedules findById(String id) {
        return assignmentRepository.findById(id);
    }

    @Override
    public void update(AssignmentSchedules assignment) {
        assignmentRepository.update(assignment);
    }
}