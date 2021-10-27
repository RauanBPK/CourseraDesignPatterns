package Behavioral;

// Vending machine example
// Not finished
public class ExampleState {
}

class VendingMachine {
    private final State idleState;
    private final State hasOneDollarState;
    private final State outOfStockState;
    private final State currentState;
    private final int count;

    public void setState(State state) {}
    public State getHasOneDollarState() { return this.hasOneDollarState;}
    public State getIdleState() {return this.idleState;}
    public State getOutOfStockState() {return this.outOfStockState;}
    public void doReturnMoney() {}
    public void doReleaseProduct() {}
    public int getCount() {return this.count;}
    public VendingMachine(int count){
        idleState = new IdleState();
        hasOneDollarState = new HasOneDollarState();
        outOfStockState = new outOfStockState();
        if (count > 0) {
            this.currentState = idleState;
            this.count = count;
        } else {
            this.currentState = outOfStockState;
            this.count = 0;
        }
    }
}

interface State {
    void insertDollar(VendingMachine vendingMachine);
    void ejectMoney(VendingMachine vendingMachine);
    void dispense(VendingMachine vendingMachine);
}

class IdleState implements State {

    @Override
    public void insertDollar(VendingMachine vendingMachine) {
        System.out.println("Dollar inserted");

        vendingMachine.setState(
                vendingMachine.getHasOneDollarState()
        );
    }

    @Override
    public void ejectMoney(VendingMachine vendingMachine) {
        System.out.println("No money to return");
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {
        System.out.println("Payment required");
    }
}

class HasOneDollarState implements State {

    @Override
    public void insertDollar(VendingMachine vendingMachine) {
        System.out.println("Already have one dollar");
   }

    @Override
    public void ejectMoney(VendingMachine vendingMachine) {
        System.out.println("Returning money");
        vendingMachine.doReturnMoney();
        vendingMachine.setState(vendingMachine.getIdleState());
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {
        System.out.println("Releasing product");

        if (vendingMachine.getCount() > 1){
            vendingMachine.doReleaseProduct();
            vendingMachine.setState(vendingMachine.getIdleState());
        } else {
            vendingMachine.doReleaseProduct();
            vendingMachine.setState(vendingMachine.getOutOfStockState());
        }
    }
}

class outOfStockState implements State {

    @Override
    public void insertDollar(VendingMachine vendingMachine) {
        System.out.println("Out of stock");
        this.ejectMoney(vendingMachine);
   }

    @Override
    public void ejectMoney(VendingMachine vendingMachine) {
        System.out.println("Returning money");
        vendingMachine.doReturnMoney();
        vendingMachine.setState(vendingMachine.getOutOfStockState());
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {
        System.out.println("Out of stock");
    }
}