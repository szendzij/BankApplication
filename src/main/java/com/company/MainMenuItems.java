package com.company;

public enum MainMenuItems {
    NEW_ACCOUNT("Rejestracja nowego konta"),
    LOGIN_TO_ACCOUNT("Zalogowanie się do istniejącego konta"),
    EXPORT_TO_FILE("Zapis danych do pliku"),
    IMPORT_FROM_FILE("Pobranie danych z pliku"),
    EXIT("Exit");

    public final String label;

    MainMenuItems(String label) {
        this.label = label;
    }

    MainMenuItems[] itemsList = MainMenuItems.values();
}
