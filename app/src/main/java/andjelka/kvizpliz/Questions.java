package andjelka.kvizpliz;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Questions
{
    List<Question> list = new ArrayList<>();

    DbHelper dbHelper;

    //Metoda koja vraca velicinu liste tj broj pitanja u listi
    public int getLength()
    {
        return list.size();
    }

    //Metoda koja vraca pitanje iz liste
    public String getQuestion(int a)
    {
        return list.get(a).getQuestion();
    }

    //Moguci odgovori
    public String getChoice(int index, int num)
    {
        return list.get(index).getChoice(num);
    }

    //Tacan odgovor iz liste
    public String getCorrectAnswer(int a)
    {
        return list.get(a).getAnswer();
    }

    //Inicijalnizacija liste u slucaju da je prazna
    public void initQuests(Context context)
    {
        dbHelper = new DbHelper(context);

        list = dbHelper.getAllQuestions();

        if(list.isEmpty())
        {
            dbHelper.addInitQuestion(new Question("Čime se bavi felinolog?", new String[] {"Proučavanjem mačaka", "Proučavanjem pasa", "Proučavanjem perja", "Proučavanjem lekovitih biljaka"}, "Proučavanjem mačaka"));

            list = dbHelper.getAllQuestions();
        }

    }


}
