package connectors;

import java.io.DataInputStream;
import java.net.Socket;
import java.net.URL;

public class TelnetConnector {

	public static boolean canConnectTo(URL url) {

		try {

			Socket socket = new Socket(url.getHost(), url.getPort());
			DataInputStream connectionDataInputStream = new DataInputStream(socket.getInputStream());
			if (connectionDataInputStream != null) {
				
				return true;
			}
		} catch (Exception exception) {
		}
		return false;
	}
}