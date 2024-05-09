package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingService {

    Training addTraining(Training training);

    void deleteTrainingById(String id);

    Optional<Training> getTrainingById(String id);

    List<Training> getAllTrainings();
}
