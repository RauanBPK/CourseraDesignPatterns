package Behavioral;

import java.util.ArrayList;

public class ExampleObserver  {
    public static void main(String[] args) {
        Subject myBlog = new Blog();

        Observer mySubscriberOne = new Subscriber("Subscriber one");
        Observer mySubscriberTwo = new Subscriber("Subscriber two");

        myBlog.registerObserver(mySubscriberOne);
        myBlog.registerObserver(mySubscriberTwo);

        myBlog.notifyObservers();

        myBlog.unregisterObserver(mySubscriberOne);

        myBlog.notifyObservers();
    }
}

class Subject {
    private final ArrayList<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer observer){
        observers.add(observer);
    }

    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}

class Blog extends Subject {
    private String state;
    public String getState() {
        return state;
    }

    // Blog stuff...
}

// Observer interface
interface Observer {
    void update();
}

class Subscriber implements Observer {
    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println(name + " is getting blog changes...");
    }
}