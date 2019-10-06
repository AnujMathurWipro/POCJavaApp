package com.example.pocjavaapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pocjavaapp.models.BaseResult;
import com.example.pocjavaapp.models.Response;
import com.example.pocjavaapp.repository.Repository;

public class MainScreenViewModel extends ViewModel {

    private MutableLiveData<BaseResult<Response>> result = new MutableLiveData<>();
    private Repository repository = new Repository();
    public LiveData<BaseResult<Response>> getResult() {
        return result;
    }

    public void getResponse(boolean force) {
        if(force || result.getValue() == null)
            repository.getMainScreenList(result);
    }

    public String getErrorMessage() {
        return "There was some problem with the request. Please try again.";
    }

    public boolean isSuccessful(Response res) {
        return res.getRows() != null && res.getRows().size() > 0;
    }
}
