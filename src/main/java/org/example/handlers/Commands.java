package org.example.handlers;

import org.example.ApiService;
import org.example.BotService;
import org.example.buttons.Inline;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

public class Commands extends BotHandler {

    public Commands(BotService botService) {
        super(botService);
    }


    public void onUpdateReceived (Message message) throws Exception {
        if (message.getText().equals("/start")){
            handleStartCommand(message);
        }
    }

    private void handleStartCommand (Message message) throws Exception {
//        String url = "";
//        ApiService.sendGetRequest(url);
        String response = "Привет";
        SendMessage sendMessage = new SendMessage ();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(response);
        sendMessage.setReplyMarkup(Inline.startButtons());
        this.botService.sendMessage(sendMessage);
    }
}
