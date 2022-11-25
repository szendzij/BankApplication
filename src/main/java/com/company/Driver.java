package com.company;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class Driver extends BankAccount {
    public static ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public static void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        Driver.bankAccounts = bankAccounts;
    }

    static ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    static Driver driver = new Driver();

    public static void main(String[] args)  {
        JOptionPane.showMessageDialog(null, "Witaj w aplikacji bankowej");
        boolean exitRequested = false;
        while (!exitRequested) {
            switch (MenuService.showMenu()) {
                case "Rejestracja nowego konta":
                    driver.registerAccount();
                    break;
                case "Zalogowanie się do istniejącego konta":
                    driver.manageLoggedAccount();
                    break;
                case "Zapis danych do pliku":
                    driver.saveAccountsToFile(bankAccounts);
                    break;
                case "Pobranie danych z pliku":
                    driver.readAccountsFromFile(bankAccounts);
                    break;
                case "Exit":
                    JOptionPane.showMessageDialog(null, "Zamknięcie programu");
                    exitRequested = true;
                    break;
            }
        }
        System.exit(0);
    }

    public void registerAccount() {
        String accountType = MenuService.showMenuAccountType();
        driver.setBalance(0.0);
        switch (accountType) {
            case "Osobiste":
                BankAccount personalAccount = new BankAccount();
                personalAccount.setName(JOptionPane.showInputDialog("Wprowadź nazwę konta"));
                bankAccounts.add(personalAccount);
                personalAccount.idRandomizer(personalAccount);
                driver.info(personalAccount);
                break;
            case "Firmowe":
                BankAccount_firma companyAccount = new BankAccount_firma();
                companyAccount.setName(JOptionPane.showInputDialog("Wprowadź nazwę konta"));
                companyAccount.setBalance(5000);
                companyAccount.setREGON(JOptionPane.showInputDialog("Wprowadź numer regon"));
                companyAccount.idRandomizer(companyAccount);
                bankAccounts.add(companyAccount);
                driver.info(companyAccount);
                break;
            case "Międzynarodowe":
                BankAccount_INT internationalAccount = new BankAccount_INT();
                internationalAccount.setName(JOptionPane.showInputDialog("Wprowadź nazwę konta"));
                internationalAccount.setOrigin(JOptionPane.showInputDialog("Wprowadź kraj pochodzenia"));
                internationalAccount.idRandomizer(internationalAccount);
                bankAccounts.add(internationalAccount);
                driver.info(internationalAccount);
                break;
        }
    }

    public void saveAccountsToFile(ArrayList accountList) {
        try {
            FileOutputStream writeData = new FileOutputStream(new File("src/main/resources/accounts.txt"));
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(getBankAccounts());
            writeStream.close();
            writeData.close();

            JOptionPane.showMessageDialog(null, "Dane zostały poprawnie zapisane dp pliku");

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Problem z pikiem");
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void readAccountsFromFile(ArrayList accountList) {
        try {
            FileInputStream readData = new FileInputStream("src/main/resources/accounts.txt");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            setBankAccounts((ArrayList) readStream.readObject());

            JOptionPane.showMessageDialog(null, "Pobrano następujące dane:");
            getBankAccounts().forEach(this::info);

            readStream.close();
            readData.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Problem z pikiem");
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public BankAccount isAccountExist(String accountName, String id) {
        Optional<BankAccount> matchingAccount = bankAccounts.stream().filter(p -> p.getId().equals(id) && p.getName().equals(accountName)).findFirst();
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
        String accountId = JOptionPane.showInputDialog("Wprowadź unikalny identyfikator konta");
        BankAccount account = isAccountExist(accountName, accountId);
        boolean exitRequested = false;
        while (account != null && !exitRequested) {
            String selectedOption = MenuService.showActionMenu();
            switch (selectedOption) {
                case "Wpłata środków":
                    account.deposit(account, Double.parseDouble(JOptionPane.showInputDialog("Wprowadź kwotę wpłacanych środków")));
                    break;
                case "Wypłata środków":
                    account.withdraw(account, Double.parseDouble(JOptionPane.showInputDialog("Wprowadź kwotę wypłacanych środków")));
                    break;
                case "Zamknięcie konta":
                    exitRequested = closeAccount(account);
                    break;
                case "Wylogowanie":
                    exitRequested = true;
                    JOptionPane.showMessageDialog(null, "Nastąpiło poprawne wylogowanie");
                    break;
                case "Informacje o stanie konta":
                    driver.info(account);
                    break;
            }
        }
    }

    public boolean closeAccount(BankAccount account) {
        int value = JOptionPane.showConfirmDialog(null, "Czy aby na pewno chcesz zamknąć konto?", "System bankowy", JOptionPane.YES_NO_CANCEL_OPTION);
        switch (value) {
            case 0:
                JOptionPane.showMessageDialog(null, "Usunięto konto " + account.getName() + " o id " + account.getId());
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
}
