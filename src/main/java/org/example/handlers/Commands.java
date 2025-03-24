package org.example.handlers;

import org.example.ApiService;
import org.example.BotService;
import org.example.buttons.Keyboard;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Objects;

import static org.example.buttons.Inline.hashString;

public class Commands extends BotHandler {


    public Commands(BotService botService) {
        super(botService);
    }


    public void onUpdateReceived (Message message) throws Exception {
        if (message.getText().startsWith("/start")){
            handleStartCommand(message);
        }
    }

    private void handleStartCommand (Message message) throws Exception {
//        String url = "";
//        ApiService.sendGetRequest(url);

        String getChatId = String.valueOf(message.getChatId());
        String getMessageText = message.getText();
        String[] splitForText = getMessageText.split(" ");
        if (splitForText.length >= 2){
            String encryptTextId = splitForText[1];
            if (encryptTextId.startsWith("auth_")) {
                String encryptLoginId = encryptTextId.split("_")[1];
                ApiService.sendPostRequest(encryptLoginId, getChatId);
            }
        }
        String response = "Привет";
        SendMessage sendMessage = new SendMessage ();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(response);
        String userCouchJson = ApiService.sendGetRequest("/api/login/tgId/" + hashString(String.valueOf(message.getChatId()))).toString();
        JSONObject userType = new JSONObject(userCouchJson);


         if (!userType.isNull("user")) {
             sendMessage.setReplyMarkup(Keyboard.getHotButtonsUser());
         }
         else if (!userType.isNull("couch")) {
                sendMessage.setReplyMarkup(Keyboard.getHotButtonsCouch());
         }
         else {
             sendMessage.setReplyMarkup(Keyboard.getHotButtonsAnonymous());
         }
        this.botService.sendMessage(sendMessage);

    }


}
