package data;

import entity.Entity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import object.OBJ_Axe;
import object.OBJ_Axe_BattleAxe;
import object.OBJ_Axe_Rusty;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_ManaPotion;
import object.OBJ_Potion_Red;
import object.OBJ_Rock;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;
import object.OBJ_Torch;

import rpggame.GamePanel;

public class SaveLoad {
    
    GamePanel gp;
    
    public SaveLoad(GamePanel gp){
        this.gp = gp;
    }
    public Entity getObject(String itemName){
        
        Entity obj = null;
        
        switch(itemName){
            case "Gladiator's Axe": obj = new OBJ_Axe_BattleAxe(gp); break;
            case "Woodcutter's Axe": obj = new OBJ_Axe(gp); break;
            case "old used up Axe": obj = new OBJ_Axe_Rusty(gp); break;
            case "Boots": obj = new OBJ_Boots(gp); break;
            case "Chest": obj = new OBJ_Chest(gp); break;
            case "Bronze Coin": obj = new OBJ_Coin_Bronze(gp); break;
            case "Door": obj = new OBJ_Door(gp); break;
            case "Heart": obj = new OBJ_Heart(gp); break;
            case "Key": obj = new OBJ_Key(gp); break;
            case "Lantern": obj = new OBJ_Lantern(gp); break;
            case "Blue Potion": obj = new OBJ_ManaPotion(gp); break;
            case "Red Potion": obj = new OBJ_Potion_Red(gp); break;
            case "Rock": obj = new OBJ_Rock(gp); break;
            case "Blue Shield": obj = new OBJ_Shield_Blue(gp); break;
            case "Wood Shield": obj = new OBJ_Shield_Wood(gp); break;
            case "Normal Sword": obj = new OBJ_Sword_Normal(gp); break;
            case "Tent": obj = new OBJ_Tent(gp); break;
            case "Torch": obj = new OBJ_Torch(gp); break;
        }
        return obj;
    }
    public void save(){
     
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            
            DataStorage ds = new DataStorage();
            
            //PLAYER STATS
            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxEnergy = gp.player.maxEnergy;
            ds.energy = gp.player.energy;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;
            
            //PLAYER INVENTORY
            for(int i = 0; i < gp.player.inventory.size(); i++){
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }
            //PLAYER EQUIPMENT
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();

            //OBJECT ON MAP
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];
            
            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++){
                
                for(int i = 0; i < gp.obj[1].length; i++){
                    
                    if(gp.obj[mapNum][i] == null){
                        ds.mapObjectNames[mapNum][i] = "NA";
                    }
                    else{
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
                        if(gp.obj[mapNum][i].loot != null){
                            ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
                    }
                }
            }
            
            //Write the DataStorage object
            oos.writeObject(ds);
        }
        catch(Exception e){
            System.out.println("Save Exception!");
        }
    }
    public void load(){
        
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            
            //Read the DataStorage object
            DataStorage ds = (DataStorage)ois.readObject();
            
            //PLAYER STATS
            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxEnergy = ds.maxEnergy;
            gp.player.energy = ds.energy;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.coin = ds.coin;
            
            //PLAYER INVENTORY
            gp.player.inventory.clear();
            for(int i = 0; i < ds.itemNames.size(); i++){
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }
            //PLAYER EQUIPMENT
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getAttackImage();
            
            //OBJECT ON MAP
            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++){
                
                for(int i = 0; i < gp.obj[1].length; i++){
                    
                    
                    if(ds.mapObjectNames[mapNum][i].equals("NA")){
                        gp.obj[mapNum][i] = null;
                    }
                    else{
                        gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if(ds.mapObjectLootNames[mapNum][i] != null){
                            gp.obj[mapNum][i].loot = getObject(ds.mapObjectLootNames[mapNum][i]);
                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if(gp.obj[mapNum][i].opened == true){
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                        }
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println("Load Exception!");
        }
    }
}
