package com.company;//package com.company;
//
//public class Driver {
//
//    public static void main(String[] args) {
//	// write your code here
//    }
//}

import javax.swing.JOptionPane;

public class Driver {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("", 0, 0.0);
        JOptionPane.showMessageDialog(null, "Witaj w aplikacji bankowej");
        boolean exitRequested = false;
        while(!exitRequested) {
            switch (MenuService.showMenu()) {
                case "Rejestracja nowego konta":
                    bankAccount.registerAccount();
                    break;
                case "Zalogowanie się do istniejącego konta":
                    bankAccount.loginAccount();
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

