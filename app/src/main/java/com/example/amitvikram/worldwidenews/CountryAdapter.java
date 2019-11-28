package com.example.amitvikram.worldwidenews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<CountryItem> {

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable  View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.country_spinner, parent, false
            );
        }

       // ImageView imageViewFlag = convertView.findViewById(R.id.image_view_flag);
        TextView textViewName = convertView.findViewById(R.id.text_country_name);
        CountryItem currentItem = getItem(position);
        textViewName.setText("Select Country");
        if(currentItem != null) {
          //  imageViewFlag.setImageResource(currentItem.getFlagImage());
            textViewName.setText(currentItem.getCountryName());
        }

        return convertView;
    }

    public CountryAdapter(Context context, ArrayList<CountryItem> countryList){
        super(context, 0, countryList);
    }



}
