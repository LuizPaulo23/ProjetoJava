package ProjectBank;
import javax.swing.*;
import java.awt.*;

public class Front extends JFrame {
    private JLabel labelName;
    private JTextField fieldName;
    private JLabel labelAction;
    private JComboBox<String> comboAction;
    private JLabel labelValue;
    private JFormattedTextField fieldValue;
    private JButton buttonSubmit;
    private JButton buttonCancel;
    private JButton buttonBalance;

    private Bank Transaction = new Bank("0001");

    public Front() {
        setTitle("Transaction");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        labelName = new JLabel("Name: ");
        fieldName = new JTextField(10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        add(labelName, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(fieldName, constraints);

        labelAction = new JLabel("Action: ");
        comboAction = new JComboBox<>(new String[]{"Deposit", "Withdraw"});
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(labelAction, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(comboAction, constraints);

        labelValue = new JLabel("Value: ");
    }
}