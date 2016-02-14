package Model;

/**
 * klasa obslugujaca dodatkowe ustawienia
 *
 */
public class Ustawienia
{
    /**
     * zmienna ma wartosc true jesli jest wlaczona opcja pokazywania bledow
     */
    private boolean bledy;
    /**
     * zmienna ma wartosc true jesli jest wlaczona opcja mierzenia czasu
     */
    private boolean mierzCzas;
    /**
     * konstruktor klasy Ustawienia - domyslnie pola bledy i mierzCzas ustawione na false
     */
    public Ustawienia()
    {
        bledy = false;
        mierzCzas = false;
    }
    /**
     *
     * ustawia nowa wartosc pola bledy
     *
     * @param x nowa wartosc pola bledy
     *
     */
    public void ustawBledy(boolean x)
    {
        bledy = x;
    }
    /**
     * 	 ustawia nowa wartosc pola mierzCzas
     *
     * @param x nowa wartosc pola mierzCzas
     *
     */
    public void ustawCzas(boolean x)
    {
        mierzCzas = x;
    }
    /**
     * zwraca true jesli jest wlaczona opcja pokazywania bledow
     */
    public boolean czyBledy()
    {
        return bledy;
    }
    /**
     * zwraca true jesli jest wlaczona opcja mierzenia czasu
     */
    public boolean czyCzas()
    {
        return mierzCzas;
    }

}
