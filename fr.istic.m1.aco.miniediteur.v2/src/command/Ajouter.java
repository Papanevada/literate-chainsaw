package command;

import ihm.Ihm;
import memento.Memento;
import receiver.Enregistreur;
import receiver.Moteur;
import receiver.MoteurImpl;

/**
 * Concrete Command "Inserer" implementant l'interface Command
 * 
 * @author Alexis LE MASLE et Fanny PRIEUR
 * 
 */
public class Ajouter implements Command {

	/**
	 * Nouvelle instance de l'interface Moteur declarant la methode inserer
	 * 
	 * @see Moteur
	 */
	private Moteur moteur;

	/**
	 * Nouvelle String a inserer
	 */
	private Ihm ihm;

	private Enregistreur enregistreur;

	private boolean replay = false;

	/**
	 * Constructeur de la classe Inserer
	 * 
	 * @param moteur
	 * @param str
	 */
	public Ajouter(Moteur moteur, Ihm ihm, Enregistreur enregistreur) {
		this.moteur = moteur;
		this.ihm = ihm;
		this.enregistreur = enregistreur;
	}

	// Operations

	/**
	 * Appel de la mise en oeuvre de la fonction "inserer" dans l'implementation
	 * Moteur.
	 * 
	 * @see MoteurImpl
	 * 
	 */
	public void execute() {
		String str = "";
		if (replay) {
			str = ihm.getLastInput();
			enregistreur.setBuffer(moteur.getBuffer());
		} else {
			str = ihm.getText();
			if (enregistreur.getRecord()) {
				enregistreur.addMemento(getMemento());
			}
		}
		moteur.ajouter(str);
	}

	public void setIhm(Ihm ihm) {
		this.ihm = ihm;
	}

	@Override
	public Memento getMemento() {
		return new Memento(new Ajouter(moteur, ihm, enregistreur));
	}

	@Override
	public void setReplay(boolean bool) {
		this.replay = bool;
	}

	@Override
	public Moteur getMoteur() {
		return moteur;
	}

}
