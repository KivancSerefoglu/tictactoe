import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Play extends JPanel {
    JButton[] buttons= new JButton[9];
    char sign = 'X';

    public static void main(String[] args) {

        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setSize(600,700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


        JPanel panel = new JPanel();



        JButton resetButton = new JButton("RESET");
        resetButton.setBackground(Color.getHSBColor((float) 0.3, 0.5F, (float) 0.8));

        resetButton.addActionListener(e -> {
            frame.setVisible(false);
            main(new String[]{});
        });


        panel.add(resetButton);

        frame.getContentPane().add(BorderLayout.PAGE_START, panel);
        frame.getContentPane().add(new Play());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public Play() {

        GridLayout grid = new GridLayout(3, 3);
        setLayout(grid);
        buttonsActions();
    }
    public void buttonsActions() {

        for (int i = 0; i <= 8; i++) {

            buttons[i] = new JButton();
            buttons[i].setText("");
            buttons[i].addActionListener(e -> {
                JButton next = (JButton) e.getSource();
                ImageIcon scaledIcon = null;
                try {
                    Image img =null;
                    if (sign == 'X')
                        img = ImageIO.read(getClass().getResource("X.jpeg"));
                    else if (sign =='O')
                        img = ImageIO.read(getClass().getResource("O.jpeg"));

                    assert img != null;
                    Image imgScale= img.getScaledInstance(next.getWidth(), next.getHeight(),Image.SCALE_SMOOTH);
                    scaledIcon= new ImageIcon(imgScale);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                next.setText(String.valueOf(sign));
                next.setIcon(scaledIcon);
                next.setModel( new DefaultButtonModel()
                {
                    @Override
                    public boolean isArmed()
                    {
                        return false;
                    }

                    @Override
                    public boolean isPressed()
                    {
                        return false;
                    }
                });

                if (sign == 'X') {
                    sign = 'O';
                }else {
                    sign = 'X';
                }
                winnerScreen();
            });
            add(buttons[i]);
        }
    }

    public void winnerScreen() {

        if (winCondition()) {
            if (sign == 'X') {
                sign = 'O';
            }else {
                sign = 'X';
            }
            JOptionPane jOptionPane = new JOptionPane();
            int result = JOptionPane.showConfirmDialog(jOptionPane,"The winner is " + sign + "." +" Congratulations! ", "Score",  JOptionPane.DEFAULT_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                System.exit(0);
            }

        }else if (drawCondition()) {
            JOptionPane jOptionPane = new JOptionPane();
            int result = JOptionPane.showConfirmDialog(jOptionPane,"Game draw. ", "Score", JOptionPane.DEFAULT_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
    }
    public boolean winCondition() {

        for (int i = 0; i < 9; i+=3) {
            if (buttons[i].getText().equals(buttons[i + 1].getText()) && buttons[i].getText().equals(buttons[i + 2].getText())
                    && !buttons[i].getText().equals("")) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {

            if (buttons[j].getText().equals(buttons[j + 3].getText()) && buttons[j].getText().equals(buttons[j + 6].getText())
                    && !buttons[j].getText().equals("")) {
                return true;
            }

        }

        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText())
                && !buttons[0].getText().equals("")) {
            return true;
        }else {
            return buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())
                    && !buttons[2].getText().equals("");
        }
    }
    public boolean drawCondition() {

        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                return false;
            }
        }
        return true;
    }

}