package object;

import entity.Entity;
import rpggame.GamePanel;

public class OBJ_LegendaryItem  extends Entity{
    
    GamePanel gp;
    public static final String objName = "Legendary Item";

    public OBJ_LegendaryItem(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        type = type_pickupOnly;
        name = objName;
        down1 = setup("/objects/legendaryitem",gp.tileSize,gp.tileSize);
        
        setDialogues();
    }

    private void setDialogues() {
        
        dialogues[0][0] = "You pick up a beautiful weapon of legends";
        dialogues[0][1] = "You find the treasure of the hidden deep caves!";
    }
    public boolean use(Entity entity){
        
        gp.gameState = gp.cutsceneState;
        gp.csManager.sceneNum = gp.csManager.ending;
        return true;
    }
    
}
