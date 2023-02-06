package ProjectBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

public class Front extends JFrame {
    private JLabel labelName;
    private JTextField fieldName;
    private JLabel labelAction;
    private JComboBox<String> comboAction;
    private JLabel labelValue;
    private JTextField fieldValue;
    private JButton buttonSubmit;
    private JButton buttonCancel;

    private Bank Transaction = new Bank("0001");
    private Scanner scanner = new Scanner(System.in);

    public Front() {
        setTitle("Transaction");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setBackground(Color.BLACK);

        labelName = new JLabel("Nome: ");
        fieldName = new JTextField(10);
        labelAction = new JLabel("Escolha: ");
        comboAction = new JComboBox<>(new String[]{"Depositar", "Sacar"});
        labelValue = new JLabel("Valor: ");
        fieldValue = new JTextField(10);
        buttonSubmit = new JButton("Submit");
        buttonCancel = new JButton("Cancel");

        add(labelName);
        add(fieldName);
        add(labelAction);
        add(comboAction);
        add(labelValue);
        add(fieldValue);
        add(buttonSubmit);
        add(buttonCancel);

        buttonSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = fieldName.getText();
                Account account = Transaction.generateAccount(name);
                Transaction.insertAccount(account);
                String action = comboAction.getSelectedItem().toString();
                double value = Double.parseDouble(fieldValue.getText());
                if (action.equals("Depositar")) {
                    account.deposit(value);
                    JOptionPane.showMessageDialog(Front.this, "Valor depositado R$ " + value);
                } else {
                    if (!account.withDraw(value)) {
                        JOptionPane.showMessageDialog(Front.this, "Saldo insuficiente R$" + value);
                    }
                }
                fieldName.setText("");
                fieldValue.setText("");
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Account> accountList = Transaction.getAccounts();
                StringBuilder accounts = new StringBuilder();
                for (Account cc : accountList) {
                    accounts.append(cc).append("\n");
                }
                JOptionPane.showMessageDialog(Front.this, accounts.toString());
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Front();
    }
}
