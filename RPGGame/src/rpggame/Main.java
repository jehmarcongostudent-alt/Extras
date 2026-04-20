/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rpggame;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //prevents window from running on the background when closed
        window.setResizable(false);
        window.setTitle("2D RPG");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        window.pack();  //makes window to be sized to fit the preferred size and layout of its subcomponents (game panel)
        
        window.setLocationRelativeTo(null); //centers the window on the screen
        window.setVisible(true);
        
        gamePanel.setUpGame();
        gamePanel.startGameThread();
        
    }
    
}
