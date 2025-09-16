import java.util.Scanner;
class BankAccount{
    int account;
    String password;
    BankAccount(int account,String password){
        this.account=account;
        this.password=password;
    }
    void save(){
        System.out.println("You have successfully logged in...");
    }
}
class InsufficientBalanceException extends Exception{
    InsufficientBalanceException(String message){
        super(message);
    }
}
class InvalidAmountException extends Exception{
    InvalidAmountException(String message){
        super(message);
    }
}
abstract class Account{
    int balance;
    void credit(int amount) throws InvalidAmountException{}
    void withdraw(int amount) throws InvalidAmountException,InsufficientBalanceException{}
}
class SavingsAccount extends Account{
    private int balance;
    SavingsAccount(){
        this.balance=1000;
    }
    public void credit(int amount) throws InvalidAmountException{
        if(amount<0){
            throw new InvalidAmountException("Invalid amount");
        }else{
            balance+=amount;
            System.out.println("THE NEW BALANCE AFTER TRANSACTION IS..."+balance);
        }
    }
    public void withdraw(int amount) throws InvalidAmountException,InsufficientBalanceException{
        if(amount<0){
            throw new InsufficientBalanceException("Invalid amount");
        }else if((balance-amount)<1000){
            throw new InsufficientBalanceException("Your savings account must maintain a balance of 1000");
        }else{
            balance-=amount;
            System.out.println("The new balance after the transaction is..."+balance);
        }
    }
}
class CurrentAccount extends Account{
    private int balance;
    CurrentAccount(){
        this.balance=0;
    }
    public void credit(int amount) throws InvalidAmountException{
        if(amount<0){
            throw new InvalidAmountException("Invalid amount");
        }else{
            balance+=amount;
            System.out.println("THE NEW BALANCE AFTER TRANSACTION IS..."+balance);
        }
    }
    public void withdraw(int amount) throws InvalidAmountException,InsufficientBalanceException{
        if(amount<0){
            throw new InsufficientBalanceException("Invalid amount");
        }else{
            balance-=amount;
            System.out.println("The new balance after the transaction is..."+balance);
        }
    }
}
class Transaction{
    int amount;
    String type;
    int creditcount;
    int withdrawcount;
    Transaction(int amount,String type,int creditcount,int withdrawcount){
        this.amount=amount;
        this.type=type;
        this.creditcount=creditcount;
        this.withdrawcount=0;
    }
    Transaction(int amount,String type,int withdrawcount){
        this.amount=amount;
        this.type=type;
        this.withdrawcount=withdrawcount;
    }
}
public class BankSystem{
    public static void main(String[]args){
        Scanner sc =new Scanner(System.in);
        System.out.print("Enter your account number:");
        int account=sc.nextInt();
        System.out.print("Enter your password:");
        sc.nextLine();
        String password=sc.nextLine();
        BankAccount b=new BankAccount(account,password);
        b.save();
        while(true){
            System.out.print("Enter the account type 1.Savings/2.Current/3.Exit...");
            int n=sc.nextInt();
            if(n==1){
                SavingsAccount s=new SavingsAccount();
                System.out.print("1.Credit/2.Debit/3.Exit...");
                int num=sc.nextInt();
                if(num==1){
                    System.out.print("Enter the amount you want to credit...");
                    int amount=sc.nextInt();
                    try{
                        s.credit(amount);
                    }catch(InvalidAmountException e){
                        System.out.println("Error message:"+e.getMessage());
                    }
                }else if(num==2){
                    System.out.println("Enter the amount you want to withdraw");
                    int amount=sc.nextInt();
                    try{
                        s.withdraw(amount);
                    }catch(Exception e){
                        System.out.println("The error message is:"+e.getMessage());
                    }
                }else if(num==3){
                    break;
                }else{
                    System.out.println("Invalid input");
                }
            }else if(n==2){
                CurrentAccount s=new CurrentAccount();
                System.out.print("1.Credit/2.Debit/3.Exit...");
                int num=sc.nextInt();
                if(num==1){
                    System.out.print("Enter the amount you want to credit:");
                    int amount=sc.nextInt();
                    try{
                        s.credit(amount);
                    }catch(InvalidAmountException e){
                        System.out.println("Error message:"+e.getMessage());
                    }
                }else if(num==2){
                    System.out.println("Enter the amount you want to withdraw:");
                    int amount=sc.nextInt();
                    try{
                        s.withdraw(amount);
                    }catch(Exception e){
                        System.out.println("The error message is:"+e.getMessage());
                    }
                }else if(num==3){
                    break;
                }else{
                    System.out.println("Invalid input");
                }
            }else if(n==3){
                break;
            }else{
                System.out.println("Invalid input");
            }
        }
        sc.close();
    }
}