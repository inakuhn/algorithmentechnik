package dynprog;

import java.io.*;
import java.util.*;


class Rucksack01{
	public static void main (String args[]){
		
		int WerteTab[][];		// Matrix zur Berechnung der optimalen Komibantion (i X g)
		int anzahlDerObjekte;	// Anzahl Objekte
		int kapazitaet;			// max. Kapazit�t R�cksack
		int wert[];				// Speicher der Werte zu i
		int gewicht[];			// Speicher der Gewichte zu i
		int i;					// Aktuelles Objekt
		int g;					// Gewicht des Objekts
		int aktuellesGewicht;

		//Eingabe der Anzahl der Objekte und Kapazit�t Rucksack		
		Scanner eingabe = new Scanner(System.in);
		System.out.println("Anzahl der Objekte eingeben:");
		anzahlDerObjekte = eingabe.nextInt();
		System.out.println("Kapazit�t des Rucksacks eingeben:");
		kapazitaet = eingabe.nextInt();
		System.out.println();
		
		wert = new int [anzahlDerObjekte+1];
		gewicht = new int [anzahlDerObjekte+1];
		
		System.out.println("Wert und Gewicht der Gegenst�nde eingeben:");
		  for (i=1; i<=anzahlDerObjekte; i++)
		  {
		   System.out.println("Objekt " + i);
		   wert[i]=eingabe.nextInt();
		   gewicht[i]=eingabe.nextInt();
		   System.out.println();
		   }
	  		    
		  //Initialisiere WerteTab 
		  //Basisf�lle
		  WerteTab = new int [anzahlDerObjekte+1][kapazitaet+1];
		  for (g = 0; g <= kapazitaet; g++)
		  {
			  WerteTab[0][g]= 0;	//f�r Auswahl keines Objekts ist das Gewicht immer 0
		  }
		  for (i = 0; i <= anzahlDerObjekte; i++)
		  {
			  WerteTab[i][0]= 0;			//Es gibt keine Objekte mit Gewicht 0
		  }
		  
		  //Aufstellen der Kombinationen
		  // Zeile f�r Zeile in Matrix WerteTab durchgehen [i]
		  // von rechts nach link durchgehen, Kapazit�t - Gewicht [i]
		  for (i=1; i<=anzahlDerObjekte; i++)
		  {
			  for (g = kapazitaet; g >= gewicht[i]; g--)
				  if(wert[i] + WerteTab[i-1][g-gewicht[i]] > WerteTab[i-1][g])
				  	WerteTab[i][g] = wert[i] + WerteTab[i-1][g-gewicht[i]];
				  else
				  	WerteTab[i][g] = WerteTab[i-1][g];
				   
				  for (g = 0; g < gewicht[i]; g++)
					  WerteTab[i][g] = WerteTab[i-1][g];
		  }
		  
		  
		  // Ergebnissausgabe und suche der ausgew�hlten Objekte in WerteTab. 
          System.out.println("Maximaler Wert= " + WerteTab[anzahlDerObjekte][kapazitaet]);
          System.out.print("Eingepackte Objekte:");
          for (i = anzahlDerObjekte, aktuellesGewicht=kapazitaet; i > 0; i--)
              {
                if (aktuellesGewicht >= gewicht[i])
                   if ( WerteTab[i][aktuellesGewicht] == (wert[i] + WerteTab[i-1][aktuellesGewicht - gewicht[i]]) )
                    {
                       System.out.print("  " + i);
                       aktuellesGewicht -= gewicht[i];
                    }
              }
              System.out.println();
		}
		  
}
	
