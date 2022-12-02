package com.roydon.weatherforcast.provider;

import com.roydon.weatherforcast.bean.DayWeatherBean;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.AttrSet;
import ohos.agp.components.ListContainer;
import ohos.app.Context;

import java.util.List;

public class DayListContainer extends ListContainer {

    private List<DayWeatherBean> dayWeatherBeanList;
    private AbilitySlice abilitySlice;


    public DayListContainer(Context context) {
        super(context);
    }

}
