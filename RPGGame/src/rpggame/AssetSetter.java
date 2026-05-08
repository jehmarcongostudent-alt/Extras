/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpggame;

import entity.NPC_shopKeeper;
import monster.MON_BrownSlime;
import monster.MON_GreenSlime;
import object.*;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        
        int i = 0;
        gp.obj[i] = new OBJ_Boots(gp);
        gp.obj[i].worldX = gp.tileSize*15;
        gp.obj[i].worldY = gp.tileSize*3;
        i++;
        gp.obj[i] = new OBJ_Boots(gp);
        gp.obj[i].worldX = gp.tileSize*16;
        gp.obj[i].worldY = gp.tileSize*3;
        i++;
        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX = gp.tileSize*13;
        gp.obj[i].worldY = gp.tileSize*2;
        i++;
        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX = gp.tileSize*12;
        gp.obj[i].worldY = gp.tileSize*2;
        i++;
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = gp.tileSize*11;
        gp.obj[i].worldY = gp.tileSize*2;
        i++;
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = gp.tileSize*10;
        gp.obj[i].worldY = gp.tileSize*2;
        i++;
        gp.obj[i] = new OBJ_Shield_Blue(gp);
        gp.obj[i].worldX = gp.tileSize*10;
        gp.obj[i].worldY = gp.tileSize*10;
        i++;
        gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].worldX = gp.tileSize*3;
        gp.obj[i].worldY = gp.tileSize*11;
        i++;
        gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].worldX = gp.tileSize*6;
        gp.obj[i].worldY = gp.tileSize*21;
        
        i++;
        gp.obj[i] = new OBJ_Heart(gp);
        gp.obj[i].worldX = gp.tileSize*7;
        gp.obj[i].worldY = gp.tileSize*21;
        
        i++;
        gp.obj[i] = new OBJ_Bow_Arrow(gp);
        gp.obj[i].worldX = gp.tileSize*8;
        gp.obj[i].worldY = gp.tileSize*21;
        
    }
    public void setNPC(){
        
        gp.npc[0] = new NPC_shopKeeper(gp);
        gp.npc[0].worldX = gp.tileSize*14;
        gp.npc[0].worldY = gp.tileSize*11;
    }
    public void setMonster(){
        
        int i = 0;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX =gp.tileSize*37;
        gp.monster[i].worldY =gp.tileSize*43;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX =gp.tileSize*1;
        gp.monster[i].worldY =gp.tileSize*15;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX =gp.tileSize*4;
        gp.monster[i].worldY =gp.tileSize*5;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX =gp.tileSize*5;
        gp.monster[i].worldY =gp.tileSize*5;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX =gp.tileSize*6;
        gp.monster[i].worldY =gp.tileSize*5;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX =gp.tileSize*6;
        gp.monster[i].worldY =gp.tileSize*6;
        i++;
        gp.monster[i] = new MON_BrownSlime(gp);
        gp.monster[i].worldX =gp.tileSize*7;
        gp.monster[i].worldY =gp.tileSize*7;
    }
}
