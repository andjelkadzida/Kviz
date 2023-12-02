package andjelka.kvizpliz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper
{

    //Ime baze
    public static final String DbName = "kvizPliz.db";
    //Verzija baze
    private static final int DbVersion = 1;
    //Ime tabele
    private static final String tablePitanja = "Pitanja";
    //Kolone
    private static final String KEY = "id";
    private static final String QUESTION = "question";
    private static final String CHOICE1 = "choice1";
    private static final String CHOICE2 = "choice2";
    private static final String CHOICE3 = "choice3";
    private static final String CHOICE4 = "choice4";
    private static final String ANSWER = "answer";

    //Upit za kreiranje tabele
    private static final String createTableQuestion ="CREATE TABLE "
            + tablePitanja + "(" + KEY
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + QUESTION + " TEXT,"
            + CHOICE1 + " TEXT, " + CHOICE2 + " TEXT, " + CHOICE3 + " TEXT, "
            + CHOICE4 + " TEXT, " + ANSWER + " TEXT);";

    //Konstruktor za bazu koji u sebi ima parametre kao sto su context, ime baze, verzija
    public DbHelper(Context context)
    {
        super(context, DbName, null, DbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Izvrsavanje upita za kreiranje tabele
        db.execSQL(createTableQuestion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //Brisi tabelu ako postoji
        db.execSQL("DROP TABLE IF EXISTS '" + createTableQuestion + "'");
        onCreate(db);
    }


    public long addInitQuestion(Question question)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        //Kreiranje instance klase contentValues tj klase koja sluzi za unosenje novih redova u tabelu
        ContentValues contentValues = new ContentValues();
        contentValues.put(QUESTION, question.getQuestion());
        contentValues.put(CHOICE1, question.getChoice(0));
        contentValues.put(CHOICE2, question.getChoice(1));
        contentValues.put(CHOICE3, question.getChoice(2));
        contentValues.put(CHOICE4, question.getChoice(3));
        contentValues.put(ANSWER, question.getAnswer());
        //Unos reda
        return database.insert(tablePitanja, null, contentValues);
    }

    //Uzimanje svih podataka iz tabele i pakovanje u ArrayList tipa Question
    public List<Question> getAllQuestions()
    {
        List<Question> questionList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + tablePitanja;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Prolazak kroz celu listu
        if(cursor.moveToFirst())
        {
            do
            {
                //Kreiranje instance klase Question
                Question question = new Question();

                //Citanje pitanja iz baze
                String questionText = cursor.getString(cursor.getColumnIndex(QUESTION));
                question.setQuestion(questionText);

                //Izbor 1
                String choice1Text = cursor.getString(cursor.getColumnIndex(CHOICE1));
                question.setChoice(0, choice1Text);

                //Izbor 2
                String choice2Text = cursor.getString(cursor.getColumnIndex(CHOICE2));
                question.setChoice(1, choice2Text);

                //Izbor 3
                String choice3Text = cursor.getString(cursor.getColumnIndex(CHOICE3));
                question.setChoice(2, choice3Text);

                //Izbor 4
                String choice4Text = cursor.getString(cursor.getColumnIndex(CHOICE4));
                question.setChoice(3, choice4Text);

                //Tacan odgovor
                String answerText = cursor.getString(cursor.getColumnIndex(ANSWER));
                question.setAnswer(answerText);

                //Dodavanje na listu pitanja
                questionList.add(question);
            }
            while (cursor.moveToNext());
            Collections.shuffle(questionList);
        }
        return questionList;
    }

}
