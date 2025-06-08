package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingService;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingService, TrainingProvider {

    private final TrainingRepository trainingRepository;

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public void createTraining(Training training) {
        trainingRepository.save(training);
    }

    @Override
    public void removeTrainingsByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null!");
        }
        trainingRepository.deleteByUserId(userId);
    }

    @Override
    public List<Training> getTrainingsByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null!");
        }
        return trainingRepository.findByUserId(userId);
    }

    @Override
    public List<Training> getTrainingsAfterDate(Date afterTime) {
        if (afterTime == null) {
            throw new IllegalArgumentException("After time cannot be null!");
        }

        return trainingRepository.findAfterDate(afterTime);
    }

    // Tutaj powinna być tablica
    @Override
    public  List<Training> getTrainingsByActivityType(ActivityType activityType) {
        if (activityType == null) {
            throw new IllegalArgumentException("Activity type cannot be null!");
        }

        return trainingRepository.findByActivityType(activityType);
    }
}
