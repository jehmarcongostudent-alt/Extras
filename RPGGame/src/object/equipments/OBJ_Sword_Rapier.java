package object.equipments;

import java.awt.image.BufferedImage;
import rpggame.GamePanel;

public class OBJ_Sword_Rapier extends Equipment{
    
    public static final String objName = "Rapier";
    
    public OBJ_Sword_Rapier(GamePanel gp){
        super(gp);
        
        type = type_sword;
        name = objName;
        down1 = setup("/objects/rapier", gp.tileSize, gp.tileSize);
        attackDown0 = down1;
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        weaponPivotDistance = 0;
        description = "[" + name + "]\nA fast and stabby \nsword.";
        price = 20;
        knockBackPower = 2;
        motion1_duration = 3;
        motion2_duration = 15;
        attackDuration = motion1_duration + motion2_duration;
        weaponArmLength = 0;
        

        
    }
}
