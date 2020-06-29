package com.example.barbershop.client.viewmodels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.barbershop.client.http.SearchedBarbersHttp;
import com.example.barbershop.models.Barber;
import java.util.ArrayList;
import java.util.List;

public class SearchedBarbersViewModel extends AndroidViewModel {

    private MutableLiveData<List<Barber>> data;

    public SearchedBarbersViewModel(@NonNull Application application) { super(application); }

    public LiveData<List<Barber>> getData()
    {
        if (data == null)
        {
            data = new MutableLiveData<>();
            loadData();
        }
        return data;
    }

    @SuppressLint("StaticFieldLeak")
    private void loadData()
    {
        new AsyncTask<Void,Void,List<Barber>>() {
            @Override
            protected List<Barber> doInBackground(Void... voids) {
                List<Barber> searchResult = new ArrayList<>();
                SearchedBarbersHttp httpClient = new SearchedBarbersHttp(getApplication());
                searchResult = httpClient.getSearchedBarbers();
                return searchResult;
            }

            @Override
            protected void onPostExecute(List<Barber> searchResult) {
                data.setValue(searchResult);
            }
        }.execute();
    }
}
