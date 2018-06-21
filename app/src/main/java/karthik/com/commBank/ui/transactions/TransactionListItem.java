package karthik.com.commBank.ui.transactions;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;
import static karthik.com.commBank.ui.transactions.TransactionListItem.ListItemType.TYPE_ACCOUNT_DETAILS;
import static karthik.com.commBank.ui.transactions.TransactionListItem.ListItemType.TYPE_TRANSACTION;
import static karthik.com.commBank.ui.transactions.TransactionListItem.ListItemType.TYPE_TRANSACTION_GROUP;

interface TransactionListItem {

    @Retention(SOURCE)
    @IntDef({
            TYPE_ACCOUNT_DETAILS,
            TYPE_TRANSACTION_GROUP,
            TYPE_TRANSACTION
    })
    public @interface ListItemType {
        int TYPE_ACCOUNT_DETAILS = 0;
        int TYPE_TRANSACTION_GROUP = 1;
        int TYPE_TRANSACTION = 2;
    }
}
