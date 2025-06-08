package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final UserProvider userProvider;

    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream().map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsForUser(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId)
                .stream().map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getTrainingsAfterTime(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        return trainingService.getTrainingsAfterDate(afterTime)
                .stream().map(trainingMapper::toDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<TrainingDto> addTraining(@RequestBody SimpleTrainingDto simpleTrainingDto) {
        return userProvider.getUser(simpleTrainingDto.userId())
                .map(user -> {
                    Training training = trainingMapper.toEntity(user, simpleTrainingDto);
                    trainingService.createTraining(training);
                    return ResponseEntity.status(HttpStatus.CREATED).body(trainingMapper.toDto(training));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activityType/{activityType}")
    public List<TrainingDto> getTrainingsByActivityType(@PathVariable ActivityType activityType) {
        return trainingService.getTrainingsByActivityType(activityType)
                .stream().map(trainingMapper::toDto)
                .toList();
    }

}
