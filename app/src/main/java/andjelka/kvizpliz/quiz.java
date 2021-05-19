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
    DbHelper helper = new DbHelper(this);


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



        answer = (Button)findViewById(R.id.answer);
        answer2 = (Button)findViewById(R.id.answer2);
        answer3 = (Button)findViewById(R.id.answer3);
        answer4 = (Button)findViewById(R.id.answer4);
        score = (TextView)findViewById(R.id.score);
        question = (TextView)findViewById(R.id.question);
        imageButton=(ImageButton)findViewById(R.id.imageButton);
        score.setText("Rezultat: " + mScore);

        mQuestions.initQuests(getApplicationContext());
        updateQuestion();


    }

    public void chooseAnswer(View view)
    {
        Button answer = (Button) view;
        if(answer.getText().equals(mAnswer))
        {
            mScore+=1;
            Toast.makeText(quiz.this, "Tačan odgovor :-)", Toast.LENGTH_SHORT).show();
            updateQuestion();
        }
        else
        {
            gameOver();
        }
    }

    public void drive(View view)
    {
        Intent browse=new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSdEMSDCOhO7Gt0ZkNjW-XSPZGRhNfj-6JQ5UOIv6NLPv7DvSw/viewform?usp=sf_link"));
        startActivity(browse);
    }

    private void updateQuestion()
    {
        if(mQuestionsNumber<mQuestions.getLength())
        {
            question.setText(mQuestions.getQuestion(mQuestionsNumber));
            answer.setText(mQuestions.getChoice(mQuestionsNumber, 0));
            answer2.setText(mQuestions.getChoice(mQuestionsNumber, 1));
            answer3.setText(mQuestions.getChoice(mQuestionsNumber, 2));
            answer4.setText(mQuestions.getChoice(mQuestionsNumber, 3));
            mAnswer = mQuestions.getCorrectAnswer(mQuestionsNumber);
            mQuestionsNumber++;
        }
        else
        {
            Toast.makeText(quiz.this, "Kviz kompletiran!", Toast.LENGTH_SHORT).show();
            /*Intent intent = new Intent(quiz.this, HighestScore.class);
            intent.putExtra("score", mScore);
            startActivity(intent);*/
        }
    }
    private void gameOver() {
        AlertDialog.Builder alertDialogBuilder =
                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setTitle("IGRA JE ZAVRŠENA")
                        .setMessage("Igra je završena! Da li želite da pokušate ponovo?")
                        .setNegativeButton("KRAJ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                finish();
                            }
                        })
                        .setPositiveButton("POKUŠAJ PONOVO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                mQuestions.initQuests(getApplicationContext());
                               // updateQuestion(r.nextInt(mQuestionsLenght));
                                score.setText("Rezultat: " + 0);
                                mScore = 0;
                            }
                        });

        // Show the AlertDialog.
        AlertDialog alertDialog = alertDialogBuilder.show();


    }
}
