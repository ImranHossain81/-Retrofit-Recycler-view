package com.example.testretrofit_recyclerppt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterAuthor adapterAuthor;
    ProgressBar progressBar;
    ArrayList<ModelData> arrayList=new ArrayList<>();
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        progressBar=findViewById(R.id.progressBar);

        Retrofit instance = ApiClientfile.getClient();
        apiInterface = instance.create(ApiInterface.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapterAuthor=new AdapterAuthor(arrayList,MainActivity.this);


        apiInterface.getData().enqueue(new Callback<List<ModelData>>() {
            @Override
            public void onResponse(Call<List<ModelData>> call, Response<List<ModelData>> response) {

                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                arrayList.addAll(response.body());
                recyclerView.setAdapter(adapterAuthor);
                adapterAuthor.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ModelData>> call, Throwable t) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Something wrong !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}