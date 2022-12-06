package com.roydon.weatherforcast.utils;

import com.roydon.weatherforcast.ResourceTable;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;

public class ToastUtil {

    /**
     * @param context 上下文参数
     * @param msg 内容
     */
    public static void showToast(Context context, String msg){
        DirectionalLayout layout = (DirectionalLayout) LayoutScatter.getInstance(context)
                .parse(ResourceTable.Layout_layout_toast, null, false);
        Text msg_toast =layout.findComponentById(ResourceTable.Id_msg_toast);
        msg_toast.setText(msg);
        new ToastDialog(context)
                .setContentCustomComponent(layout)
                .setSize(DirectionalLayout.LayoutConfig.MATCH_CONTENT, DirectionalLayout.LayoutConfig.MATCH_CONTENT)
                .setAlignment(LayoutAlignment.TOP)
                .show();
    }

    public static void showTips(Context context, String msg) {
        new ToastDialog(context).setText(msg).setAlignment(LayoutAlignment.TOP).show();
    }
}
