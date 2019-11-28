package com.example.amitvikram.worldwidenews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amitvikram.worldwidenews.api.ApiClient;
import com.example.amitvikram.worldwidenews.api.ApiInterface;
import com.example.amitvikram.worldwidenews.models.Article;
import com.example.amitvikram.worldwidenews.models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Entertainment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    public static final String API_KEY = "d7c4bc6cf53e4904b6def07e18344f06";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles =  new ArrayList<>();
    private Adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences;
    private String country;
    private RelativeLayout errorLayout;
    private ImageView errorImage;
    private TextView errorTitle, errorMessage;
    private Button btnRetry;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_entertainment, container, false);
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        swipeRefreshLayout = rootView.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        onLoadingSwipeRefresh();
        errorLayout = rootView.findViewById(R.id.errorLayout);
        errorImage = rootView.findViewById(R.id.errorImage);
        errorTitle = rootView.findViewById(R.id.errorTitle);
        errorMessage = rootView.findViewById(R.id.errorMessage);
        btnRetry = rootView.findViewById(R.id.btnRetry);
        recyclerView = rootView.findViewById(R.id.recycler_View);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        
        return rootView;
    }

    public void loadData() {
        String countryName = sharedPreferences.getString("countries", "");
        switch (countryName) {
            case "Argentina":
                country = "ar";
                break;
            case "Australia":
                country = "au";
                break;
            case "Austria":
                country = "at";
                break;
            case "Belgium":
                country = "be";
                break;
            case "Brazil":
                country = "br";
                break;
            case "Bulgaria":
                country = "bg";
                break;
            case "Canada":
                country = "ca";
                break;
            case "China":
                country = "cn";
                break;
            case "Colombia":
                country = "co";
                break;
            case "Cuba":
                country = "cu";
                break;
            case "Czech Republic":
                country = "cz";
                break;
            case "Egypt":
                country = "eg";
                break;
            case "France":
                country = "fr";
                break;
            case "Germany":
                country = "de";
                break;
            case "Greece":
                country = "gr";
                break;
            case "Hong Kong":
                country = "hk";
                break;
            case "Hungary":
                country = "hu";
                break;
            case "India":
                country = "in";
                break;
            case "Indonesia":
                country = "id";
                break;
            case "Ireland":
                country = "ie";
                break;
            case "Israel":
                country = "il";
                break;
            case "Italy":
                country = "it";
                break;
            case "Japan":
                country = "jp";
                break;
            case "Latvia":
                country = "lv";
                break;
            case "Lithuania":
                country = "lt";
                break;
            case "Malaysia":
                country = "my";
                break;
            case "Mexico":
                country = "mx";
                break;
            case "Morocco":
                country = "ma";
                break;
            case "Netherlands":
                country = "nl";
                break;
            case "New Zealand":
                country = "nz";
                break;
            case "Nigeria":
                country = "ng";
                break;
            case "Norway":
                country = "no";
                break;
            case "Philippines":
                country = "ph";
                break;
            case "Poland":
                country = "pl";
                break;
            case "Portugal":
                country = "pt";
                break;
            case "Romania":
                country = "ro";
                break;
            case "Russia":
                country = "ru";
                break;
            case "Saudi Arabia":
                country = "sa";
                break;
            case "Serbia":
                country = "rs";
                break;
            case "Singapore":
                country = "sg";
                break;
            case "Slovakia":
                country = "sk";
                break;
            case "Slovenia":
                country = "si";
                break;
            case "South Africa":
                country = "za";
                break;
            case "South Korea":
                country = "kr";
                break;
            case "Sweden":
                country = "se";
                break;
            case "Switzerland":
                country = "ch";
                break;
            case "Taiwan":
                country = "tw";
                break;
            case "Thailand":
                country = "th";
                break;
            case "Turkey":
                country = "tr";
                break;
            case "UAE":
                country = "ae";
                break;
            case "Ukraine":
                country = "ua";
                break;
            case "United Kingdom":
                country = "gb";
                break;
            case "United States":
                country = "us";
                break;
            case "Vanezuela":
                country = "ve";
                break;
            default:
                country = "in";
        }
    }
    public void LoadJson(){
        loadData();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        swipeRefreshLayout.setRefreshing(true);
        errorLayout.setVisibility(View.GONE);
        Call<News> call;
        call = apiInterface.getNews(country ,API_KEY,"entertainment","");

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful() && response.body().getArticle() != null){
                    if(!articles.isEmpty()){
                        articles.clear();
                    }

                    articles = response.body().getArticle();
                    adapter = new Adapter(articles,getContext());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    initListener();

                }else {
                    swipeRefreshLayout.setRefreshing(false);

                    String errorCode;

                    switch (response.code()){
                        case 404:
                            errorCode = "404 not found";
                            break;
                        case 505:
                            errorCode = "505 server broken";
                            break;
                        default:
                            errorCode = "unknown error";
                            break;

                    }

                    showErrorMessage(
                            R.drawable.no_result,
                            "No Result",
                            "Please Try Again!\n"+
                                    errorCode);
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                showErrorMessage(
                        R.drawable.no_result,
                        "Oops...",
                        "Network failture, Please Try Again\n"+
                                t.toString());
            }
        });

    }

    private void  initListener(){
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),NewsDetailActivity.class);
                Article article = articles.get(position);
                intent.putExtra("url", article.getUrl());
                intent.putExtra("title", article.getTitle());
                intent.putExtra("img", article.getUrlToImage());
                intent.putExtra("date", article.getPublishedAt());
                intent.putExtra("source", article.getSource().getName());
                intent.putExtra("author", article.getAuthor());

                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        LoadJson();
    }
    private void onLoadingSwipeRefresh(){
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                LoadJson();
            }
        });
    }
    private void showErrorMessage(int imageView, String title, String message){
        if(errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
        }

        errorImage.setImageResource(imageView);
        errorTitle.setText(title);
        errorMessage.setText(message);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoadingSwipeRefresh();
            }
        });

    }
}

