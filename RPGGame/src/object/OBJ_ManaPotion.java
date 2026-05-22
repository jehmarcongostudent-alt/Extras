    package object;

    import entity.Entity;
    import rpggame.GamePanel;

    public class OBJ_ManaPotion extends Entity{
    
    public static final String objName = "Blue Potion";

        GamePanel gp;

        public OBJ_ManaPotion(GamePanel gp){
            super(gp);
            this.gp = gp;

            type = type_consumable ;
            name = objName;
            image = setup("/objects/potion_blue", gp.tileSize, gp.tileSize);
            image2 = setup("/objects/potion_empty", gp.tileSize, gp.tileSize);
        }
    }