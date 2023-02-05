package ProjectBank;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    Bank Transaction = new Bank("0001");

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Transaction");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        Label welcomeLabel = new Label("Bem vindo ao Transaction");
        grid.add(welcomeLabel, 0, 0);

        Label nameLabel = new Label("Digite o seu nome:");
        grid.add(nameLabel, 0, 1);

        TextField nameField = new TextField();
        grid.add(nameField, 1, 1);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);

        Button generateAccountBtn = new Button("Gerar Conta");
        generateAccountBtn.setOnAction(e -> {
            String name = nameField.getText();
            Account account = Transaction.generateAccount(name);
            Transaction.insertAccount(account);
            operationAccount(account, primaryStage);
        });
        hbBtn.getChildren().add(generateAccountBtn);
        grid.add(hbBtn, 1, 4);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void operationAccount(Account account, Stage primaryStage) {
        Stage stage = new Stage();
        stage.setTitle("Operação");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 300, 275);
        stage.setScene(scene);

        Label chooseOperationLabel = new Label("Escolha a operação desejada:");
        grid.add(chooseOperationLabel, 0, 0);

        HBox hbDeposit = new HBox(10);
        hbDeposit.setAlignment
