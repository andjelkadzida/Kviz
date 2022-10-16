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
            dbHelper.addInitQuestion(new Question("Kada je osnovana SHS?", new String[] {"1914. godine", "1915.godine", "1918.godine", "1945.godine"}, "1918.godine"));
            dbHelper.addInitQuestion(new Question("Ko je agnostik?", new String[] {"Nevernik", "Osoba koja pati od brojnih fobija", "Osoba koja nema pouzdanja u svoje znanje", "Osoba koja smatra da viša duhovna sila (božanstvo) možda postoji, ali da se ne može utvrditi oblik i priroda te sile"}, "Osoba koja smatra da viša duhovna sila (božanstvo) možda postoji, ali da se ne može utvrditi oblik i priroda te sile"));
            dbHelper.addInitQuestion(new Question("Ko je osnovao kompaniju Microsoft?", new String[] {"Steve Jobs", "Bill Gates", "Lawrence (Larry) Page", "Mark Zuckerberg"}, "Bill Gates"));
            dbHelper.addInitQuestion(new Question("U kojim jedinicama se izražava magnetna indukcija?", new String[] {"Tesla(T)", "Veber(Wb)", "Džul(J)", "Paskal(Pa)"}, "Tesla(T)"));
            dbHelper.addInitQuestion(new Question("Koja je najveća evropska luka po prometu?", new String[] {"Roterdam", "Antverpen", "Marsej", "Hamburg"}, "Roterdam"));
            dbHelper.addInitQuestion(new Question("U sastav RNK ulaze?", new String[] {"Adenin, timin, citozin i guanin", "Adenin, citozin, guanin i uracil", "Adenin, citozin, uracil i timin", "Adenin, timin, guanin i uracil"}, "Adenin, citozin, guanin i uracil"));
            dbHelper.addInitQuestion(new Question("Afekti su emocionalni doživljaji koji su:", new String[] {"Slabi i dugotrajni", "Jaki i dugotrajni", "Slabi i kratkotrajni", "Jaki i kratkotrajni"}, "Jaki i kratkotrajni"));
            dbHelper.addInitQuestion(new Question("Sn je hemijski simbol?", new String[] {"Antimon", "Stroncijum", "Kalaj", "Srebro"}, "Kalaj"));
            dbHelper.addInitQuestion(new Question("Ko je autor pesme 'Santa Maria della Salute'?", new  String[] {"Laza Kostić", "Aleksa Šantić", "Milan Rakić", "Dušan Vasiljev"}, "Laza Kostić"));
            dbHelper.addInitQuestion(new Question("Ko peva pesmu 'Oči jedne žene'?", new String[]{"Miroslav Ilić", "Predrag Živković Tozovac", "Haris Džinović", "Šaban Šaulić"}, "Predrag Živković Tozovac"));
            dbHelper.addInitQuestion(new Question("Statički deo memorije, koji može samo da se čita, označava se kao:", new String[] {"RAM", "ROM", "Keš", "Buffer"}, "ROM"));
            dbHelper.addInitQuestion(new Question("Šta je JVM?", new String[] {"Java Visual Method", "Java Virtual Machine", "Java Visual Machine", "Java Virtual Method"}, "Java Virtual Machine"));
            dbHelper.addInitQuestion(new Question("Ko tumači lik Harijevog kuma u filmu 'Harry Potter'?", new String[] {"Geri Oldman", "David Thewlis", "Robbie Coltrane", "Alan Rickman"}, "Geri Oldman"));
            dbHelper.addInitQuestion(new Question("Ko je komponovao 'Odu radosti'?", new String[] {"Ludwig van Beethoven", "Wolfgang Amadeus Mozart", "Felix Mendelssohn", "Hector Berlioz"}, "Ludwig van Beethoven"));
            dbHelper.addInitQuestion(new Question("Čiji lik se nalazi na novčanici od 1000 dinara?", new String[] {"Vuka Stefanovića Karadžića", "Ðorda Vajferta", "Nikole Tesle", "Jovana Cvijića"}, "Ðorda Vajferta"));
            dbHelper.addInitQuestion(new Question("Adolescencija je:", new String[] {"Detinjstvo", "Zrelo doba", "Mladalačko doba", "Staračko doba"}, "Mladalačko doba"));
            dbHelper.addInitQuestion(new Question("Koji glumac nije igrao u filmu Maratonci trce pocasni krug?", new String[] {"Bogdan Diklić", "Bora Todorović", "Dragan Nikolić", "Zoran Radmilović"}, "Dragan Nikolić"));
            dbHelper.addInitQuestion(new Question("NATO savez je:", new String[] {"Ekonomski savez", "Kulturni savez", "Vojni savez", "Politički savez"}, "Vojni savez"));
            dbHelper.addInitQuestion(new Question("Koliko iznosi dužina staze za maratonsku trku?", new String[]{"42km", "35km", "22km", "32km"}, "42km"));
            dbHelper.addInitQuestion(new Question("Za koji fudbalski klub igra poljski fudbaler Robert Lewandowski?", new String[] {"FC Barcelona", "Borussia Dortmund", "FC Bayern München", "KKS Lech Poznan"}, "FC Barcelona"));
            dbHelper.addInitQuestion(new Question("Kakvi su ireverzibilni procesi?", new String[] {"Procesi koji se mogu kontrolisati", "Nepromenljivi procesi", "Kratkotrajni procesi", "Nepovratni procesi"}, "Nepovratni procesi"));
            dbHelper.addInitQuestion(new Question("Šta leči kardiolog?", new String[] {"Bolesti srca", "Bolesti jetre", "Nasledne bolesti", "Bolesti pluća"}, "Bolesti srca"));
            dbHelper.addInitQuestion(new Question("Koji broj na dresu nosi Miloš Teodosić?", new String[] {"9", "4", "11", "5"}, "4"));
            dbHelper.addInitQuestion(new Question("Maslinova grančica je simbol:", new String[] {"Mira", "Slobode", "Hrabrosti", "Pravde"}, "Mira"));
            dbHelper.addInitQuestion(new Question("Ko je osvojio Davis cup 2010. godine?", new String[] {"Francuska", "Češka", "Srbija", "Velika Britanija"}, "Srbija"));
            dbHelper.addInitQuestion(new Question("Na Eurosongu 2004. godine Srbiju je predstavljao Željko Joksimovic sa pesmom:", new String[] {"Nije ljubav stvar", "Lane moje", "Supermen", "Balada"}, "Lane moje"));
            dbHelper.addInitQuestion(new Question("Koje godine je slovo H uvedeno u srpski jezik?", new String[] {"1834.", "1835.", "1836.", "1838."}, "1836."));
            dbHelper.addInitQuestion(new Question("Na poljskom kanapka, a na srpskom:", new String[] {"Sendvič", "Čokolada", "Večera", "Posteljina"}, "Sendvič"));
            dbHelper.addInitQuestion(new Question("Najmlađi šampion u formuli 1 je:", new String[] {"Michael Schumacher", "Lewis Hamilton", "Sebastian Fetel", "Nico Rosberg"}, "Sebastian Fetel"));
            dbHelper.addInitQuestion(new Question("Kakvu vlast ima vlada Republike Srbije?", new String[] {"Sudsku", "Izvršnu", "Zakonodavnu", "Vrhovnu"}, "Izvršnu"));
            dbHelper.addInitQuestion(new Question("Najveca pustinja na Zemlji je:", new String[] {"Gobi", "Sahara", "Kalahari", "Velika pešcana pustinja"}, "Sahara"));
            dbHelper.addInitQuestion(new Question("Koji slikar je kreirao logo za Chupa Chups?", new String[] {"Pablo Picasso", "Leonardo da Vinci", "Salvador Dali", "Francisco Goya"}, "Salvador Dali"));
            dbHelper.addInitQuestion(new Question("Grad Valjevo je sedište:", new String[] {"Pčinjskog okruga", "Mačvanskog okruga", "Kolubarskog okruga", "Zlatiborskog okruga"}, "Kolubarskog okruga"));
            dbHelper.addInitQuestion(new Question("Šta proučava ekologija?", new String[] {"Životnu sredinu", "Insekte", "Poreklo reči", "Vina"}, "Životnu sredinu"));
            dbHelper.addInitQuestion(new Question("Legendarni nemački fudbaler Oliver Kahn igrao je na poziciji:", new String[] {"Veznog igrača", "Napadača", "Golmana", "Odbrambenog igrača"}, "Golmana"));
            dbHelper.addInitQuestion(new Question("Koliko traje jedna četvrtina u vaterpolo utakmici?", new String[] {"10 minuta", "7 minuta", "8 minuta", "5 minuta"}, "8 minuta"));
            dbHelper.addInitQuestion(new Question("U seriji Ubice mog oca, lik inspektora iz Odeljenja za krvne delikte tumači:", new String[] {"Vuk Kostić", "Marko Janketić", "Tihomir Stanić", "Slavko Štimac"}, "Vuk Kostić"));
            dbHelper.addInitQuestion(new Question("Prvi zvučni film je:", new String[] {"Veliki diktator", "Pevač džeza", "Nevinost bez zaštite", "Svetla New Yorka"}, "Pevač džeza"));
            dbHelper.addInitQuestion(new Question("Nordeus je kompanija koja se bavi pravljenjem:", new String[] {"Video igrica", "Antivirus programa", "Računarske opreme", "Programa za obradu fotografija"}, "Video igrica"));
            dbHelper.addInitQuestion(new Question("Olimpijske igre 2016. godine održane su u:", new String[] {"Londonu", "Pekingu", "Tokiju", "Rio de Žaneiru"}, "Rio de Žaneiru"));
            dbHelper.addInitQuestion(new Question("Koja zemlja je pobednik Eurosonga 2017. godine?", new String[] {"Portugalija", "Australija", "Ukrajina", "Izrael"}, "Portugalija"));
            dbHelper.addInitQuestion(new Question("Eutanazija je:", new String[] {"Ubistvo iz nehata", "Ubistvo iz milosrđa", "Ubistvo iz koristoljublja", "Ubistvo iz osvete"}, "Ubistvo iz milosrđa"));
            dbHelper.addInitQuestion(new Question("Koliko iznosi jedan inch?", new String[] {"1,54cm", "2,44cm", "2,54cm", "2,64cm"}, "2,54cm"));
            dbHelper.addInitQuestion(new Question("U kojoj državi se nalazi najviše vulkana?", new String[] {"U Japau", "U Indoneziji", "U Meksiku", "U Italiji"},"U Indoneziji"));
            dbHelper.addInitQuestion(new Question("Koji bend je osnovao Jimmy Page?", new String[] {"Led Zeppelin", "Queen", "Pink Floyd", "The Beatles"}, "Led Zeppelin"));
            dbHelper.addInitQuestion(new Question("Ko je napisao delo 'Gospodar prstenova'?", new String[] {"Suzanne Collins", "J.R.R.Tolkien", "J.K.Rowling", "George R.R.Martin"},"J.R.R.Tolkien"));
            dbHelper.addInitQuestion(new Question("Koje godine je Neil Armstrong sleteo na Mesec?", new String[] {"1959.", "1965.", "1969.", "1962."}, "1969."));
            dbHelper.addInitQuestion(new Question("Enolog je poznavalac:", new String[] {"Rakije", "Piva", "Viskija", "Vina"}, "Vina"));
            dbHelper.addInitQuestion(new Question("Kako se zove frontmen grupe The Rolling Stones?", new String[] {"Mick Jagger", "Michael Jordan", "Michael Jackson", "Jerry Lewis"}, "Mick Jagger"));
            dbHelper.addInitQuestion(new Question("Rotonda označava:", new String[] {"Silos", "Mlin", "Crkvu", "Cilindričnu građevinu"}, "Cilindričnu građevinu"));
            dbHelper.addInitQuestion(new Question("Šta je polihronija?", new String[] {"Višeboj", "Višebojnost", "Grčka proročica", "Privremeno slepilo"}, "Višebojnost"));
            dbHelper.addInitQuestion(new Question("Koji arhitekta je stvorio urbanističku koncepciju Novog Beograda?", new String[] {"Milan Zloković", "Jan Nevole", "Nikola Dobrović", "Aleksej Brkić"}, "Nikola Dobrović"));
            dbHelper.addInitQuestion(new Question("Šta su lagumi?", new String[] {"Podzemni prolazi ili prostorije", "Poluzatvoreni nadzemni prostori", "Zatvoreni prostori na osojnim padinama", "Prostorije na prirodno osunčanim prisojnim padinama"}, "Podzemni prolazi ili prostorije"));
            dbHelper.addInitQuestion(new Question("Čime se hrani svilena buba?", new String[] {"Dudovim lišćem", "Smolom", "Lišajevima", "Larvama mrava"}, "Dudovim lišćem"));
            dbHelper.addInitQuestion(new Question("Šta je 'bela kuga?'", new String[] {"Smanjenje nataliteta", "Smanjenje mortaliteta", "Porast nataliteta", "Porast mortaliteta"}, "Smanjenje nataliteta"));
            dbHelper.addInitQuestion(new Question("Eros i Amor su jedan drugome:", new String[] {"Osiromašenje građana", "Unikat", "Pandan", "Sinonimi"}, "Pandan"));
            dbHelper.addInitQuestion(new Question("Koja vrsta gramofonskih ploča se vrti na 33 obrtaja?", new String[] {"Ne postoji takva vrsta ploča", "Singl", "Extended play", "Long play"}, "Long play"));
            dbHelper.addInitQuestion(new Question("Prvi avion kompanije 'Air Serbia' nosi naziv:", new String[] {"Novak Đoković", "Vlade Divac", "Nikola Tesla", "Miki Manojlović"},  "Novak Đoković"));
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());
            dbHelper.addInitQuestion(new Question());





































            list = dbHelper.getAllQuestions();
        }

    }


}
