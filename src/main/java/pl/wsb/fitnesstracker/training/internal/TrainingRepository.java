package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.Objects;

interface TrainingRepository extends JpaRepository<Training, Long> {

    default void deleteByUserId(Long userId) {
        findAll().stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId))
                .forEach(this::delete);
    }
}
