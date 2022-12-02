package com.roydon.weatherforcast.handler;

import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import ohos.eventhandler.InnerEvent;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MyEventHandler extends EventHandler {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "TC_Log");
    private static final int EVENT_MESSAGE_NORMAL = 1;
    private static final int EVENT_MESSAGE_DELAY = 2;
    private static final int EVENT_MESSAGE_CROSS_THREAD = 3;

    public MyEventHandler(EventRunner runner) throws IllegalArgumentException {
        super(runner);
    }

    /**
     * 1、创建EventHandler的子类，在子类中重写实现方法processEvent()来处理事件
     *
     * @param event
     */
    @Override
    protected void processEvent(InnerEvent event) {
        super.processEvent(event);

        if (event == null) {
            return;
        }

        int eventId = event.eventId;
        switch (eventId) {
            case EVENT_MESSAGE_NORMAL:
                // 待执行的操作，由开发者定义
                HiLog.info(LABEL_LOG, "111");
                break;

            case EVENT_MESSAGE_DELAY:
                // 待执行的操作，由开发者定义
                HiLog.info(LABEL_LOG, "222");
                break;

            case EVENT_MESSAGE_CROSS_THREAD:
                // 待执行的操作，由开发者定义
                HiLog.info(LABEL_LOG, "333");
                Object object = event.object;
                if (object instanceof EventRunner) {
                    // 将原先线程的EventRunner实例投递给新创建的线程
                    EventRunner eventRunner = (EventRunner) object;//强转
                    // 将原先线程的EventRunner实例与新创建的线程的EventHandler绑定
                    EventHandler eventHandler = new EventHandler(eventRunner) {
                        @Override
                        public void processEvent(InnerEvent event) {
                            // 需要在原先线程执行的操作
                            HiLog.info(LABEL_LOG, "OriginalThread receive a message");
                        }
                    };
                    int testEventId = 1;
                    long testParam = 0;
                    Object testObject = null;
                    InnerEvent innerEvent = InnerEvent.get(testEventId, testParam, testObject);
                    eventHandler.sendEvent(innerEvent); //投递事件到原先的线程
                }
                break;

            default:
                break;
        }
    }
}
