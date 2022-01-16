package connectors;

import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

public class WebConnector {

	static final int timeout = 5000;

	public static boolean canConnectTo(URL url) {

		try {
			HttpGet getRequest = new HttpGet();
			getRequest.setURI(url.toURI());
			CloseableHttpResponse response = getCloseableHttpClient().execute(getRequest);// Execute HTTP method
			return response.getStatusLine().getStatusCode() == 200;
		} catch (SocketException socketException) {
		} catch (IOException socketException) {
		} catch (Exception e) {
		}
		return false;
	}

	public static CloseableHttpClient getCloseableHttpClient() throws Exception {

		RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).build();
		SSLContext context = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				return true;
			}
		}).build();

		return HttpClients.custom().setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
				.setSSLContext(context).setDefaultRequestConfig(config).build();
	}
}
