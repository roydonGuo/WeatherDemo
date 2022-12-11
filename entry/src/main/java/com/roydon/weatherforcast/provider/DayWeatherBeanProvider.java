package com.roydon.weatherforcast.provider;

import com.roydon.weatherforcast.ResourceTable;
import com.roydon.weatherforcast.bean.DayWeatherBean;
import com.roydon.weatherforcast.utils.WeatherImgUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;

import java.util.List;

/**
 * 官方开发文档见下方链接
 * https://developer.harmonyos.com/cn/docs/documentation/doc-guides/ui-java-component-listcontainer-0000001060007847
 */
public class DayWeatherBeanProvider extends BaseItemProvider {
    private List<DayWeatherBean> list;
    private AbilitySlice slice;

    public DayWeatherBeanProvider(List<DayWeatherBean> list, AbilitySlice slice) {
        this.list = list;
        this.slice = slice;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        if (list != null && i >= 0 && i < list.size()){
            return list.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        //可添加具体处理逻辑

        return i;
    }


    @Override
    public Component getComponent(int i, Component component, ComponentContainer componentContainer) {
        final Component cpt;
        if (component == null) {
            cpt = LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_item_day_weather, null, false);
        } else {
            cpt = component;
        }
        DayWeatherBean dayWeatherBean = list.get(i);
        Text date = (Text) cpt.findComponentById(ResourceTable.Id_text_date);
        Text tem = (Text) cpt.findComponentById(ResourceTable.Id_text_tem_low_high);
        Image image = (Image) cpt.findComponentById(ResourceTable.Id_day_weather_img);
        date.setText(dayWeatherBean.getDate().substring(5,10));
        image.setPixelMap(WeatherImgUtil.getImgResOfWeather(dayWeatherBean.getWea_img()));
        tem.setText(dayWeatherBean.getTem2() + "/" + dayWeatherBean.getTem1());
        return cpt;
    }
}
