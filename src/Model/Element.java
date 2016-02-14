package Model;

/**
 * klasa reprezentujaca element tablicy
 *
 *
 */
public class Element
{
    /**
     * wartosc pola planszy
     */
    private int wartosc;
    /**
     * zmienna ma wartosc true jesli element jest w tablicy poczatkowej
     */
    private boolean poczatkowy;
    /**
     *
     * konstruktor klasy Element - domyslnie pole poczatkowy ustawione na false
     *
     *
     */
    public Element()
    {
        poczatkowy = false;
    }
    /**
     *
     * ustawia nowa wartosc pola wartosc
     *
     * @param ile nowa wartosc elementu
     */
    public void ustawWartosc(int ile)
    {
        wartosc = ile;
    }
    /**
     *
     *
     * zwraca zawartosc pola wartosc
     */

    public int getWartosc()
    {
        return wartosc;
    }
    /**
     *
     *
     * zwraca true jesli element jest w tablicy poczatkowej
     */
    public boolean czyPoczatkowy()
    {
        return poczatkowy;
    }
    /**
     *
     * ustawia wartosc pola poczatkowy na podana wartosc
     *
     * @param x nowa wartosc pola poczatkowy
     */
    public void jestPoczatkowy(boolean x)
    {
        poczatkowy=x;
    }
}
