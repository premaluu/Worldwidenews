package com.example.amitvikram.worldwidenews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.amitvikram.worldwidenews.models.VideoItem;
import com.example.amitvikram.worldwidenews.models.YoutubeConnector;

import java.util.List;

public class VideoListActivity extends AppCompatActivity {

    //EditText for input search keywords
    private EditText searchInput;

    //YoutubeAdapter class that serves as a adapter for filling the
    //RecyclerView by the CardView (video_item.xml) that is created in layout folder
    private YoutubeAdapter youtubeAdapter;

    //RecyclerView manages a long list by recycling the portion of view
    //that is currently visible on screen
    private RecyclerView mRecyclerView;

    //Handler to run a thread which could fill the list after downloading data
    //from the internet and inflating the images, title and description
    private Handler handler;

    //results list of type VideoItem to store the results so that each item
    //int the array list has id, title, description and thumbnail url
    private List<VideoItem> searchResults;
    private BottomNavigationView mMainnav;
    private ProgressBar pbLoader;
    private String query;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(amIconnected()){

        }
        else{
            Intent i = new Intent(VideoListActivity.this,errornetwork.class);
            startActivity(i);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        mMainnav = findViewById(R.id.bottom_navigation_view);
        pbLoader = findViewById(R.id.pbLoader);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        mMainnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Home:
                        Intent intent1 = new Intent(VideoListActivity.this, MainActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.Browse:
                        Intent Browse = new Intent(VideoListActivity.this, Browse.class);
                        startActivity(Browse);
                        return true;

                    case R.id.Video:

                        return true;
                    case R.id.Setting:

                        Intent intent3 = new Intent(VideoListActivity.this, SettingsActivity.class);
                        startActivity(intent3);
                        return true;

                }
                return false;
            }
        });

        //initailising the objects with their respective view in activity_main.xml file
        searchInput = findViewById(R.id.search_input);
        mRecyclerView = findViewById(R.id.rcVideo);


        //Fixing the size of recycler view which means that the size of the view
        //should not change if adapter or children size changes
        mRecyclerView.setHasFixedSize(true);
        //give RecyclerView a layout manager to set its orientation to vertical
        //by default it is vertical
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        handler = new Handler();

        //add listener to the EditText view which listens to changes that occurs when
        //users changes the text or deletes the text
        //passing object of Textview's EditorActionListener to this method
        loadData();
        searchOnYoutube(query);
        //setting progress message so that users can understand what is happening

        pbLoader.setVisibility(View.VISIBLE);
        //displaying the progress dialog on the top of activity for two reasons
        //1.user can see what is going on
        //2.User cannot click anything on screen for time being


        //onEditorAction method called when user clicks ok button or any custom
//button set on the bottom right of keyboard
        searchInput.setOnEditorActionListener((v, actionId, event) -> {

            //actionId of the respective action is returned as integer which can
            //be checked with our set custom search button in keyboard
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                pbLoader.setVisibility(View.VISIBLE);

                //calling our search method created below with input keyword entered by user
                //by getText method which returns Editable type, get string by toString method
                if (!TextUtils.isEmpty(v.getText())) {
                    searchOnYoutube(v.getText().toString());
                } else {
                    searchOnYoutube("world wide news");
                }

                //getting instance of the keyboard or any other input from which user types
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                //hiding the keyboard once search button is clicked
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.RESULT_UNCHANGED_SHOWN);

                return false;
            }
            return true;
        });

    }
    private boolean amIconnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return  activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void loadData(){
        String queryName = sharedPreferences.getString("countries","");
        switch(queryName) {
            case "Argentina":
                query = "Argentina News";
                break;
            case "Australia":
                query = "Australia News";
                break;
            case "Austria":
                query = "Austria News";
                break;
            case "Belgium":
                query = "Belgium News";
                break;
            case "Brazil":
                query = "Brazil News";
                break;
            case "Bulgaria":
                query = "Bulgaria News";
                break;
            case "Canada":
                query = "Canada News";
                break;
            case "China":
                query = "China News";
                break;
            case "Colombia":
                query = "Colombia News";
                break;
            case "Cuba":
                query = "Cuba News";
                break;
            case "Czech Republic":
                query = "Czech Republic News";
                break;
            case "Egypt":
                query = "Egypt News";
                break;
            case "France":
                query = "France News";
                break;
            case "Germany":
                query = "Germany News";
                break;
            case "Greece":
                query = "Greece News";
                break;
            case "Hong Kong":
                query = "Hong Kong News";
                break;
            case "Hungary":
                query = "Hungary News";
                break;
            case "India":
                query = "India News";
                break;
            case "Indonesia":
                query = "Indonesia News";
                break;
            case "Ireland":
                query = "Ireland News";
                break;
            case "Israel":
                query = "Israel News";
                break;
            case "Italy":
                query = "Italy News";
                break;
            case "Japan":
                query = "Japan News";
                break;
            case "Latvia":
                query = "Latvia News";
                break;
            case "Lithuania":
                query = "Lithuania News";
                break;
            case "Malaysia":
                query = "Malaysia News";
                break;
            case "Mexico":
                query = "Mexico News";
                break;
            case "Morocco":
                query = "Morocco News";
                break;
            case "Netherlands":
                query = "Netherlands News";
                break;
            case "New Zealand":
                query = "New Zealand News";
                break;
            case "Nigeria":
                query = "Nigeria News";
                break;
            case "Norway":
                query = "Norway News";
                break;
            case "Philippines":
                query = "Philippines News";
                break;
            case "Poland":
                query = "Poland News";
                break;
            case "Portugal":
                query = "Portugal News";
                break;
            case "Romania":
                query = "Romania News";
                break;
            case "Russia":
                query = "Russia News";
                break;
            case "Saudi Arabia":
                query = "Saudi Arabia News";
                break;
            case "Serbia":
                query = "Serbia News";
                break;
            case "Singapore":
                query = "Singapore News";
                break;
            case "Slovakia":
                query = "Slovakia News";
                break;
            case "Slovenia":
                query = "Slovenia News";
                break;
            case "South Africa":
                query = "South Africa News";
                break;
            case "South Korea":
                query = "South Korea News";
                break;
            case "Sweden":
                query = "Sweden News";
                break;
            case "Switzerland":
                query = "Switzerland News";
                break;
            case "Taiwan":
                query = "Taiwan News";
                break;
            case "Thailand":
                query = "Thailand News";
                break;
            case "Turkey":
                query = "Turkey News";
                break;
            case "UAE":
                query = "UAE News";
                break;
            case "Ukraine":
                query = "Ukraine News";
                break;
            case "United Kingdom":
                query = "United Kingdom News";
                break;
            case "United States":
                query = "United States News";
                break;
            case "Vanezuela":
                query = "Vanezuela News";
                break;
            default:
                query = "India live News";
        }
    }

    @Override
    protected void onResume() {
        if(amIconnected()){
            loadData();
            searchOnYoutube(query);
            //setting progress message so that users can understand what is happening

            pbLoader.setVisibility(View.VISIBLE);
            //displaying the progress dialog on the top of activity for two reasons
            //1.user can see what is going on
            //2.User cannot click anything on screen for time being
        }
        else{
            Intent i = new Intent(VideoListActivity.this,errornetwork.class);
            startActivity(i);
        }
        super.onResume();
        mMainnav.setSelectedItemId(R.id.Video);
    }


    //custom search method which takes argument as the keyword for which videos is to be searched
    private void searchOnYoutube(final String keywords) {

        //A thread that will execute the searching and inflating the RecyclerView as and when
        //results are found
        new Thread() {

            //implementing run method
            public void run() {

                //create our YoutubeConnector class's object with Activity context as argument
                YoutubeConnector yc = new YoutubeConnector(VideoListActivity.this);

                //calling the YoutubeConnector's search method by entered keyword
                //and saving the results in list of type VideoItem class
                searchResults = yc.search(keywords);

                //handler's method used for doing changes in the UI
                handler.post(new Runnable() {

                    //implementing run method of Runnable
                    public void run() {

                        //call method to create Adapter for RecyclerView and filling the list
                        //with thumbnail, title, id and description
                        fillYoutubeVideos();

                        //after the above has been done hiding the ProgressDialog
                        pbLoader.setVisibility(View.GONE);
                    }
                });
            }
            //starting the thread
        }.start();
    }

    //method for creating adapter and setting it to recycler view
    private void fillYoutubeVideos() {

        //object of YoutubeAdapter which will fill the RecyclerView
        youtubeAdapter = new YoutubeAdapter(getApplicationContext(), searchResults);

        //setAdapter to RecyclerView
        mRecyclerView.setAdapter(youtubeAdapter);

        //notify the Adapter that the data has been downloaded so that list can be updapted
        youtubeAdapter.notifyDataSetChanged();
    }
}
