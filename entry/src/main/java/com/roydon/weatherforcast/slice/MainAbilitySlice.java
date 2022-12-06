package com.roydon.weatherforcast.slice;

import com.google.gson.Gson;
import com.roydon.weatherforcast.ResourceTable;
import com.roydon.weatherforcast.bean.DayWeatherBean;
import com.roydon.weatherforcast.bean.WeatherBean;
import com.roydon.weatherforcast.provider.DayWeatherBeanProvider;
import com.roydon.weatherforcast.utils.NetworkUtil;
import com.roydon.weatherforcast.utils.ToastUtil;
import com.roydon.weatherforcast.utils.WeatherImgUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;

import java.util.List;

public class MainAbilitySlice extends AbilitySlice {

    private Text city,weather,tem,temLowHigh,week,win,air,tips;
    private Image weatherImg;
    private ListContainer listContainer;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initView();
    }

    public void initView() {
        initComponent();
        getWeather("北京");
    }

    public void initComponent() {
        city = (Text) findComponentById(ResourceTable.Id_text_city);
        weatherImg = (Image) findComponentById(ResourceTable.Id_weather_img);
        weather = (Text) findComponentById(ResourceTable.Id_text_weather);
        tem = (Text) findComponentById(ResourceTable.Id_text_tem);
        temLowHigh = (Text) findComponentById(ResourceTable.Id_text_tem_low_high);
        week = (Text) findComponentById(ResourceTable.Id_text_week);
        win = (Text) findComponentById(ResourceTable.Id_text_win);
        air = (Text) findComponentById(ResourceTable.Id_text_air);
        tips = (Text) findComponentById(ResourceTable.Id_text_tips);
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_list_container);

    }

    /**
     * GlobalTaskDispatcher 派发异步任务
     */
    public void getWeather(String cityName) {
        //网络请求
        TaskDispatcher globalTaskDispatcher = getGlobalTaskDispatcher(TaskPriority.DEFAULT);
        globalTaskDispatcher.asyncDispatch(() -> {
            String result = NetworkUtil.httpGet(cityName);
//            System.out.println(result);
            Gson gson = new Gson();
            WeatherBean weatherBean = gson.fromJson(result, WeatherBean.class);
            if (weatherBean == null) {
                getUITaskDispatcher().asyncDispatch(() -> {
                    ToastUtil.showToast(this, "貌似出了点问题~");
                });
            } else {
                System.out.println(weatherBean);
                getUITaskDispatcher().asyncDispatch(() -> {
                    ToastUtil.showToast(this, weatherBean.getCity() + "天气更新");
                    dataShow(weatherBean);
                });
            }
        });
    }

    public void dataShow(WeatherBean weatherBean) {
        if (weatherBean == null) {
            return;
        }
        city.setText(weatherBean.getCity());
        DayWeatherBean dayWeather = weatherBean.getData().get(0);//当天天气
        if (dayWeather == null) {
            return;
        }
        // 当天天气
        weatherImg.setPixelMap(WeatherImgUtil.getImgResOfWeather(dayWeather.getWea_img()));
        weather.setText(dayWeather.getWea());
        tem.setText(dayWeather.getTem());
        temLowHigh.setText(dayWeather.getTem2() + "/" + dayWeather.getTem1());
        week.setText(dayWeather.getWeek());
        win.setText(dayWeather.getWin()[0] + dayWeather.getWin_speed());
        air.setText(dayWeather.getAir() + " | " + dayWeather.getAir_level());
        tips.setText("👒：" + dayWeather.getAir_tips());
        // ListContainer展示未来七天天气
        List<DayWeatherBean> dayWeatherBeanList = weatherBean.getData();
        DayWeatherBeanProvider dayWeatherBeanProvider = new DayWeatherBeanProvider(dayWeatherBeanList, this);
        listContainer.setItemProvider(dayWeatherBeanProvider);

    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
