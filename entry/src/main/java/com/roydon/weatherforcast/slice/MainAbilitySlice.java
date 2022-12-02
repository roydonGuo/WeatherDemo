package com.roydon.weatherforcast.slice;

import com.google.gson.Gson;
import com.roydon.weatherforcast.ResourceTable;
import com.roydon.weatherforcast.bean.DayWeatherBean;
import com.roydon.weatherforcast.bean.WeatherBean;
import com.roydon.weatherforcast.utils.NetworkUtil;
import com.roydon.weatherforcast.utils.WeatherImgUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Image;
import ohos.agp.components.ListContainer;
import ohos.agp.components.Text;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;

public class MainAbilitySlice extends AbilitySlice {

     Text city;
     Image weatherImg;
     Text weather;
     Text tem;
     Text temLowHigh;
     Text week;
     ListContainer listContainer;

//    private WeatherBean weatherBean = null;
//    private DayWeatherBean dayWeather = null;
    // 定义日志标签
//    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "WeatherForcast");

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        initView();

    }

    public void initView() {
        initComponent();
        getWeather("深圳");
//        dataShow(weatherBean);
    }

    private void initComponent() {
        city = (Text) findComponentById(ResourceTable.Id_text_city);
        weatherImg = (Image) findComponentById(ResourceTable.Id_weather_img);
        weather = (Text) findComponentById(ResourceTable.Id_text_weather);
        tem = (Text) findComponentById(ResourceTable.Id_text_tem);
        temLowHigh = (Text) findComponentById(ResourceTable.Id_text_tem_low_high);
        week = (Text) findComponentById(ResourceTable.Id_text_week);
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_list_container);

    }

    private void dataShow(WeatherBean weatherBean) {
        if (weatherBean == null) {
            return;
        }
        DayWeatherBean dayWeather = weatherBean.getData().get(0);//当天天气
        if (dayWeather == null) {
            return;
        }
        System.out.println(dayWeather);
//        city.setText(weatherBean.getCity());

        /**
         * 当天天气
         */
        weatherImg.setPixelMap(WeatherImgUtil.getImgResOfWeather(dayWeather.getWea_img()));
        weather.setText(dayWeather.getWea());
        tem.setText(dayWeather.getTem());
        temLowHigh.setText(dayWeather.getTem2() + "/" + dayWeather.getTem1());
        week.setText(dayWeather.getWeek());

    }

    /**
     * GlobalTaskDispatcher 派发任务
     */
    private void getWeather(String cityName) {
        //网络请求
        TaskDispatcher globalTaskDispatcher = getGlobalTaskDispatcher(TaskPriority.DEFAULT);
        globalTaskDispatcher.asyncDispatch(() -> {
            String result = NetworkUtil.httpGet(cityName);
            Gson gson = new Gson();
            WeatherBean weatherBean = gson.fromJson(result, WeatherBean.class);
            city.setText(weatherBean.getCity());
            System.out.println(weatherBean);
//            getUITaskDispatcher().asyncDispatch(() -> {
//                city = (Text) findComponentById(ResourceTable.Id_text_city);
//                weatherImg = (Image) findComponentById(ResourceTable.Id_weather_img);
//                weather = (Text) findComponentById(ResourceTable.Id_text_weather);
//                tem = (Text) findComponentById(ResourceTable.Id_text_tem);
//                temLowHigh = (Text) findComponentById(ResourceTable.Id_text_tem_low_high);
//                week = (Text) findComponentById(ResourceTable.Id_text_week);
//                DayWeatherBean dayWeather = weatherBean.getData().get(0);//当天天气
//                System.out.println("dayWeather = " + dayWeather);
//                city.setText(weatherBean.getCity());
////                weatherImg.setPixelMap(WeatherImgUtil.getImgResOfWeather(dayWeather.getWea_img()));
//                weather.setText(dayWeather.getWea());
//                tem.setText(dayWeather.getTem());
//                temLowHigh.setText(dayWeather.getTem2() + "/" + dayWeather.getTem1());
//                week.setText(dayWeather.getWeek());
//            });
        });
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
