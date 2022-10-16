package andjelka.kvizpliz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class quiz extends AppCompatActivity
{

    AdView adView;

    Button answer, answer2, answer3, answer4;

    TextView score, question;

    ImageButton imageButton;

    private Questions mQuestions = new Questions();

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionsNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Ads
        AdView mAdView;
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.AdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        answer = findViewById(R.id.answer);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        score = findViewById(R.id.score);
        question = findViewById(R.id.question);
        imageButton = findViewById(R.id.imageButton);
        score.setText(R.string.yourScore + mScore);

        mQuestions.initQuests(getApplicationContext());
        updateQuestion();

        updateScore(mScore);
    }

    public void chooseAnswer(View view)
    {
        Button answer = (Button) view;
        if(answer.getText().equals(mAnswer))
        {
            mScore+=1;
            Toast.makeText(quiz.this, R.string.correctAnswer, Toast.LENGTH_SHORT).show();
            updateScore(mScore);
            updateQuestion();
        }
        else
        {
            gameOver();
        }
    }

    public void drive(View view)
    {
        Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSdEMSDCOhO7Gt0ZkNjW-XSPZGRhNfj-6JQ5UOIv6NLPv7DvSw/viewform?usp=sf_link"));
        startActivity(browse);
    }

    private void updateQuestion()
    {
        if(mQuestionsNumber<mQuestions.getLength())
        {
            question.setText(mQuestions.getQuestion(mQuestionsNumber));
            answer.setText(mQuestions.getChoice(mQuestionsNumber, 1));
            answer2.setText(mQuestions.getChoice(mQuestionsNumber, 2));
            answer3.setText(mQuestions.getChoice(mQuestionsNumber, 3));
            answer4.setText(mQuestions.getChoice(mQuestionsNumber, 4));
            mAnswer = mQuestions.getCorrectAnswer(mQuestionsNumber);
            mQuestionsNumber++;
        }
        else
        {
            Toast.makeText(quiz.this, R.string.gameCompleted, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(quiz.this, HighestScoreActivity.class);
            intent.putExtra("rezultat", mScore);
            startActivity(intent);
        }
    }

    private void updateScore(int points) {
        String yourResult = getString(R.string.yourScore,  mScore) + getString(R.string.from, mQuestions.getLength());
        score.setText(yourResult);
    }

    private void gameOver() {
        AlertDialog.Builder alertDialogBuilder =
                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setTitle(R.string.gameOver)
                        .setMessage(R.string.gameOverMessage)
                        .setNegativeButton(R.string.end, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                finish();
                            }
                        })
                        .setPositiveButton(R.string.tryAgain, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                mQuestions.initQuests(getApplicationContext());
                                mScore = 0;
                                score.setText(getString(R.string.yourScore, mScore));
                            }
                        });

        // Show the AlertDialog.
        AlertDialog alertDialog = alertDialogBuilder.show();

    }
}
