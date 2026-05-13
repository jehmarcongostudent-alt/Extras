package rpggame;
import ai.PathFinder;
import entity.Entity;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import tile.TileManager;

import javax.swing.JPanel;
import entity.Player;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import tile_interactive.InteractiveTile;


public class GamePanel extends JPanel implements Runnable{
    
    //SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;  //48x48 tiles
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;    //960 pixel
    public final int screenHeight = tileSize * maxScreenRow;   //576 pixel
    //WORLD SETTINGS
    public int maxWorldCol;
    public int maxWorldRow;
    public final int maxMap = 10;
    public int currentMap = 0;
    //FOR FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;
    
    //FPS
    int FPS = 60;
    
    //SYSTEM
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    public PathFinder pFinder = new PathFinder(this);
    Thread gameThread;
    
    //ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public Entity obj[][] = new Entity[maxMap][20]; //sets the maximum objects displayed at a time and the maximum map
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster[][] = new Entity[maxMap][20];   //sets total number of monsters at once
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][100];
    public Entity projectile[][] = new Entity[maxMap][20];
//public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();
    
    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);  //added so that GamePanel can recognize key inputs.
        this.setFocusable(true);    //GamePanel can be focused to recieve keyInputs
    }
    
    public void setUpGame(){
        
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
//        playMusic(0);
//        stopMusic();
        gameState = titleState;
        
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();
        
        if(fullScreenOn == true){
            setFullScreen();
        }
    }
    public void retry(){
        
        player.setDefaultPositions();
        player.restoreLifeAndEnergy();
        aSetter.setMonster();
    }
    public void restart(){
        
        player.setDefaultValues();
        player.setDefaultPositions();
        player.restoreLifeAndEnergy();
        player.setItems();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
    }
    public void setFullScreen(){
        
        //GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();    //these both lines basically get the screen size of the user wether its laptop or PC
        gd.setFullScreenWindow(Main.window);
        
        //GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
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
            drawToTempScreen();  //draw everything to the buffered image
            drawToScreen();     //draw buffered image to the screen
            
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

        if(gameState == playState){
            //PLAYER
            player.update();
            //NPC
            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }
            for (int i = 0; i < monster[1].length; i++){
                if(monster[currentMap][i] != null){
                    if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false){
                        monster[currentMap][i].update();
                    }
                    if(monster[currentMap][i].alive == false){
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }
            for (int i = 0; i < projectile[1].length; i++){
                if(projectile[currentMap][i] != null){
                    if(projectile[currentMap][i].alive == true){
                        projectile[currentMap][i].update();
                    }
                    if(projectile[currentMap][i].alive == false){
                        projectile[currentMap][i] = null;
                    }
                }
            }
            for (int i = 0; i < particleList.size(); i++){
                if(particleList.get(i) != null){
                    if(particleList.get(i).alive == true){
                        particleList.get(i).update();
                    }
                    if(particleList.get(i).alive == false){
                        particleList.remove(i);
                    }
                }
            }
            for(int i = 0; i < iTile[1].length; i++){
                if(iTile[currentMap][i] != null){
                    iTile[currentMap][i].update();
                }
            }
        }
        if(gameState == pauseState){
            //nothing
        }
    }
    public void drawToTempScreen(){
        
        // CLEAR SCREEN FIRST (really it just covers the screen with black to avoid overlapping the previoujs screen)
        g2.setColor(Color.black);
        g2.fillRect(0, 0, screenWidth2, screenHeight2);

        //DEBUG
        long drawStart = 0;
        if(keyH.showDebugText == true){
            drawStart = System.nanoTime();
        }
        
        //TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        }
        //OTHERS
        else{
            //TILE
            tileM.draw(g2); //draw tile first before player otherwise tiles will cover player
            
            //INTERACTIVE TILE
            for(int i = 0; i < iTile[1].length; i++){
                if(iTile[currentMap][i] != null){
                    iTile[currentMap][i].draw(g2);
                }
            }
        
            //ADD ENTITIES TO THE LIST
            entityList.add(player);

            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null){
                    entityList.add(npc[currentMap][i]);
                }
            }

            for(int i =0; i < obj[1].length; i++){
                if(obj[currentMap][i] != null){
                    entityList.add(obj[currentMap][i]);
                }
            }
            
            for(int i =0; i < monster[1].length; i++){
                if(monster[currentMap][i] != null){
                    entityList.add(monster[currentMap][i]);
                }
            }
            for(int i =0; i < projectile[1].length; i++){
                if(projectile[currentMap][i] != null){
                    entityList.add(projectile[currentMap][i]);
                }
            }
            for(int i =0; i < particleList.size(); i++){
                if(particleList.get(i) != null){
                    entityList.add(particleList.get(i));
                }
            }
            
            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {
                    
                    int result = Integer.compare(o1.worldY, o2.worldY);
                    return result;
                }
            });
            
            //DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            //EMPTY ENTITY LIST
            for(int i = 0; i < entityList.size(); i++){
                entityList.clear();
            }

            //UI
            ui.draw(g2);
        }
        

        
        //DEBUG
        if(keyH.showDebugText == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            
            g2.setFont(new Font("Arial", Font.PLAIN,20));
            g2.setColor(Color.blue);
            int x = 10;
            int y = 400;
            int lineHeight = 20;
            
            g2.drawString("WorldX" + player.worldX, x, y); y += lineHeight;
            g2.drawString("WorldY" + player.worldY, x, y); y += lineHeight;
            g2.drawString("Col" + (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
            g2.drawString("Row" + (player.worldY + player.solidArea.y)/tileSize, x, y); y += lineHeight;
            g2.drawString("Draw Time: " + passed, x, y);    //(iterator, TOP_ALIGNMENT, TOP_ALIGNMENT);
            System.out.println("Draw Time: " + passed);
        }
    }
    public void drawToScreen(){
        
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
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
