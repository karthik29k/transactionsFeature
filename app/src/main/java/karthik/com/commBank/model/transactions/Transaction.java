package karthik.com.commBank.model.transactions;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("effectiveDate")
    @Expose
    private Date effectiveDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("amount")
    @Expose
    private float amount;
    @SerializedName("atmId")
    @Expose
    private String atmId;

    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public float getAmount() {
        return amount;
    }

    @NonNull
    public String getAtmId() {
        return atmId;
    }

    public boolean isPending() {
        return false;
    }

}
