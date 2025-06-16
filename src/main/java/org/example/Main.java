package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.handlers.CallBack;
import org.example.handlers.Commands;
import org.example.handlers.HotButtons;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main extends TelegramLongPollingBot {

    private final Commands commands;
    private final HotButtons hotButtons;
    private final CallBack callBack;


    public Main() {
        BotService botService = new BotService(this);
        this.commands = new Commands(botService);
        this.hotButtons = new HotButtons(botService);
        this.callBack = new CallBack(botService);
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
                if (update.getMessage().hasText() && update.getMessage().getText().startsWith("/")){
                    commands.onUpdateReceived(update.getMessage());

                }
                else if (update.getMessage().hasText()){
                    hotButtons.onUpdateReceived(update.getMessage());

                }
            }
            else  if (update.hasCallbackQuery()){
                callBack.onUpdateReceived(update.getCallbackQuery());

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getBotUsername() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("USERNAME");
    }

    @Override
    public String getBotToken() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("TOKEN");

    }
}