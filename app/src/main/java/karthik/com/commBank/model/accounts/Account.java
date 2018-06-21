package karthik.com.commBank.model.accounts;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Account implements Serializable {

    @SerializedName("accountName")
    @Expose
    private String accountName;
    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("available")
    @Expose
    private float available;
    @SerializedName("balance")
    @Expose
    private float balance;

    @NonNull
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(@NonNull String accountName) {
        this.accountName = accountName;
    }

    @NonNull
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NonNull String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getAvailable() {
        return available;
    }

    public void setAvailable(float available) {
        this.available = available;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

}
