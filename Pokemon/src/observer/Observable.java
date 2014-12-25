package observer;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Observable {
	private Set<Observer> observers = new LinkedHashSet<Observer>();

	public void addObserver(Observer ob) {
		observers.add(ob);
	}

	public void notifyObservers() {
		for (Observer ob : observers)
			ob.update(this);
	}
}