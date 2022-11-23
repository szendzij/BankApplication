package com.company;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

public class Driver {

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Witaj w aplikacji bankowej");
        BankAccount account = new BankAccount("", 0, 0.0);
        boolean exitRequested = false;
        while (!exitRequested) {
            switch (MenuService.showMenu()) {
                case "Rejestracja nowego konta":
                    account.registerAccount();
                    break;
                case "Zalogowanie się do istniejącego konta":
                    account.loginAccount();
                    break;
                case "Exit":
                    JOptionPane.showMessageDialog(null, "Zamknięcie programu");
                    exitRequested = true;
                    break;
            }

        }
        System.exit(0);

    }
}
