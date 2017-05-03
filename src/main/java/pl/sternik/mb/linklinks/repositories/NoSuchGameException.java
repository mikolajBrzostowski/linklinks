package pl.sternik.mb.linklinks.repositories;

public class NoSuchGameException extends Exception {
    private static final long serialVersionUID = -8555511053844242536L;

    public NoSuchGameException(String string) {
		super(string);
	}

	public NoSuchGameException() {
	}


}
