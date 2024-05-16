package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.Customization;
import id.ac.ui.cs.rpl.flextime.repository.CustomizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomizationServiceImpl implements CustomizationService {
    @Autowired
    private CustomizationRepository customizationRepository;
    @Override
    public void deleteCustomizationById(String id) {
        customizationRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Customization saveCustomization(Customization customization) {
        return customizationRepository.save(customization);
    }

    @Override
    public Customization getCustomizationById(String id) {
        return customizationRepository.findById(UUID.fromString(id)).orElse(null);
    }

    @Override
    public Customization getCustomizationByTrainingId(String trainingId) {
        return customizationRepository.findCustomizationByTraining_Id(UUID.fromString(trainingId));
    }

}
