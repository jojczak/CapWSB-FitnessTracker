package pl.wsb.fitnesstracker.training.internal;


import java.util.Date;

record TrainingDto(
        Long id,
        TrainingUserDto user,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {
}
