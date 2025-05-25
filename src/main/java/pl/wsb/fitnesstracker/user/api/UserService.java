package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     * @param user The user to be created
     */
    void createUser(User user);

    /**
     * Removes the user with the given ID.
     * @param userId The ID of the user to be removed
     */
    void removeUser(Long userId);

    /**
     * Updates the given user.
     * @param userId The ID of the user to be updated
     * @param user The updated user
     */
    void updateUser(Long userId, User user);
}
