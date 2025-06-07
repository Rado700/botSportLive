package org.example.buttons;

import org.example.ApiService;
import org.example.utils.QrCodeGenerator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Inline {

    public static InlineKeyboardMarkup startButtons(){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>rowsInline = new ArrayList<>();
        List<InlineKeyboardButton>rowInline = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Начать");
        button.setWebApp(new WebAppInfo("https://sportliveapp.ru"));
        rowInline.add(button);
        rowsInline.add(rowInline);

        markup.setKeyboard(rowsInline);
        return markup;
    }

    public static InlineKeyboardMarkup startScheduleButtons(){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>rowsInline = new ArrayList<>();
        List<InlineKeyboardButton>rowInline = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Расписание тренировок");
//        button.setWebApp(new WebAppInfo("https://localhost:8080/auth/tg"));
        button.setWebApp(new WebAppInfo("https://sportliveapp.ru/auth/tg?page=scheduleScreen"));
        rowInline.add(button);
        rowsInline.add(rowInline);

        markup.setKeyboard(rowsInline);
        return markup;
    }

    public static InlineKeyboardMarkup addBalanceButtons(){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Пополнить баланс");
        button.setWebApp(new WebAppInfo("https://sportliveapp.ru/auth/tg?page=addBalanceScreen"));
        rowInline.add(button);
        rowsInline.add(rowInline);
        markup.setKeyboard(rowsInline);

        return markup;

    }

    public static InlineKeyboardMarkup getSupport(){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Написать в поддержку");
        button.setUrl("https://t.me/Raxary");
        rowInline.add(button);
        rowsInline.add(rowInline);
        markup.setKeyboard(rowsInline);

        return markup;
    }

    public static String hashString(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static InlineKeyboardMarkup getMessageCouch(Long chatId) throws Exception {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>rowsLine = new ArrayList<>();

        String couchesString = ApiService.sendGetRequest("/api/user/couch/tgId/" + hashString(String.valueOf(chatId))).toString();

        JSONArray couchesJson = new JSONArray(couchesString);

        for (int i =0; i <couchesJson.length(); i++){
            List<InlineKeyboardButton>rowLine = new ArrayList<>();
            JSONObject couch = couchesJson.getJSONObject(i);
            String name = couch.getString("name");
            String couchId = couch.getString("tgId");
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(name);
            button.setUrl("tg://user?id=" + couchId);
            rowLine.add(button);
            rowsLine.add(rowLine);

        }
        markup.setKeyboard(rowsLine);
        return markup;
    }

    public static InlineKeyboardMarkup addScheduleCouch(){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>rowsInline = new ArrayList<>();
        List<InlineKeyboardButton>rowInline = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Добавить тренировку");
//        button.setWebApp(new WebAppInfo("https://localhost:8080/auth/tg"));
        button.setWebApp(new WebAppInfo("https://sportliveapp.ru/auth/tg?page=addScheduleScreen"));
        rowInline.add(button);
        rowsInline.add(rowInline);

        markup.setKeyboard(rowsInline);
        return markup;
    }


    public static InlineKeyboardMarkup withdrawBalanceForCouch(){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>rowsInline = new ArrayList<>();
        List<InlineKeyboardButton>rowInline = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Вывод средств");
//        button.setWebApp(new WebAppInfo("https://localhost:8080/auth/tg"));
        button.setWebApp(new WebAppInfo("https://sportliveapp.ru/auth/tg?page=withdrawBalanceForCouchScreen"));
        rowInline.add(button);
        rowsInline.add(rowInline);

        markup.setKeyboard(rowsInline);
        return markup;
    }

    public static InlineKeyboardMarkup openScheduleForCouch(){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>rowsInline = new ArrayList<>();
        List<InlineKeyboardButton>rowInline = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Мой тренировки");
//        button.setWebApp(new WebAppInfo("https://localhost:8080/auth/tg"));
        button.setWebApp(new WebAppInfo("https://sportliveapp.ru/auth/tg?page=scheduleForCouchScreen"));
        rowInline.add(button);
        rowsInline.add(rowInline);

        markup.setKeyboard(rowsInline);
        return markup;
    }

    public static InlineKeyboardMarkup addInventoryForCouch(){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>rowsInline = new ArrayList<>();
        List<InlineKeyboardButton>rowInline = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Добавить инвентарь");
//        button.setWebApp(new WebAppInfo("https://localhost:8080/auth/tg"));
        button.setWebApp(new WebAppInfo("https://sportliveapp.ru/auth/tg?page=addInventoryForCouchScreen"));
        rowInline.add(button);
        rowsInline.add(rowInline);

        markup.setKeyboard(rowsInline);
        return markup;
    }

    public static InlineKeyboardMarkup authorisationForAnonymous(){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>rowsInline = new ArrayList<>();
        List<InlineKeyboardButton>rowInline = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Авторизоваться");
//        button.setWebApp(new WebAppInfo("https://localhost:8080/auth/tg"));
        button.setWebApp(new WebAppInfo("https://sportliveapp.ru"));
        rowInline.add(button);
        rowsInline.add(rowInline);

        markup.setKeyboard(rowsInline);
        return markup;
    }

    public static InlineKeyboardMarkup getSectionForCouch(Long chatId) throws Exception {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>rowsLine = new ArrayList<>();

        String sectionString = ApiService.sendGetRequest("/api/couch/sportSection/tgId/" + hashString(String.valueOf(chatId))).toString();

        JSONArray sectionsJson = new JSONArray(sectionString);

        for (int i =0; i <sectionsJson.length(); i++){
            List<InlineKeyboardButton>rowLine = new ArrayList<>();
            JSONObject section = sectionsJson.getJSONObject(i);
            String name = section.getString("name");
            String sectionId = section.getString("id");
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(name);
            button.setCallbackData("section|"+sectionId);
            rowLine.add(button);
            rowsLine.add(rowLine);

        }
        markup.setKeyboard(rowsLine);
        return markup;
    }

}
