package king.leonidas.inventory.dto.user;

/**
 * User Details Request
 *
 * @param username Username
 * @param password Password
 * @param firstName First Name
 * @param lastName Last Name
 * @param role Role
 */
public record UserDetailsRequest(String username, String password, String firstName, String lastName, String role)
{
}
