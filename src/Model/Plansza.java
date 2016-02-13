package Model;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * 
 *klasa modelu reprezentujaca plansze
 */
public class Plansza {
	/**
	 * sciezka pliku z planszami na poziomie latwym
	 */
	private final String latwe= "latwe.txt";
	/**
	 * sciezka pliku z planszami na poziomie srednim
	 */
	private final String srednie= "srednie.txt";
	/**
	 * sciezka pliku z planszami na poziomie trudnym
	 */
	private final String trudne= "trudne.txt";
	/**
	 * tablica przechowujaca elementy planszy poczatkowej
	 */
	private Element[][] poczatkowa; //poczatkowa plansza
	/**
	 * tablica przechowujaca elementy planszy uzupelnionej
	 */
	private Element[][] uzupelniona; //uzupelniona plansza
	/**
	 * tablica przechowujaca elementy planszy aktualnej
	 */
	private Element[][] aktualna; //aktualna plansza
	/**
	 * zmienna przechowujaca aktualny poziom gry
	 */
	private String aktualny_poziom;
	/**
	 * obiekt klasy Ustawienia do obslugi dodatkowych ustawien gry
	 */
	private Ustawienia ustawienia;
	/**
	 * zmienna przechowujaca moment rozpoczecia mierzenia czasu
	 */
	private long czasStart;
	/**
	 * zmienna przechowujaca moment zakonczenia
	 */
	private long czasStop;
	/**
	 * konstruktor klasy Plansza
	 */
	public Plansza()	{
		ustawienia = new Ustawienia();
		poczatkowa = new Element[9][9];
		uzupelniona = new Element[9][9];
		aktualna = new Element[9][9];
		aktualny_poziom=latwe;	
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
				aktualna[i][j] = new Element();
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
				poczatkowa[i][j] = new Element();
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
				uzupelniona[i][j] = new Element();
	}
	/**
	 * zaladowanie nowej planszy
	 */
	public void nowaGra()	{

		try {
			wczytajZPliku();
		} catch (IOException e) {
			e.printStackTrace();
		}
		zmierzStart(System.nanoTime());
	}
	/**
	 * laduje poczatkowa zawartosc planszy
	 */
	public void zaladujPoczatkowa()	{
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
			{
				aktualna[i][j].ustawWartosc(poczatkowa[i][j].getWartosc());	
				if (poczatkowa[i][j].czyPoczatkowy())
					aktualna[i][j].jestPoczatkowy(true);
				else
					aktualna[i][j].jestPoczatkowy(false);
			}
	}
	/**
	 * laduje zawartosc planszy uzupelnionej do aktualnie wyswietlanej
	 */
	public void zaladujUzupelniona()	{
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
			{
				aktualna[i][j].ustawWartosc(uzupelniona[i][j].getWartosc());	
				if (uzupelniona[i][j].czyPoczatkowy())
					aktualna[i][j].jestPoczatkowy(true);
				else
					aktualna[i][j].jestPoczatkowy(false);
			}
	}
	/**
	 * 
	 * @param i pierwszy indeks elementu w tablicy aktualna
	 * @param j drugi indeks elementu w tablicy aktualna
	 * @param nowaWartosc nowa wartosc elementu
	 * ustawia nowa wartosc pola wartosc elementu o indeksach i,j
	 */
	public void zmienWartosc(int i, int j, int nowaWartosc)	{
		aktualna[i][j].ustawWartosc(nowaWartosc);
	}	
	/**
	 * 
	 * @param i pierwszy indeks elementu w tablicy aktualna
	 * @param j drugi indeks elementu w tablicy aktualna
	 * zwraca wartosc pola wartosc elementu o indeksach i,j
	 */
	public int getWartosc(int i, int j)	{
		return aktualna[i][j].getWartosc();
	}
	/**
	 * 
	 * @param poziom nowy poziom gry
	 * zmienia poziom gry na podany
	 */
	public void zmienPoziom(int poziom)	{
		if (poziom==0)
			aktualny_poziom=latwe;
		else if (poziom==1)
			aktualny_poziom=srednie;
		else if (poziom==2)
			aktualny_poziom=trudne;
	}
	/**
	 * zwraca aktualny poziom gry
	 */
	public int getPoziom()	{
		if (aktualny_poziom==latwe)
			return 0;
		else if (aktualny_poziom==srednie)
			return 1;
		else
			return 2;
	}
	/**
	 * zwraca true jesli zawartosc wyswietlanej planszy jest poprawnym rozwiazaniem
	 */
	public boolean sprawdzPoprawnosc(){
		
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
		if (aktualna[i][j].getWartosc() != uzupelniona[i][j].getWartosc())
				return false;
		return true;
				
	}
	/**
	 * zwraca true jesli dany element jest w planszy poczatkowej
	 * 
	 * @param i pierwszy indeks elementu w planszy
	 * @param j drugi indeks elementu w planszy
	 */
	public boolean czyPoczatkowy(int i, int j)	{
		return aktualna[i][j].czyPoczatkowy();
	}
	/**
	 * 
	 * wlacza/wylacza sprawdzanie bledow
	 */
	public void sprawdzBl(boolean x)	{	
		ustawienia.ustawBledy(x);
	}
	/**
	 * 
	 * zwraca true jesli wlaczona jest opcja sprawdzania bledow
	 */
	public boolean czySprawdzaBledy()	{	
		return ustawienia.czyBledy();
	}
	/**
	 * zwraca true jesli dany element jest poprawny
	 * 
	 * @param i pierwszy indeks elementu w planszy
	 * @param j drugi indeks elementu w planszy
	 */
	public boolean czyPoprawny (int i, int j)	{
		//wiersze
		for (int k=0;k<9;k++)
			if ( (k!=j) && (aktualna[i][j].getWartosc() == aktualna[i][k].getWartosc() ))
				return false;

		//kolumny
		for (int k=0;k<9;k++)
			if ( (k!=i) && (aktualna[i][j].getWartosc() == aktualna[k][j].getWartosc()))
				return false;
		
		//kwadrat
		
		// if i % 3 = 0 i = i, i+1, i+2
		int [] a = new int[3];
		int [] b = new int[3];
		int modi = i%3;
		int modj = j%3;
		
		if (modi==0)
		{
			a[0]=i;
			a[1]=i+1;
			a[2]=i+2;
		}
		else if (modi==1)
		{
			a[0]=i-1;
			a[1]=i;
			a[2]=i+1;
		}
		else
		{
			a[0]=i-2;
			a[1]=i-1;
			a[2]=i;
		}
		
		if (modj==0)
		{
			b[0]=j;
			b[1]=j+1;
			b[2]=j+2;
		}
		else if (modj==1)
		{
			b[0]=j-1;
			b[1]=j;
			b[2]=j+1;
		}
		else
		{
			b[0]=j-2;
			b[1]=j-1;
			b[2]=j;
		}
		for (int k=0;k<3;k++)
			for (int m=0;m<3;m++)
			{
				if ( (a[k]==i) && (b[m]==j))
					continue;
				else if (aktualna[i][j].getWartosc() == aktualna[a[k]][b[m]].getWartosc())
					return false;
			}

		return true;
	}
	public int ileBledow() {
		int licznikBledow=0;
		for (int i=0;i<9;i++)
			for (int j=0;j<9;j++)
			{
				if (aktualna[i][j].getWartosc()!=uzupelniona[i][j].getWartosc())	
					licznikBledow++;
			}
		return licznikBledow;
	}
	/**
	 * 
	 * wlacza mierzenie czasu
	 */
	public void mierzCzas(boolean x)	{
		ustawienia.ustawCzas(x);
	}
	/**
	 * 
	 * zwraca true jesli wlaczona jest opcja mierzenia czasu
	 */
	public boolean czyMierzyCzas()	{
		return ustawienia.czyCzas();
	}
	/**
	 * 
	 * ustawia poczatek mierzenia czasu
	 * 
	 * @param start poczatek mierzenia czasu
	 */
	public void zmierzStart(long start){
		czasStart = start;
	}
	/**
	 * 
	 * ustawia koniec mierzenia czasu
	 * 
	 * @param start koniec mierzenia czasu
	 */
	public void zmierzStop(long stop){
		czasStop = stop;
	}
	/**
	 * 
	 * zwraca rozpoczecie mierzenia czasu
	 */
	public long getCzasStart(){
		return czasStart;
	}
	/**
	 * 
	 * zwraca rozpoczecie mierzenia czasu
	 */
	public long getCzasStop(){
		return czasStop;
	}
	/**
	 * wczytuje odpowiednia plansze z pliku,
	 * sciezka pliku jest tworzona na podstawie zawartosci pola aktualny_poziom.
	 * Pliki zawieraja plansze w nastepujacej konwencji:
	 * w kazdej linii jest 1 plansza, najpierw w wersji poczatkowej, pozniej w uzupelnionej
	 */
	  public  void wczytajZPliku() throws IOException  {

		 String path= aktualny_poziom; //sciezka pliku z wszystkimi planszami planszami
		  
		  //tu jakies losowanie nr planszy, pozniej dodac 'przeskoczenie' odpowiedniej liczby linii
		  Random generator = new Random();
		  int skok = generator.nextInt(27);
		  
	    try {
	  	  	BufferedReader we = new BufferedReader(new FileReader(path)); //wczytanie pliku
	  	  	
	  	  	for (int i=0;i<skok;i++)
	  	  		we.readLine();
	  	  	
	  	  	String linia = we.readLine();	//czytanie odpowiedniej linii
	  	  	StringTokenizer t = new StringTokenizer(linia); //token do separacji zawartosci linii
	        for (int j=0;j<9;j++)
	        		for (int i=0;i<9;i++)
	        		{
	        			poczatkowa[i][j].ustawWartosc(Integer.parseInt(t.nextToken())); //wczytanie poczatkowej planszy
	        			if (poczatkowa[i][j].getWartosc()!=0)
	        				poczatkowa[i][j].jestPoczatkowy(true);
	        			else
	        				poczatkowa[i][j].jestPoczatkowy(false);
	        		}
	        for (int j=0;j<9;j++)
	    		for (int i=0;i<9;i++)
	    		{
	    			uzupelniona[i][j].ustawWartosc(Integer.parseInt(t.nextToken())); //wczytanie uzupelnionej planszy
        			if (poczatkowa[i][j].getWartosc()!=0)
        				uzupelniona[i][j].jestPoczatkowy(true);
        			else
        				uzupelniona[i][j].jestPoczatkowy(false);
			}	        
	    	we.close();
	        }
	    catch (EOFException ignored) {
	        System.out.println("[EOF]"); //zignoruj EOF
	    }
        zaladujPoczatkowa();   	
	 }
				
}

