package king.leonidas.inventory.dto.user;

/**
 * User Display Response
 *
 * @param id User ID
 * @param firstName First Name
 * @param lastName Last Name
 * @param role Role
 */
public record UserDisplayResponse(int id, String firstName, String lastName, String role)
{
}
