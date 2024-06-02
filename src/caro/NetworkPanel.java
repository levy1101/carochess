package caro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author AnhDuc
 */
public class NetworkPanel extends JPanel {

    public static Client myClient;
    public BackButton myBackButton;
    public String host;
    public int port;
    public static Server myServer;

    public static JButton createServerButton;
    public static JButton joinButton;

    public ImagePanel waitingPanel;

    ImagePanel background = new ImagePanel("picture/main.png", 0, 0, 800, 600);

    public SoundPlayer mySoundPlayer = new SoundPlayer();

    public void addCreateServerButton() {
        createServerButton = new JButton("Tạo máy chủ");
        createServerButton.setBounds(300, 200, 200, 50);
        createServerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Lấy địa chỉ IP
                            String serverAddress = InetAddress.getLocalHost().getHostAddress();
                            JOptionPane.showMessageDialog(null, "Địa chỉ IP của bạn là: " + serverAddress);

                            myClient = new Client(serverAddress);

                            MainFrame.myFrame.remove(MainFrame.myNetworkPanel);
                            MainFrame.twoLanPlayerPanel = myClient.background;
                            MainFrame.myFrame.add(MainFrame.twoLanPlayerPanel);
                            MainFrame.myFrame.repaint();
                            myClient.play();

                            if (myClient.wantsToPlayAgain()) {
                                MainFrame.myFrame.remove(MainFrame.twoLanPlayerPanel);
                            } else {
                                MainFrame.myFrame.remove(MainFrame.twoLanPlayerPanel);
                                MainFrame.myFrame.add(MainFrame.myStartPanel);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        add(createServerButton);
    }

    public void addJoinButton() {
        joinButton = new JButton("Tham gia máy chủ");
        joinButton.setBounds(300, 300, 200, 50);
        joinButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (GamePanel.canPlaySound) {
                    mySoundPlayer.playSound("C:\\Users\\LeVy\\IdeaProjects\\Caro Chest\\sound\\click.mp3");
                }

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        while (true) {
                            try {
                                String serverAddress = InetAddress.getLocalHost().getHostAddress();
                                serverAddress = (String) JOptionPane.showInputDialog(null, "Nhập địa chỉ IP", "Thông tin",
                                        JOptionPane.INFORMATION_MESSAGE, null, null, serverAddress);

                                System.out.println("server address: " + serverAddress);

                                myClient = new Client(serverAddress);

                                JOptionPane.showMessageDialog(null, "Để kết nối, người chơi của bạn cần nhập IP : " + serverAddress);

                                MainFrame.myFrame.remove(MainFrame.myNetworkPanel);
                                MainFrame.twoLanPlayerPanel = myClient.background;
                                MainFrame.myFrame.add(MainFrame.twoLanPlayerPanel);
                                MainFrame.myFrame.repaint();
                                myClient.play();

                                if (myClient.wantsToPlayAgain()) {
                                    MainFrame.myFrame.remove(MainFrame.twoLanPlayerPanel);
                                } else {
                                    MainFrame.myFrame.remove(MainFrame.twoLanPlayerPanel);
                                    MainFrame.myFrame.add(MainFrame.myStartPanel);
                                    break;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }).start();

                joinButton.setEnabled(false);
            }
        });

        add(joinButton);
    }

    public NetworkPanel() {
        myServer = new Server();

        new Thread(
                new Runnable() {

                    @Override
                    public void run() {
                        setBounds(0, 0, 800, 600);
                        setLayout(null);

                        // button to return start menu
                        myBackButton = new BackButton("NetworkPanel");
                        add(myBackButton);

                        /*----------button to create server---------- */
                        addCreateServerButton();

                        /*----------button to join server---------- */
                        addJoinButton();

                        /*----------- Picture of backround----------- */
                        add(background);
                    }
                }
        ).start();
    }
}
