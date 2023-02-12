package ProjectBank;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SQL {

    public void writeToSQLite(List<Account> accountList, String databasePath) {

        // Dados de conexão com o banco de dados SQLite
        String url = "jdbc:sqlite:" + databasePath;
        String username = "";
        String password = "";

        // Instrução SQL para criar a tabela "DBtransaction"
        String createTableSQL = "CREATE TABLE IF NOT EXISTS DBtransaction (nome TEXT, data TEXT, deposito REAL, saque REAL, saldo REAL)";

        // Instrução SQL para inserir dados na tabela "DBtransaction"
        String insertDataSQL = "INSERT INTO DBtransaction (nome, data, deposito, saque, saldo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             PreparedStatement preparedStatement = conn.prepareStatement(insertDataSQL)) {

            // Cria a tabela "DBtransaction"
            stmt.executeUpdate(createTableSQL);

            // Insere os dados de cada objeto "Account" na tabela "DBtransaction"
            for (Account account : accountList) {
                preparedStatement.setString(1, account.getName());
                SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yy");
                Date dataAtual = new Date();
                String dataFormatada = formatoData.format(dataAtual);
                preparedStatement.setString(2, dataFormatada);
                preparedStatement.setDouble(3, account.getDeposito());
                preparedStatement.setDouble(4, account.getSaque());
                preparedStatement.setDouble(5, account.getBalance());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
