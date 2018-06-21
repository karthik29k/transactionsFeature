package karthik.com.commBank.model.transactions;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import karthik.com.commBank.model.accounts.Account;
import karthik.com.commBank.model.location.Atm;

public class TransactionData implements Serializable {

    @SerializedName("account")
    @Expose
    private Account account;
    @SerializedName("transactions")
    @Expose
    private List<Transaction> transactions = null;
    @SerializedName("pending")
    @Expose
    private List<PendingTransaction> pending = null;
    @SerializedName("atms")
    @Expose
    private List<Atm> atms = null;

    public Account getAccount() {
        return account;
    }

    public void setAccount(@NonNull Account account) {
        this.account = account;
    }

    @NonNull public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(@NonNull List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<PendingTransaction> getPending() {
        return pending;
    }

    public void setPending(@NonNull List<PendingTransaction> pendingTransaction) {
        this.pending = pendingTransaction;
    }

    @NonNull public List<Atm> getAtms() {
        return atms;
    }

}
