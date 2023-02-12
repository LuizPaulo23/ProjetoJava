// Projeto - Curso em Java
package ProjectBank;

public class Account {
    private static final int MAX_LENGTGH = 12;
    private  String ag;
    private String cc;
    private  String name;
    private double balance;

  // static final propriedade constante e compartilhada
    private Log logger;

    public Account(String ag, String cc, String name){
     this.ag = ag;
     this.cc = cc;
     this.name = name;
     setName(name);
     logger = new Log();
    }
    public void setName(String name){

        if(name.length() > MAX_LENGTGH){
             this.name = name.substring(0, MAX_LENGTGH);
        } else {
            this.name = name;
        }

    }

    // Deposito
    public void deposit(double value){
        balance += value;
        logger.out("Saldo Disponível após o Depósito: " + "R$" + balance);
    }

// Sacar valor
    public boolean withDraw(double value) {
        if(balance < value){
            logger.out("Saldo Atual: " + "R$" + balance);
            return false;
        }else{
            balance -= value; //balance = balance - value;
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


}
