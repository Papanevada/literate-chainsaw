package receiverTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import command.Coller;
import command.Command;
import receiver.Moteur;
import receiver.MoteurImpl;
import state.Buffer;
import state.ClipBoard;
import state.ClipboardImpl;
import state.Selection;

/**
 * Fichier Test Coller
 * 
 * @author Alexis LE MASLE et Fanny PRIEUR
 * 
 */

public class Jcoller {

	
	
	/**
	 * Coller la chaine=abcdef et le stringBuffer=ABCDEFGH (concat�nation donne abcdefABCDEFGH )
	 * test en assertTrue
	 */
	@Test
	public void testColler2() {
		String chaine = "abcdef";

		StringBuffer stringBuffer = new StringBuffer("ABCDEFGH");
		Buffer buffer = new Buffer();
		Selection selection = new Selection();
		ClipBoard pressePapier = new ClipboardImpl();

		pressePapier.setClip(chaine);
		buffer.setBuffer(stringBuffer);
	
		Moteur moteur = new MoteurImpl(buffer, pressePapier, selection);
		Command coller = new Coller(moteur);
		coller.execute();

		assertTrue(("abcdefABCDEFGH").compareTo(buffer.getBuffer().toString()) == 0);

	}
	
	
	/**
	 * selectionne le les 8 caract�res de la stringBuffer
	 * Coller  le stringBuffer=ABCDEFGH en premeier et la chaine=abcdef  (concat�nation donne ABCDEFGHabcdef )
	 * test en assertTrue
	 */

	@Test
	public void testColler3() {
		String chaine = "abcdef";

		StringBuffer stringBuffer = new StringBuffer("ABCDEFGH");
		Buffer buffer = new Buffer();
		Selection selection = new Selection();
		ClipBoard pressePapier = new ClipboardImpl();
		

		pressePapier.setClip(chaine);
		buffer.setBuffer(stringBuffer);
		
		selection.setDebut(8);
		selection.setFin(8);
		Moteur moteur = new MoteurImpl(buffer, pressePapier, selection);
		Command coller = new Coller(moteur);
		coller.execute();

		assertTrue(("ABCDEFGHabcdef").compareTo(buffer.getBuffer().toString()) == 0);

	}

	
	/**
	 * selectionne le les 4 caract�res de la stringBuffer
	 * Coller  le stringBuffer=ABCD en premeier et la chaine=abcdef puis le reste de la stringBuffer=EFGH (concat�nation donne ABCDabcdefEFGH )
	 * test en assertTrue
	 */
	@Test
	public void testColler4() {
		String chaine = "abcdef";

		StringBuffer stringBuffer = new StringBuffer("ABCDEFGH");
		Buffer buffer = new Buffer();
		Selection selection = new Selection();
		ClipBoard pressePapier = new ClipboardImpl();
		

		pressePapier.setClip(chaine);
		buffer.setBuffer(stringBuffer);
	
		selection.setDebut(4);
		selection.setFin(4);
		Moteur moteur = new MoteurImpl(buffer, pressePapier, selection);
		Command coller = new Coller(moteur);
		coller.execute();

		assertTrue(("ABCDabcdefEFGH").compareTo(buffer.getBuffer().toString()) == 0);

	}

}
