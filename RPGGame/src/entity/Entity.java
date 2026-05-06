/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import rpggame.GamePanel;
import rpggame.UtilityTool;

public class Entity {
    
    GamePanel gp;
    public BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, right0, right1, right2;  //describes image with an accessible buffer of image data (used to store image files)
    public BufferedImage attackUp0, attackUp1, attackDown0, attackDown1, attackLeft0, attackLeft1, attackRight0, attackRight1;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues[] = new String[20];
    
    //STATE
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;
    
    //COUNTER
    public int spriteCounter = 0;   //lets it do moving animation
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    
    //CHARACTER ATTRIBUTES
    public int type;    //0=player, 1 = npc, 2 = monster
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    
    //ITEM ATTRIBUTES
    public int attackValue;
    public int defenseValue;
    public String description = "";
    
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    
    public void setAction(){}   //works as the characters AI where you decide their actions.
    public void damageReaction(){}
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        
        //lets the npc face the player when talking
        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void update() {
    
        setAction();
        
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer =gp.cChecker.checkPlayer(this);
        
        if(this.type == 2 && contactPlayer == true){
            if(gp.player.invincible == false){
                //we give damage
                gp.playSE(6);
                
                int damage = attack - gp.player.defense;
                if(damage < 0){
                    damage = 0;
                }
                gp.player.life -= damage;
                
                gp.player.invincible = true;
            }
        }
        
        // IF COLLITION IS FALSE, Entity CAN MOVE
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
        
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
        
            BufferedImage image = null;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            //makes it so that it only prints tiles within the player screen boundary
            if( worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                
            switch(direction){
                case "up":
                    if(spriteNum == 0){image = up0;}
                    if(spriteNum == 1){image = up1;}
                    if(spriteNum == 2){image = up2;}
                    break;  
                case "down":
                    if(spriteNum == 0){image = down0;}
                    if(spriteNum == 1){image = down1;}
                    if(spriteNum == 2){image = down2;}
                    break;
                case "left":
                    if(spriteNum == 0){image = left0;}
                    if(spriteNum == 1){image = left1;}
                    if(spriteNum == 2){image = left2;}
                    break;
                case"right":
                    if(spriteNum == 0){image = right0;}
                    if(spriteNum == 1){image = right1;}
                    if(spriteNum == 2){image = right2;}
                    break; 
            }
            
            //Monster HP bar
            if(type == 2 && hpBarOn == true){
                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;
                
                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1, screenY - 16, gp.tileSize+2, 12);
                
                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
                
                hpBarCounter++;
            
                if(hpBarCounter > 600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }
            
            if(invincible == true){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2,0.4f);   //sets visual for invinsible active state
            }
            if(dying == true){
                dyingAnimation(g2);
            }
                
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            
                changeAlpha(g2,1f);
            }
    }
    public void dyingAnimation(Graphics2D g2){
        
        dyingCounter++;
        
        int i = 5;
        
        //Dying animation flashing 
        if(dyingCounter <= i){changeAlpha(g2,0f);}
        if(dyingCounter > i && dyingCounter <= i*2){changeAlpha(g2,1f);}    //can replace changeAlpha(g2,1f) into changing image for death animation
        if(dyingCounter > i*2 && dyingCounter <= i*3){changeAlpha(g2,0f);}
        if(dyingCounter > i*3 && dyingCounter <= i*4){changeAlpha(g2,1f);}
        if(dyingCounter > i*4 && dyingCounter <= i*5){changeAlpha(g2,0f);}
        if(dyingCounter > i*5 && dyingCounter <= i*6){changeAlpha(g2,1f);}
        if(dyingCounter > i*6 && dyingCounter <= i*7){changeAlpha(g2,0f);}
        if(dyingCounter > i*7 && dyingCounter <= i*8){changeAlpha(g2,1f);}
        if(dyingCounter > i*8){
            dying = false;
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height){
        
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        }
        
        catch(IOException e){
            e.printStackTrace();
        }   
        return image;
    }
}

