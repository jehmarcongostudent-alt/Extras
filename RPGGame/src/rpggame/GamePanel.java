/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpggame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import tile.TileManager;

import javax.swing.JPanel;
import entity.Player;
import object.SuperObject;


public class GamePanel extends JPanel implements Runnable{
    
    //SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale;  //48x48 tiles
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;    //768 pixel
    public final int screenHeight = tileSize * maxScreenRow;   //576 pixel
    
    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    
    //FPS
    int FPS = 60;
    
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    
    //ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10]; //sets the maximum objects displayed at a time
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);  //added so that GamePanel can recognize key inputs.
        this.setFocusable(true);    //GamePanel can be focused to recieve keyInputs
    }
    
    public void setUpGame(){
        
        aSetter.setObject();
        
        playMusic(0);
    }
    
    public void startGameThread(){
        
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override   //when we start gameThread it auto starts run method
    public void run(){
        
        double drawInterval = 1000000000/FPS;   //1 million nano seconds divided by the FPS
        double nextDrawTime = System.nanoTime() + drawInterval; //after this. the loop starts
        
        while(gameThread != null){  //while gamThread exist it will loop
                        
            //1 UPDATE: update information such as character position
            update();
            
            //2 DRAW: draw the screen with the updated information
            repaint();  //calls the paintCompenent method apparently...
        
            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;  //translates the time from nano seconds to miliseconds
                
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime); //stops all actions until remmainingTime is over. make it accurately perform the right FPS
                
                nextDrawTime += drawInterval;
                
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void update(){   //updates the output on screen when action is done

        player.update();
    }
    //I think paintCompenent is a built in function
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;  //extends the graphic class to provide more sophistecated control over geometry, coordinate transformations, color management, and text Layout.
        
        //TILE
        tileM.draw(g2); //draw tile first before player otherwise tiles will cover player
        
        //OBJECT
        for(int i = 0; i < obj.length; i++){    //scans each object 1 by 1
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        
        //PLAYER
        player.draw(g2);
    
        //UI
        ui.draw(g2);
        
        g2.dispose();   //disposes of this graphic content and releases any resource that its using. basicaly save some memory
        
    }
    public void playMusic(int i){
        
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        
        music.stop();
    }
    public void playSE(int i){
        
        se.setFile(i);
        se.play();
    }
}
