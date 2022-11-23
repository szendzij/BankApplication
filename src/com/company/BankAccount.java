package com.company;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankAccount implements Serializable {
    private double balance;
    private int id;
    private static int uid = 0;
    private String name;
    List<BankAccount> bankAccounts = new ArrayList<>();

    public BankAccount(String name, int id, double balance) {
        this.name = name;
        this.id = uid++;
        this.balance = balance;
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

    public void setId(int id) {
        this.id = id;
    }

    public void financingOperationsOnAccount() {

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

    public void registerAccount() {
        String accountType = MenuService.showMenuAccountType();
        switch (accountType){
            case "Firmowe":
                this.setName(JOptionPane.showInputDialog("Wprowadź nazwę konta"));
                BankAccount_firma companyAccount = new BankAccount_firma(this.getName(), this.getId(), 0.0);
                companyAccount.setREGON(JOptionPane.showInputDialog("Wprowadź numer regon"));
                companyAccount.toString();
                bankAccounts.add(companyAccount);
                System.out.println(bankAccounts);
                break;
            case "Osobiste":
                this.name = JOptionPane.showInputDialog("Wprowadź nazwę konta");
                BankAccount personalAccount = new BankAccount(this.name, this.id, this.balance);
                
                bankAccounts.add(personalAccount);


                financingOperationsOnAccount();
                personalAccount.toString();

//                System.out.println(bankAccounts);
                break;
            case "Międzynarodowe":
                this.setName(JOptionPane.showInputDialog("Wprowadź nazwę konta"));
                BankAccount_INT internationalAccount = new BankAccount_INT(this.getName(), this.getId(), 0.0);
                internationalAccount.setOrigin(JOptionPane.showInputDialog("Wprowadź kraj pochodzenia"));
                bankAccounts.add(internationalAccount);
                internationalAccount.toString();

                break;
        }

    }


    public void loginAccount() {
        String name = JOptionPane.showInputDialog("Wprowadź nazwę konta");
        this.setId(this.id);
        this.setBalance(Double.parseDouble(JOptionPane.showInputDialog("Wprowadź inicjalną kwotę na koncie")));
        toString();
    }


    public void deposit(double depositAmount) {
        this.balance += depositAmount;
        JOptionPane.showMessageDialog(null, "Wpływ: " + depositAmount + " PLN\n" +
        "Stan konta: " + this.balance + " PLN");
    }

    public void withdraw(double withdrawAmount) {
        if(this.balance < withdrawAmount) {
            JOptionPane.showMessageDialog(null, "Nie masz odpowiedniej ilości środków na koncie");
        } else {
            this.balance -= withdrawAmount;
            JOptionPane.showMessageDialog(null,"Wypłata: " + withdrawAmount + " PLN\n" +
                    "Stan konta: " + this.balance);
        }

    }

    public void closeAccount() {

    }

    public String toString() {
        JOptionPane.showMessageDialog(null,
                "Nazwa użytkownika: " + this.getName() + "\n" +
                "Identyfikator użytkownika: " + this.getId() + "\n" +
                "Aktualna dostepna ilość środków: " + this.getBalance() + "PLN" );
        return null;
    }
}
