package caro;

import java.io.FileInputStream;
import javax.swing.JPanel;
import javazoom.jl.player.Player;

/**
 *
 * @author Levy
 */
public class BackgroundMusic extends JPanel {

    private int total;
    private int stop;    

    private FileInputStream FIS;
    private Player myPlayer;

    private boolean firstPress = true;
    public boolean isStopping = false;
    public boolean isPlay = true;

    public void stopMusic() {
        try {
            System.out.println("stop: " + stop);
            stop = FIS.available();
            myPlayer.close();
        } catch (Exception e) {
        }
    }

    public void repeatMusic() {
        new Thread(new Runnable() {

            @Override
            public void run() {

                while (isStopping == false) {
                    try {
                        Thread.sleep(100);
                        int now = FIS.available();
                        if (now == 0) {
                            playMusic(-1);
                        }
                    } catch (Exception e) {

                    }

                }
            }
        }).start();;
    }

    public void playMusic(final int pos) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    FIS = new FileInputStream("C:\\Users\\LeVy\\IdeaProjects\\Caro Chest\\sound\\bkmusic.mp3");
                    System.out.println("total " + total);
                    total = FIS.available();
                    if (pos > -1) {
                        FIS.skip(pos);
                    }
                    myPlayer = new Player(FIS);
                    myPlayer.play();
                } catch (Exception e) {

                }

            }
        }
        ).start();;

    }

    public void resumeMusic() {
        try {
            System.out.println("resume" + (total - stop));
            if (stop == 0) {
                playMusic(-1);
            } else {
                playMusic(total - stop);
            }
        } catch (Exception e) {

        }

    }

    public BackgroundMusic() {
        playMusic(-1);
        isStopping = false;
        repeatMusic();

    }

}
