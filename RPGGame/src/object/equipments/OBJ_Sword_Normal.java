package object.equipments;

import java.awt.image.BufferedImage;
import rpggame.GamePanel;

public class OBJ_Sword_Normal extends Equipment{
    
    public static final String objName = "Normal Sword";
    
    public OBJ_Sword_Normal(GamePanel gp){
        super(gp);
        
        type = type_sword;
        name = objName;
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackDown0 = down1;
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        weaponPivotDistance = 0;
        description = "[" + name + "]\nAn old sword.";
        price = 20;
        knockBackPower = 2;
        motion1_duration = 5;
        motion2_duration = 25;
        attackDuration = motion1_duration + motion2_duration;
        weaponArmLength = 0;
        

        
    }
}
