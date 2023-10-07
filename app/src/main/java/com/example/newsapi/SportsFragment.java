package com.example.newsapi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsFragment extends Fragment {
    String api="2ee0254a425d4efeb257887d018fd561";
    ArrayList<ModelClass>arrayList;
    Adapter adapter;
    String Country="in";
    private RecyclerView recyclerViewfssports;
    private String Category="Sports";
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.sportsfragment,null);

        recyclerViewfssports=v.findViewById(R.id.sports_fragment);
        arrayList=new ArrayList<>();
        recyclerViewfssports.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new Adapter(getContext(),arrayList);
        recyclerViewfssports.setAdapter(adapter);
        searchView = v.findViewById(R.id.searchView);


        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    filterData(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    return false;
                }
            });
        }

        findNews();

        return v;
    }

    private void findNews()
    {
        APIServices.getAPIInterface().getCategoryNews(Country,Category,api).enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                if(response.isSuccessful()){
                    arrayList.addAll(response.body().getArticles());
                    adapter.setOriginalData(arrayList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {

            }
        });


    }
    private void filterData(String query) {

        adapter.filter(query);
    }
}
