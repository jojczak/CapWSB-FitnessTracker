package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public void createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        userRepository.save(user);
    }

    @Override
    public void removeUser(final Long userId) {
        log.info("Removing User with id {}", userId);
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchUsers(String text) {
        log.info("Searching users with text {}", text);
        return userRepository.search(text);
    }

    @Override
    public List<User> findOlderThan(LocalDate date) {
        log.info("Finding users older than date {}", date);
        return userRepository.findOlderThan(date);
    }

    @Override
    public void updateUser(final Long userId, final User updatedUser) {
        log.info("Updating User with id {} with data {}", userId, updatedUser);
        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        User userToUpdate = new User(
                updatedUser.getFirstName(),
                updatedUser.getLastName(),
                updatedUser.getBirthdate(),
                updatedUser.getEmail()
        );
        userToUpdate.setId(userId);
        userRepository.save(userToUpdate);
    }
}