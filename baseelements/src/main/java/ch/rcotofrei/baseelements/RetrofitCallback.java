package ch.rcotofrei.baseelements;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import java.io.Serializable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RetrofitCallback<S> implements Callback<S> {

    private CallbackHelper callbackHelper;

    public RetrofitCallback(Handler.Callback callback) {
        callbackHelper = new CallbackHelper(callback);
    }

    @Override
    public void onResponse(@NonNull Call<S> call, @NonNull Response<S> response) {
        addDefaultValues(response);

        if (response.isSuccessful()) {
            onSuccess(call, response);
        } else {
            onNotSuccess(call, response);
        }

        callbackHelper.send();
    }

    private void addDefaultValues(@NonNull Response<S> response) {
        addData(BaseConstants.RESPONSE_SUCCESSFUL, response.isSuccessful());
        addData(BaseConstants.RESPONSE_CODE_STATUS, response.code());
    }

    @Override
    public void onFailure(@NonNull Call<S> call, @NonNull Throwable t) {

    }

    private void onSuccess(@NonNull Call<S> call, @NonNull Response<S> response) {
        addData(BaseConstants.RESPONSE_BODY, response.body());
    }

    private void onNotSuccess(@NonNull Call<S> call, @NonNull Response<S> response) {

    }

    private <K> void addData(String key, K value) {
        Bundle bundle = callbackHelper.getBundle();
        if (value instanceof String) {
            bundle.putString(key, (String) value);
        } else if (value instanceof Boolean) {
            bundle.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            bundle.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            bundle.putFloat(key, (Float) value);
        } else if (value instanceof Double) {
            bundle.putDouble(key, (Double) value);
        } else if (value instanceof Serializable) {
            bundle.putSerializable(key, (Serializable) value);
        } else {
            throw new ClassCastException("Type " + value.getClass().getName() + " is not supported yet");
        }
    }
}
