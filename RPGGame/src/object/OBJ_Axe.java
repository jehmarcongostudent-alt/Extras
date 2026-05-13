package object;

import entity.Entity;
import rpggame.GamePanel;


public class OBJ_Axe extends Entity{
    
    
    public OBJ_Axe(GamePanel gp){
        super(gp);
        
        type = type_axe;
        name = "Woodcutter's Axe";
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\ncuts wood and more.";
        price = 100;
        knockBackPower = 10;
    }
}
