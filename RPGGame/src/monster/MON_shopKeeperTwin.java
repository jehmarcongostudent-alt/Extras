package monster;

import entity.Entity;
import java.util.Random;
import object.OBJ_Boots;
import object.OBJ_Bow_Arrow;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_Rock;
import rpggame.GamePanel;

public class MON_shopKeeperTwin extends Entity{
    
    GamePanel gp;
    
    public MON_shopKeeperTwin(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        type = type_monster;
        name = "Drunk Man";
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 750;
        life = maxLife;
        attack = 10;
        defense = 2;
        exp = 500;
        projectile = new OBJ_Rock(gp);
        
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
    public void getImage(){
        
        up1 = setup("/npc/shopKeeper_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/shopKeeper_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/shopKeeper_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/shopKeeper_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/shopKeeper_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/shopKeeper_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/shopKeeper_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/shopKeeper_right2", gp.tileSize, gp.tileSize);
    }
    public void setAction(){
        
        if(onPath == true){
            
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            
            searchPath(goalCol,goalRow);
            
            int i = new Random().nextInt(200)+1;
            if(i > 197 && projectile.alive == false && shotAvailableCounter == 30){

                projectile.set(worldX, worldY, direction, true, this);
 //               gp.projectileList.add(projectile);
                shotAvailableCounter = 0;
                
                //CHECK VACANCY
                for(int ii = 0; ii < gp.projectile[1].length; ii++){
                    if(gp.projectile[gp.currentMap][ii] == null){
                        gp.projectile[gp.currentMap][ii] = projectile;
                        break;
                    }
                }
            }
        }
        else{
            
            actionLockCounter++;

            if(actionLockCounter == 120){

                Random random = new Random();
                int i = random.nextInt(100)+1;  //picks a number from 1 to 100

                if(i <= 25){
                    direction = "up";
                }
                if(i > 25 && i <= 50){
                    direction = "down";
                }
                if(i > 50 && i<= 75){
                    direction = "left";
                }
                if(i > 75 && i <= 100){
                    direction = "right";
                }

                actionLockCounter = 0;
            }
        }
    }
    public void damageReaction(){
        
        actionLockCounter = 0;
        //direction = gp.player.direction;
        onPath = true;
        speed += 1;
    }
    public void checkDrop(){
        
        //CAST A DIE(random number)
        int i = new Random().nextInt(100)+1;
        
        //SET THE MONSTER DROP
        if(i < 10){
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if(i >= 10 && i < 30){
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 30 && i < 75){
            dropItem(new OBJ_Bow_Arrow(gp));
        }
        if(i >= 30 && i < 100){
            dropItem(new OBJ_Boots(gp));
        }
    }
}
