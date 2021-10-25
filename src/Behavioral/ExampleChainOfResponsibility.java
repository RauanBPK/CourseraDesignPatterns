package Behavioral;

public class ExampleChainOfResponsibility {

    public static void main(String[] args){
        Handler handlerTwo = new ConcreteHandlerTwo(null);
        Handler handlerOne = new ConcreteHandlerOne(handlerTwo);
        System.out.println(handlerOne.handleRequest());
    }
}

abstract class Handler {

    public Handler nextHandler;
    public Handler(Handler nextHandler){
        this.nextHandler = nextHandler; // Not sure if this is the best way to represent a chain of responsibility...
    }

    public int handleRequest(){ // Makes use of a template method
        int didRuleMatch = checkIfRuleMatches();
        if (didRuleMatch == 1){
            return 1;
        } else if (this.nextHandler != null) {
            return this.nextHandler.handleRequest();
        } else {
            return 0;
        }
    }

    protected abstract int checkIfRuleMatches();

}
class ConcreteHandlerOne extends Handler {

    public ConcreteHandlerOne(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected int checkIfRuleMatches() {
        System.out.println("Rule does not match");
        return 0;
    }
}

class ConcreteHandlerTwo extends Handler {

    public ConcreteHandlerTwo(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected int checkIfRuleMatches() {
        System.out.println("Rule does match");
        return 1;
    }
}
