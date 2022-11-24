package com.company;

import java.util.*;

import javax.swing.*;

public class BankAccount {
    private static int uid = 0;
    private final int id;
    private final List<BankAccount> bankAccounts;
    private double balance;
    private String name;

    public BankAccount(String name, int id, double balance) {
        this.name = name;
        this.id = uid++;
        this.balance = balance;
        bankAccounts = new ArrayList<>();
    }

    public String getName() {
        return name;
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

    public int getId() {
        return id;
    }

    public void registerAccount() {
        String accountType = MenuService.showMenuAccountType();
        setBalance(0.0);
        switch (accountType) {
            case "Osobiste":
                this.setName(JOptionPane.showInputDialog("Wprowadź nazwę konta"));
                BankAccount personalAccount = new BankAccount(this.getName(), this.getId(), this.getBalance());
                bankAccounts.add(personalAccount);
                personalAccount.toString();
                break;
            case "Firmowe":
                this.setName(JOptionPane.showInputDialog("Wprowadź nazwę konta"));
                this.setBalance(5000);
                BankAccount_firma companyAccount = new BankAccount_firma(this.getName(), this.getId(), this.getBalance());
                companyAccount.setREGON(JOptionPane.showInputDialog("Wprowadź numer regon"));
                bankAccounts.add(companyAccount);
                companyAccount.toString();
                break;
            case "Międzynarodowe":
                this.setName(JOptionPane.showInputDialog("Wprowadź nazwę konta"));
                BankAccount_INT internationalAccount = new BankAccount_INT(this.getName(), this.getId(), this.getBalance());
                internationalAccount.setOrigin(JOptionPane.showInputDialog("Wprowadź kraj pochodzenia"));
                bankAccounts.add(internationalAccount);
                internationalAccount.toString();
                break;
        }
    }

    public BankAccount isAccountExist(String accountName, int id) {
        Optional<BankAccount> matchingAccount = bankAccounts
                .stream()
                .filter(p ->p.getId() == id && p.getName().equals(accountName))
                .findFirst();

        if (matchingAccount.isPresent()) {
            JOptionPane.showMessageDialog(null, "Poprawnie zalogowano do konta");
            return matchingAccount.get();
        } else {
            JOptionPane.showMessageDialog(null, "Nie zanaleziono konta o danym loginie");
            return null;
        }
    }

    public void manageLoggedAccount() {
        String accountName = JOptionPane.showInputDialog("Wprowadź nazwę konta");
        int accountId = Integer.parseInt(JOptionPane.showInputDialog("Wprowadź numer id konta"));
        BankAccount account = isAccountExist(accountName, accountId);
        boolean exitRequested = false;
        while (account != null && !exitRequested) {
            String selectedOption = MenuService.showActionMenu();
            switch (selectedOption) {
                case "Wpłata środków":
                    deposit(account, Double.parseDouble(JOptionPane.showInputDialog("Wprowadź kwotę wpłacanych środków")));
                    break;
                case "Wypłata środków":
                    withdraw(account, Double.parseDouble(JOptionPane.showInputDialog("Wprowadź kwotę wypłacanych środków")));
                    break;
                case "Zamknięcie konta":
                    exitRequested = closeAccount(account);
                    break;
                case "Wylogowanie":
                    exitRequested = true;
                    JOptionPane.showMessageDialog(null, "Nastąpiło poprawne wylogowanie");
                    break;
                case "Informacje o stanie konta":
                    info(account);
                    break;
            }
        }
    }

    public void info(BankAccount account) {
        account.toString();
    }

    public void deposit(BankAccount account, double depositAmount) {
        account.balance += depositAmount;
        account.setBalance(account.balance);
        JOptionPane.showMessageDialog(null, "Wpływ: " + depositAmount + " PLN\n" +
                "Stan konta: " + account.balance + " PLN");
    }

    public void withdraw(BankAccount account, double withdrawAmount) {
        if (account.balance <= withdrawAmount) {
            JOptionPane.showMessageDialog(null, "Nie masz odpowiedniej ilości środków na koncie");
        } else {
            account.balance -= withdrawAmount;
            JOptionPane.showMessageDialog(null, "Wypłata: " + withdrawAmount + " PLN\n" +
                    "Stan konta: " + account.balance + " PLN");
        }

    }

    public boolean closeAccount(BankAccount account) {
        int value = JOptionPane.showConfirmDialog(null, "Czy aby na pewno chcesz zamknąć konto?", "System bankowy", JOptionPane.YES_NO_CANCEL_OPTION);
        switch (value) {
            case 0:
                JOptionPane.showMessageDialog(null, "Usunięto konto " + account.getName() + " o id " + account.id);
                bankAccounts.remove(account);
                JOptionPane.showMessageDialog(null, "Nastąpiło automatyczne wylogowanie");
                break;
            case 1:
            case 2:
                JOptionPane.showMessageDialog(null, "Powrót to poprzedniego menu");
                return false;
        }
        return true;
    }

    public String toString() {
        JOptionPane.showMessageDialog(null,
                "Nazwa użytkownika: " + this.getName() + "\n" +
                        "Identyfikator użytkownika: " + this.getId() + "\n" +
                        "Aktualna dostepna ilość środków: " + this.getBalance() + "PLN");
        return null;
    }
}
