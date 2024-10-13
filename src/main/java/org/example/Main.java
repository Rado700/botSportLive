package org.example;

import org.example.handlers.Commands;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main extends TelegramLongPollingBot {

    String name = System.getenv("USERNAME");
    String token = System.getenv("TOKEN");

    private final Commands commands;

    public Main() {
        BotService botService = new BotService(this);
        this.commands = new Commands(botService);
    }

    public static void main(String[] args) {

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Main());
        } catch (
                TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpdateReceived(Update update) {
        try{
            if (update.hasMessage()){
                if (update.getMessage().hasText() && update.getMessage().getText().startsWith("/start")){
                    commands.onUpdateReceived(update.getMessage());
//                    System.out.println("hi");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;

    }
}