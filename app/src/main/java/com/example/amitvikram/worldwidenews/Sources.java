package com.example.amitvikram.worldwidenews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class Sources extends AppCompatActivity {
    private TextView Source;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(amIconnected()){

        }
        else{
            Intent i = new Intent(Sources.this,errornetwork.class);
            startActivity(i);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);
        ActionBar actionBar = getSupportActionBar();
    }

    public void sourceLoad(View view){
        int cardId = view.getId();
        switch(cardId){
            case R.id.googlenews:
                Source = (TextView)findViewById(R.id.txtGooglenews);
                imageView = (ImageView) findViewById(R.id.imgGooglenews);
                String txtGoogleNews = Source.getText().toString().trim();
                Drawable drawableGoogleNews=imageView.getDrawable();
                Bitmap bitmapGooglenews= ((BitmapDrawable)drawableGoogleNews).getBitmap();
                ByteArrayOutputStream baosGoogleNews = new ByteArrayOutputStream();
                bitmapGooglenews.compress(Bitmap.CompressFormat.PNG, 100, baosGoogleNews);
                byte[] bGoogleNews = baosGoogleNews.toByteArray();
                Intent iGooglenews = new Intent(Sources.this, SourceNews.class);
                iGooglenews.putExtra("txtSource",txtGoogleNews);
                iGooglenews.putExtra("imgSource",bGoogleNews);
                startActivity(iGooglenews);
                break;
            case R.id.abcnews:
                Source = (TextView)findViewById(R.id.txtABCnews);
                imageView = (ImageView) findViewById(R.id.imgABCnews);
                String txtABCnews = Source.getText().toString().trim();
                Drawable drawableABCnews=imageView.getDrawable();
                Bitmap bitmapABCnews= ((BitmapDrawable)drawableABCnews).getBitmap();
                ByteArrayOutputStream baosABCnews = new ByteArrayOutputStream();
                bitmapABCnews.compress(Bitmap.CompressFormat.PNG, 100, baosABCnews);
                byte[] bABCnews = baosABCnews.toByteArray();
                Intent iABCnews = new Intent(Sources.this, SourceNews.class);
                iABCnews.putExtra("txtSource",txtABCnews);
                iABCnews.putExtra("imgSource",bABCnews);
                startActivity(iABCnews);
                break;
            case R.id.thehindu:
                Source = (TextView)findViewById(R.id.txtThehindu);
                imageView = (ImageView) findViewById(R.id.imgThehindu);
                String txtThehindu = Source.getText().toString().trim();
                Drawable drawableThehindu=imageView.getDrawable();
                Bitmap bitmapThehindu= ((BitmapDrawable)drawableThehindu).getBitmap();
                ByteArrayOutputStream baosThehindu = new ByteArrayOutputStream();
                bitmapThehindu.compress(Bitmap.CompressFormat.PNG, 100, baosThehindu);
                byte[] bThehindu = baosThehindu.toByteArray();
                Intent iThehindu = new Intent(Sources.this, SourceNews.class);
                iThehindu.putExtra("txtSource",txtThehindu);
                iThehindu.putExtra("imgSource",bThehindu);
                startActivity(iThehindu);
                break;
            case R.id.TOI:
                Source = (TextView)findViewById(R.id.txtTOI);
                imageView = (ImageView) findViewById(R.id.imgTOI);
                String txttheTOI = Source.getText().toString().trim();
                Drawable drawabletheTOI=imageView.getDrawable();
                Bitmap bitmaptheTOI= ((BitmapDrawable)drawabletheTOI).getBitmap();
                ByteArrayOutputStream baostheTOI = new ByteArrayOutputStream();
                bitmaptheTOI.compress(Bitmap.CompressFormat.PNG, 100, baostheTOI);
                byte[] btheTOI = baostheTOI.toByteArray();
                Intent itheTOI = new Intent(Sources.this, SourceNews.class);
                itheTOI.putExtra("txtSource",txttheTOI);
                itheTOI.putExtra("imgSource",btheTOI);
                startActivity(itheTOI);
                break;
            case R.id.BBCnews:
                Source = (TextView)findViewById(R.id.txtBBCnews);
                imageView = (ImageView) findViewById(R.id.imgBBCnews);
                String txtBBCnews = Source.getText().toString().trim();
                Drawable drawableBBCnews=imageView.getDrawable();
                Bitmap bitmapBBCnews= ((BitmapDrawable)drawableBBCnews).getBitmap();
                ByteArrayOutputStream baosBBCnews = new ByteArrayOutputStream();
                bitmapBBCnews.compress(Bitmap.CompressFormat.PNG, 100, baosBBCnews);
                byte[] bBBCnews = baosBBCnews.toByteArray();
                Intent iBBCnews = new Intent(Sources.this, SourceNews.class);
                iBBCnews.putExtra("txtSource",txtBBCnews);
                iBBCnews.putExtra("imgSource",bBBCnews);
                startActivity(iBBCnews);
                break;
            case R.id.CNN:
                Source = (TextView)findViewById(R.id.txtCNN);
                imageView = (ImageView) findViewById(R.id.imgCNN);
                String txtCNN = Source.getText().toString().trim();
                Drawable drawableCNN=imageView.getDrawable();
                Bitmap bitmapCNN= ((BitmapDrawable)drawableCNN).getBitmap();
                ByteArrayOutputStream baosCNN = new ByteArrayOutputStream();
                bitmapCNN.compress(Bitmap.CompressFormat.PNG, 100, baosCNN);
                byte[] bCNN = baosCNN.toByteArray();
                Intent iCNN = new Intent(Sources.this, SourceNews.class);
                iCNN.putExtra("txtSource",txtCNN);
                iCNN.putExtra("imgSource",bCNN);
                startActivity(iCNN);
                break;
            case R.id.ESPN:
                Source = (TextView)findViewById(R.id.txtESPN);
                imageView = (ImageView) findViewById(R.id.imgESPN);
                String txtESPN = Source.getText().toString().trim();
                Drawable drawableESPN=imageView.getDrawable();
                Bitmap bitmapESPN= ((BitmapDrawable)drawableESPN).getBitmap();
                ByteArrayOutputStream baosESPN = new ByteArrayOutputStream();
                bitmapESPN.compress(Bitmap.CompressFormat.PNG, 100, baosESPN);
                byte[] bESPN = baosESPN.toByteArray();
                Intent iESPN = new Intent(Sources.this, SourceNews.class);
                iESPN.putExtra("txtSource",txtESPN);
                iESPN.putExtra("imgSource",bESPN);
                startActivity(iESPN);
                break;
            case R.id.hackernews:
                Source = (TextView)findViewById(R.id.txthackernews);
                imageView = (ImageView) findViewById(R.id.imghackernews);
                String txthackernews = Source.getText().toString().trim();
                Drawable drawablehackernews=imageView.getDrawable();
                Bitmap bitmaphackernews= ((BitmapDrawable)drawablehackernews).getBitmap();
                ByteArrayOutputStream baoshackernews = new ByteArrayOutputStream();
                bitmaphackernews.compress(Bitmap.CompressFormat.PNG, 100, baoshackernews);
                byte[] bhackernews = baoshackernews.toByteArray();
                Intent ihackernews = new Intent(Sources.this, SourceNews.class);
                ihackernews.putExtra("txtSource",txthackernews);
                ihackernews.putExtra("imgSource",bhackernews);
                startActivity(ihackernews);
                break;
            case R.id.techcrunch:
                Source = (TextView)findViewById(R.id.txttechcrunch);
                imageView = (ImageView) findViewById(R.id.imgtechcrunch);
                String txttechcrunch = Source.getText().toString().trim();
                Drawable drawabletechcrunch=imageView.getDrawable();
                Bitmap bitmaptechcrunch= ((BitmapDrawable)drawabletechcrunch).getBitmap();
                ByteArrayOutputStream baostechcrunch = new ByteArrayOutputStream();
                bitmaptechcrunch.compress(Bitmap.CompressFormat.PNG, 100, baostechcrunch);
                byte[] btechcrunch = baostechcrunch.toByteArray();
                Intent itechcrunch = new Intent(Sources.this, SourceNews.class);
                itechcrunch.putExtra("txtSource",txttechcrunch);
                itechcrunch.putExtra("imgSource",btechcrunch);
                startActivity(itechcrunch);
                break;
            case R.id.nationalgeographic:
                Source = (TextView)findViewById(R.id.txtnationalgeographic);
                imageView = (ImageView) findViewById(R.id.imgnationalgeographic);
                String txtnationalgeographic = Source.getText().toString().trim();
                Drawable drawablenationalgeographic=imageView.getDrawable();
                Bitmap bitmapnationalgeographic= ((BitmapDrawable)drawablenationalgeographic).getBitmap();
                ByteArrayOutputStream baosnationalgeographic = new ByteArrayOutputStream();
                bitmapnationalgeographic.compress(Bitmap.CompressFormat.PNG, 100, baosnationalgeographic);
                byte[] bnationalgeographic = baosnationalgeographic.toByteArray();
                Intent inationalgeographic = new Intent(Sources.this, SourceNews.class);
                inationalgeographic.putExtra("txtSource",txtnationalgeographic);
                inationalgeographic.putExtra("imgSource",bnationalgeographic);
                startActivity(inationalgeographic);
                break;
            case R.id.espncricinfo:
                Source = (TextView)findViewById(R.id.txtespncricinfo);
                imageView = (ImageView) findViewById(R.id.imgespncricinfo);
                String txtespncricinfo = Source.getText().toString().trim();
                Drawable drawableespncricinfo=imageView.getDrawable();
                Bitmap bitmapespncricinfo= ((BitmapDrawable)drawableespncricinfo).getBitmap();
                ByteArrayOutputStream baosespncricinfo = new ByteArrayOutputStream();
                bitmapespncricinfo.compress(Bitmap.CompressFormat.PNG, 100, baosespncricinfo);
                byte[] bespncricinfo = baosespncricinfo.toByteArray();
                Intent iespncricinfo = new Intent(Sources.this, SourceNews.class);
                iespncricinfo.putExtra("txtSource",txtespncricinfo);
                iespncricinfo.putExtra("imgSource",bespncricinfo);
                startActivity(iespncricinfo);
                break;
            case R.id.theverge:
                Source = (TextView)findViewById(R.id.txtTheverge);
                imageView = (ImageView) findViewById(R.id.imgTheverge);
                String txtTheverge = Source.getText().toString().trim();
                Drawable drawableTheverge=imageView.getDrawable();
                Bitmap bitmapTheverge= ((BitmapDrawable)drawableTheverge).getBitmap();
                ByteArrayOutputStream baosTheverge = new ByteArrayOutputStream();
                bitmapTheverge.compress(Bitmap.CompressFormat.PNG, 100, baosTheverge);
                byte[] bTheverge = baosTheverge.toByteArray();
                Intent iTheverge = new Intent(Sources.this, SourceNews.class);
                iTheverge.putExtra("txtSource",txtTheverge);
                iTheverge.putExtra("imgSource",bTheverge);
                startActivity(iTheverge);
                break;
            case R.id.nbcnews:
                Source = (TextView)findViewById(R.id.txtnbcnews);
                imageView = (ImageView) findViewById(R.id.imgnbcnews);
                String txtnbcnews = Source.getText().toString().trim();
                Drawable drawablenbcnews=imageView.getDrawable();
                Bitmap bitmapnbcnews= ((BitmapDrawable)drawablenbcnews).getBitmap();
                ByteArrayOutputStream baosnbcnews = new ByteArrayOutputStream();
                bitmapnbcnews.compress(Bitmap.CompressFormat.PNG, 100, baosnbcnews);
                byte[] bnbcnews = baosnbcnews.toByteArray();
                Intent inbcnews = new Intent(Sources.this, SourceNews.class);
                inbcnews.putExtra("txtSource",txtnbcnews);
                inbcnews.putExtra("imgSource",bnbcnews);
                startActivity(inbcnews);
                break;
            case R.id.techradar:
                Source = (TextView)findViewById(R.id.txttechradar);
                imageView = (ImageView) findViewById(R.id.imgtechradar);
                String txttechradar = Source.getText().toString().trim();
                Drawable drawabletechradar=imageView.getDrawable();
                Bitmap bitmaptechradar= ((BitmapDrawable)drawabletechradar).getBitmap();
                ByteArrayOutputStream baostechradar = new ByteArrayOutputStream();
                bitmaptechradar.compress(Bitmap.CompressFormat.PNG, 100, baostechradar);
                byte[] btechradar = baostechradar.toByteArray();
                Intent itechradar = new Intent(Sources.this, SourceNews.class);
                itechradar.putExtra("txtSource",txttechradar);
                itechradar.putExtra("imgSource",btechradar);
                startActivity(itechradar);
                break;
            case R.id.thenextweb:
                Source = (TextView)findViewById(R.id.txtthenextweb);
                imageView = (ImageView) findViewById(R.id.imgthenextweb);
                String txtthenextweb = Source.getText().toString().trim();
                Drawable drawablethenextweb=imageView.getDrawable();
                Bitmap bitmapthenextweb= ((BitmapDrawable)drawablethenextweb).getBitmap();
                ByteArrayOutputStream baosthenextweb = new ByteArrayOutputStream();
                bitmapthenextweb.compress(Bitmap.CompressFormat.PNG, 100, baosthenextweb);
                byte[] bthenextweb = baosthenextweb.toByteArray();
                Intent ithenextweb = new Intent(Sources.this, SourceNews.class);
                ithenextweb.putExtra("txtSource",txtthenextweb);
                ithenextweb.putExtra("imgSource",bthenextweb);
                startActivity(ithenextweb);
                break;
            case R.id.cryptocoinsnews:
                Source = (TextView)findViewById(R.id.txtcryptocoinsnews);
                imageView = (ImageView) findViewById(R.id.imgcryptocoinsnews);
                String txtcryptocoinsnews = Source.getText().toString().trim();
                Drawable drawablecryptocoinsnews=imageView.getDrawable();
                Bitmap bitmapcryptocoinsnews= ((BitmapDrawable)drawablecryptocoinsnews).getBitmap();
                ByteArrayOutputStream baoscryptocoinsnews = new ByteArrayOutputStream();
                bitmapcryptocoinsnews.compress(Bitmap.CompressFormat.PNG, 100, baoscryptocoinsnews);
                byte[] bcryptocoinsnews = baoscryptocoinsnews.toByteArray();
                Intent icryptocoinsnews = new Intent(Sources.this, SourceNews.class);
                icryptocoinsnews.putExtra("txtSource",txtcryptocoinsnews);
                icryptocoinsnews.putExtra("imgSource",bcryptocoinsnews);
                startActivity(icryptocoinsnews);
                break;
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        if(amIconnected()){

        }
        else{
            Intent i = new Intent(Sources.this,errornetwork.class);
            startActivity(i);
        }
    }
    private boolean amIconnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return  activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
