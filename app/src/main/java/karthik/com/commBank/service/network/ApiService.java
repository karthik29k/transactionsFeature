package karthik.com.commBank.service.network;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import karthik.com.commBank.service.exception.NoConnectivityException;
import karthik.com.commBank.service.exception.UnknownException;
import karthik.com.commBank.utils.ConnectivityUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiService<T> implements Callback<T> {

    private Call<T> call;
    private ConnectivityUtils connectivityUtils;

    public ApiService(ConnectivityUtils connectivityUtils) {
        this.connectivityUtils = connectivityUtils;
    }

    public void runService() {

        call = null;

        if (!connectivityUtils.isNetworkConnectionAvailable()) {
            handleFailure(new NoConnectivityException());
        }
        call = getCall();

        if (call == null) {
            handleFailure(new UnknownException());
            return;
        }
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (call.isCanceled()) {
            handleFailure(null);
        } else if (!response.isSuccessful()) {
            handleFailure(new UnknownException());
        } else {
            handleSuccess(response.body());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (call.isCanceled()) {
            handleFailure(null);
        } else if (t instanceof IOException) {
            handleFailure(new TimeoutException());
        } else {
            handleFailure(new UnknownException());
        }
    }

    public void cancel() {
        if (call != null && call.isExecuted() && !call.isCanceled()) {
            call.cancel();
        }
    }

    protected abstract Call<T> getCall();

    protected abstract void handleSuccess(T t);

    protected abstract void handleFailure(Throwable t);
}
