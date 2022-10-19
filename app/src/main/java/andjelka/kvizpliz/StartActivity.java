package andjelka.kvizpliz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;



public class StartActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Ads
        AdView adView;
        MobileAds.initialize(this, initializationStatus -> {
        });

        adView = findViewById(R.id.BannerAd);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }

    public void startQuiz(View view)
    {
        Intent intent = new Intent(StartActivity.this, quiz.class);
        startActivity(intent);
    }

    public void showHighscores(View view) {
        Intent i = new Intent(StartActivity.this, HighestScoreActivity.class);
        startActivity(i);
    }

}