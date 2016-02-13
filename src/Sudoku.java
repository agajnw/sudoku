
import Model.*;
import View.*;
import Controler.*;
/**
 * glowna klasa posiadajace metode main w ktorej sa inicjalizowane wszystkie poszczegolne obiekty
 *
 */
public class Sudoku {


	public static void main(String[] args){
		
		Plansza nowaPlansza;
		View nowyWidok;
		Kontroler nowyKontroler;
		nowaPlansza = new Plansza();
		nowyWidok = new View();
		nowyKontroler = new Kontroler(nowaPlansza,nowyWidok);
		nowyWidok.setControler(nowyKontroler);
	    nowyKontroler.nowaGra();
	}

}
