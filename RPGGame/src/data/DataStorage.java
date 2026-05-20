package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{   //basically enables to right and read the DataStorage objects so this class and be readable and writeable
    
    //PLAYER STATS
    int level;
    int maxLife;
    int life;
    int maxEnergy;
    int energy;
    int strength;
    int dexterity;
    int exp;
    int nextLevelExp;
    int coin;
    
    //PLAYER INVENTORY
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    int currentWeaponSlot;
    int currentShieldSlot;
    
    //OBJECT ON MAP
    String mapObjectNames[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    String mapObjectLootNames[][];
    boolean mapObjectOpened[][];
}
