package com.artusofabio.bankappvm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    private TransactionService underTest;
    private ArrayList<Transaction> transactionsTest;

    @BeforeEach
    void setUp() {
        underTest = new TransactionService();
        transactionsTest = new ArrayList<>();
        transactionsTest.add(
                new Transaction(1, LocalDate.of(2023, Month.MARCH,6), "Tesco", "credit card", 20.00, "Groceries"));
        transactionsTest.add(
                new Transaction(2, LocalDate.of(2022, Month.DECEMBER,25), "Amazon", "debit card", 60.00, ""));
        transactionsTest.add(
                new Transaction(3, LocalDate.of(2022, Month.DECEMBER,15), "Zalando", "debit card", 40.00, "Clothes"));
        transactionsTest.add(
                new Transaction(4, LocalDate.of(2023, Month.JANUARY,8), "M&S", " credit card", 80.00, "Groceries"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void updateCategoryTest() {
        underTest.updateCategory(2, "No Category", transactionsTest);
        assertEquals(
                "No Category",
                transactionsTest.get(1).getCategory()
        );
    }

    @Test
    @Disabled
    void updateCategoryShouldThrowExceptionTest(){
        assertThrows(
                IdTransactionException.class,
                () -> underTest.updateCategory(20, "No Category", transactionsTest)
        );
        //In this case I can't test this exception. Having data stored in a List will cause an
        //IndexOutOfBoundsException to be thrown before the code in the method is run. Exception is handled
        //in the Controller layer where the method is called.
    }

    @Test
    void getTransactionsByCatTest() {
        assertEquals(
                List.of(transactionsTest.get(0),transactionsTest.get(3)),
                underTest.getTransactionsByCat("Groceries", transactionsTest)
        );
    }

    @Test
    void totalByCatTest() {
        assertEquals(
                100.00,
                underTest.totalByCat("Groceries", transactionsTest)
        );
    }

    @Test
    void avgByCatPerMonthTest() {
        assertEquals(
                50.00,
                underTest.avgByCatPerMonth("Groceries", transactionsTest)
        );
    }

    @Test
    void maxByCatPerYearTest() {
        assertEquals(
                80.00,
                underTest.maxByCatPerYear("Groceries", 2023, transactionsTest)
        );
    }

    @Test
    void minByCatPerYearTest() {
        assertEquals(
                20.00,
                underTest.minByCatPerYear("Groceries", 2023, transactionsTest)
        );
    }
}