package ProjectBank;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DB {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Bank Transaction = new Bank("0001");

        while (true) {
            System.out.println("Bem vindo ao Transaction");
            System.out.println("Deseja Fazer alguma operação?");
            System.out.println("S - Sim");
            System.out.println("N - Não");
            String op = scanner.nextLine();

            if (op.equals("S")) {
                System.out.println("Digite o seu nome: ");
                String name = scanner.nextLine();
                Account account = Transaction.generateAccount(name);
                Transaction.insertAccount(account);

                // Adicionando a senha do usuário

               System.out.println("Digite a senha");
               String password = scanner.nextLine();
               //Account accountPassword = Transaction.generateAccount(password);
               //Transaction.insertAccount(accountPassword);

                operationAccount(account);
            } else if (op.equals("N")) {
                System.out.println("Okay. Volte Sempre, a Transaction agradece sua preferência!");
                break;
            } else {
                System.out.println("OPS! Operação inválida, selecione uma operação válida");
            }
        }

        List<Account> accountList = Transaction.getAccounts();
        // Salvando os dados do usuário em um xlsx

        SaveToExcel saveToExcel = new SaveToExcel();
        //saveToExcel.writeToExcel(accountList);
        saveToExcel.writeToExcel(accountList, "/home/luiz/Java/ProjetoJava/src/ProjectBank/DB.xlsx");

        // PERMISSÃO DE ACESSO AS PASTAS - UBUNTU 20.04, LINUX
        //sudo chmod 777 /home/luiz/Java/ProjetoJava/src/ProjectBank/contas.xlsx


    }

    static void operationAccount(Account account) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escolha a operação desejada:");
            System.out.println("D - Depósito");
            System.out.println("S - Saque");
            System.out.println("E - Sair");

            String op = scanner.nextLine();

            if (op.equals("D")) {
                System.out.println("Insira o valor (R$) que deseja depositar: ");
                double value = scanner.nextDouble();
                account.deposit(value);
            } else if (op.equals("S")) {
                System.out.println("Insira o valor (R$) de saque: ");
                double value = scanner.nextDouble();

                if (!account.withDraw(value)) {
                    System.out.println("OPS! Saldo Insuficiente para saque no valor de " + value);
                }
            } else if (op.equals("E")) {
                break;
            } else {
                System.out.println("OPS! Operação inválida, selecione uma operação válida: ");
            }

            scanner = new Scanner(System.in);

        }
    }
}