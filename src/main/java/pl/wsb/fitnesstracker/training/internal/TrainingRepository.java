package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.Date;
import java.util.List;
import java.util.Objects;

interface TrainingRepository extends JpaRepository<Training, Long> {

    default void deleteByUserId(Long userId) {
        findAll().stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId))
                .forEach(this::delete);
    }

    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId))
                .toList();
    }

    default List<Training> findAfterDate(Date afterTime) {
        return findAll().stream()
                .filter(training -> training.getEndTime().after(afterTime))
                .toList();
    }

    default List<Training> findByActivityType(ActivityType activityType) {
        return findAll().stream()
                .filter(training -> training.getActivityType() == activityType)
                .toList();
    }
}
