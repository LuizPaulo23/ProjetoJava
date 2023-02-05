// Projeto - Curso em Java
package ProjectBank;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank Transaction = new Bank("0001");

        while(true){
            System.out.println("Bem vindo ao Transaction");
            System.out.println("Deseja Fazer alguma operação?");
            System.out.println("S - Sim");
            System.out.println("N - Não");
            String op = scanner.nextLine();

            if(op.equals("S")){
                System.out.println("Digite o seu nome:");
                String name = scanner.nextLine();
                Account account = Transaction.generateAccount(name);
                Transaction.insertAccount(account);

                operationAccount(account);

            } else if(op.equals("N")){
                break;
            } else {

                System.out.println("OPS! Operação inválida, selecione uma operação válida");

            }

        }

        List<Account> accountList = Transaction.getAccounts();

        for (Account cc : accountList){
            System.out.println(cc);
        }
    }

    static void operationAccount(Account account){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escolha a operação desejada:");
            System.out.println("D - Depósito");
            System.out.println("S - Saque");
            System.out.println("E - Sair");

            String op = scanner.nextLine();

            if (op.equals("D")) {
                System.out.println("Insira o valor (R$) que deseja depositar");
                double value = scanner.nextDouble();
                account.deposit(value);

            }else if(op.equals("S")){
                System.out.println("Insira o valor (R$) de saque:");
                double value = scanner.nextDouble();

                if(!account.withDraw(value)){
                    System.out.println("OPS! Saldo Insuficiente para saque no valor de " + value);
                }
            } else if(op.equals("E")){
                break;
            } else {
                System.out.println("OPS! Operação inválida, selecione uma operação válida: ");
            }

            scanner = new Scanner(System.in);
        }
    }

}