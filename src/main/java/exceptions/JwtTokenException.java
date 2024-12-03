package exceptions;

public class JwtTokenException extends  RuntimeException{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -5374997125130712365L;

	public JwtTokenException(String message) {
	        super(message);
	    }
	
}
