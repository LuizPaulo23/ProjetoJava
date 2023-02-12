// Projeto - Conta bancária em Java
package ProjectBank;
public class Account {
    private  String ag;
    private String cc;
    private  String name;
    //private double password;
    private double deposito;
    private double saque;
    private double balance;

  // static final propriedade constante e compartilhada

    private Log logger;

    public Account(String ag, String cc, String name){
     this.ag = ag;
     this.cc = cc;
     this.name = name;
     logger = new Log();
    }
    // Depósito ------------------------

    public void deposit(double value){
        balance += value;
        deposito +=value;
        logger.out("Saldo Disponível após o Depósito: " + "R$" + balance);
    }

// Saque -----------------------------------

    public boolean withDraw(double value) {
        if(balance < value){
            logger.out("Saldo Atual: " + "R$" + balance);
            return false;
        }else{
            balance -= value; //balance = balance - value;
            saque -= value;
            logger.out("Saldo Disponível após o saque: " + "R$" + balance);
            return true;
        }

    }

    @Override
    public String toString() {
        //String result = "A conta " + this.name + " " + this.ag + " / " + this.cc + " possui em caixa: R$ " + balance;
        return "A conta " + this.name + " " + this.ag + " / " + this.cc + " possui em caixa: R$ " + balance;
    }

    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }

    public double getDeposito(){
        return deposito;
    }

    public double getSaque(){
        return saque;
    }

}
