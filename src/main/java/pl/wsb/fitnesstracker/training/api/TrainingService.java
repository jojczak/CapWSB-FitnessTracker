package pl.wsb.fitnesstracker.training.api;

public interface TrainingService {

    void createTraining(Training training);

    void removeTrainingsByUserId(Long userId);
}
