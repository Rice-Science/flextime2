package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.Training;
import id.ac.ui.cs.rpl.flextime.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public void deleteTrainingById(String id) {
        trainingRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Optional<Training> getTrainingById(String id) {
        return trainingRepository.findById(UUID.fromString(id));
    }

    @Override
    public Training addTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }
}
