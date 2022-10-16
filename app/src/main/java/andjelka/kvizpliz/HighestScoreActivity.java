package andjelka.kvizpliz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HighestScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);

        TextView currentScore = findViewById(R.id.currentScore);
        TextView highestScore = findViewById(R.id.highestScore);

        //Preuzimanje rezultata iz prethodne aktivnosti
        Intent intent = getIntent();
        int mCurrentScore = intent.getIntExtra("rezulat", 0);

        //Prikaz trenutnog rezultata
        currentScore.setText(R.string.yourScore + mCurrentScore);

        //SharedPreferences, key-value data
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        int mHighestScore = sharedPreferences.getInt("najboljiRezultat", 0);
        if(mHighestScore >= mCurrentScore) {
            highestScore.setText(R.string.highestScore + mHighestScore);
        }
        else {
            highestScore.setText(R.string.newHighestScore + mCurrentScore);
            //Upisivanje novog najboljeg rezultata
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("najboljiRezultat", mCurrentScore);
            editor.apply();
        }
    }

    public void onRepeatClick(View view) {
        Intent intent = new Intent(HighestScoreActivity.this, quiz.class);
        startActivity(intent);
    }
}