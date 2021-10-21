package Structural;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Hashtable;

// The client class, that uses the system through the facade class
public class ExampleFacade {
    public static void main(String[] args){
        BankService myBankService = new BankService();

        int mySavingAcc = myBankService.createNewAccount("saving", new BigDecimal("500.00"));
        int myInvestmentAcc = myBankService.createNewAccount("investment", new BigDecimal("500.00"));

        myBankService.transferMoney(mySavingAcc, myInvestmentAcc, new BigDecimal("300.00"));
        myBankService.depositMoney(myInvestmentAcc, new BigDecimal("1000.00"));
        myBankService.withdrawMoney(mySavingAcc, new BigDecimal("100.00"));
    }
}

// Interface implemented by internal service classes.
interface IAccount {
    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount);
    void transfer(IAccount toAccount, BigDecimal amount);
    //Should be random?
    Integer getAccountNumber();
}

class Investment implements IAccount {

    public BigDecimal initAmount = null;
    public Investment(BigDecimal initAmount){
        this.initAmount = initAmount;
    }

    @Override
    public void deposit(BigDecimal amount) {
        System.out.println("Depositing " + amount);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        System.out.println("Withdrawing " + amount);
    }

    @Override
    public void transfer(IAccount toAccount, BigDecimal amount) {
        System.out.println("Transferring " + amount);
    }

    @Override
    public Integer getAccountNumber() {
        return 1;
    }
}

class Saving implements IAccount {

    public BigDecimal initAmount = null;
    public Saving(BigDecimal initAmount){
        this.initAmount = initAmount;
    }

    @Override
    public void deposit(BigDecimal amount) {
        System.out.println("Depositing " + amount);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        System.out.println("Withdrawing " + amount);
    }

    @Override
    public void transfer(IAccount toAccount, BigDecimal amount) {
        System.out.println("Transferring " + amount);
    }

    @Override
    public Integer getAccountNumber() {
        return 2;
    }
}

// Facade class. Uses functionalities stated in Structural.IAccount interface
class BankService {

    private final Hashtable<Integer, IAccount> bankAccounts;
    public BankService() {
        this.bankAccounts = new Hashtable<Integer, IAccount>();
    }
    
    public Integer createNewAccount(@NotNull String type, BigDecimal initAmount) {
        IAccount newAccount = null;
        switch (type) {
            case "saving":
                newAccount = new Saving(initAmount);
                break;
            case "investment":
                newAccount = new Investment(initAmount);
                break;
            default:
                System.out.println("Invalid account type");
                break;
        }
        if (newAccount != null) {
            this.bankAccounts.put(newAccount.getAccountNumber(), newAccount);
            return newAccount.getAccountNumber();
        }
        return -1;
    }
    public void withdrawMoney(Integer from, BigDecimal amount){
        IAccount fromAccount = this.bankAccounts.get(from);
        fromAccount.withdraw(amount);
    }
    public void depositMoney(Integer to, BigDecimal amount){
        IAccount fromAccount = this.bankAccounts.get(to);
        fromAccount.deposit(amount);
    }
    public void transferMoney(Integer to, Integer from, BigDecimal amount){
        IAccount toAccount = this.bankAccounts.get(to);
        IAccount fromAccount = this.bankAccounts.get(from);
        fromAccount.transfer(toAccount, amount);
    }
}
