package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

public class BankAccount {
    private double balance;
    private int id;
    private static int uid = 0;
    private String name;
    private final List<BankAccount> bankAccounts;

    public BankAccount(String name, int id, double balance) {
        this.name = name;
        this.id = uid++;
        this.balance = balance;
        bankAccounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void manageAccount() {

        String actionType = MenuService.showActionMenu();
        switch (actionType) {
            case "Wpłata":
                deposit(Double.parseDouble(JOptionPane.showInputDialog("Wprowadź kwotę wpłaty")));
                break;
            case "Wypłata":
                withdraw(Double.parseDouble(JOptionPane.showInputDialog("Wprowadź kwotę wypłaty")));
                break;
        }

    }

    public  void registerAccount() {
        String accountType = MenuService.showMenuAccountType();
        setBalance(0.0);
        switch (accountType) {
            case "Firmowe":
                this.setName(JOptionPane.showInputDialog("Wprowadź nazwę konta"));
                this.setBalance(5000);
                BankAccount_firma companyAccount = new BankAccount_firma(this.getName(), this.getId(), this.getBalance());
                companyAccount.setREGON(JOptionPane.showInputDialog("Wprowadź numer regon"));
                bankAccounts.add(companyAccount);
                companyAccount.toString();
                break;
            case "Osobiste":
                this.setName(JOptionPane.showInputDialog("Wprowadź nazwę konta"));
                BankAccount personalAccount = new BankAccount(this.getName(), this.getId(), this.getBalance());
                bankAccounts.add(personalAccount);
                personalAccount.toString();
                break;
            case "Międzynarodowe":
                this.setName(JOptionPane.showInputDialog("Wprowadź nazwę konta"));
                BankAccount_INT internationalAccount = new BankAccount_INT(this.getName(), this.getId(), this.getBalance());
                internationalAccount.setOrigin(JOptionPane.showInputDialog("Wprowadź kraj pochodzenia"));
                bankAccounts.add(internationalAccount);
                internationalAccount.toString();
                break;
        }
        for(BankAccount q : bankAccounts) {System.out.println("In loop : " + q.getName() +" "+ q.getBalance() +" "+ q.getId());}
    }

    public void loginAccount() {
        String accountName = JOptionPane.showInputDialog("Wprowadź nazwę konta");
        bankAccounts.stream().filter(a -> a.getName() == accountName);

        toString();
    }

    public void deposit(double depositAmount) {
        this.balance += depositAmount;
        this.setBalance(this.getBalance());
        JOptionPane.showMessageDialog(null, "Wpływ: " + depositAmount + " PLN\n" +
                "Stan konta: " + this.balance + " PLN");
    }

    public void withdraw(double withdrawAmount) {
        if (this.balance < withdrawAmount) {
            JOptionPane.showMessageDialog(null, "Nie masz odpowiedniej ilości środków na koncie");
        } else {
            this.balance -= withdrawAmount;
            JOptionPane.showMessageDialog(null, "Wypłata: " + withdrawAmount + " PLN\n" +
                    "Stan konta: " + this.balance);
        }

    }

    public void closeAccount() {

    }

    public String toString() {
        JOptionPane.showMessageDialog(null,
                "Nazwa użytkownika: " + this.getName() + "\n" +
                        "Identyfikator użytkownika: " + this.getId() + "\n" +
                        "Aktualna dostepna ilość środków: " + this.getBalance() + "PLN");
        return null;
    }
}
