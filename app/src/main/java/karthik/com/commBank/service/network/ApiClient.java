package karthik.com.commBank.service.network;

import karthik.com.commBank.model.transactions.TransactionData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {

    @GET("s/tewg9b71x0wrou9/data.json?dl=1")
    public Call<TransactionData> getTransactions();
}
