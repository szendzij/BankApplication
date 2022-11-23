package com.company;

import javax.swing.*;

public class MenuService {
    public static final String[] mainMenuItems = { "Rejestracja nowego konta", "Zalogowanie się do istniejącego konta", "Exit" };
    public static final String[] accountTypeMenuItems = { "Firmowe", "Osobiste", "Międzynarodowe" };
    public static final String[] accountActionMenuItems = { "Wpłata", "Wypłata" };

    private static JFrame frame = new JFrame("Menu systemu bankowego");

    public static String showMenu() {
        String value = (String) JOptionPane.showInputDialog(frame,
                "Wybierz opcję",
                "System bankowy",
                JOptionPane.QUESTION_MESSAGE,
                null,
                mainMenuItems,
                mainMenuItems[0]);
        if(value != null && value.length() > 0) {
            return value;
        } else {
            JOptionPane.showMessageDialog(null, "Zamknięcie programu");
            System.exit(0);
            return "";
        }
    }

    public static String showMenuAccountType() {
        String value = (String) JOptionPane.showInputDialog(frame,
                "Wybierz typ konta",
                "System bankowy",
                JOptionPane.QUESTION_MESSAGE,
                null,
                accountTypeMenuItems,
                accountTypeMenuItems[0]);

        if(value != null && value.length() > 0) {
            return value;
        } else {
            return "";
        }
    }

    public static String showActionMenu() {
        String value = (String) JOptionPane.showInputDialog(frame,
                "Wybierz typ operacji",
                "System bankowy",
                JOptionPane.QUESTION_MESSAGE,
                null,
                accountActionMenuItems,
                accountActionMenuItems[0]);

        if(value != null && value.length() > 0) {
            return value;
        } else {
            return "";
        }
    }


    public MenuService existAccountMenu() {
        String fullName = JOptionPane.showInputDialog("Wprowadź imie i nazwisko");
        return this;
    }
}
