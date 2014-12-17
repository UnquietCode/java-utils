package unquietcode.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class DataUtils {
	private DataUtils() { }


	public static String readResource(String name) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
		if (is == null) {
			throw new RuntimeException("no resource found with name "+name);
		}

		try {
			return new String(readStream(is));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static byte[] readStream(InputStream is) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		readStream(is, os);
		return os.toByteArray();
	}

	public static String readStreamToString(InputStream is) throws IOException {
		return readStreamToString(is, "UTF-8");
	}

	public static String readStreamToString(InputStream is, String charType) throws IOException {
		return new String(readStream(is), charType);
	}

	public static void readStream(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[4096];
		int read;

		while ((read = is.read(bytes)) != -1) {
			os.write(bytes, 0, read);
		}
	}
}