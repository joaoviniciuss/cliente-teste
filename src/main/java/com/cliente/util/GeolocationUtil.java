package com.cliente.util;

import java.io.IOException;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GeolocationUtil {

	public JsonObject GetGeolocation(String ip) {

		ip = "189.125.78.197";
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpUriRequest getRequest = new HttpGet("http://ip-api.com/json/" + ip);
		getRequest.addHeader(HttpHeaders.ACCEPT, "application/json");

		try (CloseableHttpResponse httpResponse = httpClient.execute(getRequest)) {

			if (200 == httpResponse.getStatusLine().getStatusCode()) {
				String content = EntityUtils.toString(httpResponse.getEntity());
				JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();

				return jsonObject;
			}

		} catch (IOException e) {
			// handle exception
		}
		return null;

	}

}
