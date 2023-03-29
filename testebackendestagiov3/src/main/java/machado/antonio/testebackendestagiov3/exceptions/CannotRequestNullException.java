package machado.antonio.testebackendestagiov3.exceptions;

/**
 * Extends {@link RuntimeException}
 */
public class CannotRequestNullException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4597447058978378551L;

	/**
	 * @param attributeName the name of the object, so that the exception message
	 *                      can specify which {@code attribute} or {@code field} or
	 *                      {@code element} should not be null.
	 */
	public CannotRequestNullException(String attributeName) {
		super("Cannot get/post/put/delete null for " + attributeName);
	}

}
