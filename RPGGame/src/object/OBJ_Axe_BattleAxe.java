package object;

import entity.Entity;
import rpggame.GamePanel;


public class OBJ_Axe_BattleAxe extends Entity{
    
    
    public OBJ_Axe_BattleAxe(GamePanel gp){
        super(gp);
        
        type = type_axe;
        name = "Gladiator's Axe";
        down1 = setup("/objects/axe_battleAxe", gp.tileSize, gp.tileSize);
        attackValue = 5;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nused in countless battles.";
        price = 1500;
        knockBackPower = 15;
        motion1_duration = 19;
        motion2_duration = 39;
    }
}
