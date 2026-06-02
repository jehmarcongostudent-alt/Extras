package monster;

import entity.Entity;
import java.util.Random;
import object.OBJ_Boots;
import object.OBJ_Bow_Arrow;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import rpggame.GamePanel;

public class MON_CrystalGolem_Boss extends Entity{
    
    GamePanel gp;
    public static final String monName = "Crystal Golem";
    
    public MON_CrystalGolem_Boss(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        type = type_monster;
        name = monName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 100;
        life = maxLife;
        attack = 10;
        defense = 2;
        exp = 50;
        knockBackPower = 5;
        
        int size = gp.tileSize*5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48*2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;
        motion1_duration = 50;
        motion2_duration = 65;
        
        getImage();
        getAttackImage();
    }
    public void getImage(){
        
        int imageSize = 5;
        
        if(inRage == false){
            up0 = setup("/monster/golem_boss_up0", gp.tileSize*imageSize, gp.tileSize*imageSize);
            up1 = setup("/monster/golem_boss_up1", gp.tileSize*imageSize, gp.tileSize*imageSize);
            up2 = setup("/monster/golem_boss_up2", gp.tileSize*imageSize, gp.tileSize*imageSize);
            down0 = setup("/monster/golem_boss_down0", gp.tileSize*imageSize, gp.tileSize*imageSize);
            down1 = setup("/monster/golem_boss_down1", gp.tileSize*imageSize, gp.tileSize*imageSize);
            down2 = setup("/monster/golem_boss_down2", gp.tileSize*imageSize, gp.tileSize*imageSize);
            left0 = setup("/monster/golem_boss_left0", gp.tileSize*imageSize, gp.tileSize*imageSize);
            left1 = setup("/monster/golem_boss_left1", gp.tileSize*imageSize, gp.tileSize*imageSize);
            left2 = setup("/monster/golem_boss_left2", gp.tileSize*imageSize, gp.tileSize*imageSize);
            right0 = setup("/monster/golem_boss_right0", gp.tileSize*imageSize, gp.tileSize*imageSize);
            right1 = setup("/monster/golem_boss_right1", gp.tileSize*imageSize, gp.tileSize*imageSize);
            right2 = setup("/monster/golem_boss_right2", gp.tileSize*imageSize, gp.tileSize*imageSize);
        }
        if(inRage == true){
            up0 = setup("/monster/golem_boss_phase2_up0", gp.tileSize*imageSize, gp.tileSize*imageSize);
            up1 = setup("/monster/golem_boss_phase2_up1", gp.tileSize*imageSize, gp.tileSize*imageSize);
            up2 = setup("/monster/golem_boss_phase2_up2", gp.tileSize*imageSize, gp.tileSize*imageSize);
            down0 = setup("/monster/golem_boss_phase2_down0", gp.tileSize*imageSize, gp.tileSize*imageSize);
            down1 = setup("/monster/golem_boss_phase2_down1", gp.tileSize*imageSize, gp.tileSize*imageSize);
            down2 = setup("/monster/golem_boss_phase2_down2", gp.tileSize*imageSize, gp.tileSize*imageSize);
            left0 = setup("/monster/golem_boss_phase2_left0", gp.tileSize*imageSize, gp.tileSize*imageSize);
            left1 = setup("/monster/golem_boss_phase2_left1", gp.tileSize*imageSize, gp.tileSize*imageSize);
            left2 = setup("/monster/golem_boss_phase2_left2", gp.tileSize*imageSize, gp.tileSize*imageSize);
            right0 = setup("/monster/golem_boss_phase2_right0", gp.tileSize*imageSize, gp.tileSize*imageSize);
            right1 = setup("/monster/golem_boss_phase2_right1", gp.tileSize*imageSize, gp.tileSize*imageSize);
            right2 = setup("/monster/golem_boss_phase2_right2", gp.tileSize*imageSize, gp.tileSize*imageSize);
        }
    }
    public void getAttackImage(){
        
        int imageSize = 5;
        
        if(inRage == false){
            attackUp0 = setup("/monster/golem_boss_attack_up1", gp.tileSize*imageSize, gp.tileSize*imageSize*2);
            attackUp1 = setup("/monster/golem_boss_attack_up2", gp.tileSize*imageSize, gp.tileSize*imageSize*2);
            attackDown0 = setup("/monster/golem_boss_attack_down1", gp.tileSize*imageSize, gp.tileSize*imageSize*2);
            attackDown1 = setup("/monster/golem_boss_attack_down2", gp.tileSize*imageSize, gp.tileSize*imageSize*2);
            attackLeft0 = setup("/monster/golem_boss_attack_left1", gp.tileSize*imageSize*2, gp.tileSize*imageSize);
            attackLeft1 = setup("/monster/golem_boss_attack_left2", gp.tileSize*imageSize*2, gp.tileSize*imageSize);
            attackRight0 = setup("/monster/golem_boss_attack_right1", gp.tileSize*imageSize*2, gp.tileSize*imageSize);
            attackRight1 = setup("/monster/golem_boss_attack_right2", gp.tileSize*imageSize*2, gp.tileSize*imageSize);
        }
        if(inRage == true){
            attackUp0 = setup("/monster/golem_boss_phase2_attack_up1", gp.tileSize*imageSize, gp.tileSize*imageSize*2);
            attackUp1 = setup("/monster/golem_boss_phase2_attack_up2", gp.tileSize*imageSize, gp.tileSize*imageSize*2);
            attackDown0 = setup("/monster/golem_boss_phase2_attack_down1", gp.tileSize*imageSize, gp.tileSize*imageSize*2);
            attackDown1 = setup("/monster/golem_boss_phase2_attack_down2", gp.tileSize*imageSize, gp.tileSize*imageSize*2);
            attackLeft0 = setup("/monster/golem_boss_phase2_attack_left1", gp.tileSize*imageSize*2, gp.tileSize*imageSize);
            attackLeft1 = setup("/monster/golem_boss_phase2_attack_left2", gp.tileSize*imageSize*2, gp.tileSize*imageSize);
            attackRight0 = setup("/monster/golem_boss_phase2_attack_right1", gp.tileSize*imageSize*2, gp.tileSize*imageSize);
            attackRight1 = setup("/monster/golem_boss_phase2_attack_right2", gp.tileSize*imageSize*2, gp.tileSize*imageSize);
        }
    }
    public void setAction(){
        
        if(inRage == false && life < maxLife/2){
            inRage = true;
            getImage();
            getAttackImage();
            defaultSpeed++;
            speed = defaultSpeed;
            attack *= 2;
        }
        
        if(getTileDistance(gp.player) < 7){
            
            moveTowardPlayer(60);
        }
        else{
            //Get a random direction
            getRandomDirection(120);
        }
        
        //Check if it attacks
        if(attacking == false){
            checkAttackOrNot(60, gp.tileSize*10, gp.tileSize*5);
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
