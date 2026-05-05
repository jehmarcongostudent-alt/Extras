package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import rpggame.GamePanel;
import rpggame.KeyHandler;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import rpggame.UtilityTool;

public class Player extends Entity{
    
    //Attributes
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    
    //player
    public String playerClass = "archer";   //setDefault class as archer
    String avatar;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        super(gp);
        
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
        
        attackArea.width = 36;
        attackArea.height = 36;
        
        setDefaultValues();
        playerClasses();
        getPlayerImage();
        getPlayerAttackImage();
    }
    public void setDefaultValues(){
        
        worldX = gp.tileSize * 6;  //starting position
        worldY = gp.tileSize * 2;  //starting postion
        speed = 4;
        direction = "down";
        
        //PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }
    //PLAYER CLASSES
    public void playerClasses(){
        
        switch(playerClass){
            case "archer":
                avatar = "archer";
                break;
            case "warrior":
                avatar = "warrior";
                break;
            case "tank":
                avatar = "tank";
                break;
            case "mage":
                avatar = "mage";
                break;
            default:
                avatar = "archer";
                break;
        }
    }
    
    public void getPlayerImage(){
        
        up0 = setup("/player/"+avatar+"/"+avatar+"_up0", gp.tileSize, gp.tileSize);
        up1 = setup("/player/"+avatar+"/"+avatar+"_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/"+avatar+"/"+avatar+"_up2", gp.tileSize, gp.tileSize);
        down0 = setup("/player/"+avatar+"/"+avatar+"_down0", gp.tileSize, gp.tileSize);
        down1 = setup("/player/"+avatar+"/"+avatar+"_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/"+avatar+"/"+avatar+"_down2", gp.tileSize, gp.tileSize);
        left0 = setup("/player/"+avatar+"/"+avatar+"_left0", gp.tileSize, gp.tileSize);
        left1 = setup("/player/"+avatar+"/"+avatar+"_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/"+avatar+"/"+avatar+"_left2", gp.tileSize, gp.tileSize);
        right0 = setup("/player/"+avatar+"/"+avatar+"_right0", gp.tileSize, gp.tileSize);
        right1 = setup("/player/"+avatar+"/"+avatar+"_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/"+avatar+"/"+avatar+"_right2", gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage(){
        
        attackUp0 = setup("/player/"+avatar+"/"+avatar+"_attack_up0", gp.tileSize, gp.tileSize*2);
        attackUp1 = setup("/player/"+avatar+"/"+avatar+"_attack_up1", gp.tileSize, gp.tileSize*2);
        attackDown0 = setup("/player/"+avatar+"/"+avatar+"_attack_down0", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/player/"+avatar+"/"+avatar+"_attack_down1", gp.tileSize, gp.tileSize*2);
        attackLeft0 = setup("/player/"+avatar+"/"+avatar+"_attack_left0", gp.tileSize*2, gp.tileSize);
        attackLeft1 = setup("/player/"+avatar+"/"+avatar+"_attack_left1", gp.tileSize*2, gp.tileSize);
        attackRight0 = setup("/player/"+avatar+"/"+avatar+"_attack_right0", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/player/"+avatar+"/"+avatar+"_attack_right1", gp.tileSize*2, gp.tileSize);
    }
    
    public void update(){
        
        if(attacking == true){
            attacking();
        }
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.spacePressed == true){    //only moves when a key is pressed
            
            if(spriteNum == 0){
                spriteNum = 1;
            }
            
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

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            
            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            
            //CHECK EVENT
            gp.eHandler.checkEvent();
            
            // IF COLLITION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false && keyH.spacePressed == false){
                
                //checks if direction player will go to has collision and will not let it move is it has collision
                switch(direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            
            gp.keyH.spacePressed = false; 
            
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
        else{
            standCounter++;
            
            if(standCounter == 20){
                spriteNum = 0;
                standCounter = 0;
            }
        }
        
        //This needs to be outside of key if statement!
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void attacking(){
        
        spriteCounter++;
        
        if(spriteCounter <= 5){
            spriteNum =1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum =2;
            
            //Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            
            //Adjust player's worldX/Y for the attackArea
            switch(direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }
            
            //attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            //Check monster collision with the updated worldX, worldY, and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);
            
            //After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
            
        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void pickUpObject(int i){
        if(i != 999){
            
            String objectName = gp.obj[i].name;
            
            switch(objectName){
                case "Boots":
                    gp.playSE(2);
                    speed += 2;
                    gp.obj[i] = null;   //removes boots after picking up
                    gp.ui.showMessage("Speed Up!");
                    break;
            }
        }
    }
    
    public void interactNPC(int i){
        
        if(gp.keyH.spacePressed == true){
            
            if(i != 999){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            else{
                attacking = true;
            }
        }
    }
    public void contactMonster(int i){
        
        if(i != 999){
            
            if(invincible == false){
                life -= 1;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i){
        
        if(i != 999){
            
            if(gp.monster[i].invincible == false){
                
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                
                if(gp.monster[i].life <= 0){
                    gp.monster[i] = null;
                }
            }
        }
        else{
            System.out.println("Miss!");
        }
    }
    
    public void draw(Graphics2D g2){
        
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);  //holds the x, y coordinates and  width, height. Draws rectangle and fills it with specified colors.
        
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        
        switch(direction){
            case "up":
                if(attacking == false){
                    if(spriteNum == 0){image = up0;}
                    if(spriteNum == 1){image = up1;}
                    if(spriteNum == 2){image = up2;}
                }
                if(attacking == true){
                    tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 0){image = attackUp0;}
                    if(spriteNum == 1){image = attackUp1;}
                    if(spriteNum == 2){image = attackUp1;}
                }
                break;  
            case "down":
                if(attacking == false){
                    if(spriteNum == 0){image = down0;}
                    if(spriteNum == 1){image = down1;}
                    if(spriteNum == 2){image = down2;}
                }
                if(attacking == true){
                    if(spriteNum == 0){image = attackDown0;}
                    if(spriteNum == 1){image = attackDown1;}
                    if(spriteNum == 2){image = attackDown1;}
                }
                break;
            case "left":
                if(attacking == false){
                    if(spriteNum == 0){image = left0;}
                    if(spriteNum == 1){image = left1;}
                    if(spriteNum == 2){image = left2;}
                }
                if(attacking == true){
                    tempScreenX = screenX - gp.tileSize;
                    if(spriteNum == 0){image = attackLeft0;}
                    if(spriteNum == 1){image = attackLeft1;}
                    if(spriteNum == 2){image = attackLeft1;}
                }
                break;
            case"right":
                if(attacking == false){
                    if(spriteNum == 0){image = right0;}
                    if(spriteNum == 1){image = right1;}
                    if(spriteNum == 2){image = right2;}
                }
                if(attacking == true){
                    if(spriteNum == 0){image = attackRight0;}
                    if(spriteNum == 1){image = attackRight1;}
                    if(spriteNum == 2){image = attackRight1;}
                }
                break; 
        }
        
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));                //sets visual for invinsible active state
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);  //given image, xaxis, y axis, wight height, null?
    
        //Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
        //DEBUG
        g2.setFont(new Font("Arial", Font.PLAIN, 26));
        g2.setColor(Color.white);
        g2.drawString("Invincible: " + invincibleCounter, 10, 400);
    }
}
