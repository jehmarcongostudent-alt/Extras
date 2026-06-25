package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import rpggame.GamePanel;
import rpggame.UtilityTool;
import vfx.SlashEffect;

public class Entity {
    
    GamePanel gp;
    public BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, right0, right1, right2;  //describes image with an accessible buffer of image data (used to store image files)
    public BufferedImage attackUp0, attackUp1, attackDown0, attackDown1, attackLeft0, attackLeft1, attackRight0, attackRight1, 
            guardUp, guardDown, guardLeft, guardRight;
    public BufferedImage headUp0, headUp1, headDown0, headDown1, headLeft0, headLeft1, headRight0, headRight1;
    public BufferedImage image, image2, image3, displayImage;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public String dialogues[][] = new String[20][20];
    public Entity attacker;
    public Entity linkedEntity;
    public boolean temp = false;
    
    //STATE
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDirection;
    public boolean guarding = false;
    public boolean transparent = false;
    public boolean offBalance = false;
    public Entity loot;
    public boolean opened = false;
    public boolean inRage = false;
    public boolean sleep = false;
    public boolean drawing = true;
    
    //COUNTER
    public int spriteCounter = 0;   //lets it do moving animation
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    public int hpBarCounter = 0;
    int knockBackCounter = 0;
    public int guardCounter = 0;
    int offBalanceCounter = 0;
    int attackCounter = 0;
    public int windupTime = 0;
    public int strikeTime = 0;
    public int attackDuration = 0;
    public int attackAreaActiveDuration = 6;
    
    
    //CHARACTER ATTRIBUTES
    public String name;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
    public int maxEnergy;
    public int energy;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentBoots;
    public Entity currentLight;
    public Projectile projectile;
    public boolean boss;
    
    //ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenseValue;
    public int speedValue;
    public String description = "";
    public int useCost;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;
    public double spriteRotation = 0;
    public int weaponOffsetX = 0;
    public int weaponOffsetY = 0;
    public int weaponGripX = -1;
    public int weaponGripY = -1;
    public int weaponPivotDistance = 0;
    public int weaponArmLength = 0;
    public int weaponArcDistance = 0;
    public int attackAreaOffsetX = 0;
    public int attackAreaOffsetY = 0;
    
    // Held weapon display (idle, non-attacking)
    public boolean showHeld = false;
    public int heldOffsetX = 0;
    public int heldOffsetY = 0;
    public double heldRotation = 0.0;
    public boolean heldFlipHorizontal = false;
    public int heldAnchorX = 0;  // pixels from player center, positive = right
    public int heldAnchorY = 0;  // pixels from player center, positive = down
    
    //TYPE
    public int type;    //0=player, 1 = npc, 2 = monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;
    public final int type_pickaxe = 10;
    public final int type_boots = 20;
    
    //VFX
    ArrayList<SlashEffect> slashEffects = new ArrayList<>();
    public BufferedImage[] slashFrames;
    public int slashSize = 1;
    
    public boolean showAttackArea = false;
    
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public int getScreenX(){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        return screenX;
    }
    public int getScreenY(){
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        return screenY;
    }
    public int getLeftX(){
        return worldX + solidArea.x;
    }
    public int getRightX(){
        return worldX + solidArea.x + solidArea.width;
    }
    public int getTopY(){
        return worldY + solidArea.y;
    }
    public int getBottomY(){
        return worldY + solidArea.y + solidArea.height;
    }
    public Rectangle getAttackAreaBounds(){
        int solidLeft = worldX + solidArea.x;
        int solidTop = worldY + solidArea.y;
        int solidRight = solidLeft + solidArea.width;
        int solidBottom = solidTop + solidArea.height;
        int solidCenterX = solidLeft + solidArea.width / 2;
        int solidCenterY = solidTop + solidArea.height / 2;

        int attackX = solidCenterX - attackArea.width / 2; // Adjust attackAreaOffsetX in the weapon class to nudge left/right.
        int attackY = solidCenterY - attackArea.height / 2; // Adjust attackAreaOffsetY in the weapon class to nudge up/down.

        switch(direction){
            case "up":    attackY = solidTop - attackArea.height; break; // Change weapon attackArea.height to resize vertical reach.
            case "down":  attackY = solidBottom; break;                  // Change attackAreaOffsetY to nudge down attacks up/down.
            case "left":  attackX = solidLeft - attackArea.width; break; // Change weapon attackArea.width to resize horizontal reach.
            case "right": attackX = solidRight; break;                   // Change attackAreaOffsetX to nudge side attacks left/right.
        }
        return new Rectangle(attackX + attackAreaOffsetX, attackY + attackAreaOffsetY, attackArea.width, attackArea.height);
    }
    public Rectangle getSolidAreaBounds(){
        return new Rectangle(worldX + solidArea.x, worldY + solidArea.y, solidArea.width, solidArea.height);
    }
    public int getCol(){
        return (worldX + solidArea.x)/gp.tileSize;
    }
    public int getRow(){
        return (worldY + solidArea.y)/gp.tileSize;
    }
    public int getCenterX(){
        int centerX = worldX + up1.getWidth()/2;
        return centerX;
    }
    public int getCenterY(){
        int centerY = worldY + up1.getHeight()/2;
        return centerY;
    }
    public int getXdistance(Entity target){
        int xDistance = Math.abs(getCenterX() - target.getCenterX());
        return xDistance;
    }
    public int getYdistance(Entity target){
        int yDistance = Math.abs(getCenterY() - target.getCenterY());
        return yDistance;
    }
    public int getTileDistance(Entity target){
        int tileDistance = (getXdistance(target) + getYdistance(target))/gp.tileSize;
        return tileDistance;
    }
    public int getGoalCol(Entity target){
        int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
        return goalCol;
    }
    public int getGoalRow(Entity target){
        int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
        return goalRow;
    }
    public void resetCounter(){
        
        spriteCounter = 0;   //lets it do moving animation
        actionLockCounter = 0;
        invincibleCounter = 0;
        shotAvailableCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }
    public void setLoot(Entity loot){}
    public void setAction(){}   //works as the characters AI where you decide their actions.
    public void move(String direction){}
    public void damageReaction(){}
    public void speak(){}
    public void facePlayer(){
        
        //lets the npc face the player when talking
        switch(gp.player.direction){
            case "up":direction = "down";break;
            case "down":direction = "up";break;
            case "left":direction = "right";break;
            case "right":direction = "left";break;
        }
    }
    public void startDialogue(Entity entity, int setNum){
        
        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }
    public void interact(){}
    public boolean use(Entity entity){return false;}
    public void checkDrop(){}
    public void dropItem(Entity droppedItem){
    
        for(int i = 0; i < gp.obj[1].length; i++){
            if(gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    public Color getParticleColor(){
        Color color = null;
        return color;
    }
    public int getParticleSize(){
        int size = 0;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 0;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 0;
        return maxLife;
    }
    public void generateParticle(Entity generator, Entity target){
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();
        
        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }
    public void checkCollision(){
        
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer =gp.cChecker.checkPlayer(this);
        
        if(this.type == type_monster && contactPlayer == true){
            
            damagePlayer(attack);
        }
    }
    public void update() {
    
        if(sleep == false){
            
            if(knockBack == true){

                checkCollision();

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
            }
            else{
                setAction();
                checkCollision();

                // IF COLLITION IS FALSE, Entity CAN MOVE
                if(collisionOn == false){

                    //checks if direction player will go to has collision and will not let it move is it has collision
                    switch(direction){
                        case "up": worldY -= speed; break;
                        case "down": worldY += speed; break;
                        case "left": worldX -= speed; break;
                        case "right": worldX += speed; break;
                    }
                }

                spriteCounter++;
                if(spriteCounter > 24){ //when count reaches this number, picture is changed to next frame
                    if(spriteNum == 1){
                        spriteNum = 2;
                    }
                    else if(spriteNum == 2){
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }

            if(invincible == true){
                invincibleCounter++;
                if(invincibleCounter > 40){
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
            if(shotAvailableCounter < 30){
                shotAvailableCounter++;
            }
            if(offBalance == true){
                offBalanceCounter++;
                if(offBalanceCounter > 60){
                    offBalance = false;
                    offBalanceCounter = 0;
                }
            }
            
        }

    }
    public void checkAttackOrNot(int rate, int straight, int horizontal){
        
        boolean targetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);
        
        switch(direction){
            case "up":
                if(gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "down":
                if(gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "left":
                if(gp.player.getCenterX() < getCenterX() && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "right":
                if(gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
        }
        
        if(targetInRange == true){
            //check if it iniaties an attack
            int i = new Random().nextInt(rate);
            if(i == 0){
                attacking = true;
                spriteNum = 0;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }
    public void checkShootOrNot(int rate, int shotInterval){
        
        int i = new Random().nextInt(rate);
        if(i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval){

            projectile.set(worldX, worldY, direction, true, this);

            //CHECK VACANCY
            for(int ii = 0; ii < gp.projectile[1].length; ii++){
                if(gp.projectile[gp.currentMap][ii] == null){
                    gp.projectile[gp.currentMap][ii] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;
        }
    }
    public void checkStartChasingOrNot(Entity target, int distance, int rate){
        
        if(getTileDistance(target) < distance){
            int i = new Random().nextInt(rate);
            if(i ==  0){
                onPath = true;
            }
        }
    }
    public void checkStopChasingOrNot(Entity target, int distance, int rate){
        
        if(getTileDistance(target) > distance){
            int i = new Random().nextInt(rate);
            if(i ==  0){
                onPath = false;
            }
        }
    }
    public void getRandomDirection(int interval){
        
            actionLockCounter++;

            if(actionLockCounter >= interval){

                Random random = new Random();
                int i = random.nextInt(100)+1;  //picks a number from 1 to 100

                if(i <= 25){direction = "up";}
                if(i > 25 && i <= 50){direction = "down";}
                if(i > 50 && i<= 75){direction = "left";}
                if(i > 75 && i <= 100){direction = "right";}

                actionLockCounter = 0;
            }
    }
    public void moveTowardPlayer(int interval){
        
        actionLockCounter ++;
        
        if(actionLockCounter >= interval){
            if(getXdistance(gp.player) > getYdistance(gp.player)) {
                if(gp.player.getCenterX() < getCenterX()){
                    direction = "left";
                }
                else{
                    direction = "right";
                }
            }
            else if(getXdistance(gp.player) < getYdistance(gp.player)){
                if(gp.player.getCenterY() < getCenterY()){
                    direction = "up";
                }
                else{
                    direction = "down";
                }
            }
            actionLockCounter = 0;
        }
        
    }
    public String getOppositeDirection(String direction){
        
        String oppositeDirection = "";
        
        switch(direction){
            case "up": oppositeDirection = "down";
            case "down": oppositeDirection = "up";
            case "left": oppositeDirection = "right";
            case "right": oppositeDirection = "left";
        }
        return oppositeDirection;
    }
    public void attacking(){
        spriteCounter++;
        showAttackArea = spriteCounter > motion1_duration
                && spriteCounter <= motion1_duration + attackAreaActiveDuration;
        
        if(spriteCounter <= motion1_duration){
            spriteNum =0;
        }
        if(spriteCounter > motion1_duration && spriteCounter <= motion1_duration + motion2_duration){
            spriteNum =1;
        }
        if(showAttackArea == true){
             
            //Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaX = solidArea.x;
            int solidAreaY = solidArea.y;
            int solidAreaDefaultXTemp = solidAreaDefaultX;
            int solidAreaDefaultYTemp = solidAreaDefaultY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            int currentSpeed = speed;
            
            Rectangle attackBounds = getAttackAreaBounds();
            
            //attackArea becomes solidArea
            worldX = attackBounds.x;
            worldY = attackBounds.y;
            solidArea.x = 0;
            solidArea.y = 0;
            solidAreaDefaultX = 0;
            solidAreaDefaultY = 0;
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            speed = 0;
            
            if(type == type_monster){
                if(attackBounds.intersects(gp.player.getSolidAreaBounds())){
                    damagePlayer(attack);
                }
            }
            else{//Player
                for(int i = 0; i < gp.monster[gp.currentMap].length; i++){
                    if(gp.monster[gp.currentMap][i] != null
                            && attackBounds.intersects(gp.monster[gp.currentMap][i].getSolidAreaBounds())){
                        gp.player.damageMonster(i, this, attack, currentWeapon.knockBackPower);
                    }
                }

                for(int i = 0; i < gp.iTile[gp.currentMap].length; i++){
                    if(gp.iTile[gp.currentMap][i] != null
                            && attackBounds.intersects(gp.iTile[gp.currentMap][i].getSolidAreaBounds())){
                        gp.player.damageInteractiveTile(i);
                    }
                }

                for(int i = 0; i < gp.projectile[gp.currentMap].length; i++){
                    if(gp.projectile[gp.currentMap][i] != null
                            && attackBounds.intersects(gp.projectile[gp.currentMap][i].getSolidAreaBounds())){
                        gp.player.damageProjectile(i);
                    }
                }
            }
            
            
            //After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.x = solidAreaX;
            solidArea.y = solidAreaY;
            solidAreaDefaultX = solidAreaDefaultXTemp;
            solidAreaDefaultY = solidAreaDefaultYTemp;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
            speed = currentSpeed;
            
        }
        if(spriteCounter > motion1_duration + motion2_duration){
            spriteNum = 1;
            spriteCounter = 0;
            showAttackArea = false;
            attacking = false;
        }
    }
    public void damagePlayer(int attack){
        
        if(gp.player.invincible == false){
            
                int damage = attack - gp.player.defense;
                
                //Get an opposite direction of attacker
                String canGuardDirection = getOppositeDirection(direction);
                
                if(gp.player.guarding == true && gp.player.direction.equals(canGuardDirection)){
                    
                    //Parry
                    if(gp.player.guardCounter < 20){
                        damage = 0;
                        gp.playSE(16);
                        setKnockBack(this, gp.player, knockBackPower);
                        offBalance = true;
                        spriteCounter =- 60;
                    }
                    //Normal guard
                    else{
                    damage /= 3;
                    gp.playSE(15);
                        
                    }
                }
                else{
                    //Not guarding
                    gp.playSE(6);
                    if(damage < 0){
                        damage = 0;
                    }
                }
                
                if(damage != 0){
                    gp.player.transparent = true;
                    setKnockBack(gp.player, this, knockBackPower);
                }

                gp.player.life -= damage;
                gp.player.invincible = true;
            }
    }
    public void setKnockBack(Entity target, Entity attacker, int knockBackPower){
        
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }
    public boolean inCamera(){
        boolean inCamera = false;
        
            if( worldX + gp.tileSize*5 > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize*5 > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                inCamera = true;
            }
            return inCamera;
    }
    public void draw(Graphics2D g2){
        
        BufferedImage image = null;

        //makes it so that it only prints tiles within the player screen boundary
        if( inCamera() == true){

            int tempScreenX = getScreenX();
            int tempScreenY = getScreenY();

            switch(direction){
                case "up":
                    if(attacking == false){
                        if(spriteNum == 0){image = up0;}
                        if(spriteNum == 1){image = up1;}
                        if(spriteNum == 2){image = up2;}
                    }
                    if(attacking == true){
                        tempScreenY = getScreenY() - up1.getHeight();
                        if(spriteNum == 0){image = attackUp0;}
                        if(spriteNum == 1){image = attackUp1;}
                    }
                    break;  
                case "down":
                    if(attacking == false){
                        if(spriteNum == 0){image = down0;}
                        if(spriteNum == 1){image = down1;}
                        if(spriteNum == 2){image = down2;}
                    }
                    if(attacking == true){
                        if(spriteNum == 0){image = attackDown0;}
                        if(spriteNum == 1){image = attackDown1;}
                    }
                    break;
                case "left":
                    if(attacking == false){
                        if(spriteNum == 0){image = left0;}
                        if(spriteNum == 1){image = left1;}
                        if(spriteNum == 2){image = left2;}
                    }
                    if(attacking == true){
                        tempScreenX = getScreenX() - left1.getWidth();
                        if(spriteNum == 0){image = attackLeft0;}
                        if(spriteNum == 1){image = attackLeft1;}
                    }
                    break;
                case"right":
                    if(attacking == false){
                        if(spriteNum == 0){image = right0;}
                        if(spriteNum == 1){image = right1;}
                        if(spriteNum == 2){image = right2;}
                    }
                    if(attacking == true){
                        if(spriteNum == 0){image = attackRight0;}
                        if(spriteNum == 1){image = attackRight1;}
                    }
                    break; 
            }

        if(invincible == true){
            hpBarOn = true;
            hpBarCounter = 0;
            changeAlpha(g2,0.4f);   //sets visual for invinsible active state
        }
        if(dying == true){
            dyingAnimation(g2);
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);
            changeAlpha(g2,1f);
        }
        
        // DEBUG: draw solid and attack areas
        if(gp.keyH.showDebugHitbox == true){
            int screenX = getScreenX();
            int screenY = getScreenY();

            // green solid area - always show
            g2.setColor(new Color(0, 255, 0, 100));
            g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

            // red attack area - only show when attacking
            if(showAttackArea == true){
                Rectangle attackBounds = getAttackAreaBounds();
                int attackScreenX = attackBounds.x - worldX + screenX;
                int attackScreenY = attackBounds.y - worldY + screenY;
                g2.setColor(new Color(255, 0, 0, 255));
                g2.drawRect(attackScreenX, attackScreenY, attackArea.width, attackArea.height);
            }
        }
    }
    public void dyingAnimation(Graphics2D g2){
        
        dyingCounter++;
        
        int i = 5;
        
        //Dying animation flashing 
        if(dyingCounter <= i){changeAlpha(g2,0f);}
        if(dyingCounter > i && dyingCounter <= i*2){changeAlpha(g2,1f);}    //can replace changeAlpha(g2,1f) into changing image for death animation
        if(dyingCounter > i*2 && dyingCounter <= i*3){changeAlpha(g2,0f);}
        if(dyingCounter > i*3 && dyingCounter <= i*4){changeAlpha(g2,1f);}
        if(dyingCounter > i*4 && dyingCounter <= i*5){changeAlpha(g2,0f);}
        if(dyingCounter > i*5 && dyingCounter <= i*6){changeAlpha(g2,1f);}
        if(dyingCounter > i*6 && dyingCounter <= i*7){changeAlpha(g2,0f);}
        if(dyingCounter > i*7 && dyingCounter <= i*8){changeAlpha(g2,1f);}
        if(dyingCounter > i*8){
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height){
        
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try{
            // check if file exists first
            if(getClass().getResourceAsStream(imagePath + ".png") == null){
                return null; // file not found, return null safely
            }
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        }
        
        catch(IOException e){
            e.printStackTrace();
        }   
        return image;
    }
    public void searchPath(int goalCol, int goalRow){
        
        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;
        
        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
        
        if(gp.pFinder.search() == true){
            
            //Next worldX & worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
            //ENtity's solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;
            
            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize){
                //left or right
                if(enLeftX > nextX){
                    direction = "left";
                }
                if(enLeftX < nextX){
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX){
                //up or left
                direction = "up";
                checkCollision();
                if(collisionOn == true){
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                //up or right
                direction = "up";
                checkCollision();
                if(collisionOn == true){
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX){
                //down or left
                direction = "down";
                checkCollision();
                if(collisionOn == true){
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX){
                //down or right
                direction = "down";
                checkCollision();
                if(collisionOn == true){
                    direction = "right";
                }
            }
            
            //If reaches the goal, stop the search
//            int nextCol = gp.pFinder.pathList.get(0).col;
//            int nextRow = gp.pFinder.pathList.get(0).row;
//            if(nextCol == goalCol && nextRow == goalRow){
//                onPath = false;
//            }
        }
    }
    public int getDetected(Entity user, Entity target[][],String targetName){

        int index = 999;
        
        //Check the surrounding object
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();
        
        switch(user.direction){
            case "up": nextWorldY = user.getTopY()-gp.player.speed; break;    // change 1 to user.speed
            case "down": nextWorldY = user.getBottomY()+gp.player.speed; break;    // change 1 to user.speed
            case "left": nextWorldX = user.getLeftX()-gp.player.speed; break;    // change 1 to user.speed
            case "right": nextWorldX = user.getRightX()+gp.player.speed; break;    // change 1 to user.speed
        }
        int col = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;
        
        for(int i = 0; i < target[1].length; i++){
            if(target[gp.currentMap][i] != null){
                if(target[gp.currentMap][i].getCol() == col &&
                        target[gp.currentMap][i].getRow() == row &&
                        target[gp.currentMap][i].name.equals(targetName)){
                    
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}


