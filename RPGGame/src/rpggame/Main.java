package rpggame;
import javax.swing.JFrame;

public class Main {
    
    public static JFrame window;

    public static void main(String[] args) {
        
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //prevents window from running on the background when closed
        window.setResizable(false);
        window.setTitle("2D RPG");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        gamePanel.config.loadConfig();
        if(gamePanel.fullScreenOn == true){
            window.setUndecorated(true);
        }
        
        window.pack();  //makes window to be sized to fit the preferred size and layout of its subcomponents (game panel)
        
        window.setLocationRelativeTo(null); //centers the window on the screen
        window.setVisible(true);
        
        gamePanel.setUpGame();
        gamePanel.startGameThread();
        
    }
    
}
