package Memento;

import java.util.LinkedList;


public class Caretaker {
    private static final int SIZE = 3;
    private LinkedList<Memento> mementoList = new LinkedList<>();

    public void addMemento(Memento memento) {
        if (mementoList.size() == SIZE) {
            mementoList.removeFirst();
        }
        mementoList.add(memento);
    }
    public Memento getMemento() {
        return mementoList.pollLast();
    } // returns null if empty

}
