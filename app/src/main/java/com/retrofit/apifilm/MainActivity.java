package com.retrofit.apifilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import com.retrofit.apifilm.Api.BaseUrl;
import com.retrofit.apifilm.Api.RestApiClient;
import com.retrofit.apifilm.Model.FilmMainResponseItem;
import com.retrofit.apifilm.Recycle.RecycleAdapter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
    RecycleAdapter oAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.rcyleView);
        getFilms();
    }

    private void getFilms() {
        Call<List<FilmMainResponseItem>> mainResponseData= new RestApiClient(BaseUrl.baseUrl).getRestApiInterface().getData();
        mainResponseData.enqueue(new Callback<List<FilmMainResponseItem>>() {
            @Override
            public void onResponse(Call<List<FilmMainResponseItem>> call, Response<List<FilmMainResponseItem>> response) {
                List<FilmMainResponseItem> filmler = response.body();

                recyclerView.setLayoutManager(layoutManager);
                oAdapter = new RecycleAdapter(getApplicationContext(),(ArrayList<FilmMainResponseItem>) filmler);
                recyclerView.setAdapter(oAdapter);
/*
                for(int i=0;i<response.body().size();i++){
                Log.i("testt","Name : "+response.body().get(i).getName());
                        Log.i("testt","Image : "+response.body().get(i).getImage().getOriginal());
                            Log.i("testt","Rating : "+String.valueOf(response.body().get(i).getRating().getAverage()));
                                Log.i("testt","Time : "+new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(new Date(response.body().get(i).getUpdated())));
                }
 */
            }

            @Override
            public void onFailure(Call<List<FilmMainResponseItem>> call, Throwable t) {
                Log.i("error",t.toString());
            }
        });
    }
}