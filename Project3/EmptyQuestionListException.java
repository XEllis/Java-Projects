/**
 * Exception class that is thrown whenever there is a problem with
 * processing or creating a question list.
 * 
 * @author xellis
 *
 */
public class EmptyQuestionListException extends Exception {
	
	private static final String MESSAGE = "EmptyQuestionListException.";
	
	/**
	 * Used for serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a EmptyQuestionListException with a pre-set message
	 */
	public EmptyQuestionListException() {
		this(MESSAGE);
	}

	/**
	 * Creates a EmptyQuestionListException with a provided message
	 * @param message message to include in the exception information
	 */
	public EmptyQuestionListException(String message) {
		super(message);
	}
	
}
