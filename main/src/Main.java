package src;

import javax.swing.*;
import java.lang.*;

public class Main {
        public static void main(String[] Args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mf = MainFrame.getInstance();
            }
        });
    }

}