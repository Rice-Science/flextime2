package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.CustomerTraining;
import id.ac.ui.cs.rpl.flextime.repository.CustomerTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerTrainingServiceImpl implements CustomerTrainingService{
    @Autowired
    private CustomerTrainingRepository customerTrainingRepository;

    @Override
    public CustomerTraining save(CustomerTraining customerTraining) {
        return customerTrainingRepository.save(customerTraining);
    }

    @Override
    public CustomerTraining getCustomerTrainingById(String id) {
        return customerTrainingRepository.getCustomerTrainingById(UUID.fromString(id));
    }

    @Override
    public List<CustomerTraining> getCustomerTrainingsBySessionPlanId(String sessionPlanId) {
        return customerTrainingRepository.findCustomerTrainingsBySessionPlan_Id(UUID.fromString(sessionPlanId));
    }

    @Override
    public void deleteCustomerTrainingById(String id) {
        customerTrainingRepository.deleteById(UUID.fromString(id));
    }
}
