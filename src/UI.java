import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI {
    JFrame frame = new JFrame("cal");
    JPanel panel = new JPanel(new FlowLayout());
    JTextArea textArea = new JTextArea(1, 20);
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    JButton button4 = new JButton("4");
    JButton button5 = new JButton("5");
    JButton button6 = new JButton("6");
    JButton button7 = new JButton("7");
    JButton button8 = new JButton("8");
    JButton button9 = new JButton("9");
    JButton button0 = new JButton("0");
    JButton add = new JButton("+");
    JButton subtract = new JButton("-");
    JButton multiply = new JButton("*");
    JButton divide = new JButton("/");
    JButton equals = new JButton("=");
    JButton clear = new JButton("C");
    JLabel label = new JLabel();

    boolean hasAnswered = false;
    double numberOne, numberTwo, result;

    public UI() {
        ui();
        placeComponents();
        createListeners();
    }

    public void placeComponents() {
        panel.add(textArea, BorderLayout.NORTH);
        panel.add(button0);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        panel.add(button8);
        panel.add(button9);
        panel.add(add);
        panel.add(multiply);
        panel.add(subtract);
        panel.add(divide);
        panel.add(clear);
        panel.add(equals);
        panel.add(label);
    }

    public void ui() {
        frame.setVisible(true);
        frame.setSize(250, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
    }

    public void createListeners() {
        this.button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.subtract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.multiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.divide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.equals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
        this.clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onCutClicked(ae);
            }
        });
    }

    void onCutClicked(ActionEvent ae) {

        Object source = ae.getSource();
        if (hasAnswered == true) {
            textArea.setText(null);
            hasAnswered = false;
        }

        if (source == button0) {
            textArea.append("0");
        } else if (source == button1) {
            textArea.append("1");
        } else if (source == button2) {
            textArea.append("2");
        } else if (source == button3) {
            textArea.append("3");
        } else if (source == button4) {
            textArea.append("4");
        } else if (source == button5) {
            textArea.append("5");
        } else if (source == button6) {
            textArea.append("6");
        } else if (source == button7) {
            textArea.append("7");
        } else if (source == button8) {
            textArea.append("8");
        } else if (source == button9) {
            textArea.append("9");
        } else if (source == add) {
            textArea.append("+");
        } else if (source == subtract) {
            textArea.append("-");
        } else if (source == multiply) {
            textArea.append("*");
        } else if (source == divide) {
            textArea.append("/");
        } else if (source == equals) {
            String answer = Integer.toString(Web_Calculator.evaluateExpression(textArea.getText()));
            textArea.setText(answer);
            hasAnswered = true;
        } else if (source == clear) {
            textArea.setText(null);
        }
    }
    // TODO create getResult() that takes a inputed string and fetches the
    // appropriate result or error from WebCalculator

}
