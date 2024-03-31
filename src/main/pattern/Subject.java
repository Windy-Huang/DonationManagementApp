package pattern;

import java.util.ArrayList;
import java.util.List;

// represent object to be monitored in observer pattern
public abstract class Subject {

    private List<Observer> arr;

    // EFFECTS: create an object with no observer
    public Subject() {
        arr = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add an object to be notified
    public void addObserver(Observer o) {
        arr.add(o);
    }

    // MODIFIES: notify the observers
    public void notifyObserver(Object obj) {
        for (Observer o:arr) {
            o.update(obj);
        }
    }

}
