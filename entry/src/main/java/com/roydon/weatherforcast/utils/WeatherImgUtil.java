package com.roydon.weatherforcast.utils;

import com.roydon.weatherforcast.ResourceTable;

public class WeatherImgUtil {

    public static int getImgResOfWeather(String weaStr) {
        int result = 0;
        switch (weaStr) {
            case "qing":
                result = ResourceTable.Media_weather_yin;
                break;
            case "yin":
                result = ResourceTable.Media_weather_yin;
                break;
            case "yu":
                result = ResourceTable.Media_weather_dayu;
                break;
            case "yun":
                result = ResourceTable.Media_weather_duoyun;
                break;
            case "bingbao":
                result = ResourceTable.Media_weather_leizhenyubingbao;
                break;
            case "wu":
                result = ResourceTable.Media_weather_wu;
                break;
            case "shachen":
                result = ResourceTable.Media_weather_shachenbao;
                break;
            case "lei":
                result = ResourceTable.Media_weather_leizhenyu;
                break;
            case "xue":
                result = ResourceTable.Media_weather_daxue;
                break;
            default:
                result = ResourceTable.Media_weather_qing;
                break;
        }
        return result;
    }
}
