package com.artusofabio.bankappvm;

import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    //update or assign category to transaction selected by Id
    public void updateCategory(Integer trId, String category, List<Transaction> transactions) {
        Optional<Transaction> transaction = Optional.of(transactions.get(trId - 1)); //trId - 1 because indexing of the list starts from 0
        if (transaction.isEmpty()){
            throw new IdTransactionException("The transaction with id " + trId + " doesn't exist.");
        } else {
            transaction.get().setCategory(category);
        }
    }

    // 1.	All transactions for a given category - latest first1
    public List<Transaction> getTransactionsByCat(String cat,List<Transaction> transactions){
        return Optional.of(transactions.stream()
                .filter(transaction -> transaction.getCategory().equalsIgnoreCase(cat))
                .sorted(Comparator.comparing(Transaction::getTransactionDate).reversed())   //sorting by date of transaction
                .collect(Collectors.toList()))
                .orElseThrow(NoSuchElementException::new);
    }

    // 2. total outgoing per category
    public double totalByCat(String cat,List<Transaction> transactions){
        return Optional.of(transactions.stream()
                .filter(transaction -> transaction.getCategory().equalsIgnoreCase(cat))
                .reduce(0.0, (acc, tr) -> acc + tr.getAmount(), Double::sum))
                .orElseGet(()-> 0.00);
    }

    //3. Monthly average spent in a given category
    public Double avgByCatPerMonth(String cat, List<Transaction> transactions) {
        List<Transaction> listTrans = getTransactionsByCat(cat,transactions); //filters by category and ordered by date reversed
        if (listTrans.isEmpty()){
            return 0.0;
        }
        Double totalAmount = listTrans.stream()
                .reduce(0.0, (acc, tr) -> acc + tr.getAmount(), Double::sum);

        long numberOfMonths = ChronoUnit.MONTHS.between(        //calc numb of months between first and last transaction
                YearMonth.from(listTrans.get(listTrans.size()-1).getTransactionDate()),
                YearMonth.from(listTrans.get(0).getTransactionDate()));

        if (numberOfMonths == 0 ){
            if (totalAmount > 0.0){
                numberOfMonths = 1;     //in this case there are no enough days to make 1 month between first and last transaction. I decided to calculate it anyway, knowing that at least one transaction has happened(totalAmount>0)
                return totalAmount/numberOfMonths;}
            else {
                return 0.0;
            }
        }
        return totalAmount/numberOfMonths;
    }

    // 4. Highest spent in a given category, for a given year
    public double maxByCatPerYear(String cat, int year, List<Transaction> transactions) {
        Optional<Transaction> transaction = transactions.stream()
                .filter(tr -> tr.getCategory().equalsIgnoreCase(cat) && tr.getTransactionDate().getYear() == year)
                .max(Comparator.comparing(Transaction::getAmount));
        if (transaction.isPresent()) {
            return transaction.get().getAmount();
        } else {
            return 0.0;     //if value is empty I return a 0.0 default value
        }
    }

    // 5. Lowest spent in a given category, for a given year
    public double minByCatPerYear(String cat, int year, List<Transaction> transactions) {
        Optional<Transaction> transaction = transactions.stream()
                .filter( tr -> tr.getCategory().equalsIgnoreCase(cat) && tr.getTransactionDate().getYear() == year)
                .min(Comparator.comparing(Transaction::getAmount));

        if (transaction.isPresent()) {
            return transaction.get().getAmount();
        } else {
            return 0.0;     //if value is empty I return a 0.0 default value
        }
    }

}
