package Controler;

import Model.*;
import View.*;
import javax.swing.JOptionPane;

/**
 *
 * klasa bedaca glownym kontrolerem projektu
 *
 */
public class Kontroler
{
    /**
     * referencja do modelu
     */
    private Plansza cPlansza;
    /**
     * referencja do widoku
     */
    private View cWidok;
    /**
     *
     * konstruktor klasy Kontroler
     *
     * @param a - obiekt klasy Plansza potrzebny do przypisania referencji
     * @param b - obiekt klasy View potrzebny do przypisania referencji
     */
    public Kontroler(Plansza a, View b)
    {
        cPlansza = a;
        cWidok = b;

    }
    /**
     * Laduje poczatkowe wartosci do tablicy aktualna
     */
    public void zaladujPoczatkowa()
    {
        cPlansza.zaladujPoczatkowa();
        cWidok.odswiezPlansze();
    }
    /**
     * Laduje uzupelnione wartosci do tablicy aktualna
     */
    public void zaladujUzupelniona()
    {
        cPlansza.zaladujUzupelniona();
        cWidok.odswiezPlansze();
    }
    /**
     * Laduje pierwsza gre - inicjalizuje plansze
     */
    public void nowaGra()
    {
        cPlansza.nowaGra();
        cWidok.inicjalizujPlansze();

    }
    /**
     * Laduje nowa plansze
     */
    public void nowaPlansza()
    {
        cPlansza.nowaGra();
        cWidok.odswiezPlansze();
    }
    /**
     *
     * zmienia wartosc odpowiedniego elementu planszy na podana
     *
     * @param i - pierwszy indeks elementu w planszy
     * @param j - drugi indeks elementu w planszy
     * @param nowaWartosc - nowa wartosc elementu
     */
    public void zmienWartosc(int i, int j, int nowaWartosc)
    {
        cPlansza.zmienWartosc(i, j, nowaWartosc);
        cWidok.aktualizujPole(i,j);
    }
    /**
     *
     * zwraca wartosc odpowiedniego elementu
     *
     * @param i - pierwszy indeks elementu w planszy
     * @param j - drugi indeks elementu w planszy
     */
    public int getWartosc(int i, int j)
    {
        return cPlansza.getWartosc(i,j);
    }
    /**
     *
     * zmienia poziom na wybrany (0 - poziom latwy, 1 - sredni, 2 - trudny)
     *
     * @param poz - nowy poziom gry
     */
    public void zmienPoziom(int poz)
    {
        if (cPlansza.getPoziom()!=poz)
        {
            cPlansza.zmienPoziom(poz);
            cPlansza.nowaGra();
            cWidok.odswiezPlansze();
        }
    }
    /**
     *
     * tworzy okno dialogowe po ukonczeniu planszy
     */
    public void planszaUkonczona()
    {
        String [] b2 = {"Graj dalej", "Wyjscie"};
        int code;
        int messageType = JOptionPane.INFORMATION_MESSAGE;
        cPlansza.zmierzStop(System.nanoTime());
        if (cPlansza.czyMierzyCzas())
        {
            String tekst= obliczCzas();
            code = JOptionPane.showOptionDialog(cWidok, tekst, "Sudoku", 0, messageType, null, b2, null);

        }
        else
            code = JOptionPane.showOptionDialog(cWidok, "Gratulacje, plansza ukonczona!", "Sudoku", 0, messageType, null, b2, null);
        if (code==1)
            System.exit(0);
        else
            nowaPlansza();

    }
    /**
     *
     * oblicza czas gry
     */
    public String obliczCzas()
    {
        double ileSekund = Math.round((cPlansza.getCzasStop()-cPlansza.getCzasStart())/ 1.0E09);
        return "Gratulacje, plansza ukonczona! Zajelo Ci to " + (int)ileSekund + " sekund.";
    }
    /**
     *
     * zwraca true jesli dany element jest w planszy poczatkowej
     *
     * @param i - pierwszy indeks elementu w planszy
     * @param j - drugi indeks elementu w planszy
     */
    public boolean elemPoczatkowy(int i, int j)
    {
        return cPlansza.czyPoczatkowy(i,j);
    }
    /**
     *
     * ustawia opcje mierzenia czasu
     *
     * @param x - nowa wartosc pola czyMierzyc
     */
    public void mierzCzas(boolean x)
    {
        cPlansza.mierzCzas(x);
    }
    /**
     *
     * ustawia opcje sprawdzania bledow
     *
     * @param x - nowa wartosc pola czySprawdzajBledy
     */
    public boolean czySprawdzajBledy()
    {
        return cPlansza.czySprawdzaBledy();
    }
    /**
     *
     * ustawia opcje sprawdzania bledow
     *
     * @param x - nowa wartosc pola czySprawdzajBledy
     */
    public void sprawdzajBledy(boolean x)
    {
        cPlansza.sprawdzBl(x);
        if (x)
            cWidok.ustawBledne();
        else
            cWidok.ustawBezBledne();
    }
    /**
     *
     * zwraca true jesli element pola jest poprawny
     *
     * @param x - nowa wartosc pola czyMierzyc
     */
    public boolean czyPoprawny(int i, int j)
    {
        return cPlansza.czyPoprawny(i,j);
    }
    /**
     * sprawdza, czy aktualna zawartosc planszy odpowiada rozwiazaniu po kazdym wykonaniu ruchu
     */
    public void sprawdzPoprawnosc()
    {
        if (cPlansza.sprawdzPoprawnosc())
            planszaUkonczona();
    }
    /**
     * sprawdza jakosc aktualnego rozwiazania
     */
    public void sprawdzRozwiazanie()
    {
        String [] b2 = {"Ok"};
        int messageType = JOptionPane.INFORMATION_MESSAGE;
        int ileBledow = cPlansza.ileBledow();
        String tekst = "Twoje rozwiazanie ma bledy na " + ileBledow + " polach.";
        JOptionPane.showOptionDialog(cWidok, tekst, "Sudoku", 0, messageType, null, b2, null);
    }

}



