package karthik.com.commBank.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import karthik.com.commBank.model.accounts.ListItemAccount;
import karthik.com.commBank.model.transactions.TransactionData;
import rx.Observable;

public class ListItemGenerator {

    public static List<Object> getListItems(TransactionData transactionsData) {
        if (transactionsData == null) {
            return Collections.emptyList();
        }

        final List<Object> listItems = new ArrayList<>();

        Observable.just(transactionsData)
                .flatMapIterable(transactionData -> transactionData.getTransactions())
                .concatWith(Observable.just(transactionsData)
                        .flatMapIterable(transactionData -> transactionData.getPending()))
                .groupBy(transaction -> transaction.getEffectiveDate())
                .flatMap(dateTransactionGroupedObservable -> dateTransactionGroupedObservable.toList())
                .sorted((transactions1, transactions2) -> transactions2.get(0).getEffectiveDate().compareTo(transactions1.get(0).getEffectiveDate()))
                .onErrorResumeNext(throwable -> Observable.empty())
                .doOnSubscribe(() -> {
                    if (transactionsData.getAccount() != null) {
                        SpendPredictor.predictSpending(transactionsData)
                                .subscribe(projectedSpend -> listItems.add(ListItemAccount.build(transactionsData.getAccount()).projectedSpend(projectedSpend)));
                    }
                })
                .subscribe(transactions -> {
                    listItems.add(transactions.get(0).getEffectiveDate());
                    listItems.addAll(transactions);
                });

        return listItems;
    }
}
