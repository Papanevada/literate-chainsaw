package receiverTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import command.Coller;
import command.Command;
import command.CommandGeneral;
import command.Copier;
import receiver.EnregistrerImpl;
import receiver.Enregistreur;
import receiver.Manager;
import receiver.ManagerImpl;
import receiver.Moteur;
import receiver.MoteurImpl;
import state.Buffer;
import state.ClipBoard;
import state.ClipboardImpl;
import state.Selection;

/**
 * Fichier Test Copier
 * 
 * @author Alexis LE MASLE et Fanny PRIEUR
 * 
 */

public class Jcopier {

	/**
	 * 
	 *
	 */
	@Test
	public void testCopier() {

		StringBuffer stringBuffer = new StringBuffer("copier");
		Buffer buffer = new Buffer();
		Selection selection = new Selection();
		ClipBoard pressePapier = new ClipboardImpl();
		Enregistreur enregistreur = new EnregistrerImpl();
		Moteur moteur = new MoteurImpl(buffer, pressePapier, selection);
		Manager manager = new ManagerImpl(moteur);

		buffer.setBuffer(stringBuffer);
		selection.setDebut(3);
		selection.setFin(3);
		Command copier = new Copier(moteur, enregistreur, manager);
		copier.execute();
		manager.defaire();
		manager.refaire();

		assertTrue(("").compareTo(pressePapier.getClip()) == 0);

	}

	/**
	 * test le stringBuffer apr�s s�lection des caract�res de 0 � 6 soit
	 * "copier" test en assertTrue que "copier" est bien dans le presse papier
	 *
	 */
	@Test
	public void testCopie2() {
		StringBuffer stringBuffer = new StringBuffer("copier");
		Buffer buffer = new Buffer();
		Selection selection = new Selection();
		ClipBoard pressePapier = new ClipboardImpl();
		Enregistreur enregistreur = new EnregistrerImpl();
		Moteur moteur = new MoteurImpl(buffer, pressePapier, selection);
		Manager manager = new ManagerImpl(moteur);

		buffer.setBuffer(stringBuffer);
		selection.setDebut(0);
		selection.setFin(6);
		Command copier = new Copier(moteur, enregistreur, manager);
		copier.execute();
		manager.refaire();
		manager.defaire();
		manager.refaire();
		manager.refaire();
		manager.defaire();
		manager.refaire();

		assertTrue(("copier").compareTo(pressePapier.getClip()) == 0);

	}

	/**
	 * test le stringBuffer apr�s s�lection des caract�res de 4 � 6 soit
	 * "er" test en assertTrue que "er" est bien dans le presse papier
	 *
	 */

	@Test
	public void testCopie3() {
		StringBuffer stringBuffer = new StringBuffer("copier");
		Buffer buffer = new Buffer();
		Selection selection = new Selection();
		ClipBoard pressePapier = new ClipboardImpl();
		Enregistreur enregistreur = new EnregistrerImpl();
		Moteur moteur = new MoteurImpl(buffer, pressePapier, selection);
		Manager manager = new ManagerImpl(moteur);
		Command copier = new Copier(moteur, enregistreur, manager);

		buffer.setBuffer(stringBuffer);

		enregistreur.demarrer();
		selection.setDebut(4);
		selection.setFin(6);
		copier.execute();
		enregistreur.stopper();

		manager.defaire();
		manager.defaire();
		manager.refaire();
		manager.defaire();

		assertTrue(("er").compareTo(pressePapier.getClip()) == 0);

	}

	@Test
	public void testGetMemento() {
		StringBuffer stringBuffer = new StringBuffer("abcdef");
		Buffer buffer = new Buffer();
		Selection selection = new Selection();
		ClipBoard pressePapier = new ClipboardImpl();
		Enregistreur enregistreur = new EnregistrerImpl();
		Moteur moteur = new MoteurImpl(buffer, pressePapier, selection);
		Manager manager = new ManagerImpl(moteur);

		CommandGeneral copier = new Copier(moteur, enregistreur, manager);
		CommandGeneral coller = new Coller(moteur, enregistreur, manager);

		buffer.setBuffer(stringBuffer);
		selection.setDebut(0);
		selection.setFin(6);

		copier.execute();
		coller.execute();
		coller.execute();

		enregistreur.demarrer();
		selection.setDebut(0);
		selection.setFin(6);
		copier.execute();
		selection.setDebut(6);
		selection.setFin(6);
		coller.execute();
		enregistreur.stopper();
		enregistreur.rejouer();

		assertTrue("abcdef".compareTo(pressePapier.getClip()) == 0);

	}

}
