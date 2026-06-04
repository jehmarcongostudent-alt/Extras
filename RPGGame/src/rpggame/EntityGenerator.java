package rpggame;

import entity.Entity;
import object.OBJ_Arrow;
import object.OBJ_Axe;
import object.OBJ_Axe_BattleAxe;
import object.OBJ_Axe_Rusty;
import object.OBJ_Boots;
import object.OBJ_Bow_Arrow;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Door_Iron;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_LegendaryItem;
import object.OBJ_ManaPotion;
import object.OBJ_Pickaxe;
import object.OBJ_Potion_Red;
import object.OBJ_Rock;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;
import object.OBJ_Torch;

public class EntityGenerator {
    
    GamePanel gp;
    
    public EntityGenerator(GamePanel gp){
        this.gp = gp;
    }
        public Entity getObject(String itemName){
        
        Entity obj = null;
        
        switch(itemName){
            case OBJ_Arrow.objName: obj = new OBJ_Arrow(gp); break;
            case OBJ_Axe_BattleAxe.objName: obj = new OBJ_Axe_BattleAxe(gp); break;
            case OBJ_Axe.objName: obj = new OBJ_Axe(gp); break;
            case OBJ_Axe_Rusty.objName: obj = new OBJ_Axe_Rusty(gp); break;
            case OBJ_Boots.objName: obj = new OBJ_Boots(gp); break;
            case OBJ_Bow_Arrow.objName: obj = new OBJ_Bow_Arrow(gp); break;
            case OBJ_Chest.objName: obj = new OBJ_Chest(gp); break;
            case OBJ_Coin_Bronze.objName: obj = new OBJ_Coin_Bronze(gp); break;
            case OBJ_Door.objName: obj = new OBJ_Door(gp); break;
            case OBJ_Door_Iron.objName: obj = new OBJ_Door(gp); break;
            case OBJ_Heart.objName: obj = new OBJ_Heart(gp); break;
            case OBJ_Key.objName: obj = new OBJ_Key(gp); break;
            case OBJ_Lantern.objName: obj = new OBJ_Lantern(gp); break;
            case OBJ_LegendaryItem.objName: obj = new OBJ_LegendaryItem(gp); break;
            case OBJ_ManaPotion.objName: obj = new OBJ_ManaPotion(gp); break;
            case OBJ_Pickaxe.objName: obj = new OBJ_Pickaxe(gp); break;
            case OBJ_Potion_Red.objName: obj = new OBJ_Potion_Red(gp); break;
            case OBJ_Rock.objName: obj = new OBJ_Rock(gp); break;
            case OBJ_Shield_Blue.objName: obj = new OBJ_Shield_Blue(gp); break;
            case OBJ_Shield_Wood.objName: obj = new OBJ_Shield_Wood(gp); break;
            case OBJ_Sword_Normal.objName: obj = new OBJ_Sword_Normal(gp); break;
            case OBJ_Tent.objName: obj = new OBJ_Tent(gp); break;
            case OBJ_Torch.objName: obj = new OBJ_Torch(gp); break;
        }
        return obj;
    }
}
