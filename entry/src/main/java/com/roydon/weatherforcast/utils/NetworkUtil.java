package com.roydon.weatherforcast.utils;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;

public class NetworkUtil {
    /**
     * 18625561：27XjzrB7
     * 67342285：5XgTk31r
     * 19267789：Dhu3DShY
     */
    public static final String URL_WEATHER = "https://tianqiapi.com/api?version=v1&appid=67342285&appsecret=5XgTk31r";

    public static String httpGet(String cityName) {
        String urlGetJson = URL_WEATHER + "&city=" + cityName;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlGetJson);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String temp;
            while ((temp = reader.readLine()) != null) {
                sb.append(temp);
            }
            reader.close();

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return sb.toString();
    }

}
