package com.company;

import javax.swing.*;

public class MenuService {
    public static final String[] mainMenuItems = {"Rejestracja nowego konta", "Zalogowanie się do istniejącego konta", "Zapis danych do pliku", "Pobranie danych z pliku", "Exit"};
    public static final String[] accountTypeMenuItems = {"Osobiste", "Firmowe", "Międzynarodowe"};
    public static final String[] accountActionMenuItems = {"Informacje o stanie konta", "Wpłata środków", "Wypłata środków", "Zamknięcie konta", "Wylogowanie"};
    private static final JFrame frame = new JFrame("Menu systemu bankowego");

    public static String showMenu() {
        String value = (String) JOptionPane.showInputDialog(frame,
                "Wybierz opcję",
                "System bankowy",
                JOptionPane.QUESTION_MESSAGE,
                null,
                mainMenuItems,
                mainMenuItems[0]);
        if (value != null && value.length() > 0) {
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

        return checkIfValueIsNull(value);
    }

    public static String showActionMenu() {
        String value = (String) JOptionPane.showInputDialog(frame,
                "Wybierz typ operacji",
                "System bankowy",
                JOptionPane.QUESTION_MESSAGE,
                null,
                accountActionMenuItems,
                accountActionMenuItems[0]);

        return checkIfValueIsNull(value);
    }

    private static String checkIfValueIsNull(String value) {
        if (value != null && value.length() > 0) {
            return value;
        } else {
            return "";
        }
    }

}
