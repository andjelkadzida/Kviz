package andjelka.kvizpliz;

public class Question
{
    private String question;
    private String [] choice = new String[4];
    private String answer;

    //Podrazumevani konstruktor
    public Question()
    {
    }

    //Parametarizovani konstruktor
    public Question(String question, String[] choices, String answer)
    {
        this.question = question;
        this.choice[0] = choices[0];
        this.choice[1] = choices[1];
        this.choice[2] = choices[2];
        this.choice[3] = choices[3];
        this.answer = answer;
    }

    //Getteri i setteri

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getChoice(int i)
    {
            return choice[i];
    }

    public void setChoice(int i, String choice)
    {
        this.choice[i] = choice;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
}
