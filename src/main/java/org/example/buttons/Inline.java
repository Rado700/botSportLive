package org.example.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

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
}
