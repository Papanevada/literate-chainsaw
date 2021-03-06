package command;

import memento.Memento;
import receiver.Enregistreur;
import receiver.Manager;
import receiver.Moteur;
import receiver.MoteurImpl;
import state.State;

/**
 * Concrete Command "Copier" implementant l'interface Command
 * 
 * @author Alexis LE MASLE et Fanny PRIEUR
 * 
 */
public class Copier implements Command {

	/**
	 * Nouvelle instance de l'interface Moteur declarant la methode copier
	 * 
	 * @see Moteur
	 */
	private Moteur moteur;

	private Enregistreur enregistreur;

	private CopierMemento memento;

	private Manager manager;

	/**
	 * Constructeur de la classe Copier
	 * 
	 * @param moteur
	 * @param enregistreur
	 * @param manager
	 */
	public Copier(Moteur moteur, Enregistreur enregistreur, Manager manager) {
		this.moteur = moteur;
		this.enregistreur = enregistreur;
		this.manager = manager;
	}
	// Operations

	/**
	 * Appel de la mise en oeuvre de la fonction "copier" dans l'implementation
	 * Moteur.
	 * 
	 * @see MoteurImpl
	 * 
	 */
	@Override
	public void execute() {
		moteur.copier();
		CopierMemento m = getMemento();
		if (enregistreur.getRecord()) {
			enregistreur.addMemento(getMemento());
			enregistreur.addCommand(this);
		}
		if (!manager.getPlay()) {
			State st = manager.getStateCourant();
			st.addMem(m);
			st.addCmd(this);
			manager.saveState();
			manager.emptyRedo();
		}
	}

	/**
	 * Cree un nouveau CopierMemento
	 */
	@Override
	public CopierMemento getMemento() {
		return new CopierMemento();
	}

	/**
	 * Classe CopierMemento implementant Memento et ne servant qu'a Copier
	 * 
	 * @author Alexis LE MASLE et Fanny PRIEUR
	 *
	 */
	private class CopierMemento implements Memento<CopierMemento> {

		@Override
		public CopierMemento clone() {
			return new CopierMemento();
		}
	}

	@Override
	public void setMemento(Memento<?> mem) {
		this.setCopierMemento((CopierMemento) mem);
	}

	public CopierMemento getCopierMemento() {
		return memento;
	}

	public void setCopierMemento(CopierMemento memento) {
		this.memento = memento;
	}

	@Override
	public void setMoteur(Moteur moteur) {
		this.moteur = moteur;
	}

	@Override
	public Moteur getMoteur() {
		return moteur;
	}

	@Override
	public Copier clone() {
		Copier a = new Copier(moteur.clone(), enregistreur, manager);
		return a;
	}
}
