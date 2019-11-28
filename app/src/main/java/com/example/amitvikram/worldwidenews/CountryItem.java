package com.example.amitvikram.worldwidenews;

public class CountryItem {
    private String mCountryName;
    //private int mFlagImage;
    //to-do add flagname parameter in below constructor if add image
    public CountryItem(String countryName){
        mCountryName = countryName;
      //  mFlagImage = flagImage;
    }

    public String getCountryName(){
        return mCountryName;
    }

   // public int getFlagImage(){
     //   return mFlagImage;
    //}
}
