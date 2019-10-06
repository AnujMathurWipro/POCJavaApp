package com.example.pocjavaapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.pocjavaapp.mainscreen.FetchListService;
import com.example.pocjavaapp.models.BaseResult;
import com.example.pocjavaapp.models.Response;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    public void getMainScreenList(MutableLiveData<BaseResult<Response>> liveData) {
        Retrofit retrofit = createRetrofit("https://dl.dropboxusercontent.com");
        FetchListService service = retrofit.create(FetchListService.class);
        Call<Response> call = service.getList();
        getResponseFromServer(call, liveData);
    }

    private <T> void getResponseFromServer( Call<T> call,
    MutableLiveData<BaseResult<T>> liveData) {
        new Thread() {
            @Override
            public void run() {
                try {
                    handleResponse(call.execute(), liveData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private <T> void handleResponse(retrofit2.Response<T> result,
            MutableLiveData<BaseResult<T>> liveData) {

        BaseResult value = new BaseResult<T>();
        if (result.isSuccessful()) {
            T res = result.body();
            value.setResponse(res);
        }
        liveData.postValue(value);
    }

    private Retrofit createRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
