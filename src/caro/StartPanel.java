package caro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author Levy
 */
public class StartPanel extends JPanel {

    SoundPlayer mySound = new SoundPlayer();

    public StartPanel() {
        setLayout(null);
        setBounds(0, 0, 800, 600);
        JLabel helloLabel;

        /* hình nền cho Game : 
         đối tượng tự tạo ImagePanel - một panel có chức năng load ảnh */
        ImagePanel background = new ImagePanel("picture/StartGame.png", 0, 0, 800, 600);

        //Thêm các button chức năng

        JButton oneButton = new JButton("1 Player");
        JButton twoButton = new JButton("2 Players");
        JButton LANButton = new JButton("LAN game");
        JButton exitButton = new JButton("Exit");

        // định vị trí các button
        oneButton.setBounds(350, 300, 100, 30);
        twoButton.setBounds(350, 350, 100, 30);
        LANButton.setBounds(350, 400, 100, 30);
        exitButton.setBounds(350, 450, 100, 30);

        // thêm hành động cho button "1 player" : người chơi đấu với máy 
        oneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
             
                if (GamePanel.canPlaySound) {
                    mySound.playSound("C:\\Users\\LeVy\\IdeaProjects\\Caro Chest\\sound\\coin.mp3"); // phát âm thanh
                }
                // chương trình gỡ bỏ chế độ  START MENU, thay vào là chế độ GAME 1 người chơi
                MainFrame.myFrame.remove(MainFrame.myStartPanel);
                MainFrame.myGamePanel = new GamePanel();
                MainFrame.myGamePanel.numberPlayer = 1; // số người chơi là một
                MainFrame.myFrame.add(MainFrame.myGamePanel);
                MainFrame.myFrame.repaint();

            }
        });
        
// thêm hành động cho button "2 player" : chế độ 2 người chơi trên cùng một máy 
        twoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            
                if (GamePanel.canPlaySound) {
                    mySound.playSound("C:\\Users\\LeVy\\IdeaProjects\\Caro Chest\\sound\\coin.mp3"); // âm thanh
                }
                /* chương trình gỡ bỏ chế độ  START MENU : 
                 thay vào là chế độ GAME 2 người chơi */
                MainFrame.myFrame.remove(MainFrame.myStartPanel);
                MainFrame.myGamePanel = new GamePanel();
                MainFrame.myGamePanel.numberPlayer = 2;
                MainFrame.myFrame.add(MainFrame.myGamePanel);
                MainFrame.myFrame.repaint();

            }
        });

        /* thêm hành động cho LAN GAME button : 
         2 người chơi kết nối 2 máy qua mạng LAN  */
        LANButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (GamePanel.canPlaySound) {
                    mySound.playSound("C:\\Users\\LeVy\\IdeaProjects\\Caro Chest\\sound\\coin.mp3"); // âm thanh
                }
                /* chương trình gỡ bỏ chế độ  START MENU, 
                 thay vào là menu kết nối mạng LAN 
                 */
                MainFrame.myFrame.remove(MainFrame.myStartPanel);
                NetworkPanel.joinButton.setEnabled(true);
                MainFrame.myFrame.add(MainFrame.myNetworkPanel);
                MainFrame.myFrame.repaint();

            }
        });

        // thêm hành động cho EXIT button 
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện đăng xuất và quay lại trang đăng nhập
//                MainFrame.getCurrentUserDisplayName() = null; // Xóa tên người dùng hiện tại
                LoginView loginView = new LoginView(); // Hiển thị trang đăng nhập
                StartPanel.this.getRootPane().getParent().setVisible(false); // Đóng cửa sổ hiện tại
            }
        });

        add(oneButton);
        add(twoButton);
        add(LANButton);
        add(exitButton);

        this.add(background);

    }



}
