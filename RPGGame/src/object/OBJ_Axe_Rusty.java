package object;

import entity.Entity;
import rpggame.GamePanel;


public class OBJ_Axe_Rusty extends Entity{
    
    
    public OBJ_Axe_Rusty(GamePanel gp){
        super(gp);
        
        type = type_axe;
        name = "old used up Axe";
        down1 = setup("/objects/rustyaxe", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 20;
        attackArea.height = 20;
        description = "[" + name + "]\ncuts but painful to use";
        price = 5;
        motion1_duration = 6;
        motion2_duration = 25;
    }
}
