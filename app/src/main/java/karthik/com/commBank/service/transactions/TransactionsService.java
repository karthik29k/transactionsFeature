package karthik.com.commBank.service.transactions;

import android.support.annotation.NonNull;

import karthik.com.commBank.model.transactions.TransactionData;
import karthik.com.commBank.service.network.ApiClient;
import karthik.com.commBank.service.network.ApiClientGenerator;
import karthik.com.commBank.service.network.ApiService;
import karthik.com.commBank.service.network.GsonFactory;
import karthik.com.commBank.utils.ConnectivityUtils;
import okhttp3.OkHttpClient;
import retrofit2.Call;

public class TransactionsService extends ApiService<TransactionData> {

    private final GetTransactionsResponseCallback callback;

    public TransactionsService(ConnectivityUtils connectivityUtils, GetTransactionsResponseCallback callback) {
        super(connectivityUtils);
        this.callback = callback;
    }

    @Override
    protected Call<TransactionData> getCall() {
        return new ApiClientGenerator().createService(new OkHttpClient(), GsonFactory.getGsonInstance(), ApiClient.class).getTransactions();
    }

    @Override
    protected void handleSuccess(@NonNull TransactionData transactionData) {
        callback.onResponse(transactionData);
    }

    @Override
    protected void handleFailure(Throwable t) {
        callback.onFailed(t);
    }

    public interface GetTransactionsResponseCallback<T> {
        void onResponse(T transactionsData);

        void onFailed(Throwable t);
    }
}
