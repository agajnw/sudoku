package View;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;


import Controler.*;

/**
 * klasa view bedaca ramka z menu i posiadajaca w sobie klase widok
 *
 */
public class View extends JFrame implements ActionListener
{
    /**
     * kontroler posiadany przez widok
     */
    private Kontroler VControler;
    /**
     * pasek menu
     */
    private JMenuBar pasekMenu;
    /**
     * element menu - opcje
     */
    private JMenu menuOpcji;
    /**
     * element menu - ustawienia
     */
    private JMenu menuUstawien;
    /**
     * element menu - poziom
     */
    private JMenu menuPoziom;
    /**
     * element opcji - czy mierzyc czas gry
     */
    public JMenuItem mierzCzas;
    /**
     * element opcji - czy sprawdzac bledy
     */
    public JMenuItem sprawdzBl;
    /**
     * element poziomu - poziom latwy
     */
    public JRadioButtonMenuItem latwy;
    /**
     * element poziomu - poziom sredni
     */
    public JRadioButtonMenuItem sredni;
    /**
     * element poziomu - poziom trudny
     */
    public JRadioButtonMenuItem trudny;
    /**
     * element opcji - restartuj plansze
     */
    public JMenuItem restart;
    /**
     * element opcji - pokaz rozwiazanie planszy
     */
    public JMenuItem pokazRozwiazanie;
    /**
     * element opcji - sprawdz rozwiazanie
     */
    private JMenuItem sprawdzRozwiazanie;
    /**
     * element opcji - nowa gra - wylosuj nowa plansze
     */
    public JMenuItem nowaGra;
    private static final long serialVersionUID = 1L;
    /**
     * obiekt klasy Widok przechowujacy i obslugujacy siatke planszy
     */
    private Widok widok;
    /**
     * konstruktor klasy View
     */
    public View()
    {
        widok = new Widok(this);
        setTitle("Sudoku");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(376, 424);
        dolaczMenu();
        setContentPane(widok);
        setVisible(true);

    }
    /**
     * inicjalizuje pasek menu
     */
    public void dolaczMenu()
    {

        //dolaczenie menu
        pasekMenu = new JMenuBar();
        setJMenuBar(pasekMenu);

        //menu opcje
        menuOpcji = new JMenu("Opcje");
        pokazRozwiazanie = new JMenuItem("Pokaz rozwiazanie");
        restart = new JMenuItem("Restart");
        nowaGra = new JMenuItem("Nowa plansza");
        menuOpcji.add(nowaGra);
        menuOpcji.addSeparator();
        menuOpcji.add(restart);
        menuOpcji.addSeparator();
        menuOpcji.add(pokazRozwiazanie);
        pasekMenu.add(menuOpcji);
        pokazRozwiazanie.addActionListener(this);
        restart.addActionListener(this);
        nowaGra.addActionListener(this);

        //menu poziom trudnosci
        ButtonGroup poziomy = new ButtonGroup();
        menuPoziom = new JMenu("Poziom trudnosci");
        latwy = new JRadioButtonMenuItem("Latwy");
        sredni = new JRadioButtonMenuItem("Sredni");
        trudny = new JRadioButtonMenuItem("Trudny");
        latwy.setSelected(true);
        poziomy.add(latwy);
        poziomy.add(sredni);
        poziomy.add(trudny);
        menuPoziom.add(latwy);
        menuPoziom.addSeparator();
        menuPoziom.add(sredni);
        menuPoziom.addSeparator();
        menuPoziom.add(trudny);
        pasekMenu.add(menuPoziom);
        latwy.addActionListener(this);
        sredni.addActionListener(this);
        trudny.addActionListener(this);

        //menu ustawienia
        menuUstawien = new JMenu("Ustawienia");
        mierzCzas = new JCheckBoxMenuItem("Mierz czas");
        sprawdzBl = new JCheckBoxMenuItem("Pokazuj bledy");
        menuUstawien.add(mierzCzas);
        menuUstawien.addSeparator();
        menuUstawien.add(sprawdzBl);
        pasekMenu.add(menuUstawien);
        mierzCzas.addActionListener(this);
        sprawdzBl.addActionListener(this);

        //sprawdz rozwiazanie
        sprawdzRozwiazanie = new JMenuItem("Sprawdz rozwiazanie");
        sprawdzRozwiazanie.addActionListener(this);
        pasekMenu.add(sprawdzRozwiazanie);
    }
    /**
     * ustawia kontroler obiektu
     *
     * @param a obiekt klasy Kontroler do przypisania referencji
     */
    public void setControler(Kontroler a)
    {
        widok.setControler(a);
        VControler = a;
    }
    /**
     * inicjalizuje plansze
     */
    public void inicjalizujPlansze()
    {
        widok.rysujPlansze();
        setContentPane(widok);
    }
    /**
     * aktualizuje dane pole
     *
     * @param i pierwszy indeks elementu w  planszy
     * @param j drugi indeks elementu w  planszy
     */
    public void aktualizujPole(int i, int j)
    {
        widok.aktualizujPole(i,j);
        setContentPane(widok);
    }
    /**
     * aktualizuje cala plansze
     */
    public void odswiezPlansze()
    {
        widok.odswiezPlansze();
        setContentPane(widok);
    }
    /**
     * zaznacza wszystkie bledy na planszy
     *
     */
    public void ustawBledne()
    {
        widok.ustawBledne();
        setContentPane(widok);
    }
    /**
     * odznacza wszystkie bledy na planszy
     *
     */
    public void ustawBezBledne()
    {
        widok.ustawBezBledne();
        setContentPane(widok);
    }
    /**
     * obsluguje nacisniecie przyciskow menu
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object target = e.getSource();
        if(target==nowaGra)
        {
            VControler.nowaPlansza();
        }
        else if(target==restart)
        {
            VControler.zaladujPoczatkowa();
        }
        else if(target==pokazRozwiazanie)
        {
            VControler.zaladujUzupelniona();
        }
        else if (target==latwy)
        {
            VControler.zmienPoziom(0);
        }
        else if (target==sredni)
        {
            VControler.zmienPoziom(1);
        }
        else if (target==trudny)
        {
            VControler.zmienPoziom(2);
        }
        else if (target==mierzCzas)
        {
            VControler.mierzCzas(mierzCzas.isSelected());
        }
        else if (target==sprawdzBl)
        {
            VControler.sprawdzajBledy(sprawdzBl.isSelected());
        }
        else if (target==sprawdzRozwiazanie)
        {
            VControler.sprawdzRozwiazanie();
        }
    }
}





