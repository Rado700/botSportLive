package org.example.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {

    public static ReplyKeyboardMarkup getHotButtonsUser(){
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow>rowsLine = new ArrayList<>();
        KeyboardRow rowLine = new KeyboardRow();
        KeyboardRow rowLine2 = new KeyboardRow();

        KeyboardButton button = new KeyboardButton();
        KeyboardButton button2 = new KeyboardButton();
        KeyboardButton button3 = new KeyboardButton();
        KeyboardButton button4 = new KeyboardButton();
        button.setText("Записаться на тренировку");
        button2.setText("Пополнить баланс");
        button3.setText("Написать тренеру");
        button4.setText("Поддержка");


        rowLine.add(button);
        rowLine.add(button2);
        rowLine2.add(button3);
        rowLine2.add(button4);

        rowsLine.add(rowLine);
        rowsLine.add(rowLine2);

        markup.setKeyboard(rowsLine);
        markup.setResizeKeyboard(true);
        return markup;

    }

    public static ReplyKeyboardMarkup getHotButtonsCouch(){
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow>rowLines = new ArrayList<>();
        KeyboardRow rowLine1 = new KeyboardRow();
        KeyboardRow rowLine2 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton();
        KeyboardButton button2 = new KeyboardButton();
        KeyboardButton button3 = new KeyboardButton();
        KeyboardButton button4 = new KeyboardButton();

        button1.setText("Добавить тренировку");
        button2.setText("Мой тренировки");
        button3.setText("Добавить инвентарь");
        button4.setText("Вывод средств");

        rowLine1.add(button1);
        rowLine1.add(button2);
        rowLine2.add(button3);
        rowLine2.add(button4);

        rowLines.add(rowLine1);
        rowLines.add(rowLine2);

        markup.setKeyboard(rowLines);
        markup.setResizeKeyboard(true);
        return markup;
    }

}
