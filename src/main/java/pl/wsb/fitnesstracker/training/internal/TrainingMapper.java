package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;

@Component
class TrainingMapper {

    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    TrainingUserDto toDto(User user) {
        return new TrainingUserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    Training toEntity(User user, SimpleTrainingDto simpleTrainingDto) {
        return new Training(
                user,
                simpleTrainingDto.startTime(),
                simpleTrainingDto.endTime(),
                simpleTrainingDto.activityType(),
                simpleTrainingDto.distance(),
                simpleTrainingDto.averageSpeed()
        );
    }
}
