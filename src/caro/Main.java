package caro;

/*
 @author Levy Main.java : là đối tượng chạy chính , quyết định "cảnh game"
  hiện hành
 
 */
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main {

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        new LoginView ();
        try { // sử Jato libary có chức năng thay đổi giao diện game đẹp hơn 
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        } catch (Exception e) {
        };
    }

}
