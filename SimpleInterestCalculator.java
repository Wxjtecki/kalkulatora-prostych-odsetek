//Auto Kamil Pajączkowski

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleInterestCalculator extends JFrame {
    private JTextField principalField;
    private JTextField rateField;
    private JTextField timeField;
    private JTextArea resultArea;
    private JRadioButton simpleInterestButton;
    private JRadioButton compoundInterestButton;

    public SimpleInterestCalculator() {
        setTitle("Kalkulator Prosty Odsetek");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Pola wejściowe
        principalField = new JTextField(10);
        rateField = new JTextField(10);
        timeField = new JTextField(10);
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);

        // Przycisk radiowy
        simpleInterestButton = new JRadioButton("Odsetki proste");
        compoundInterestButton = new JRadioButton("Odsetki złożone");
        ButtonGroup group = new ButtonGroup();
        group.add(simpleInterestButton);
        group.add(compoundInterestButton);
        simpleInterestButton.setSelected(true);

        // Przycisk obliczania
        JButton calculateButton = new JButton("Oblicz");

        // Dodawanie komponentów do okna
        add(new JLabel("Kwota: "));
        add(principalField);
        add(new JLabel("Stopa procentowa (%): "));
        add(rateField);
        add(new JLabel("Czas (lata): "));
        add(timeField);
        add(simpleInterestButton);
        add(compoundInterestButton);
        add(calculateButton);
        add(new JScrollPane(resultArea));

        // Akcja po naciśnięciu przycisku
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateInterest();
            }
        });
    }

    private void calculateInterest() {
        try {
            double principal = Double.parseDouble(principalField.getText());
            double rate = Double.parseDouble(rateField.getText()) / 100;
            double time = Double.parseDouble(timeField.getText());

            double interest;
            if (simpleInterestButton.isSelected()) {
                interest = principal * rate * time;
                resultArea.setText("Odsetki proste: " + interest);
            } else {
                interest = principal * Math.pow(1 + rate, time) - principal;
                resultArea.setText("Odsetki złożone: " + interest);
            }
        } catch (NumberFormatException ex) {
            resultArea.setText("Proszę wprowadzić poprawne wartości.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleInterestCalculator calculator = new SimpleInterestCalculator();
            calculator.setVisible(true);
        });
    }
}
