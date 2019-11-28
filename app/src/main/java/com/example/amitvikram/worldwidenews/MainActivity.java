package com.example.amitvikram.worldwidenews;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.example.amitvikram.worldwidenews.api.ApiClient;
import com.example.amitvikram.worldwidenews.api.ApiInterface;
import com.example.amitvikram.worldwidenews.models.Article;
import com.example.amitvikram.worldwidenews.models.News;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private BottomNavigationView mMainnav;
    public static final String API_KEY = "d7c4bc6cf53e4904b6def07e18344f06";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles =  new ArrayList<>();
    private Adapter adapter;
    private String TAG = MainActivity.class.getSimpleName();
    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences;
    private String country;
    private RelativeLayout errorLayout;
    private ImageView errorImage;
    private TextView errorTitle, errorMessage;
    private Button btnRetry;
    private TextView txtHeadlines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(amIconnected()){

        }
        else{
            Intent i = new Intent(MainActivity.this,errornetwork.class);
            startActivity(i);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mMainnav = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        mMainnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.Home:
                        return true;
                    case R.id.Browse:
                        Intent intent1 = new Intent(MainActivity.this,Browse.class);
                        startActivity(intent1);
                        return true;
                    case R.id.Video:
                        Intent intent2 = new Intent(MainActivity.this,VideoListActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.Setting:

                        Intent intent3 = new Intent(MainActivity.this,SettingsActivity.class);
                        startActivity(intent3);
                        return true;

                }
                return false;
            }
        });
        mMainnav.setSelectedItemId(R.id.Home);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        onLoadingSwipeRefresh("");
        errorLayout = findViewById(R.id.errorLayout);
        errorImage = findViewById(R.id.errorImage);
        errorTitle = findViewById(R.id.errorTitle);
        errorMessage = findViewById(R.id.errorMessage);
        btnRetry = findViewById(R.id.btnRetry);
        txtHeadlines = findViewById(R.id.txtHeadlines);
    }
    public void loadData(){
        String countryName = sharedPreferences.getString("countries","");
        switch(countryName) {
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
    @Override
    public void onResume(){
        if(amIconnected()){

        }
        else{
            Intent i = new Intent(MainActivity.this,errornetwork.class);
            startActivity(i);
        }
        super.onResume();
        mMainnav.setSelectedItemId(R.id.Home);
    }
    public void LoadJson(String keyword){
        loadData();
        swipeRefreshLayout.setRefreshing(true);
        errorLayout.setVisibility(View.GONE);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        String language = Utils.getLanguage();
        Call<News> call;
        call = apiInterface.getNews(country ,API_KEY,"","");
        if(keyword.length() > 0){
            call = apiInterface.getNewsSearch(keyword ,language, "publishedAt", API_KEY);
        }
        else{
            call = apiInterface.getNews(country ,API_KEY,"","");
        }
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful() && response.body().getArticle() != null){
                    if(!articles.isEmpty()){
                        articles.clear();
                    }

                    articles = response.body().getArticle();
                    adapter = new Adapter(articles,MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    txtHeadlines.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                    initListener();

                }else {

                    swipeRefreshLayout.setRefreshing(false);
                    txtHeadlines.setVisibility(View.INVISIBLE);
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
                txtHeadlines.setVisibility(View.INVISIBLE);
                String errorCode;
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
                Intent intent = new Intent(MainActivity.this,NewsDetailActivity.class);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_twittertrends) {
            final CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
            final String url="http://mobile.twitter.com/explore";
            intent.launchUrl(this, Uri.parse(url));
            return true;
        }
        else if(id == R.id.action_sources){
            Intent i = new Intent(MainActivity.this, Sources.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean amIconnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return  activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu ) {
        getMenuInflater().inflate(R.menu.menu_browse, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search Latest News...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length() > 2){
                    onLoadingSwipeRefresh(query);;
                }
                else{
                    Toast.makeText(getApplicationContext(),"Type atleast more than two character",Toast.LENGTH_SHORT);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        searchItem.getIcon().setVisible(false,false);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onRefresh() {
        LoadJson("");
    }
    private void onLoadingSwipeRefresh(final String keyword){
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                LoadJson(keyword);
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
                onLoadingSwipeRefresh("");
            }
        });

    }
}


