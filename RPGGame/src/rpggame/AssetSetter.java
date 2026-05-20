package rpggame;

import entity.NPC_shopKeeper;
import entity.NPC_shopKeeper_seller;
import monster.MON_BrownSlime;
import monster.MON_GreenSlime;
import monster.MON_Orc;
import monster.MON_Orc2;
import monster.MON_shopKeeperTwin;
import object.*;
import tile_interactive.IT_DryTree;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*15;
        gp.obj[mapNum][i].worldY = gp.tileSize*3;
        i++;
        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*16;
        gp.obj[mapNum][i].worldY = gp.tileSize*3;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*13;
        gp.obj[mapNum][i].worldY = gp.tileSize*2;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*12;
        gp.obj[mapNum][i].worldY = gp.tileSize*2;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*11;
        gp.obj[mapNum][i].worldY = gp.tileSize*2;
        i++;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*10;
        gp.obj[mapNum][i].worldY = gp.tileSize*2;
        i++;
        gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*9;
        gp.obj[mapNum][i].worldY = gp.tileSize*10;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*10;
        gp.obj[mapNum][i].worldY = gp.tileSize*10;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Axe_BattleAxe(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*23;
        gp.obj[mapNum][i].worldY = gp.tileSize*11;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*13;
        gp.obj[mapNum][i].worldY = gp.tileSize*10;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*14;
        gp.obj[mapNum][i].worldY = gp.tileSize*10;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*15;
        gp.obj[mapNum][i].worldY = gp.tileSize*10;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*16;
        gp.obj[mapNum][i].worldY = gp.tileSize*10;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Lantern(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*18;
        gp.obj[mapNum][i].worldY = gp.tileSize*2;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Tent(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*19;
        gp.obj[mapNum][i].worldY = gp.tileSize*2;
        
        
        mapNum = 1;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Axe_BattleAxe(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*30;
        gp.obj[mapNum][i].worldY = gp.tileSize*22;
        i++;
        gp.obj[mapNum][i] = new OBJ_Lantern(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*29;
        gp.obj[mapNum][i].worldY = gp.tileSize*22;
        i++;
        gp.obj[mapNum][i] = new OBJ_BootE(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*29;
        gp.obj[mapNum][i].worldY = gp.tileSize*22;
        i++;
        gp.obj[mapNum][i] = new OBJ_Tent(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*29;
        gp.obj[mapNum][i].worldY = gp.tileSize*22;
        i++;
        
    }
    public void setNPC(){
        
        //MAP 0
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_shopKeeper(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*14;
        gp.npc[mapNum][i].worldY = gp.tileSize*11;
        
        //MAP 1
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_shopKeeper_seller(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*26;
        gp.npc[mapNum][i].worldY = gp.tileSize*22;
    }
    public void setMonster(){
        
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*36;
        gp.monster[mapNum][i].worldY =gp.tileSize*43;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*1;
        gp.monster[mapNum][i].worldY =gp.tileSize*15;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*4;
        gp.monster[mapNum][i].worldY =gp.tileSize*5;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*5;
        gp.monster[mapNum][i].worldY =gp.tileSize*5;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*6;
        gp.monster[mapNum][i].worldY =gp.tileSize*5;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*6;
        gp.monster[mapNum][i].worldY =gp.tileSize*6;
        i++;
        gp.monster[mapNum][i] = new MON_BrownSlime(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*6;
        gp.monster[mapNum][i].worldY =gp.tileSize*9;
        i++;
        gp.monster[mapNum][i] = new MON_BrownSlime(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*7;
        gp.monster[mapNum][i].worldY =gp.tileSize*9;
        i++;
        gp.monster[mapNum][i] = new MON_BrownSlime(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*8;
        gp.monster[mapNum][i].worldY =gp.tileSize*9;
        
        i++;
        gp.monster[mapNum][i] = new MON_BrownSlime(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*9;
        gp.monster[mapNum][i].worldY =gp.tileSize*9;
        i++;
        gp.monster[mapNum][i] = new MON_shopKeeperTwin(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*37;
        gp.monster[mapNum][i].worldY =gp.tileSize*43;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*6;
        gp.monster[mapNum][i].worldY =gp.tileSize*44;
        
    }
    public void setInteractiveTile(){
        
        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,35,39);i++;
        
        //TEST
        gp.iTile[mapNum][i] = new IT_DryTree(gp,5,24);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,21,13);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,22,13);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,23,13);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,24,13);i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,25,13);i++;
    }
}
