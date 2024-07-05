import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("cờ Caro");
    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton[][] buttons = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;
    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        panel.setBackground(Color.DARK_GRAY);
        label.setForeground(Color.white);
        label.setFont(new Font("Fira Code",Font.BOLD,50));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Tic Tac Toe");

        panel.setLayout(new BorderLayout());
        panel.add(label);
        frame.add(panel,BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.DARK_GRAY);
        frame.add(boardPanel);

        for(int i = 0; i<3;i++){
            for(int j=0; j < 3;j++){
                JButton title = new JButton();
                buttons[i][j] = title;
                boardPanel.add(title);

                title.setBackground(Color.darkGray);
                title.setForeground(Color.white);
                title.setFont(new Font("Arial",Font.BOLD,120));
                title.setFocusable(false);

                title.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton tile = (JButton) e.getSource();
                        if(gameOver) return;
                        if(title.getText()==""){
                            title.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                label.setText("Lượt của "+currentPlayer);
                            }

                        }

                    }
                });
            }
        }
    }
    void checkWinner(){
        // horizontal
        for(int h = 0;h<3; h++){
            if(buttons[h][0].getText()=="")continue;
            if(buttons[h][0].getText() == buttons[h][1].getText() &&
            buttons[h][1].getText()== buttons[h][2].getText()){
                for(int i = 0;i<3; i++){
                    setWinner(buttons[h][i]);
                }
                gameOver = true;
                return;
            }
        }
        //vertical
        for(int v= 0;v<3;v++){
            if(buttons[0][v].getText()=="")continue;
           if(buttons[0][v].getText() == buttons[1][v].getText()&&
           buttons[1][v].getText() == buttons[2][v].getText()){
               for(int i =0;i<3;i++){
                   setWinner(buttons[i][v]);
               }
               gameOver = true;
               return;
           }
        }
        //diagonally right to left
        if(buttons[0][0].getText()== buttons[1][1].getText()&&
        buttons[1][1].getText() == buttons[2][2].getText()&&
        buttons[0][0].getText() !=""){
            for(int i=0;i<3;i++){
                setWinner(buttons[i][i]);
            }
            gameOver = true;
            return;
        }
//        // diogonally left to right
        if(buttons[0][2].getText() == buttons[1][1].getText()&&
        buttons[1][1].getText() == buttons[2][0].getText()&&
        buttons[0][2].getText() !=""){
            setWinner(buttons[0][2]);
            setWinner(buttons[1][1]);
            setWinner(buttons[2][0]);
            gameOver = true;
            return;
        }
        //draw
        if(turns == 9){
            for(int r =0;r<3;r++){
                for(int j=0;j<3;j++){
                    setTie(buttons[r][j]);
                }
            }
            gameOver = true;

        }
    }
     void setWinner(JButton title){
        title.setForeground(Color.green);
        title.setBackground(Color.gray);
        label.setText(currentPlayer+" đã chiến thắng");
    }
    void setTie(JButton title){
        title.setForeground(Color.red);
        title.setBackground(Color.gray);
        label.setText("Hòa");
    }
}
