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

public class WeatherUtil {

	public JsonObject GetWeather(JsonObject geolocation) {

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		
		HttpUriRequest getRequest = new HttpGet("https://api.openweathermap.org/data/2.5/weather?zip=" + geolocation.get("zip").getAsString() + "," + geolocation.get("countryCode").getAsString()  + "&units=metric&APPID=a53b5e13817a0e351e7f4eda89d18445");
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
