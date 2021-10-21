package Creational;

public class ExampleFactoryMethod {
    public static void main(String[] args){
        BudgetKnifeStore myBudgetStore = new BudgetKnifeStore();
        myBudgetStore.createKnife("steak");
        //Creational.Knife myKnife = myBudgetStore.orderKnife("steak");
    }
}

abstract class Knife {
    boolean amIAKnife = true;

    public abstract void sharpen();
    public abstract void polish();
    public abstract void pack();
}

class BudgetSteakKnife extends Knife {
    @Override
    public void sharpen() {
        System.out.println("Sharpening the budget steak knife!");
    }

    @Override
    public void polish() {
        System.out.println("Packaging the budget steak knife!");
    }

    @Override
    public void pack() {
        System.out.println("Packaging the budget steak knife!");
    }
}

class BudgetChefKnife extends Knife {
    @Override
    public void sharpen() {
        System.out.println("Sharpening the budget chef knife!");
    }

    @Override
    public void polish() {
        System.out.println("Packaging the budget chef knife!");
    }

    @Override
    public void pack() {
        System.out.println("Packaging the budget chef knife!");
    }
}

abstract class KnifeStore {

    public Knife orderKnife(String knifeType) {
        Knife knifeObject;

        knifeObject = createKnife(knifeType);

        knifeObject.sharpen();
        knifeObject.polish();
        knifeObject.pack();
        return knifeObject;
    }

    // Is there a way to make so the createKnife method can't be called directly but only through orderKnife?
     abstract Knife createKnife(String knifeType);
}

class BudgetKnifeStore extends KnifeStore {

    @Override
    Knife createKnife(String knifeType) {
        if (knifeType.equals("steak")) {
            return new BudgetSteakKnife();
        }else if (knifeType.equals("chefs")) {
            return new BudgetChefKnife();
        } else {
            return null;
        }
    }
}
