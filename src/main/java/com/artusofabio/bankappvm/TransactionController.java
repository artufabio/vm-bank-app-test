package com.artusofabio.bankappvm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/allTransactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    Store store = new Store();

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return new ResponseEntity<>(store.allTransactions, HttpStatus.OK);
    }

    // Assign or update transaction category selected by transactionId
    // I had to use try/catch because data are stored in a List. With a database I would have handled the Exception like in the
    // updateCategory() method in the service layer with a custom exception
    @PutMapping("/update-category/{transactionId}")
    public ResponseEntity<String> updateCategory(@PathVariable("transactionId") Integer trId, @RequestBody String category){
        try{
            transactionService.updateCategory(trId, category, store.allTransactions);
            return new ResponseEntity<>(
                    "The transaction with id " + trId + " has now category of " + category + ".",
                    HttpStatus.ACCEPTED);
        }
        catch (IndexOutOfBoundsException e) {
            e.getMessage();
            return new ResponseEntity<>(
                    "The transaction with id " + trId + " doesn't exist.",
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // 1.	All transactions for a given category - latest first
    @GetMapping("/{category}")
    public ResponseEntity<List<Transaction>> getTransactionsByCategory(@PathVariable("category") String category){
        return new ResponseEntity<>(transactionService.getTransactionsByCat(category, store.allTransactions), HttpStatus.OK);
    }

    // 2. total outgoing per category
    @GetMapping("/{category}/total")
    public ResponseEntity<Double> getTotalByCategory(@PathVariable("category") String category){
        return new ResponseEntity<>(transactionService.totalByCat(category, store.allTransactions), HttpStatus.OK);
    }

    //3. Monthly average spent in a given category
    @GetMapping("/{category}/avg-month")
    public ResponseEntity<Double> getAvgByCategoryPerMonth(@PathVariable("category") String category){
        return new ResponseEntity<>(transactionService.avgByCatPerMonth(category, store.allTransactions), HttpStatus.OK);
    }

    // 4. Highest spent in a given category, for a given year
    @GetMapping("/{category}/{year}/max")
    public ResponseEntity<Double> getMaxByCategoryPerYear(@PathVariable("category") String category, @PathVariable("year") int year){
        return new ResponseEntity<>(transactionService.maxByCatPerYear(category, year, store.allTransactions), HttpStatus.OK);
    }

    // 5. Lowest spent in a given category, for a given year
    @GetMapping("/{category}/{year}/min")
    public ResponseEntity<Double> getMinByCategoryPerYear(@PathVariable("category") String category, @PathVariable("year") int year){
        return new ResponseEntity<>(transactionService.minByCatPerYear(category, year, store.allTransactions), HttpStatus.OK);
    }


}
