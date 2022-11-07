package main;

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
    JButton decimal = new JButton(".");
    JButton log = new JButton("ln");
    JButton power = new JButton("^");
    JButton oParenth = new JButton("(");
    JButton cParenth = new JButton(")");
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
        panel.add(decimal);
        panel.add(log);
        panel.add(power);
        panel.add(oParenth);
        panel.add(cParenth);
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
                onButtonClick(ae);
            }
        });
        this.button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.subtract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.multiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.divide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.equals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.decimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.power.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.oParenth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
        this.cParenth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                onButtonClick(ae);
            }
        });
    }

    void onButtonClick(ActionEvent ae) {

        Object source = ae.getSource();
        // sets boolean depending on if text field needs to be cleared on next button
        // press
        if (hasAnswered == true) {
            textArea.setText(null);
            hasAnswered = false;
        }
        // updates text field depending on pressed button
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
            String expression = textArea.getText();
            getResult(expression);
        } else if (source == clear) {
            textArea.setText(null);
        } else if (source == log) {
            textArea.append("ln");
        } else if (source == decimal) {
            textArea.append(".");
        } else if (source == power) {
            textArea.append("^");
        } else if (source == oParenth) {
            textArea.append("(");
        } else if (source == cParenth) {
            textArea.append(")");
        }
    }

    public void getResult(String exp) {
        int valid = Web_Calculator.isValidExpression(exp);
        if (valid == 0) {
            textArea.setText(Float.toString(Web_Calculator.evaluateExpression(exp)));
        }
        // Print out specific error message and reset exp
        else {
            if (valid == 1)
                System.out.println("Error: Operator issue or empty expression. Please try again.");
            else if (valid == 2)
                System.out.println("Error: Unknown character. Please try again.");
            else if (valid == 3)
                System.out.println("Error: Decimal number. Please try again.");
            else if (valid == 4)
                System.out.println("ERROR: Leading 0. Please try again.");
        }
        hasAnswered = true;
    }
}
