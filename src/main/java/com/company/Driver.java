package com.company;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Driver extends BankAccount {
    private static final Driver driver = new Driver();

    private static ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    private static ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public static void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        Driver.bankAccounts = bankAccounts;
    }

    public static void main(String[] args) {
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
                    driver.saveAccountsToFile();
                    break;
                case "Pobranie danych z pliku":
                    driver.readAccountsFromFile();
                    break;
                case "Exit":
                    JOptionPane.showMessageDialog(null, "Zamknięcie programu");
                    exitRequested = true;
                    break;
            }
        }
        System.exit(0);
    }

    public Boolean checkIfAccountTypeIsCovid() {
        boolean hasDateFormat = false;
        LocalDate dateOfOpenAccount = null;
        LocalDate startCovidDate = LocalDate.of(2020, 04, 01);
        LocalDate endCovidDate = LocalDate.of(2022, 01, 01);
        while (!hasDateFormat) {
            String enteredDate = validateEnteredString("Wprowadź datę otwarcia konta w podanym formacie (YYYY-MM-DD)");
            if (isDateValid(enteredDate)) {
                dateOfOpenAccount = LocalDate.parse(enteredDate);
                hasDateFormat = true;
            }
        }
        return !(dateOfOpenAccount.isAfter(startCovidDate) && dateOfOpenAccount.isBefore(endCovidDate));

    }

    public void registerAccount() {
        String accountType = MenuService.showMenuAccountType();
        driver.setBalance(0.0);
        switch (accountType) {
            case "Osobiste":
                if (checkIfAccountTypeIsCovid()) {
                    BankAccount personalAccount = new BankAccount();
                    personalAccount.setName(validateEnteredString("Wprowadź nazwę konta"));
                    personalAccount.setIsCovid(false);
                    bankAccounts.add(personalAccount);
                    personalAccount.idRandomizer(personalAccount);
                    driver.info(personalAccount);
                    break;
                } else {
                    BankAccount_COVID19 personalAccountCOVID19 = new BankAccount_COVID19();
                    personalAccountCOVID19.setName(validateEnteredString("Wprowadź nazwę konta"));
                    bankAccounts.add(personalAccountCOVID19);
                    personalAccountCOVID19.idRandomizer(personalAccountCOVID19);
                    driver.info(personalAccountCOVID19);
                    break;
                }

            case "Firmowe":
                if (checkIfAccountTypeIsCovid()) {
                    BankAccount_firma companyAccount = new BankAccount_firma();
                    companyAccount.setName(validateEnteredString("Wprowadź nazwę konta"));
                    companyAccount.setBalance(5000);
                    companyAccount.setREGON(validateEnteredString("Wprowadź numer regon"));
                    bankAccounts.add(companyAccount);
                    info(companyAccount);
                    break;
                } else {
                    BankAccount_COVID19_firma companyAccountCOVID19 = new BankAccount_COVID19_firma();
                    companyAccountCOVID19.setName(validateEnteredString("Wprowadź nazwę konta"));
                    companyAccountCOVID19.setBalance(5000);
                    companyAccountCOVID19.setREGON(validateEnteredString("Wprowadź numer regon"));
                    companyAccountCOVID19.setIsCovid(false);
                    bankAccounts.add(companyAccountCOVID19);
                    info(companyAccountCOVID19);
                    break;
                }
            case "Międzynarodowe":
                BankAccount_INT internationalAccount = new BankAccount_INT();
                internationalAccount.setName(validateEnteredString("Wprowadź nazwę konta"));
                internationalAccount.setOrigin(validateEnteredString("Wprowadź kraj pochodzenia"));
                internationalAccount.idRandomizer(internationalAccount);
                bankAccounts.add(internationalAccount);
                info(internationalAccount);
                break;
        }
    }

    public void saveAccountsToFile() {
        try {
            FileOutputStream writeData = new FileOutputStream(new File("src/main/resources/accounts.txt"));
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(getBankAccounts());
            writeStream.close();
            writeData.close();

            JOptionPane.showMessageDialog(null, "Dane zostały poprawnie zapisane dp pliku");

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Problem z plikiem");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readAccountsFromFile() {
        try {
            FileInputStream readData = new FileInputStream("src/main/resources/accounts.txt");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            setBankAccounts((ArrayList<BankAccount>) readStream.readObject());

            JOptionPane.showMessageDialog(null, "Pobrano następujące dane:");
            getBankAccounts().forEach(this::info);

            readStream.close();
            readData.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Problem z plikiem");
        } catch (IOException | ClassNotFoundException e) {

            JOptionPane.showMessageDialog(null,"Brak danych zapisanych w pliku");
        }
    }

    public BankAccount isAccountExist(String accountName) {
        Optional<BankAccount> matchingAccount = bankAccounts.stream().filter(p -> p.getName().equals(accountName)).findFirst();
        if (matchingAccount.isPresent()) {
            JOptionPane.showMessageDialog(null, "Poprawnie zalogowano do konta");
            return matchingAccount.get();
        } else {
            JOptionPane.showMessageDialog(null, "Nie zanaleziono konta o danym loginie");
            return null;
        }
    }

    public void manageLoggedAccount() {
        String accountName = validateEnteredString("Wprowadź nazwę konta");
        BankAccount account = isAccountExist(accountName);
        boolean exitRequested = false;
        while (account != null && !exitRequested) {
            String selectedOption = MenuService.showActionMenu();
            switch (selectedOption) {
                case "Wpłata środków":
                    account.deposit(account, Double.parseDouble(validateEnteredString("Wprowadź kwotę wpłacanych środków")));
                    break;
                case "Wypłata środków":
                    account.withdraw(account, Double.parseDouble(validateEnteredString("Wprowadź kwotę wypłacanych środków")));
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
        if (!getIsCovid()) {
            int value = JOptionPane.showConfirmDialog(null, "Czy aby na pewno chcesz zamknąć konto?", "System bankowy", JOptionPane.YES_NO_OPTION);
            switch (value) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Usunięto konto " + account.getName() + " o id " + account.getId());
                    bankAccounts.remove(account);
                    JOptionPane.showMessageDialog(null, "Nastąpiło automatyczne wylogowanie");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Powrót to poprzedniego menu");
                    return false;
            }
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Podane konto nie może być zamknięte");
            return false;
        }
    }

    public String validateEnteredString(String dialogInput) {
        boolean isValueOk = false;
        String value = null;
        while (!isValueOk) {
            value = JOptionPane.showInputDialog(dialogInput);
            if (value == null || value.length() == 0) {
                JOptionPane.showMessageDialog(null, "Podano pustą wartość");
            } else if (value.length() < 3) {
                JOptionPane.showMessageDialog(null, "Wprowadzona wartość ma mniej niż 2 znaki");
            } else {
                isValueOk = true;
            }
        }
        return value;
    }


}
