package monster;

import entity.Entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import object.OBJ_Boots;
import object.OBJ_Bow_Arrow;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import rpggame.GamePanel;

public class MON_Orc2 extends Entity{
    
    GamePanel gp;
    int numOfTiles;
    
    public MON_Orc2(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        type = type_monster;
        name = "Orc test";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 5;
        defense = 2;
        exp = 10;
        
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        
        numOfTiles = 2;
        
        setCharacterSize();
        
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        attackArea.width = 48;
        attackArea.height = 48;
        motion1_duration = 40;
        motion2_duration = 85;
        
        getImage();
        getAttackImage();
    }
    public void setCharacterSize(){
        solidArea.x += gp.tileSize/numOfTiles;
        solidArea.y += gp.tileSize;
    }
    public void getImage(){
        
        up0 = setup("/monster/orc_up0", gp.tileSize * numOfTiles, gp.tileSize * numOfTiles);
        up1 = setup("/monster/orc_up1", gp.tileSize * numOfTiles, gp.tileSize * numOfTiles);
        up2 = setup("/monster/orc_up2", gp.tileSize * numOfTiles, gp.tileSize * numOfTiles);
        down0 = setup("/monster/orc_down0", gp.tileSize * numOfTiles, gp.tileSize * numOfTiles);
        down1 = setup("/monster/orc_down1", gp.tileSize * numOfTiles, gp.tileSize * numOfTiles);
        down2 = setup("/monster/orc_down2", gp.tileSize * numOfTiles, gp.tileSize * numOfTiles);
        left0 = setup("/monster/orc_left0", gp.tileSize * numOfTiles, gp.tileSize * numOfTiles);
        left1 = setup("/monster/orc_left1", gp.tileSize * numOfTiles, gp.tileSize * numOfTiles);
        left2 = setup("/monster/orc_left2", gp.tileSize * numOfTiles, gp.tileSize * numOfTiles);
        right0 = setup("/monster/orc_right0", gp.tileSize * numOfTiles, gp.tileSize * numOfTiles);
        right1 = setup("/monster/orc_right1", gp.tileSize * numOfTiles, gp.tileSize * numOfTiles);
        right2 = setup("/monster/orc_right2", gp.tileSize * numOfTiles, gp.tileSize * numOfTiles);
    }
    public void getAttackImage(){
        
        attackUp0 = setup("/monster/orc_attack_up1", gp.tileSize * numOfTiles, gp.tileSize*2 * numOfTiles);
        attackUp1 = setup("/monster/orc_attack_up2", gp.tileSize * numOfTiles, gp.tileSize*2 * numOfTiles);
        attackDown0 = setup("/monster/orc_attack_down1", gp.tileSize * numOfTiles, gp.tileSize*2 * numOfTiles);
        attackDown1 = setup("/monster/orc_attack_down2", gp.tileSize * numOfTiles, gp.tileSize*2 * numOfTiles);
        attackLeft0 = setup("/monster/orc_attack_left1", gp.tileSize*2 * numOfTiles, gp.tileSize * numOfTiles);
        attackLeft1 = setup("/monster/orc_attack_left2", gp.tileSize*2 * numOfTiles, gp.tileSize * numOfTiles);
        attackRight0 = setup("/monster/orc_attack_right1", gp.tileSize*2 * numOfTiles, gp.tileSize * numOfTiles);
        attackRight1 = setup("/monster/orc_attack_right2", gp.tileSize*2 * numOfTiles, gp.tileSize * numOfTiles);
    }
    public void setAction(){
        
        if(onPath == true){
            
            //check if it stops chasing
            checkStopChasingOrNot(gp.player, 15, 100);
            
            //Search the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        }
        else{
            //Check if it starts chasing
            checkStartChasingOrNot(gp.player, 5 ,100);
            
            //Get a random direction
            getRandomDirection();
        }
        
        //Check if it attacks
        if(attacking == false){
            checkAttackOrNot(30, gp.tileSize*2, gp.tileSize);
        }
    }
    public void damageReaction(){
        
        actionLockCounter = 0;
        //direction = gp.player.direction;
        onPath = true;
    }
    @Override
    public void draw(Graphics2D g2){
        
            BufferedImage image = null;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            //makes it so that it only prints tiles within the player screen boundary
            if( worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                
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
                                tempScreenY = screenY - gp.tileSize*2;
                                if(spriteNum == 0){image = attackUp0;}
                                if(spriteNum == 1){image = attackUp1;}
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
                            }
                            break;
                        case "left":
                            if(attacking == false){
                                if(spriteNum == 0){image = left0;}
                                if(spriteNum == 1){image = left1;}
                                if(spriteNum == 2){image = left2;}
                            }
                            if(attacking == true){
                                tempScreenX = screenX - gp.tileSize*2;
                                if(spriteNum == 0){image = attackLeft0;}
                                if(spriteNum == 1){image = attackLeft1;}
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
                            }
                            break; 
                    }
            
//            //Monster HP bar
//            if(type == 2 && hpBarOn == true){
//                double oneScale = (double)gp.tileSize/maxLife;
//                double hpBarValue = oneScale*life;
//                
//                g2.setColor(new Color(35,35,35));
//                g2.fillRect(screenX-1, screenY - 16, gp.tileSize+2, 12);
//                
//                g2.setColor(new Color(255,0,30));
//                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
//                
//                hpBarCounter++;
//            
//                if(hpBarCounter > 600){
//                    hpBarCounter = 0;
//                    hpBarOn = false;
//                }
//            }
//            
//            if(invincible == true){
//                hpBarOn = true;
//                hpBarCounter = 0;
//                changeAlpha(g2,0.4f);   //sets visual for invinsible active state
//            }
            if(dying == true){
                dyingAnimation(g2);
            }
                
            g2.drawImage(image, tempScreenX, tempScreenY, null);
                changeAlpha(g2,1f);
            }
    }
    public void checkDrop(){
        
        //CAST A DIE(random number)
        int i = new Random().nextInt(100)+1;
        
        //SET THE MONSTER DROP
        if(i < 25){
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if(i >= 25 && i < 50){
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 50 && i < 90){
            dropItem(new OBJ_Bow_Arrow(gp));
        }
        if(i >= 90 && i < 100){
            dropItem(new OBJ_Boots(gp));
        }
    }
}
