package com.cliente.util;

import com.cliente.model.Cliente;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Util {

	public void GetGeolocationInfo(Cliente cliente) {
		
		GeolocationUtil geoUtil = new GeolocationUtil();
		JsonObject geolocation = geoUtil.GetGeolocation(cliente.getIp());

		if (null != geolocation) {
			cliente.setAddress(geolocation.get("city").getAsString() + "/" + geolocation.get("region").getAsString()
					+ "/" + geolocation.get("country").getAsString());

			WeatherUtil weatherUtil = new WeatherUtil();
			JsonObject weather = weatherUtil.GetWeather(geolocation);
			
			if (null != weather) {
				JsonObject jsonMain = weather.get("main").getAsJsonObject();
				//JsonObject jsonMain = new JsonParser().parse(main).getAsJsonObject();
				cliente.setMaxTemp(jsonMain.get("temp_max").getAsDouble());
				cliente.setMinTemp(jsonMain.get("temp_min").getAsDouble());

			}
		}
	}

}
