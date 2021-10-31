package Behavioral;

import java.util.ArrayList;
//
//public class ExampleObserver  {
//    public static void main(String[] args) {
//        Subject myBlog = new Blog();
//
//        Observer mySubscriberOne = new Subscriber("Subscriber one");
//        Observer mySubscriberTwo = new Subscriber("Subscriber two");
//
//        myBlog.registerObserver(mySubscriberOne);
//        myBlog.registerObserver(mySubscriberTwo);
//
//        myBlog.notifyObservers();
//
//        myBlog.unregisterObserver(mySubscriberOne);
//
//        myBlog.notifyObservers();
//    }
//}
//
//class Subject {
//    private final ArrayList<Observer> observers = new ArrayList<>();
//
//    public void registerObserver(Observer observer){
//        observers.add(observer);
//    }
//
//    public void unregisterObserver(Observer observer) {
//        observers.remove(observer);
//    }
//
//    public void notifyObservers() {
//        for (Observer o : observers) {
//            o.update();
//        }
//    }
//}
//
//class Blog extends Subject {
//    private String state;
//    public String getState() {
//        return state;
//    }
//
//    // Blog stuff...
//}
//
//// Observer interface
//interface Observer {
//    void update();
//}
//
//class Subscriber implements Observer {
//    private final String name;
//
//    public Subscriber(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public void update() {
//        System.out.println(name + " is getting blog changes...");
//    }
//}


//Subject.java

interface Subject {
    void registerObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyObservers();
}




//Channel.java

class Channel implements Subject {

    private final ArrayList<Observer> observers = new ArrayList<>();
    private String channelName;
    private String status;

    public Channel(String channelName, String status){
        this.channelName = channelName;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.update(this.status);
        }
    }
}





//Observer.java

interface Observer {
    void update(String status);
}



//Follower.java

class Follower implements Observer {

    private String followerName;

    public Follower(String followerName){
        this.followerName = followerName;
    }

    public String getFollowerName() {
        return this.followerName;
    }

    public void setFollowerName(String followerName) {
        this.followerName = followerName;
    }

    @Override
    public void update(String status) {
        System.out.println("Updating status to " + status);
        if (status.equals("live")) {
            this.play();
        }
    }

    public void play() {
        System.out.println("Playing...");
    }
}
