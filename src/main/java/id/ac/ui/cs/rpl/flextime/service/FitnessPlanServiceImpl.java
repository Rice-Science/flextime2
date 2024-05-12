package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.FitnessPlan;
import id.ac.ui.cs.rpl.flextime.repository.FitnessPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FitnessPlanServiceImpl implements FitnessPlanService {
    @Autowired
    private FitnessPlanRepository fitnessPlanRepository;

    @Override
    public FitnessPlan saveFitnessPlan(FitnessPlan fitnessPlan) {
        return fitnessPlanRepository.save(fitnessPlan);
    }

    @Override
    public Optional<FitnessPlan> getFitnessPlanById(String id) {
        return fitnessPlanRepository.findById(UUID.fromString(id));
    }

    @Override
    public void deleteFitnessPlanById(String id) {
        fitnessPlanRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public FitnessPlan getFitnessPlanByCustomerId(String customerId) {
        return fitnessPlanRepository.findFitnessPlanByCustomer_Id(UUID.fromString(customerId));
    }
}
