package com.roydon.weatherforcast.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeatherBean implements Serializable {

    @SerializedName("cityid")
    private String cityid;

    private String city;//城市名称

    private String update_time;//更新时间

    private List<DayWeatherBean> data;//获取今日天气，get[0]

    @Override
    public String toString() {
        return "WeatherBean{" +
                "city='" + city + '\'' +
                ", update_time='" + update_time + '\'' +
                ", data=" + data +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public List<DayWeatherBean> getData() {
        return data;
    }

    public void setData(List<DayWeatherBean> data) {
        this.data = data;
    }
}
