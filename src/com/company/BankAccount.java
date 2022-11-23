package com.company;

import java.util.*;

import javax.swing.*;

public class BankAccount {
    private double balance;
    private int id;
    private static int uid = 0;
    private String name;
    private int phoneNumber;
    private final List<BankAccount> bankAccounts;

    public BankAccount(String name, int id, double balance, int phoneNumber) {
        this.name = name;
        this.id = uid++;
        this.balance = balance;
        this.phoneNumber = phoneNumber;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
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
            case "Osobiste":
                this.setName(JOptionPane.showInputDialog("Wprowadź nazwę konta"));
                this.setPhoneNumber(Integer.parseInt(JOptionPane.showInputDialog("Wprowadź numer telefonu")));
                BankAccount personalAccount = new BankAccount(this.getName(), this.getId(), this.getBalance(), this.getPhoneNumber());
                bankAccounts.add(personalAccount);
                personalAccount.toString();
                break;
            case "Firmowe":
                this.setName(JOptionPane.showInputDialog("Wprowadź nazwę konta"));
                this.setPhoneNumber(Integer.parseInt(JOptionPane.showInputDialog("Wprowadź numer telefonu")));
                this.setBalance(5000);
                BankAccount_firma companyAccount = new BankAccount_firma(this.getName(), this.getId(), this.getBalance(), this.getPhoneNumber());
                companyAccount.setREGON(JOptionPane.showInputDialog("Wprowadź numer regon"));
                bankAccounts.add(companyAccount);
                companyAccount.toString();
                break;
            case "Międzynarodowe":
                this.setName(JOptionPane.showInputDialog("Wprowadź nazwę konta"));
                this.setPhoneNumber(Integer.parseInt(JOptionPane.showInputDialog("Wprowadź numer telefonu")));
                BankAccount_INT internationalAccount = new BankAccount_INT(this.getName(), this.getId(), this.getBalance(), this.getPhoneNumber());
                internationalAccount.setOrigin(JOptionPane.showInputDialog("Wprowadź kraj pochodzenia"));
                bankAccounts.add(internationalAccount);
                internationalAccount.toString();
                break;
        }
    }

    public BankAccount isAccountExist() {
        return null;
    }

    public void loginAccount() {
        String accountName = JOptionPane.showInputDialog("Wprowadź nazwę konta");
        int accountNumber = Integer.parseInt(JOptionPane.showInputDialog("Wprowadź number telefonu"));
        boolean accuntFounded = false;
        while(!accuntFounded) {
            Optional<BankAccount> matchingAccount = bankAccounts
                    .stream()
                    .filter(p -> p.getName().equals(accountName))
//                    .filter(v -> p.getPhoneNumber())
                    .findFirst();
            if(matchingAccount.isPresent()) {
                JOptionPane.showMessageDialog(null, "ZNALEZIONO KONTO");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "NIE ZNALEZIONO KONTA ");
                accuntFounded = true;
                break;
            }
        }
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
                        "Numer telefonu: " + this.getPhoneNumber() + "\n" +
                        "Identyfikator użytkownika: " + this.getId() + "\n" +
                        "Aktualna dostepna ilość środków: " + this.getBalance() + "PLN");
        return null;
    }
}
