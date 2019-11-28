package com.example.amitvikram.worldwidenews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.amitvikram.worldwidenews.MyApplication.mPreferencesHelper;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private BottomNavigationView mMainnav;
    private CardView cardColorScheme, cardLogin, cardSearchOver;
    private TextView tvAppMode;
    private ArrayList<CountryItem> mCountryList;
    private CountryAdapter mAdapter;
    private static final String SHARED_PREFS = "sharedPrefs";
    private String countryname;
    public Spinner spinner;
    SharedPreferences sharedPreferences;
    private int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mMainnav = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        cardLogin = findViewById(R.id.cardLogin);
        cardColorScheme = findViewById(R.id.cardColorScheme);
        cardSearchOver = findViewById(R.id.cardSearchOver);
        tvAppMode = findViewById(R.id.tvAppMode);
        spinner = findViewById(R.id.spiner_countries);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        i = 0;
        mMainnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Home:
                        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.Browse:
                        Intent intent1 = new Intent(SettingsActivity.this, Browse.class);
                        startActivity(intent1);
                        return true;

                    case R.id.Video:
                        Intent video = new Intent(SettingsActivity.this, VideoListActivity.class);
                        startActivity(video);
                        return true;
                    case R.id.Setting:
                        return true;

                }
                return false;
            }
        });

        registerForContextMenu(cardColorScheme);
        mMainnav.setSelectedItemId(R.id.Setting);
        cardLogin.setOnClickListener(this);
        cardColorScheme.setOnClickListener(this);
        cardSearchOver.setOnClickListener(this);
        tvAppMode.setText(mPreferencesHelper.getString(PreferencesHelper.PREF_APP_COLOR_SCHEME));
        initList();
        mAdapter = new CountryAdapter(this,mCountryList);
        spinner.setAdapter(mAdapter);
        countryname = sharedPreferences.getString("country","");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
                if(!clickedItem.getCountryName().equals("Choose Country")) {
                    countryname = clickedItem.getCountryName();
                    saveData();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        loadData();
    }

    private void initList(){
        mCountryList = new ArrayList<>();
        mCountryList.add(new CountryItem("Choose Country"));
        mCountryList.add(new CountryItem("Argentina"));
        mCountryList.add(new CountryItem("Australia"));
        mCountryList.add(new CountryItem("Austria"));
        mCountryList.add(new CountryItem("Belgium"));
        mCountryList.add(new CountryItem("Brazil"));
        mCountryList.add(new CountryItem("Bulgaria"));
        mCountryList.add(new CountryItem("Canada"));
        mCountryList.add(new CountryItem("China"));
        mCountryList.add(new CountryItem("Colombia"));
        mCountryList.add(new CountryItem("Cuba"));
        mCountryList.add(new CountryItem("Czech Republic"));
        mCountryList.add(new CountryItem("Egypt"));
        mCountryList.add(new CountryItem("France"));
        mCountryList.add(new CountryItem("Germany"));
        mCountryList.add(new CountryItem("Greece"));
        mCountryList.add(new CountryItem("Hong Kong"));
        mCountryList.add(new CountryItem("Hungary"));
        mCountryList.add(new CountryItem("India"));
        mCountryList.add(new CountryItem("Indonesia"));
        mCountryList.add(new CountryItem("Ireland"));
        mCountryList.add(new CountryItem("Israel"));
        mCountryList.add(new CountryItem("Italy"));
        mCountryList.add(new CountryItem("Japan"));
        mCountryList.add(new CountryItem("Latvia"));
        mCountryList.add(new CountryItem("Lithuania"));
        mCountryList.add(new CountryItem("Malaysia"));
        mCountryList.add(new CountryItem("Mexico"));
        mCountryList.add(new CountryItem("Morocco"));
        mCountryList.add(new CountryItem("Netherlands"));
        mCountryList.add(new CountryItem("New Zealand"));
        mCountryList.add(new CountryItem("Nigeria"));
        mCountryList.add(new CountryItem("Norway"));
        mCountryList.add(new CountryItem("Philippines"));
        mCountryList.add(new CountryItem("Poland"));
        mCountryList.add(new CountryItem("Portugal"));
        mCountryList.add(new CountryItem("Romania"));
        mCountryList.add(new CountryItem("Russia"));
        mCountryList.add(new CountryItem("Saudi Arabia"));
        mCountryList.add(new CountryItem("Serbia"));
        mCountryList.add(new CountryItem("Singapore"));
        mCountryList.add(new CountryItem("Slovakia"));
        mCountryList.add(new CountryItem("Slovenia"));
        mCountryList.add(new CountryItem("South Africa"));
        mCountryList.add(new CountryItem("South Korea"));
        mCountryList.add(new CountryItem("Sweden"));
        mCountryList.add(new CountryItem("Switzerland"));
        mCountryList.add(new CountryItem("Taiwan"));
        mCountryList.add(new CountryItem("Thailand"));
        mCountryList.add(new CountryItem("Turkey"));
        mCountryList.add(new CountryItem("UAE"));
        mCountryList.add(new CountryItem("Ukraine"));
        mCountryList.add(new CountryItem("United Kingdom"));
        mCountryList.add(new CountryItem("United States"));
        mCountryList.add(new CountryItem("Vanezuela"));

    }
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("countries", countryname);
        editor.apply();
        if(i == 0){

        }
        else {
            restartApplication();
        }
        i++;
    }
    public void loadData(){
        String countryName = sharedPreferences.getString("countries","");
        switch(countryName){
            case "Argentina":
                spinner.setSelection(1);
                break;
            case "Australia":
                spinner.setSelection(2);
                break;
            case "Austria":
                spinner.setSelection(3);
                break;
            case "Belgium":
                spinner.setSelection(4);
                break;
            case "Brazil":
                spinner.setSelection(5);
                break;
            case "Bulgaria":
                spinner.setSelection(6);
                break;
            case "Canada":
                spinner.setSelection(7);
                break;
            case "China":
                spinner.setSelection(8);
                break;
            case "Colombia":
                spinner.setSelection(9);
                break;
            case "Cuba":
                spinner.setSelection(10);
                break;
            case "Czech Republic":
                spinner.setSelection(11);
                break;
            case "Egypt":
                spinner.setSelection(12);
                break;
            case "France":
                spinner.setSelection(13);
                break;
            case "Germany":
                spinner.setSelection(14);
                break;
            case "Greece":
                spinner.setSelection(15);
                break;
            case "Hong Kong":
                spinner.setSelection(16);
                break;
            case "Hungary":
                spinner.setSelection(17);
                break;
            case "India":
                spinner.setSelection(18);
                break;
            case "Indonesia":
                spinner.setSelection(19);
                break;
            case "Ireland":
                spinner.setSelection(20);
                break;
            case "Israel":
                spinner.setSelection(21);
                break;
            case "Italy":
                spinner.setSelection(22);
                break;
            case "Japan":
                spinner.setSelection(23);
                break;
            case "Latvia":
                spinner.setSelection(24);
                break;
            case "Lithuania":
                spinner.setSelection(25);
                break;
            case "Malaysia":
                spinner.setSelection(26);
                break;
            case "Mexico":
                spinner.setSelection(27);
                break;
            case "Morocco":
                spinner.setSelection(28);
                break;
            case "Netherlands":
                spinner.setSelection(29);
                break;
            case "New Zealand":
                spinner.setSelection(30);
                break;
            case "Nigeria":
                spinner.setSelection(31);
                break;
            case "Norway":
                spinner.setSelection(32);
                break;
            case "Philippines":
                spinner.setSelection(33);
                break;
            case "Poland":
                spinner.setSelection(34);
                break;
            case "Portugal":
                spinner.setSelection(35);
                break;
            case "Romania":
                spinner.setSelection(36);
                break;
            case "Russia":
                spinner.setSelection(37);
                break;
            case "Saudi Arabia":
                spinner.setSelection(38);
                break;
            case "Serbia":
                spinner.setSelection(39);
                break;
            case "Singapore":
                spinner.setSelection(40);
                break;
            case "Slovakia":
                spinner.setSelection(41);
                break;
            case "Slovenia":
                spinner.setSelection(42);
                break;
            case "South Africa":
                spinner.setSelection(43);
                break;
            case "South Korea":
                spinner.setSelection(44);
                break;
            case "Sweden":
                spinner.setSelection(45);
                break;
            case "Switzerland":
                spinner.setSelection(46);
                break;
            case "Taiwan":
                spinner.setSelection(47);
                break;
            case "Thailand":
                spinner.setSelection(48);
                break;
            case "Turkey":
                spinner.setSelection(49);
                break;
            case "UAE":
                spinner.setSelection(50);
                break;
            case "Ukraine":
                spinner.setSelection(51);
                break;
            case "United Kingdom":
                spinner.setSelection(52);
                break;
            case "United States":
                spinner.setSelection(53);
                break;
            case "Vanezuela":
                spinner.setSelection(54);
                break;
                default:
                    spinner.setSelection(0);
                    break;

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        mMainnav.setSelectedItemId(R.id.Setting);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardLogin:
                Intent intent2 = new Intent(SettingsActivity.this, User.class);
                startActivity(intent2);
                break;
            case R.id.cardColorScheme:
                openContextMenu(cardColorScheme);
                break;
            case R.id.cardSearchOver:
                Intent cardSearchOver = new Intent(SettingsActivity.this, SearchOverActivity.class);
                startActivity(cardSearchOver);
                break;
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select Color Scheme");
        menu.add(0, v.getId(), 0, PreferencesHelper.CONST_AUTOMATIC);
        menu.add(0, v.getId(), 0, PreferencesHelper.CONST_LIGHT_MODE);
        menu.add(0, v.getId(), 0, PreferencesHelper.CONST_DARK_MODE);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        mPreferencesHelper.putString(PreferencesHelper.PREF_APP_COLOR_SCHEME, item.getTitle().toString());
        setColorScheme();
        restartApplication();

        return true;
    }

    private void setColorScheme() {
        if (mPreferencesHelper.getString(PreferencesHelper.PREF_APP_COLOR_SCHEME).equals(PreferencesHelper.CONST_AUTOMATIC)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
        } else if (mPreferencesHelper.getString(PreferencesHelper.PREF_APP_COLOR_SCHEME).equals(PreferencesHelper.CONST_LIGHT_MODE)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if(mPreferencesHelper.getString(PreferencesHelper.PREF_APP_COLOR_SCHEME).equals(PreferencesHelper.CONST_DARK_MODE)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void restartApplication() {

        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
