package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.CustomerTraining;

import java.util.List;

public interface CustomerTrainingService {
    CustomerTraining getCustomerTrainingById(String id);
    List<CustomerTraining> getCustomerTrainingsBySessionPlanId(String sessionPlanId);
    void deleteCustomerTrainingById(String id);
    CustomerTraining save(CustomerTraining customerTraining);
}
