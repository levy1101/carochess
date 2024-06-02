
package caro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Levy
 */
public class BackButton extends JButton {

    String presentPanel;

    public BackButton(String presnetPanel) {
        this.presentPanel = presnetPanel;
        Icon myIcon = new ImageIcon(getClass().getResource("picture/BackButton.png"));
        setIcon(myIcon);
        setBorderPainted(false);
        setBounds(650, 80, 60, 60);
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                if ("GamePanel".equals(presentPanel)) {
                    MainFrame.myFrame.remove(MainFrame.myGamePanel);
                }

                if ("NetworkPanel".equals(presentPanel) ) {
                    MainFrame.myFrame.remove(MainFrame.myNetworkPanel);

//                    /* Khi trở về startMenu, nếu là host thì tất cả kết nối của server bị ngắt*/
                   
                } // end of else -if clause 
                if("towLanPlayerPanel".equals(presentPanel)) {
                    MainFrame.myFrame.remove(MainFrame.twoLanPlayerPanel);
                    try {
                    
                   
                      
                    } catch (Exception ex) {
                       
                    }
                   
            
                }

                MainFrame.myFrame.add(MainFrame.myStartPanel);
                MainFrame.startGame = true;
                MainFrame.myFrame.repaint();

            }
        });

    }
}
