package entity;

import java.util.Random;
import rpggame.GamePanel;


public class NPC_shopKeeper extends Entity{
    
    
    public NPC_shopKeeper(GamePanel gp){
        super(gp);
        
        direction = "down";
        speed = 1;
        
        getImage();
        setDialogue();
    }
    public void getImage(){

        up0 = setup("/npc/shopKeeper_up0", gp.tileSize, gp.tileSize);
        up1 = setup("/npc/shopKeeper_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/shopKeeper_up2", gp.tileSize, gp.tileSize);
        down0 = setup("/npc/shopKeeper_down0", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/shopKeeper_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/shopKeeper_down2", gp.tileSize, gp.tileSize);
        left0 = setup("/npc/shopKeeper_left0", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/shopKeeper_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/shopKeeper_left2", gp.tileSize, gp.tileSize);
        right0 = setup("/npc/shopKeeper_right0", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/shopKeeper_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/shopKeeper_right2", gp.tileSize, gp.tileSize);
    }
    public void setDialogue(){
        
        dialogues[0] = "Buy 1 for the price of 2 and get \nanother one ABSOLUTELY FREE!!!";
        dialogues[1] = "I never really liked Cedric";
        dialogues[2] = "But his brother is the best!!";
        
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
    }
    public void speak(){
        
        //Do this character specific stuff
        
        super.speak();
    }
}
