package View;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Controler.*;
/**
 * klasa widoku - odpowiada za stworzenie i obsluge siatki planszy
 *
 */
public class Widok extends JPanel implements ActionListener
{
    /**
     * referencja na obiekt klasy Kontroler
     */
    private Kontroler VControler;
    /**
     * referencja na obiekt klasy View
     */
    private View view;
    /**
     * tablica przyciskow odpowiadajacych polom planszy
     */
    private JButton[][] b;
    /**
     * ograniczenia layoutu
     */
    private GridBagConstraints gbc;
    /**
     * layout siatki
     */
    private GridBagLayout gbl;
    /**
     * zmienna pomocnicza potrzebna do generowania sciezek ikon
     */
    final String pocz = "b";
    /**
     * zmienna pomocnicza potrzebna do generowania sciezek ikon
     */
    final String zle = "z";
    /**
     * zmienna pomocnicza potrzebna do generowania sciezek ikon
     */
    final String rozszerzenie = ".jpg";
    private static final long serialVersionUID = 1L;
    /**
     * konstruktor widoku
     */
    Widok(View v)
    {
        view = v;
        b = new JButton[9][9];
        gbl=new GridBagLayout();
        gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.VERTICAL;
        setBackground(Color.BLACK);
        setLayout(gbl);
    }
    /**
     * inicjalizuje mape
     * @param a przekazany kontroler do modelu
     */
    public void setControler(Kontroler a)
    {
        VControler = a;
    }
    /**
     * inicjalizuje siatke planszy
     */
    public void rysujPlansze()
    {
        //siatka
        int lewy,gora;

        for (int i=0; i<9; i++)
            for (int j=0; j<9; j++)
            {
                lewy=0;
                gora=0;
                if (i==3 || i==6)
                    lewy=1;
                if (j==3 || j==6)
                    gora=1;
                String ikona;
                if (VControler.elemPoczatkowy(i,j))
                    ikona = VControler.getWartosc(i,j)+pocz+rozszerzenie;
                else
                    ikona = VControler.getWartosc(i,j)+rozszerzenie;
                b[i][j]=new JButton(new ImageIcon(ikona));
                b[i][j].setBorder(BorderFactory.createEmptyBorder());
                b[i][j].setContentAreaFilled(false);
                b[i][j].addActionListener(this);
                b[i][j].setFocusable(false);
                gbc.gridx=i;
                gbc.gridy=j+1;
                gbc.gridwidth=1;
                gbc.ipadx=1;
                gbc.ipady=1;
                gbc.insets=new Insets(gora,lewy,0,0);
                gbl.setConstraints(b[i][j],gbc);
                add(b[i][j]);
            }
    }
    /**
     * aktualizuje wyglad calej planszy
     */
    void odswiezPlansze()
    {
        for (int i=0; i<9; i++)
            for (int j=0; j<9; j++)
                aktualizujPole(i,j);
    }
    /**
     * aktualizuje wyglad danego pola
     *
     * @param i pierwszy indeks pola w planszy
     * @param j drugi indeks pola w planszy
     */
    public void aktualizujPole(int i,int j)
    {

        String ikona = VControler.getWartosc(i,j) + "";
        if (VControler.elemPoczatkowy(i,j))
            ikona += pocz;
        else if (VControler.czySprawdzajBledy() && (VControler.getWartosc(i,j)!=0) &&!VControler.czyPoprawny(i, j))
            ikona += zle;
        ikona += rozszerzenie;
        b[i][j].setIcon(new ImageIcon(ikona));

    }
    /**
     * zaznacza wszystkie bledy na planszy
     *
     */
    public void ustawBledne()
    {
        for (int i=0; i<9; i++)
            for (int j=0; j<9; j++)
                if(!VControler.elemPoczatkowy(i,j) && (VControler.getWartosc(i,j)!=0) && !VControler.czyPoprawny(i, j))
                {

                    String ikona = VControler.getWartosc(i,j)+zle + rozszerzenie;
                    b[i][j].setIcon(new ImageIcon(ikona));

                }

    }
    /**
     * odznacza wszystkie bledy na planszy
     *
     */
    public void ustawBezBledne()
    {
        for (int i=0; i<9; i++)
            for (int j=0; j<9; j++)
                if(!VControler.elemPoczatkowy(i,j))
                {
                    String ikona = VControler.getWartosc(i,j)+rozszerzenie;
                    b[i][j].setIcon(new ImageIcon(ikona));

                }

    }
    /**
     * tworzy okno dialogowe z wyborem odpowiedniej liczby do wstawienia
     */
    int wyborLiczby()
    {
        ImageIcon[] b3 = new ImageIcon[10];
        for (int i=0; i<10; i++)
        {
            String ikona = i+rozszerzenie;
            b3[i]= new ImageIcon(ikona);
        }
        int messageType = JOptionPane.QUESTION_MESSAGE;
        int code = JOptionPane.showOptionDialog(view, "Wybierz liczbe", "Sudoku", 0, messageType, null, b3, null);
        return code;
    }
    /**
     * obsluguje dzialanie na polach - przyciskach siatki planszy
     *
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object target = e.getSource();
        int a;
        for (int i=0; i<9; i++)
            for (int j=0; j<9; j++)
                if(target==b[i][j] && !VControler.elemPoczatkowy(i,j))
                {
                    a = wyborLiczby();
                    if (a!=-1)
                    {
                        VControler.zmienWartosc(i,j,a);
                        if (a==0)
                        {
                            System.out.println(i+ " " + j);
                            ustawBezBledne();
                            ustawBledne();
                        }
                        else
                            VControler.sprawdzPoprawnosc();
                    }
                }
    }

}


