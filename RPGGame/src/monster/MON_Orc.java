package monster;

import entity.Entity;
import java.util.Random;
import object.OBJ_Boots;
import object.OBJ_Bow_Arrow;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import rpggame.GamePanel;

public class MON_Orc extends Entity{
    
    GamePanel gp;
    
    public MON_Orc(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        type = type_monster;
        name = "Orc";
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
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        motion1_duration = 40;
        motion2_duration = 85;
        
        getImage();
        getAttackImage();
    }
    public void getImage(){
        
        up0 = setup("/monster/orc_up0", gp.tileSize, gp.tileSize);
        up1 = setup("/monster/orc_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/orc_up2", gp.tileSize, gp.tileSize);
        down0 = setup("/monster/orc_down0", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/orc_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/orc_down2", gp.tileSize, gp.tileSize);
        left0 = setup("/monster/orc_left0", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/orc_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/orc_left2", gp.tileSize, gp.tileSize);
        right0 = setup("/monster/orc_right0", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/orc_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/orc_right2", gp.tileSize, gp.tileSize);
    }
    public void getAttackImage(){
        
        attackUp0 = setup("/monster/orc_attack_up1", gp.tileSize, gp.tileSize*2);
        attackUp1 = setup("/monster/orc_attack_up2", gp.tileSize, gp.tileSize*2);
        attackDown0 = setup("/monster/orc_attack_down1", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/monster/orc_attack_down2", gp.tileSize, gp.tileSize*2);
        attackLeft0 = setup("/monster/orc_attack_left1", gp.tileSize*2, gp.tileSize);
        attackLeft1 = setup("/monster/orc_attack_left2", gp.tileSize*2, gp.tileSize);
        attackRight0 = setup("/monster/orc_attack_right1", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/monster/orc_attack_right2", gp.tileSize*2, gp.tileSize);
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
