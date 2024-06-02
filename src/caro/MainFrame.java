package caro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private JPanel jpnMenu, jpnView;
    private JButton btnSignOut;
    public static JFrame myFrame;
    public static StartPanel myStartPanel = new StartPanel();
    public static GamePanel myGamePanel = new GamePanel();
    public static NetworkPanel myNetworkPanel = new NetworkPanel();
    public static ImagePanel twoLanPlayerPanel = new ImagePanel("picture/main.png", 0, 0, 800, 400);
    public static boolean startGame;
    public MainFrame(){
        startGame = true; // đánh dấu game đã bắt đầu

        //  Khởi tạo cửa sổ Game

        myFrame = new JFrame("Game");
        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.setLayout(null);
        myFrame.setBounds(100, 100, 800, 600);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // nhạc nền
        MusicPanel myMusicPanel = new MusicPanel();
        myFrame.add(myMusicPanel);

        // âm thanh
        SoundPanel mySoundPanel = new SoundPanel();

        // thêm button  nhạc nền và âm thanh vào Frame chính
        myFrame.add(mySoundPanel);
        myFrame.repaint();

        // khởi chạy menu start game
        myFrame.add(myStartPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource ()==btnSignOut){
            new LoginView ();
            this.dispose ();
        }
    }
}
