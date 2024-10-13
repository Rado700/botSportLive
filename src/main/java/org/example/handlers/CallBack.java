package org.example.handlers;

import org.example.BotService;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Map;

public class CallBack extends BotHandler {

//
    public CallBack(BotService botService) {
        super(botService);
    }

//
//    public void onUpdateReceived(CallbackQuery callbackQuery) throws Exception {
//        String[] data = callbackQuery.getData().split("\\|");
//        if (data[0].equals("start")) {
//            handleStartButtons(callbackQuery.getMessage(), data);
//        }
//    }
}