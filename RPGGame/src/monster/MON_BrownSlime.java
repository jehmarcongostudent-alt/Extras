package monster;

import entity.Entity;
import java.util.Random;
import object.OBJ_Rock;
import rpggame.GamePanel;

public class MON_BrownSlime extends Entity{
    
    GamePanel gp;
    
    public MON_BrownSlime(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        type = type_monster;
        name = "Brown Slime";
        speed = 1;
        maxLife = 6;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 3;
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
        
        up1 = setup("/monster/brownSlime_down0", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/brownSlime_down1", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/brownSlime_down0", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/brownSlime_down1", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/brownSlime_down0", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/brownSlime_down1", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/brownSlime_down0", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/brownSlime_down1", gp.tileSize, gp.tileSize);
    }
    public void setAction(){
        
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
        
        int i = new Random().nextInt(100)+1;
        if(i > 99 && projectile.alive == false && shotAvailableCounter == 30){
            
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }
    public void damageReaction(){
        
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
    
}
