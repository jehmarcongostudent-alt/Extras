package rpggame;

import entity.NPC_BigRock;
import entity.NPC_shopKeeper;
import entity.NPC_shopKeeper_seller;
import monster.MON_Bat;
import monster.MON_BrownSlime;
import monster.MON_CrystalGolem_Boss;
import monster.MON_GreenSlime;
import monster.MON_Orc;
import monster.MON_Orc2;
import monster.MON_Orc3;
import monster.MON_shopKeeperTwin;
import object.*;
import tile_interactive.IT_DestructibleWall;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_MetalPlate;

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
        
        mapNum = 2;
        i = 0;
        //Chests
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*25;
        gp.obj[mapNum][i].worldY = gp.tileSize*3;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*26;
        gp.obj[mapNum][i].worldY = gp.tileSize*42;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*12;
        gp.obj[mapNum][i].worldY = gp.tileSize*48;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*44;
        gp.obj[mapNum][i].worldY = gp.tileSize*14;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*43;
        gp.obj[mapNum][i].worldY = gp.tileSize*14;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*42;
        gp.obj[mapNum][i].worldY = gp.tileSize*14;
        i++;
        //Iron Doors
        gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*30;
        gp.obj[mapNum][i].worldY = gp.tileSize*45;
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
        
        //MAP 2
        mapNum = 2;
        i = 0;
        //Big Rocks
        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*21;
        gp.npc[mapNum][i].worldY = gp.tileSize*36;
        i++;
        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*38;
        gp.npc[mapNum][i].worldY = gp.tileSize*4;
        i++;
        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*4;
        gp.npc[mapNum][i].worldY = gp.tileSize*24;
        i++;
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
        
        mapNum = 2;
        i++;
        gp.monster[mapNum][i] = new MON_Orc3(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*32;
        gp.monster[mapNum][i].worldY =gp.tileSize*36;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*6;
        gp.monster[mapNum][i].worldY =gp.tileSize*6;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*7;
        gp.monster[mapNum][i].worldY =gp.tileSize*6;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*45;
        gp.monster[mapNum][i].worldY =gp.tileSize*4;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*3;
        gp.monster[mapNum][i].worldY =gp.tileSize*45;
        i++;
        
        mapNum = 3;
        i++;
        gp.monster[mapNum][i] = new MON_CrystalGolem_Boss(gp);
        gp.monster[mapNum][i].worldX =gp.tileSize*32;
        gp.monster[mapNum][i].worldY =gp.tileSize*36;
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
        
        
        mapNum = 2;
        i = 0;
        
        //Destructible Wall
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,2,39);i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,3,39);i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,24,23);i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,31,23);i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,24,20);i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,31,20);i++;
        //Metal Plate
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp,41,36);i++;
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp,45,5);i++;
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp,4,29);i++;
        
    }
}
