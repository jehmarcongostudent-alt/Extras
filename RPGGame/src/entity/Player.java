package entity;

import java.awt.Color;
import rpggame.GamePanel;
import rpggame.KeyHandler;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);   //fixes character at center of screen
        screenY = gp.screenHeight/2 - (gp.tileSize/2);  //fixes character at center of screen
        
        solidArea = new Rectangle();    //x, y, width, height sets the collission of the player
        solidArea.x = 8;
        solidArea.y = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 28;
        solidArea.height = 16;
        
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        
        worldX = gp.tileSize * 25;  //starting position
        worldY = gp.tileSize * 25;  //starting postion
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        
        try{
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/archer_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/archer_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/archer_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/archer_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/archer_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/archer_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/archer_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/archer_right2.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void update(){
        
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){    //only moves when a key is pressed
            
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }
            
            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLITION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false){
                
                //checks if direction player will go to has collision and will not let it move is it has collision
                switch(direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            spriteCounter++;
            if(spriteCounter > 12){ //when count reaches this number, picture is changed to next frame
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            
        }
    }
    public void pickUpObject(int i){
        if(i != 999){
            
            String objectName = gp.obj[i].name;
            
            switch(objectName){
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;   //removes key object
                    gp.ui.showMessage("You got a Key");
                    
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.playSE(3);
                        gp.obj[i] = null;   //removes door object
                        hasKey--;
                        gp.ui.showMessage("You opened a door!");
                    }
                    else{
                        gp.ui.showMessage("You don't have a Key...");
                    }
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed += 2;
                    gp.obj[i] = null;   //removes boots after picking up
                    gp.ui.showMessage("Speed Up!");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
        }
    }
    
    public void draw(Graphics2D g2){
        
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);  //holds the x, y coordinates and  width, height. Draws rectangle and fills it with specified colors.
        
        BufferedImage image = null;
        
        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;  
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case"right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;   
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);  //given image, xaxis, y axis, wight height, null?
    }
}
