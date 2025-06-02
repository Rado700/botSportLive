package org.example.handlers;

import org.example.BotService;
import org.example.utils.QrCodeGenerator;
import org.example.buttons.Inline;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.ByteArrayInputStream;
import java.util.Objects;

public class HotButtons extends BotHandler{

    public HotButtons(BotService botService) {
        super(botService);
    }

    public void onUpdateReceived(Message message) throws Exception {
        if (Objects.equals(message.getText(), "Поддержка")){
            handleSupport(message);
        }
        else if(Objects.equals(message.getText(), "Записаться на тренировку")){
            handleStartSchedule(message);
        }
        else if (Objects.equals(message.getText(), "Пополнить баланс")){
            handleAddBalance(message);
        }
        else if (Objects.equals(message.getText(), "Написать тренеру")){
            handleMessageForCouch(message);
        } else if (message.getText().equals("Добавить тренировку")) {
            handleMessageAddSchedule(message);
        } else if (message.getText().equals("Мой тренировки")) {
            handleMessageGetScheduleForCouch(message);
        } else if (message.getText().equals("Добавить инвентарь")) {
            handleMessageAddInventory(message);
        } else if (message.getText().equals("Вывод средств")) {
            handleMessageWithdrawBalance(message);
        } else if (message.getText().equals("Авторизоваться")) {
            handleMessageAuthorisation(message);
        }else if (message.getText().equals("Генерация QR-Cod")) {
            handleStartCommand(message);
        }
    }

    public void handleSupport(Message message){
        String response = "Для обращения в поддержку жмите кнопку ниже \uD83D\uDC47";
        SendMessage sendMessage = new SendMessage ();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(response);
        sendMessage.setReplyMarkup(Inline.getSupport());
        this.botService.sendMessage(sendMessage);
    }

    private void handleStartSchedule(Message message){
        String response = "Забронировать тренировку";
        SendMessage sendMessage = new SendMessage ();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(response);
        sendMessage.setReplyMarkup(Inline.startScheduleButtons());
        this.botService.sendMessage(sendMessage);

    }

    private void handleAddBalance(Message message){
        String response = "Перейти для оплаты";
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(response);
        sendMessage.setReplyMarkup(Inline.addBalanceButtons());
        this.botService.sendMessage(sendMessage);
    }

    private void handleMessageForCouch(Message message) throws Exception {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Выберите тренера \uD83D\uDC47");
        sendMessage.setReplyMarkup(Inline.getMessageCouch(message.getChatId()));
        this.botService.sendMessage(sendMessage);
    }

    private void handleMessageAddSchedule(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Выбрать дату ⬆");
        sendMessage.setReplyMarkup(Inline.addScheduleCouch());
        this.botService.sendMessage(sendMessage);
    }

    private void handleMessageWithdrawBalance(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Снять со счета");
        sendMessage.setReplyMarkup(Inline.withdrawBalanceForCouch());
        this.botService.sendMessage(sendMessage);
    }
    private void handleMessageAddInventory(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Добавить в инвентарь");
        sendMessage.setReplyMarkup(Inline.addInventoryForCouch());
        this.botService.sendMessage(sendMessage);
    }
    private void handleMessageGetScheduleForCouch(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Показать мой тренировки");
        sendMessage.setReplyMarkup(Inline.openScheduleForCouch());
        this.botService.sendMessage(sendMessage);
    }

    public void handleStartCommand (Message message) throws Exception {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Генерация QR-кода");

        String chatId = String.valueOf(message.getChatId());
        // Генерация уникального URL
        String qrUrl = "https://sportliveapp.ru/auth/qr?couchTgId=" + chatId+"page=recordScreen";

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

    private void handleMessageAuthorisation(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Авторизоваться");
        sendMessage.setReplyMarkup(Inline.authorisationForAnonymous());
        this.botService.sendMessage(sendMessage);
    }

}
