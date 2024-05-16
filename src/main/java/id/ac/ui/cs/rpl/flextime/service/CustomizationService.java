package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.Customization;

public interface CustomizationService {
    public void deleteCustomizationById(String id);
    public Customization getCustomizationById(String id);
    public Customization saveCustomization(Customization customization);
    public Customization getCustomizationByTrainingId(String trainingId);

}
