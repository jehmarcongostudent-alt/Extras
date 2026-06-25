package entity;

import java.util.Random;
import object.equipments.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import rpggame.GamePanel;


public class NPC_shopKeeper_seller extends Entity{
    
    
    public NPC_shopKeeper_seller(GamePanel gp){
        super(gp);
        
        direction = "down";
        speed = 1;
        
        getImage();
        setDialogue();
        setItem();
    }
    public void getImage(){

        up0 = setup("/npc/shopKeeper_up0", gp.tileSize, gp.tileSize);
        up1 = setup("/npc/shopKeeper_up0", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/shopKeeper_up0", gp.tileSize, gp.tileSize);
        down0 = setup("/npc/shopKeeper_down0", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/shopKeeper_down0", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/shopKeeper_down0", gp.tileSize, gp.tileSize);
        left0 = setup("/npc/shopKeeper_left0", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/shopKeeper_left0", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/shopKeeper_left0", gp.tileSize, gp.tileSize);
        right0 = setup("/npc/shopKeeper_right0", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/shopKeeper_right0", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/shopKeeper_right0", gp.tileSize, gp.tileSize);
    }
    public void setDialogue(){
        
        dialogues[0][0] = "Welcome traveler\nBrave of you to enter my shop";
        dialogues[1][0] = "Blessed be you journey";
        dialogues[2][0] = "You need more coins to but that!";
        dialogues[3][0] = "Storage is full!";
        dialogues[4][0] = "You cannot sell an equipped item!";
    }
    public void setItem(){
        
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
        inventory.add(new OBJ_Boots(gp));
    }
    public void speak(){
        
        facePlayer();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
