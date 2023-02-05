package ProjectBank;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
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
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        labelName = new JLabel("Name: ");
        fieldName = new JTextField(10);
        labelAction = new JLabel("Action: ");
        comboAction = new JComboBox<>(new String[]{"Deposit", "Withdraw"});
        labelValue = new JLabel("Value: ");
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
                if (action.equals("Deposit")) {
                    account.deposit(value);
                } else {
                    if (!account.withDraw(value)) {
                        JOptionPane.showMessageDialog(Front.this, "Insufficient balance for withdrawal of " + value);
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