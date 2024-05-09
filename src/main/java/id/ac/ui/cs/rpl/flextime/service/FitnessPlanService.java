package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.FitnessPlan;

import java.util.Optional;

public interface FitnessPlanService {
    FitnessPlan saveFitnessPlan(FitnessPlan fitnessPlan);
    Optional<FitnessPlan> getFitnessPlanById(String id);
    void deleteFitnessPlanById(String id);
}
