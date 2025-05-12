package king.leonidas.inventory.security.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import king.leonidas.inventory.model.user.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * JWT Token Component
 */
@Component
public class JwtTokenComponent
{
	/**
	 * Secret Key
	 */
	private static final String SECRET_KEY = "secret_key";

	/**
	 * Algorithm
	 */
	private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

	/**
	 * Access Token Expiry (in Minutes)
	 */
	private static final int ACCESS_TOKEN_EXPIRY = 15;

	/**
	 * Refresh Token Expiry (in Days)
	 */
	private static final int REFRESH_TOKEN_EXPIRY = 10;

	/**
	 * The Claim for an Access Token
	 */
	private static final String ACCESS_TOKEN_CLAIM = "roles";

	/**
	 * Bearer Token Prefix
	 */
	private static final String BEARER_PREFIX = "Bearer ";

	/**
	 * Create Access Token
	 *
	 * @param user User
	 * @param path Path
	 * @return Access Token
	 */
	public String createAccessToken(final User user, final String path)
	{
		return JWT.create()
				// Determines how to differentiate each token to a specific user
				.withSubject(user.getUsername())
				.withExpiresAt(Instant.now().plus(ACCESS_TOKEN_EXPIRY, ChronoUnit.MINUTES))
				// Author of this Token
				.withIssuer(path)
				.withClaim(ACCESS_TOKEN_CLAIM, user.getRoles())
				.sign(ALGORITHM);
	}

	/**
	 * Create Refresh token
	 *
	 * @param user User
	 * @param path Path
	 * @return Refresh Token
	 */
	public String createRefreshToken(final User user, final String path)
	{
		return JWT.create()
				// Determines how to differentiate each token to a specific user
				.withSubject(user.getUsername())
				.withExpiresAt(Instant.now().plus(REFRESH_TOKEN_EXPIRY, ChronoUnit.DAYS))
				// Author of this Token
				.withIssuer(path)
				.sign(ALGORITHM);
	}

	/**
	 * Validation on Authorization Token
	 *
	 * @param token Authorization Token
	 * @throws JWTVerificationException if the token is invalid
	 */
	public void validateAuthorization(final String token) throws JWTVerificationException
	{
		if (StringUtils.isEmpty(token) || !token.startsWith(BEARER_PREFIX))
		{
			throw new JWTVerificationException("Empty or Invalid Authorization Token");
		}

		//Verify and Decode the JWT Token
		final DecodedJWT decodedJWT = decodedJWT(token);

		final String username = decodedJWT.getSubject();
		final Collection<GrantedAuthority> authorities = getAuthoritiesFromClaim(decodedJWT.getClaim(ACCESS_TOKEN_CLAIM));

		// Generate Token
		final UsernamePasswordAuthenticationToken authToken =
				new UsernamePasswordAuthenticationToken(username, null, authorities);

		// Place the Token to the Security Context
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}

	/**
	 * Gets Subject from JWT
	 *
	 * @param token Authorization Token
	 * @return Subject/Username
	 */
	public String getSubject(final String token)
	{
		if (StringUtils.isEmpty(token) || !token.startsWith(BEARER_PREFIX)) {
			return StringUtils.EMPTY;
		}

		return decodedJWT(token).getSubject();
	}

	/**
	 * Gets Authorization Token and Removes the Bearer Prefix
	 *
	 * @param token Authorization Token from Header
	 * @return Token
	 */
	public String getAuthorizationToken(final String token)
	{
		if (StringUtils.isEmpty(token) || !token.startsWith(BEARER_PREFIX)) {
			return StringUtils.EMPTY;
		}

		return token.substring(BEARER_PREFIX.length());
	}

	/**
	 * Decodes JWT
	 *
	 * @param token Authorization Token
	 * @return Decoded JWT
	 * @throws JWTVerificationException if error occurs
	 */
	private DecodedJWT decodedJWT(final String token) throws JWTVerificationException
	{
		final String authToken = token.substring(BEARER_PREFIX.length());
		final JWTVerifier verifier = JWT.require(ALGORITHM).build();
		return verifier.verify(authToken);
	}

	/**
	 * Get Authorities from Claim
	 *
	 * @param claim Claim
	 * @return Collection of Granted Authorities
	 */
	private Collection<GrantedAuthority> getAuthoritiesFromClaim(final Claim claim)
	{
		return Arrays.stream(claim.asArray(String.class))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
}
