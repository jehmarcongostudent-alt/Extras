package rpggame;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
    
    public static JFrame window;

    public static void main(String[] args) {
        
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //prevents window from running on the background when closed
        window.setResizable(false);
        window.setTitle("J's 2D RPG");
        new Main().setIcon();
        
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
    public void setIcon(){
        
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("player/mage/mage_down0.png"));
        window.setIconImage(icon.getImage());
    }
    
}
