package karthik.com.commBank.utils;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import karthik.com.commBank.model.transactions.TransactionData;
import rx.Observable;
import rx.functions.Func1;

public class SpendPredictor {

    public static Observable<Float> predictSpending(TransactionData transactionsData) {

        if (transactionsData == null) {
            return null;
        }

        return getFortnightSpendingMap(transactionsData)
                .flatMap(spendingMap -> Observable.from(spendingMap.values())
                        .reduce((total, fortnight) -> total + fortnight)
                        .flatMap(total -> {
                            Long max = Collections.max(spendingMap.keySet());
                            return Observable.just(Math.abs(total / (max + 1)));
                        })
                )
                .onErrorResumeNext(throwable -> Observable.empty());
    }

    private static Observable<Map<Long, Float>> getFortnightSpendingMap(TransactionData transactionsData) {
        Date today = new Date();

        return Observable.just(transactionsData)
                .flatMapIterable(transactionData -> transactionData.getTransactions())
                .concatWith(Observable.just(transactionsData)
                        .flatMapIterable(transactionData -> transactionData.getPending()))
                .filter(transaction -> transaction.getAmount() < 0)
                .groupBy(transaction -> {
                    long diff = ViewUtils.numberOfDaysBetweenDates(transaction.getEffectiveDate(), today);
                    long week = diff / 14;
                    return week;
                })
                .flatMap(group -> group.toList()
                        .flatMapIterable(transactionList -> transactionList)
                        .map(transaction -> transaction.getAmount())
                        .reduce((total, amount) -> total + amount)
                        .map((Func1<Float, Map.Entry<Long, Float>>) totalAmount -> new AbstractMap.SimpleImmutableEntry<Long, Float>(group.getKey(), totalAmount)))
                .toMap(totalAmountListEntry -> totalAmountListEntry.getKey(), totalAmountListEntry -> totalAmountListEntry.getValue())
                .onErrorResumeNext(throwable -> Observable.empty());
    }
}
