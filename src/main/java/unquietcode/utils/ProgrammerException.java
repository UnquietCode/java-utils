package unquietcode.utils;

public class ProgrammerException extends IllegalStateException {

	public static ProgrammerException notImplemented() throws ProgrammerException {
		throw new ProgrammerException("not implemented");
	}

	public ProgrammerException(String message) {
		super(message);
	}

	public ProgrammerException(Throwable cause) {
		super(cause);
	}

	public ProgrammerException(String message, Throwable cause) {
		super(message, cause);
	}
}