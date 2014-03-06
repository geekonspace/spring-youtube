/**
 * COPYRIGHT (C) 2014 Alcaldía de Iribarren. Todos los derechos reservados.
 */
package ve.gob.iribarren.tube.exceptions;

@SuppressWarnings("serial")
public class HttpGetException extends RuntimeException{

	public HttpGetException() {
		super();
	}

	public HttpGetException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HttpGetException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpGetException(String message) {
		super(message);
	}

	public HttpGetException(Throwable cause) {
		super(cause);
	}

}
