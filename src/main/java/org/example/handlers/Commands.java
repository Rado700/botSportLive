package org.example.handlers;

import org.example.ApiService;
import org.example.BotService;
import org.example.buttons.Inline;
import org.example.buttons.Keyboard;
import org.example.utils.QrCodeGenerator;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.ByteArrayInputStream;

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
        if (splitForText.length >= 2) {
            String encryptTextId = splitForText[1];
            if (encryptTextId.startsWith("auth_")) {
                String encryptLoginId = encryptTextId.split("_")[1];
                ApiService.sendPostRequest(encryptLoginId, getChatId);
            }
        }
        String response = "\uD83D\uDC4B Привет! Я — ассистент нашей спортивной платформы.\n" +
                "Помогу вам быстро и удобно:\n" +
                "\n" +
                "\uD83C\uDFCB️ Записаться на тренировку к тренеру\n" +
                "\uD83D\uDED2 Приобрести спортивный инвентарь\n" +
                "\uD83D\uDCCA Посмотреть статистику ваших тренировок\n" +
                "\uD83D\uDE80 Повысить свои спортивные способности шаг за шагом" +
                "\n" +
                "Для быстрого доступа к приложению и получению уведомлений через тг и быстрый доступ к записям" +
                "пройдите в пункт 1)информация и 2)подключите уведомления" +
                "\n" +
                "\n" +
                "Здесь вы на расстояний одного клика до новой цели!";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(response);
        String userCouchJson = ApiService.sendGetRequest("/api/login/tgId/" + hashString(String.valueOf(message.getChatId()))).toString();
        if (userCouchJson.isEmpty()) {
            sendMessage.setReplyMarkup(Inline.authorisationForAnonymous());
        } else {
            JSONObject userType = new JSONObject(userCouchJson);

            if (!userType.isNull("user")) {
                sendMessage.setReplyMarkup(Keyboard.getHotButtonsUser());
            } else if (!userType.isNull("couch")) {
                sendMessage.setReplyMarkup(Keyboard.getHotButtonsCouch());
            }
        }
        this.botService.sendMessage(sendMessage);

    }




}
