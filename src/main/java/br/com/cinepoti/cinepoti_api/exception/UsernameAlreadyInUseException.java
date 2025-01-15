package br.com.cinepoti.cinepoti_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for when a username is already in use.
 */
@ResponseStatus(value = HttpStatus.CONFLICT) // Sets HTTP status 409 (Conflict)
public class UsernameAlreadyInUseException extends RuntimeException {

  /**
   * Constructor with a custom message.
   *
   * @param username The username that is already in use.
   */
  public UsernameAlreadyInUseException(String username) {
    super("The username '" + username + "' is already in use.");
  }
}
