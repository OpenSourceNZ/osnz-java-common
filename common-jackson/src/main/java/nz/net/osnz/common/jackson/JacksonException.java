package nz.net.osnz.common.jackson;

/**
 * Thrown from the {@link JacksonHelper} methods when any exception occurs during serialising or de-serialising
 * operations. Really just a runtime wrapper for the checked exceptions that the Jackson mapper throws itself.
 */
public class JacksonException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * @see Exception#Exception()
   */
  public JacksonException() {
    super();
  }

  /**
   * @see Exception#Exception(String, Throwable)
   */
  public JacksonException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @see Exception#Exception(String)
   */
  public JacksonException(String message) {
    super(message);
  }

  /**
   * @see Exception#Exception(Throwable)
   */
  public JacksonException(Throwable cause) {
    super(cause);
  }

}
