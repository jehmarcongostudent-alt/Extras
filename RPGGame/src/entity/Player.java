package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import rpggame.GamePanel;
import rpggame.KeyHandler;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.util.ArrayList;
import object.OBJ_Arrow;
import object.equipments.OBJ_Axe;
import object.equipments.OBJ_Axe_Rusty;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.equipments.OBJ_Sword_Normal;
import object.OBJ_Torch;
import object.equipments.OBJ_Axe_Divine_Rhitta;
import object.equipments.OBJ_Sword_Rapier;
import object.equipments.OBJ_Sword_Scimitar;
import rpggame.UtilityTool;
import vfx.SlashEffect;

public class Player extends Entity{
    
    //Attributes
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;
    public boolean lightUpdated = false;
    
    // Default slash textures for fallback
    public BufferedImage defaultSlash1, defaultSlash2, defaultSlash3;
    
    //player
    public String playerClass = "archer";   //setDefault class as archer
    String avatar;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        super(gp);
        
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);   //fixes character at center of screen
        screenY = gp.screenHeight/2 - (gp.tileSize/2);  //fixes character at center of screen
        
        solidArea = new Rectangle();    //x, y, width, height sets the collission of the player
        solidArea.x = 8;
        solidArea.y = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 28;
        solidArea.height = 16;
        
        setDefaultValues();
    }
    public void setDefaultValues(){
        
        worldX = gp.tileSize * 6;  //starting position
        worldY = gp.tileSize * 2;  //starting postion
        //for testing
//        worldX = gp.tileSize*26;
//        worldY = gp.tileSize*47;
//        gp.currentMap = 3;
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down";
        
        //PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxEnergy = 20;
        energy = maxEnergy;
        strength = 5;   //strenth = damage
        dexterity = 1;  //dex = less damage
        exp = 0;
        nextLevelExp = 5;
        coin = 50000;
        currentWeapon = new OBJ_Sword_Normal(gp);
        //currentWeapon = new OBJ_Axe(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        currentLight = new OBJ_Torch(gp);
        projectile = new OBJ_Arrow(gp);
        attack = getAttack();   //total attack is from strenth and weapon
        defense = getDefense(); //total shield is from dex and shield
        
        //weapon stats
        windupTime = 2;
        strikeTime = 18;
        attackDuration = windupTime + strikeTime;
        
        playerClasses();
        getImage();
//        getAttackImage();
        getGuardImage();
        setItems();
        setDialogue();
    }
    public void setDefaultPositions(){
        
        gp.currentMap = 0;
        worldX = gp.tileSize * 6;
        worldY = gp.tileSize * 2;
        direction = "down";
    }
    public void setDialogue(){
        
        dialogues[0][0] = "You are level " + level + " now!\n"+"You feel stronger!";
    }
    public void restoreStatus(){
        
        life = maxLife;
        energy = maxEnergy;
        speed = defaultSpeed;
        invincible = false;
        transparent = false;
        attacking = false;
        guarding = false;
        knockBack = false;
        lightUpdated = true;
    }
    public void setItems(){
        
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        if(currentLight != null){
            inventory.add(currentLight);
        }
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Axe_Rusty(gp));
        inventory.add(new OBJ_Axe_Divine_Rhitta(gp));
        inventory.add(new OBJ_Sword_Rapier(gp));
        inventory.add(new OBJ_Sword_Scimitar(gp));
    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
    }
    public int getCurrentWeaponSlot(){
        int currentWeaponSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentWeapon){
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }
    public int getCurrentShieldSlot(){
        int getCurrentShieldSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentShield){
                getCurrentShieldSlot = i;
            }
        }
        return getCurrentShieldSlot;
    }
    public int getSpeed(){
        return speed = speed + currentBoots.speedValue;
    }
    //PLAYER CLASSES
    public void playerClasses(){
        
        switch(playerClass){
            case "archer":
                avatar = "archer";
                break;
            case "warrior":
                avatar = "warrior";
                break;
            case "tank":
                avatar = "tank";
                break;
            case "mage":
                avatar = "mage";
                break;
            default:
                avatar = "archer";
                break;
        }
    }
    public void getImage(){
        up0 = setup("/player/"+avatar+"/"+avatar+"_up0", gp.tileSize, gp.tileSize);
        up1 = setup("/player/"+avatar+"/"+avatar+"_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/"+avatar+"/"+avatar+"_up2", gp.tileSize, gp.tileSize);
        down0 = setup("/player/"+avatar+"/"+avatar+"_down0", gp.tileSize, gp.tileSize);
        down1 = setup("/player/"+avatar+"/"+avatar+"_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/"+avatar+"/"+avatar+"_down2", gp.tileSize, gp.tileSize);
        left0 = setup("/player/"+avatar+"/"+avatar+"_left0", gp.tileSize, gp.tileSize);
        left1 = setup("/player/"+avatar+"/"+avatar+"_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/"+avatar+"/"+avatar+"_left2", gp.tileSize, gp.tileSize);
        right0 = setup("/player/"+avatar+"/"+avatar+"_right0", gp.tileSize, gp.tileSize);
        right1 = setup("/player/"+avatar+"/"+avatar+"_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/"+avatar+"/"+avatar+"_right2", gp.tileSize, gp.tileSize);
        
        headDown0 = setup("/player/"+avatar+"/"+avatar+"_down_head1", gp.tileSize, gp.tileSize);
        headDown1 = setup("/player/"+avatar+"/"+avatar+"_down_head2", gp.tileSize, gp.tileSize);
        headLeft0 = setup("/player/"+avatar+"/"+avatar+"_left_head1", gp.tileSize, gp.tileSize);
        headLeft1 = setup("/player/"+avatar+"/"+avatar+"_left_head2", gp.tileSize, gp.tileSize);
        headRight0 = setup("/player/"+avatar+"/"+avatar+"_right_head1", gp.tileSize, gp.tileSize);
        headRight1 = setup("/player/"+avatar+"/"+avatar+"_right_head2", gp.tileSize, gp.tileSize);
        
        // 🔥 Load the default slash effects here
        defaultSlash1 = setup("/vfx/slash1", gp.tileSize, gp.tileSize);
        defaultSlash2 = setup("/vfx/slash2", gp.tileSize, gp.tileSize);
        defaultSlash3 = setup("/vfx/slash3", gp.tileSize, gp.tileSize);
    }
    public void getSleepingImage(BufferedImage image){
        up0 = image;
        up1 = image;
        up2 = image;
        down0 = image;
        down1 = image;
        down2 = image;
        left0 = image;
        left1 = image;
        left2 = image;
        right0 = image;
        right1 = image;
        right2 = image;
    }
//    public void getAttackImage(){
//        
//        if(currentWeapon.type == type_sword){
//            attackUp0 = setup("/player/"+avatar+"/"+avatar+"_attack_up0", gp.tileSize, gp.tileSize*2);
//            attackUp1 = setup("/player/"+avatar+"/"+avatar+"_attack_up1", gp.tileSize, gp.tileSize*2);
//            attackDown0 = setup("/player/"+avatar+"/"+avatar+"_attack_down0", gp.tileSize, gp.tileSize*2);
//            attackDown1 = setup("/player/"+avatar+"/"+avatar+"_attack_down1", gp.tileSize, gp.tileSize*2);
//            attackLeft0 = setup("/player/"+avatar+"/"+avatar+"_attack_left0", gp.tileSize*2, gp.tileSize);
//            attackLeft1 = setup("/player/"+avatar+"/"+avatar+"_attack_left1", gp.tileSize*2, gp.tileSize);
//            attackRight0 = setup("/player/"+avatar+"/"+avatar+"_attack_right0", gp.tileSize*2, gp.tileSize);
//            attackRight1 = setup("/player/"+avatar+"/"+avatar+"_attack_right1", gp.tileSize*2, gp.tileSize);
//        }
//        if(currentWeapon.type == type_axe){
//            attackUp0 = setup("/player/"+avatar+"/"+avatar+"_axe_up0", gp.tileSize, gp.tileSize*2);
//            attackUp1 = setup("/player/"+avatar+"/"+avatar+"_axe_up1", gp.tileSize, gp.tileSize*2);
//            attackDown0 = setup("/player/"+avatar+"/"+avatar+"_axe_down0", gp.tileSize, gp.tileSize*2);
//            attackDown1 = setup("/player/"+avatar+"/"+avatar+"_axe_down1", gp.tileSize, gp.tileSize*2);
//            attackLeft0 = setup("/player/"+avatar+"/"+avatar+"_axe_left0", gp.tileSize*2, gp.tileSize);
//            attackLeft1 = setup("/player/"+avatar+"/"+avatar+"_axe_left1", gp.tileSize*2, gp.tileSize);
//            attackRight0 = setup("/player/"+avatar+"/"+avatar+"_axe_right0", gp.tileSize*2, gp.tileSize);
//            attackRight1 = setup("/player/"+avatar+"/"+avatar+"_axe_right1", gp.tileSize*2, gp.tileSize);
//        }
//        if(currentWeapon.type == type_pickaxe){
//            attackUp0 = setup("/player/"+avatar+"/"+avatar+"_pickaxe_up0", gp.tileSize, gp.tileSize*2);
//            attackUp1 = setup("/player/"+avatar+"/"+avatar+"_pickaxe_up1", gp.tileSize, gp.tileSize*2);
//            attackDown0 = setup("/player/"+avatar+"/"+avatar+"_pickaxe_down0", gp.tileSize, gp.tileSize*2);
//            attackDown1 = setup("/player/"+avatar+"/"+avatar+"_pickaxe_down1", gp.tileSize, gp.tileSize*2);
//            attackLeft0 = setup("/player/"+avatar+"/"+avatar+"_pickaxe_left0", gp.tileSize*2, gp.tileSize);
//            attackLeft1 = setup("/player/"+avatar+"/"+avatar+"_pickaxe_left1", gp.tileSize*2, gp.tileSize);
//            attackRight0 = setup("/player/"+avatar+"/"+avatar+"_pickaxe_right0", gp.tileSize*2, gp.tileSize);
//            attackRight1 = setup("/player/"+avatar+"/"+avatar+"_pickaxe_right1", gp.tileSize*2, gp.tileSize);
//        }
//    }
    public void getGuardImage(){
        
        guardUp = setup("/player/"+avatar+"/"+avatar+"_shield_up", gp.tileSize, gp.tileSize);
        guardDown = setup("/player/"+avatar+"/"+avatar+"_shield_down", gp.tileSize, gp.tileSize);
        guardLeft = setup("/player/"+avatar+"/"+avatar+"_shield_left", gp.tileSize, gp.tileSize);
        guardRight = setup("/player/"+avatar+"/"+avatar+"_shield_right", gp.tileSize, gp.tileSize);
    }
    public void update(){
        
        if(knockBack == true){

            collisionOn = false;
            gp.cChecker.checkTile(this);
            gp.cChecker.checkObject(this, true);
            gp.cChecker.checkEntity(this, gp.npc);
            gp.cChecker.checkEntity(this, gp.monster);
            gp.cChecker.checkEntity(this, gp.iTile);
            
            if(collisionOn == true){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if(collisionOn == false){
                switch(knockBackDirection){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            
            knockBackCounter++;
            if(knockBackCounter == 10){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }
        
        else if(attacking == true){
            attacking();
            
            //added by AI counts the attack duration when attacking
            attackCounter++;
            
            // 🔥 SPAWN SLASH AT STRIKE MOMENT
            if(attackCounter == currentWeapon.motion1_duration){
                spawnSlash();
            }
        }
        else if(keyH.blockKeyPressed == true){
            guarding = true;
            guardCounter++;
        }
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.spacePressed == true){    //only moves when a key is pressed
            
            if(spriteNum == 0){
                spriteNum = 1;
            }
            
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }
            
            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            
            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            
            //CHECK INTERACTIVE COLLISION
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            
            //CHECK EVENT
            gp.eHandler.checkEvent();
            
            // IF COLLITION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false && keyH.spacePressed == false){
                
                //checks if direction player will go to has collision and will not let it move is it has collision
                switch(direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            
            if(keyH.spacePressed == true && attackCanceled == false){
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
                attackCounter = 0;
            }
            
            attackCanceled = false;
            gp.keyH.spacePressed = false;
            guarding = false;
            guardCounter = 0;
            
            spriteCounter++;
            if(spriteCounter > 12){ //when count reaches this number, picture is changed to next frame
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            
        }
        else{
            standCounter++;
            
            if(standCounter == 20){
                spriteNum = 0;
                standCounter = 0;
            }
            guarding = false;
            guardCounter = 0;
        }
        
        if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30 && projectile.haveResource(this) == true){
            
            //SET DEFAULT COORDINATES, DIRECTION, AND USER
            projectile.set(worldX, worldY, direction, true, this);
            
            //SUBTRACT THE COST(MANA, ARROW, ETC)
            projectile.subtractResource(this);

            //CHECK VACANCY
            for(int i = 0; i < gp.projectile[1].length; i++){
                if(gp.projectile[gp.currentMap][i] == null){
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }
            
            shotAvailableCounter = 0;
            
            gp.playSE(10);
        }
        
        //This needs to be outside of key if statement!
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                transparent = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
        if(life > maxLife){
            life = maxLife;
        }
        if(energy > maxEnergy){
            energy = maxEnergy;
        }
        if(keyH.godModeOn == false){
            if(life <= 0){
                gp.gameState = gp.gameOverState;
                gp.ui.commandNum = -1;
                gp.playSE(12);
            }
            
        }
        for(int i = 0; i < slashEffects.size(); i++){
            SlashEffect s = slashEffects.get(i);

            s.update();

            if(!s.isAlive()){
                slashEffects.remove(i);
                i--;
            }
        }
    }
    public void pickUpObject(int i){
        if(i != 999){
            
            //PICKUP ONLY ITEMS
            if(gp.obj[gp.currentMap][i].type == type_pickupOnly){
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }
            //OBSTACLE
            else if(gp.obj[gp.currentMap][i].type == type_obstacle){
                if(keyH.enterPressed == true || keyH.spacePressed == true){
                    attackCanceled = true;
                    gp.obj[gp.currentMap][i].interact();
                }
            }
            //INVENTORY ITEMS
            else{
                String text;
            
                if(canObtainItem(gp.obj[gp.currentMap][i]) == true){
                    gp.playSE(1);
                    text = "Got a "+ gp.obj[gp.currentMap][i].name + "!";
                }
                else{
                    text = "You cannot carry any more!";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }
            
        }
    }
    public void interactNPC(int i){
        
        if(i != 999){
            
            if(gp.keyH.spacePressed == true || gp.keyH.enterPressed){
                attackCanceled = true;
                gp.npc[gp.currentMap][i].speak();
            }
            
            gp.npc[gp.currentMap][i].move(direction);
        }
    }
    public void contactMonster(int i){
        
        if(i != 999){
            
            if(invincible == false && gp.monster[gp.currentMap][i].dying == false){
                gp.playSE(6);
                
                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if(damage < 0){
                    damage = 0;
                }
                life -= damage;
                invincible = true;
                transparent = true;
            }
        }
    }
    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower){
        
        if(i != 999){
            if(gp.monster[gp.currentMap][i].invincible == false){
                
                gp.playSE(5);
                
                if(knockBackPower > 0){
                    setKnockBack(gp.monster[gp.currentMap][i],attacker, knockBackPower);
                }
                
                if(gp.monster[gp.currentMap][i].offBalance == true){
                    attack *= 5;
                }
                
                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if(damage < 0){
                    damage = 0;
                }
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();
                
                if(gp.monster[gp.currentMap][i].life <= 0){
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("killed the "+ gp.monster[gp.currentMap][i].name+"!");
                    gp.ui.addMessage("Exp + "+ gp.monster[gp.currentMap][i].exp+"!");
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void damageInteractiveTile(int i){
        
        if(i != 999 && gp.iTile[gp.currentMap][i].destructable == true 
                && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true
                && gp.iTile[gp.currentMap][i].invincible == false){
            
            gp.iTile[gp.currentMap][i].playSE();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;
            
            //Generate particle
            generateParticle(gp.iTile[gp.currentMap][i],gp.iTile[gp.currentMap][i]);
            
            if(gp.iTile[gp.currentMap][i].life == 0){
                gp.iTile[gp.currentMap][i].checkDrop();
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm(); 
            }
        }
    }
    public void damageProjectile(int i){
        
        if(i != 999){
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile,projectile);
        }
    }
    public void checkLevelUp(){
        
        if(exp >= nextLevelExp){
            
            level++;
            nextLevelExp = nextLevelExp*2;  //sets the next lvl up requirement
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            
            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            
            setDialogue();
            startDialogue(this,0);
            
            startDialogue(this,0);
        }
    }
    public void selectItem(){
        
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
        
        if (itemIndex < inventory.size()){
            
            Entity selectedItem = inventory.get(itemIndex);
            
            if (selectedItem.type == type_sword || selectedItem.type == type_axe || selectedItem.type == type_pickaxe){
                
                currentWeapon = selectedItem;
                attack = getAttack();
//                getAttackImage();
            }
            if(selectedItem.type == type_shield){
                
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_boots){
                
                currentBoots = selectedItem;
                speed = getSpeed();
            }
            if(selectedItem.type == type_light){
                
                if(currentLight == selectedItem){
                    currentLight = null;
                }
                else{
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
            if(selectedItem.type == type_consumable){
                
                if(selectedItem.use(this) == true){
                    if(selectedItem.amount > 1){
                        selectedItem.amount--;
                    }
                    else{
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }
    public int searchItemInInventory(String itemName){
        
        int itemIndex = 999;
        
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).name.equals(itemName)){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }
    public boolean canObtainItem(Entity item){
        
        boolean canObtain = false;
        
        Entity newItem = gp.eGenerator.getObject(item.name);
        
        //CHECK IF STACKABLE
        if(newItem.stackable == true){
            
            int index = searchItemInInventory(item.name);
            
            if(index != 999){
                inventory.get(index).amount++;
                canObtain = true;
            }
            else{// New item so need to check vacancy
                if(inventory.size() != maxInventorySize){
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        else{// NOT STACKABLE so check vacancy)
            if(inventory.size() != maxInventorySize){
                inventory.add(newItem);
                canObtain = true;
            }
        }
        return canObtain;
    }
    public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        
        BufferedImage headImage = null;
        switch(direction){
            case "down":
                if(guarding == true){ headImage = headDown1; }
                else if(spriteNum == 0 || spriteNum == 2){ headImage = headDown0; }
                else if(spriteNum == 1){ headImage = headDown1; }
                break;
            case "left":
                if(guarding == true){ headImage = headLeft1; }
                else if(spriteNum == 0 || spriteNum == 2){ headImage = headLeft0; }
                else if(spriteNum == 1){ headImage = headLeft1; }
                break;
            case "right":
                if(guarding == true){ headImage = headRight1; }
                else if(spriteNum == 0 || spriteNum == 2){ headImage = headRight0; }
                else if(spriteNum == 1){ headImage = headRight1; }
                break;
        }
        
        switch(direction){
            case "up":
                if(attacking == false){
                    if(spriteNum == 0){image = up0;}
                    if(spriteNum == 1){image = up1;}
                    if(spriteNum == 2){image = up2;}
                }
                if(attacking == true){
//                    tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 0){image = up0;}
                    if(spriteNum == 1){image = up1;}
                    if(spriteNum == 2){image = up2;}
                }
                if(guarding == true){
                    image = guardUp;
                }
                break;  
            case "down":
                if(attacking == false){
                    if(spriteNum == 0){image = down0;}
                    if(spriteNum == 1){image = down1;}
                    if(spriteNum == 2){image = down2;}
                }
                if(attacking == true){
                    if(spriteNum == 0){image = down0;}
                    if(spriteNum == 1){image = down1;}
                    if(spriteNum == 2){image = down2;}
                }
                if(guarding == true){
                    image = guardDown;
                }
                break;
            case "left":
                if(attacking == false){
                    if(spriteNum == 0){image = left0;}
                    if(spriteNum == 1){image = left1;}
                    if(spriteNum == 2){image = left2;}
                }
                if(attacking == true){
//                    tempScreenX = screenX - gp.tileSize;
                    if(spriteNum == 0){image = left0;}
                    if(spriteNum == 1){image = left1;}
                    if(spriteNum == 2){image = left2;}
                }
                if(guarding == true){
                    image = guardLeft;
                }
                break;
            case"right":
                if(attacking == false){
                    if(spriteNum == 0){image = right0;}
                    if(spriteNum == 1){image = right1;}
                    if(spriteNum == 2){image = right2;}
                }
                if(attacking == true){
                    if(spriteNum == 0){image = right0;}
                    if(spriteNum == 1){image = right1;}
                    if(spriteNum == 2){image = right2;}
                }
                if(guarding == true){
                    image = guardRight;
                }
                break; 
        }
        
        if(transparent == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));                //sets visual for invinsible active state
        }
        if(drawing == true){
            if(direction.equals("up")) {
                g2.drawImage(image, tempScreenX, tempScreenY, null);
                drawWeapon(g2, tempScreenX, tempScreenY);
            }
            else{
                g2.drawImage(image, tempScreenX, tempScreenY, null);
                drawWeapon(g2, tempScreenX, tempScreenY);
                if(!guarding){
                    g2.drawImage(headImage, tempScreenX, tempScreenY, null);
                }
            }
        }
    
        //Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
        for(SlashEffect s : slashEffects){
            s.draw(g2);
        }
        
        // DEBUG: draw solid and attack areas
        if(gp.keyH.showDebugHitbox == true){
            g2.setColor(new Color(0, 255, 0, 100));
            g2.fillRect(tempScreenX + solidArea.x, tempScreenY + solidArea.y, solidArea.width, solidArea.height);

            if(showAttackArea == true){
                Rectangle attackBounds = getAttackAreaBounds();
                int attackScreenX = attackBounds.x - worldX + tempScreenX;
                int attackScreenY = attackBounds.y - worldY + tempScreenY;
                g2.setColor(new Color(255, 0, 0, 255));
                g2.drawRect(attackScreenX, attackScreenY, attackArea.width, attackArea.height);
            }
        }
    }
        public void drawWeapon(Graphics2D g2, int x, int y){
            
        if(currentWeapon == null) return;
        BufferedImage weaponImage = currentWeapon.attackDown0 != null ? currentWeapon.attackDown0 : currentWeapon.down1;
        if(weaponImage == null) return;
        
        int windupTime = currentWeapon.motion1_duration;
        int strikeTime = currentWeapon.motion2_duration;


        if(!attacking){
            if(currentWeapon.showHeld == false) return;

            BufferedImage heldImage = currentWeapon.down1;
            if(heldImage == null) return;

            Graphics2D g2d = (Graphics2D) g2.create();

            double heldAngle = 0;
            switch(direction){
                case "up":    heldAngle = Math.toRadians(180); currentWeapon.heldFlipHorizontal = true;  break;
                case "down":  heldAngle = Math.toRadians(90);  currentWeapon.heldFlipHorizontal = false; break;
                case "left":  heldAngle = Math.toRadians(180); currentWeapon.heldFlipHorizontal = true;  break;
                case "right": heldAngle = Math.toRadians(90);  currentWeapon.heldFlipHorizontal = false; break;
            }
            heldAngle += Math.toRadians(currentWeapon.heldRotation);

            int gripX = currentWeapon.weaponGripX >= 0 ? currentWeapon.weaponGripX : heldImage.getWidth() / 2;
            int gripY = currentWeapon.weaponGripY >= 0 ? currentWeapon.weaponGripY : heldImage.getHeight() - 8;

            int drawX = x + gp.tileSize / 2 + currentWeapon.heldOffsetX;
            int drawY = y + gp.tileSize / 2 + currentWeapon.heldOffsetY;
        switch(direction){
            case "down":
            case "right":
                drawX += currentWeapon.heldAnchorX;
                drawY += currentWeapon.heldAnchorY;
                break;
            case "up":
            case "left":
                drawX += currentWeapon.heldAnchorX;
                drawY += currentWeapon.heldAnchorY;
                drawX += (heldImage.getHeight() - 2 * gripY)- 10;
                drawY -= (heldImage.getWidth() - 2 * gripX)- 40;
                break;
        }

            g2d.translate(drawX, drawY);
            g2d.rotate(heldAngle);

            java.awt.geom.AffineTransform imgTransform = new java.awt.geom.AffineTransform();
            imgTransform.translate(-gripX, -gripY);
            if(currentWeapon.heldFlipHorizontal){
                imgTransform.scale(-1, 1);
                imgTransform.translate(-heldImage.getWidth(), 0);
            }
            imgTransform.rotate(currentWeapon.spriteRotation, gripX, gripY);

            g2d.drawImage(heldImage, imgTransform, null);
            g2d.dispose();
            return;
        }
        
        int handX = x + gp.tileSize / 2 + currentWeapon.weaponOffsetX;
        int handY = y + gp.tileSize / 2 + currentWeapon.weaponOffsetY;
        switch(direction){
            case "up": handY -= currentWeapon.weaponPivotDistance; break;
            case "down": handY += currentWeapon.weaponPivotDistance; break;
            case "left": handX -= currentWeapon.weaponPivotDistance; break;
            case "right": handX += currentWeapon.weaponPivotDistance; break;
        }
        int gripX = currentWeapon.weaponGripX >= 0 ? currentWeapon.weaponGripX : weaponImage.getWidth() / 2;
        int gripY = currentWeapon.weaponGripY >= 0 ? currentWeapon.weaponGripY : weaponImage.getHeight() - 8;

        Graphics2D g2d = (Graphics2D) g2.create();

        double baseAngle = 0;

        switch(direction){
            case "up": baseAngle = Math.toRadians(270); break;
            case "down": baseAngle = Math.toRadians(90); break;
            case "left": baseAngle = Math.toRadians(180); break;
            case "right": baseAngle = Math.toRadians(0); break;
        }

        baseAngle += Math.toRadians(90);

        double angle = baseAngle;

        if(attacking){

            if(spriteCounter < currentWeapon.motion1_duration){
                angle = baseAngle - Math.toRadians(80);
            }
            else if(spriteCounter < currentWeapon.motion1_duration + currentWeapon.motion2_duration){
                angle = baseAngle + Math.toRadians(80);
            }
            else{
                return;
            }
        }

        g2d.translate(handX, handY);
        g2d.rotate(angle); // swing rotation only, no spriteRotation here

        // rotate the image itself separately around its grip point
        java.awt.geom.AffineTransform imgTransform = new java.awt.geom.AffineTransform();
        float arcScale = 1.0f + (currentWeapon.weaponArcDistance / 100.0f);

        imgTransform.translate((int)(-gripX * arcScale), (int)(-gripY * arcScale));
        imgTransform.rotate(currentWeapon.spriteRotation, gripX, gripY);

        g2d.drawImage(weaponImage, imgTransform, null);
        g2d.dispose();

    }
    public void spawnSlash(){

         // 1. Create the container for the frames
        BufferedImage[] frames = new BufferedImage[3];

        // 2. Check if the current weapon actually has custom slash effects loaded
        if(currentWeapon != null && currentWeapon.slashFrames != null) {
            // Use the weapon's unique animations!
            frames = currentWeapon.slashFrames;
        } 
        else {
            // ✅ FALLBACK: Use a basic default slash if the weapon has none
            frames[0] = defaultSlash1; // Loaded in Player's setDefaultValues()
            frames[1] = defaultSlash2;
            frames[2] = defaultSlash3;
        }

        int slashX = worldX; // adjust later
        int slashY = worldY;

        switch(direction){
            case "up":    slashY -= gp.tileSize; break; // Spawns 1 tile above
            case "down":  slashY += gp.tileSize; break; // Spawns 1 tile below
            case "left":  slashX -= gp.tileSize; break; // Spawns 1 tile left
            case "right": slashX += gp.tileSize; break; // Spawns 1 tile right
        }
        
        // 3. Only pass it forward if we successfully grabbed frames to prevent null crashes
        if(frames != null) {
            // 1. Grab the size from the weapon, defaulting to 1 if something goes wrong
            int weaponSlashSize = (currentWeapon != null) ? currentWeapon.slashSize : 1;

            // 2. ✅ Pass weaponSlashSize as the final argument
            slashEffects.add(new SlashEffect(gp, slashX, slashY, frames, direction, weaponSlashSize)); 
        }
    }
}
