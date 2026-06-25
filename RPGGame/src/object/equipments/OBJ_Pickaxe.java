package object.equipments;

import entity.Entity;
import rpggame.GamePanel;

public class OBJ_Pickaxe extends Equipment{
    
    public static final String objName = "Pickaxe";
    
    public OBJ_Pickaxe(GamePanel gp){
        super(gp);
        
        type = type_pickaxe;
        name = objName;
        down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
        attackDown0 = down1;
        spriteRotation = Math.toRadians(-45);
        weaponGripX = gp.tileSize / 2;
        weaponGripY = gp.tileSize - 8;
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nJust like minec***t.";
        price = 100;
        knockBackPower = 10;
        motion1_duration = 10;
        motion2_duration = 20;
    }
    
}
