package org.example.handlers;

import org.example.BotService;
import org.example.utils.QrCodeGenerator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.ByteArrayInputStream;
import java.util.Map;

public class CallBack extends BotHandler {

    //
    public CallBack(BotService botService) {
        super(botService);
    }


    public void onUpdateReceived(CallbackQuery callbackQuery) throws Exception {
        String[] data = callbackQuery.getData().split("\\|");
        if (data[0].equals("section")) {
            handleGenerateQrCod((Message) callbackQuery.getMessage(), data);
        }
    }

    public void handleGenerateQrCod(Message message, String[] data) throws Exception {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Генерация QR-кода");

        String chatId = String.valueOf(message.getChatId());
        // Генерация уникального URL
        String qrUrl = "https://sportliveapp.ru/auth/qr?couchTgId=" + chatId + "&page=recordScreen&section=" + data[1];

        // Генерация QR-кода
        byte[] qrBytes = QrCodeGenerator.generateQrCode(qrUrl, 300, 300);
        InputFile qrImage = new InputFile(new ByteArrayInputStream(qrBytes), "qr_code.png");

        // Отправка QR-кода
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(qrImage);
        sendPhoto.setCaption("Сканируйте этот QR-код для авторизации пользователя:");
        botService.sendPhoto(sendPhoto);

        this.botService.sendMessage(sendMessage);
    }
}