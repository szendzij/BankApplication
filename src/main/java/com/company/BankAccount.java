package com.company;

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BankAccount implements Serializable {
    private static int uid = 0;
    private String id;
    private double balance;
    private String name;
    private Boolean isCovid;
    private double depositLimit;

    private final static String DATE_FORMAT = "yyyy-MM-dd";


    public BankAccount() {
        this.id = String.valueOf(uid++);
        this.isCovid = true;
    }

    public Boolean getIsCovid() {
        return isCovid;
    }

    public void setIsCovid(Boolean isCovid) {
        this.isCovid = isCovid;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void idRandomizer(BankAccount account) {
        account.setId(account.getName().toUpperCase() + "_00" + account.getId());
    }

    public void deposit(BankAccount account, double depositAmount) {
        account.balance += depositAmount;
        account.setBalance(account.balance);
        JOptionPane.showMessageDialog(null, "Wpływ: " + depositAmount + " PLN\n" + "Stan konta: " + account.balance + " PLN");
    }

    public void withdraw(BankAccount account, double withdrawAmount) {
        if (account.balance <= withdrawAmount) {
            JOptionPane.showMessageDialog(null, "Nie masz odpowiedniej ilości środków na koncie");
        } else if (account.depositLimit >= 1000 && account.isCovid) {
            JOptionPane.showMessageDialog(null, "Dzienny limit został wykorzystany");
        } else {
            account.depositLimit += withdrawAmount;
            account.balance -= withdrawAmount;
            JOptionPane.showMessageDialog(null, "Wypłata: " + withdrawAmount + " PLN\n" + "Stan konta: " + account.balance + " PLN");
        }
    }

    public void info(BankAccount account) {
        JOptionPane.showMessageDialog(null, account.toString());
    }

    public static boolean isDateValid(String date) {
        boolean valid = false;
        while (!valid){
            try {
                LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
                valid = true;
                return valid;
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null,"Wprowadzona wartośc nie jest datą o podanym formacie");
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString() {
        return "Nazwa użytkownika: " + this.getName() + "\n" + "Identyfikator użytkownika: " + this.getId() + "\n" + "Aktualna dostępna ilość środków: " + this.getBalance() + " PLN";
    }

}
