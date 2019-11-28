package com.example.amitvikram.worldwidenews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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

public class SourceNews extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private ImageView imageView;
    private TextView txtView;
    public static final String API_KEY = "d7c4bc6cf53e4904b6def07e18344f06";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles =  new ArrayList<>();
    private Adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(amIconnected()){

        }
        else{
            Intent i = new Intent(SourceNews.this,errornetwork.class);
            startActivity(i);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_news);
        imageView = findViewById(R.id.imgSource);
        txtView = findViewById(R.id.txtSource);
        swipeRefreshLayout = findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        Bundle extras = getIntent().getExtras();
        Intent receivedIntent = getIntent();
        if(extras != null){
            byte[] b = extras.getByteArray("imgSource");
            Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
            imageView.setImageBitmap(bmp);
            txtView.setText(receivedIntent.getStringExtra("txtSource"));
        }

        switch(txtView.getText().toString().trim()){
            case "Google News":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("google-news");
                break;
            case "ABC News":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("abc-news");
                break;
            case "The Hindu":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("the-hindu");
                break;
            case "The Times Of India":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("the-times-of-india");
                break;
            case "BBC News":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("bbc-news");
                break;
            case "CNN":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("cnn");
                break;
            case "ESPN":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("espn");
                break;
            case "Hacker News":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("hacker-news");
                break;
            case "TechCrunch":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("techcrunch");
                break;
            case "National Geographic":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("national-geographic");
                break;
            case "ESPN Cric Info":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("espn-cric-info");
                break;
            case "The Verge":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("the-verge");
                break;
            case "NBC News":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("nbc-news");
                break;
            case "TechRadar":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("techradar");
                break;
            case "The Next Web":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("the-next-web");
                break;
            case "Crypto Coins News":
                recyclerView = findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(SourceNews.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setNestedScrollingEnabled(false);
                onLoadingSwipeRefresh("crypto-coins-news");
                break;
        }
    }
    private boolean amIconnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return  activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public void onResume(){
        super.onResume();
        if(amIconnected()){
            switch(txtView.getText().toString().trim()){
                case "Google News":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("google-news");
                    break;
                case "ABC News":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("abc-news");
                    break;
                case "The Hindu":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("the-hindu");
                    break;
                case "The Times Of India":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("the-times-of-india");
                    break;
                case "BBC News":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("bbc-news");
                    break;
                case "CNN":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("cnn");
                    break;
                case "ESPN":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("espn");
                    break;
                case "Hacker News":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("hacker-news");
                    break;
                case "TechCrunch":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("techcrunch");
                    break;
                case "National Geographic":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("national-geographic");
                    break;
                case "ESPN Cric Info":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("espn-cric-info");
                    break;
                case "The Verge":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("the-verge");
                    break;
                case "NBC News":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("nbc-news");
                    break;
                case "TechRadar":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("techradar");
                    break;
                case "The Next Web":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("the-next-web");
                    break;
                case "Crypto Coins News":
                    recyclerView = findViewById(R.id.recyclerView);
                    layoutManager = new LinearLayoutManager(SourceNews.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    onLoadingSwipeRefresh("crypto-coins-news");
                    break;
            }
        }
        else{
            Intent i = new Intent(SourceNews.this,errornetwork.class);
            startActivity(i);
        }
    }
    public void LoadJson(String source){

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        swipeRefreshLayout.setRefreshing(true);
        String country = "in";
        Call<News> call;
        call = apiInterface.getSourceNews(API_KEY,source);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful() && response.body().getArticle() != null){
                    if(!articles.isEmpty()){
                        articles.clear();
                    }

                    articles = response.body().getArticle();
                    adapter = new Adapter(articles,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    initListener();

                }else {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getApplicationContext(), "No Result!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void  initListener(){
        adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(SourceNews.this,NewsDetailActivity.class);
            Article article = articles.get(position);
            intent.putExtra("url", article.getUrl());
            intent.putExtra("title", article.getTitle());
            intent.putExtra("img", article.getUrlToImage());
            intent.putExtra("date", article.getPublishedAt());
            intent.putExtra("source", article.getSource().getName());
            intent.putExtra("author", article.getAuthor());

            startActivity(intent);
        });
    }

    @Override
    public void onRefresh() {
        switch(txtView.getText().toString().trim()){
            case "Google News":
                LoadJson("google-news");
                break;
            case "ABC News":
                LoadJson("abc-news");
                break;
            case "The Hindu":
                LoadJson("the-hindu");
                break;
            case "The Times Of India":
                LoadJson("the-times-of-india");
                break;
            case "BBC News":
                LoadJson("bbc-news");
                break;
            case "CNN":
                LoadJson("cnn");
                break;
            case "ESPN":
                LoadJson("espn");
                break;
            case "Hacker News":
                LoadJson("hacker-news");
                break;
            case "TechCrunch":
                LoadJson("techcrunch");
                break;
            case "National Geographic":
                LoadJson("national-geographic");
                break;
            case "ESPN Cric Info":
                LoadJson("espn-cric-info");
                break;
            case "The Verge":
                LoadJson("the-verge");
                break;
            case "NBC News":
                LoadJson("nbc-news");
                break;
            case "TechRadar":
                LoadJson("techradar");
                break;
            case "The Next Web":
                LoadJson("the-next-web");
                break;
            case "Crypto Coins News":
                LoadJson("crypto-coins-news");
                break;
        }
    }
    private void onLoadingSwipeRefresh(final String s){
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                LoadJson(s);
            }
        });
    }
}
