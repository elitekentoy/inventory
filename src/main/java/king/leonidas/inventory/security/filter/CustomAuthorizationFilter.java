package king.leonidas.inventory.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import king.leonidas.inventory.security.component.JwtTokenComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Custom Authorization Filter
 */
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter
{
	/**
	 * JWT Tokens Component
	 */
	private final JwtTokenComponent tokens;

	/**
	 * Error
	 */
	private static final String ERROR_KEY = "error";

	/**
	 * Message
	 */
	private static final String MESSAGE_KEY = "message";

	/**
	 * Excluded in the Filter API
	 */
	private static final List<String> EXCLUDED_FILTER_API = List.of(
			"/api/v1/login",
			"/api/v1/register",
			"/api/v1/tokens/refresh");

	/**
	 * Constructor
	 *
	 * @param tokens JWT Tokens Component
	 */
	@Autowired
	public CustomAuthorizationFilter(final JwtTokenComponent tokens)
	{
		this.tokens = tokens;
	}

	/**
	 * Same contract as for {@code doFilter}, but guaranteed to be
	 * just invoked once per request within a single request thread.
	 * See {@link #shouldNotFilterAsyncDispatch()} for details.
	 * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
	 * default ServletRequest and ServletResponse ones.
	 *
	 * @param request HTTP Request
	 * @param response HTTP Response
	 * @param filterChain Filter Chain
	 */
	@Override
	protected void doFilterInternal(
			final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
			throws ServletException, IOException
	{
		if (EXCLUDED_FILTER_API.contains(request.getServletPath())) {
			filterChain.doFilter(request, response);
			return;
		}

		final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		try {
			tokens.validateAuthorization(token);
			filterChain.doFilter(request, response);
		} catch (final IOException | ServletException exception) {
			if (log.isErrorEnabled()) {
				log.error("Error logging in: {}", exception.getLocalizedMessage());
			}
			response.setHeader(ERROR_KEY, exception.getLocalizedMessage());
			response.setStatus(HttpStatus.FORBIDDEN.value());

			writeErrorMessage(response, exception.getLocalizedMessage());
		}
	}

	private void writeErrorMessage(final HttpServletResponse response, final String message)
	{
		try {
			final HashMap<String, String> output = new HashMap<>();
			output.put(MESSAGE_KEY, message);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);

			new ObjectMapper().writeValue(response.getOutputStream(), output);
		} catch (final IOException exception) {
			if (log.isErrorEnabled()) {
				log.error("Error in writing message...");
			}
		}
	}
}
