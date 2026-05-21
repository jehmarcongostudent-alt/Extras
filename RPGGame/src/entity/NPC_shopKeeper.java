package entity;

import java.awt.Rectangle;
import java.util.Random;
import rpggame.GamePanel;


public class NPC_shopKeeper extends Entity{
    
    
    public NPC_shopKeeper(GamePanel gp){
        super(gp);
        
        direction = "down";
        speed = 2;
        
    // ADD THESE - match whatever the tutorial uses
    solidArea = new Rectangle(8, 16, 32, 32);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    
    dialogueSet = -1;
        
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
        
        dialogues[0][0] = "Buy 1 for the price of 2 and get \nanother one ABSOLUTELY FREE!!!";
        dialogues[0][1] = "I never really liked Cedric";
        dialogues[0][2] = "But his brother is the best!!";
        
        dialogues[1][0] = "If you're ever tired, drink the spring water up north west.";
        dialogues[1][1] = "It attracts monsters though.";
        dialogues[1][2] = "In any case, I better noy see you dying.";
        
        dialogues[2][0] = "Why do all door get opened by the same keys?";
    }
    public void setAction(){
        
        if(onPath == true){
            
//            int goalCol = 19;
//            int goalRow = 11;
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            
            searchPath(goalCol,goalRow);
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
    public void speak(){
        
        //Do this character specific stuff
        
        facePlayer();
        startDialogue(this,dialogueSet);
        
        dialogueSet++;
        
        if(dialogues[dialogueSet][0] == null){

            dialogueSet--;
        }
        
//        onPath = true;
    }
}
