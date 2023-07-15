package com.latihanpbo.aplikasilaundryfix.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.latihanpbo.aplikasilaundryfix.model.nearby.ModelResults;
import com.latihanpbo.aplikasilaundryfix.model.response.ModelResultNearby;
import com.latihanpbo.aplikasilaundryfix.networking.ApiClient;
import com.latihanpbo.aplikasilaundryfix.networking.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    MutableLiveData<ArrayList<ModelResults>> modelResultsMutableLiveData = new MutableLiveData<>();
    public static String strApiKey = "YOUR API KEY";

    public void setMarkerLocation(String strLocation) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ModelResultNearby> call = apiService.getDataResult(strApiKey, "Laundry", strLocation, "distance");
        call.enqueue(new Callback<ModelResultNearby>() {
            @Override
            public void onResponse(Call<ModelResultNearby> call, Response<ModelResultNearby> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    ArrayList<ModelResults> items = new ArrayList<>(response.body().getModelResults());
                    modelResultsMutableLiveData.setValue(items);
                }
            }

            @Override
            public void onFailure(Call<ModelResultNearby> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    public LiveData<ArrayList<ModelResults>> getMarkerLocation() {
        return modelResultsMutableLiveData;
    }

}
