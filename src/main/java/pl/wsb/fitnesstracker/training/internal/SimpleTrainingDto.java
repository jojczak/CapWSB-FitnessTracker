package pl.wsb.fitnesstracker.training.internal;

import java.util.Date;

record SimpleTrainingDto (
    Long userId,
    Date startTime,
    Date endTime,
    ActivityType activityType,
    double distance,
    double averageSpeed
) {

}
