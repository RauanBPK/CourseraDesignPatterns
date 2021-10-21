package Creational;

public class ExampleSingleton {
    private static ExampleSingleton meuSingleton = null;
    int number = 1;
    public ExampleSingleton(){
    }

    public static ExampleSingleton getSingleton(){
        if (meuSingleton == null){
            meuSingleton = new ExampleSingleton();
        }
        return meuSingleton;
    }
}

class MainSingleton{
    public static void main(String[] args){
        ExampleSingleton meuSingleton = ExampleSingleton.getSingleton();
        System.out.println(meuSingleton.number);
    }
}