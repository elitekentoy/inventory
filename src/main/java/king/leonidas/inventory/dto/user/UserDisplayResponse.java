package king.leonidas.inventory.dto.user;

/**
 * User Display Response
 *
 * @param username Username
 * @param firstName First Name
 * @param lastName Last Name
 * @param role Role
 */
public record UserDisplayResponse(String username, String firstName, String lastName, String role)
{
}
