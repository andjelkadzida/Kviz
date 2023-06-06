package andjelka.kvizpliz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class HighestScoreActivity extends AppCompatActivity {

    //sharedPreferences
    private static final String PREF_NAME = "MyResults";
    private static final String SCORE = "score";
    private static final String HIGHSCORE = "highscore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);

        //Ad
        AdView adView;
        MobileAds.initialize(this, initializationStatus -> {
        });

        adView = findViewById(R.id.bannerAboveScores);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        TextView currentScore = findViewById(R.id.currentScore);
        TextView highestScore = findViewById(R.id.highestScore);

        //Preuzimanje rezultata iz prethodne aktivnosti
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int mCurrentScore = sharedPreferences.getInt(SCORE, 0);

        //Prikaz trenutnog rezultata
        currentScore.setText(getString(R.string.yourScore,  mCurrentScore));

        //SharedPreferences, key-value data
        int mHighestScore = sharedPreferences.getInt(HIGHSCORE, 0);
        if(mHighestScore >= mCurrentScore) {
            highestScore.setText(getString(R.string.highestScore, mHighestScore));
        }
        else {
            highestScore.setText(getString(R.string.newHighestScore, mCurrentScore));
        }
    }

    public void onRepeatClick(View view) {
        Intent intent = new Intent(HighestScoreActivity.this, quiz.class);
        startActivity(intent);
    }
}