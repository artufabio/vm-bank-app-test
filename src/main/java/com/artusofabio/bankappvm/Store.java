package com.artusofabio.bankappvm;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class Store {

    List<Transaction> allTransactions;

    public Store() {
        allTransactions = new ArrayList<>();
        allTransactions.add(
                new Transaction(1, LocalDate.of(2023, Month.JANUARY,16), "Tesco", "credit card", 50.40, "Groceries"));
        allTransactions.add(
                new Transaction(2, LocalDate.of(2023, Month.JANUARY,25), "Amazon", "debit card", 65.00, ""));
        allTransactions.add(
                new Transaction(3, LocalDate.of(2023, Month.JANUARY,15), "Zalando", "debit card", 12.10, "Clothes"));
        allTransactions.add(
                new Transaction(4, LocalDate.of(2022, Month.MARCH,8), "M&S", " credit card", 20.50, "House"));
        allTransactions.add(
                new Transaction(5, LocalDate.of(2022, Month.DECEMBER,24), "Adidas", "debit card", 49.90, "Clothes"));
        allTransactions.add(
                new Transaction(6, LocalDate.of(2021, Month.NOVEMBER,1), "Tesco", "credit card", 20.00, "Groceries"));
        allTransactions.add(
                new Transaction(7, LocalDate.of(2023, Month.MAY,6), "Tesco", "credit card", 14.10, "Groceries"));
        allTransactions.add(
                new Transaction(8, LocalDate.of(2023, Month.MAY,2), "Sky", "direct debit", 70.00, ""));
        allTransactions.add(
                new Transaction(9, LocalDate.of(2021, Month.MARCH,15), "M&S", "debit card", 6.10, "House"));
        allTransactions.add(
                new Transaction(10, LocalDate.of(2021, Month.MARCH,18), "M&S", " credit card", 24.50, "House"));
        allTransactions.add(
                new Transaction(11, LocalDate.of(2021, Month.NOVEMBER,21), "Better", "direct debit", 45.90, "Gym"));
        allTransactions.add(
                new Transaction(12, LocalDate.of(2023, Month.NOVEMBER,14), "Tesco", "credit card", 20, "Groceries"));
        allTransactions.add(
                new Transaction(13, LocalDate.of(2022, Month.MARCH,13), "Tesco", "credit card", 90.00, "Groceries"));
        allTransactions.add(
                new Transaction(14, LocalDate.of(2022, Month.JANUARY,23), "Amazon", "debit card", 59.00, "House"));
        allTransactions.add(
                new Transaction(15, LocalDate.of(2022, Month.MAY,15), "Zalando", "debit card", 12.80, "Clothes"));
        allTransactions.add(
                new Transaction(16, LocalDate.of(2021, Month.JANUARY,8), "M&S", " credit card", 20.50, "House"));
        allTransactions.add(
                new Transaction(17, LocalDate.of(2021, Month.NOVEMBER,24), "Better", "direct debit", 40.50, "Gym"));
        allTransactions.add(
                new Transaction(18, LocalDate.of(2021, Month.NOVEMBER,16), "Tesco", "credit card", 30.00, "Groceries"));
    }
}
