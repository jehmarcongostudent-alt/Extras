package tile;

import java.io.IOException;

import rpggame.GamePanel;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import rpggame.UtilityTool;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    //drawPath??
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();
    
    public TileManager(GamePanel gp){
        
        this.gp = gp;
        
        //READ TILE DATA FILE
        InputStream is = getClass().getResourceAsStream("/maps/tileData.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        //GETTING TILE NAMES AND COLLISION
        String line;
        
        try{
            while((line = br.readLine()) != null){
                fileNames.add(line);
                collisionStatus.add(br.readLine());
            }
            br.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        //INITIALIZE THE TILE ARRAY BASED ON THE fileNams size
        tile = new Tile[fileNames.size()];  //auto set number of tiles based on filename size
        getTileImage();
        
        //GET THE maxWorldCol & Row
        is = getClass().getResourceAsStream("/maps/world01.txt");
        br = new BufferedReader(new InputStreamReader(is));
        
        try{
            String line2 = br.readLine();
            String maxTile[] = line2.split(" ");
            
            gp.maxWorldCol = maxTile.length;
            gp.maxWorldRow = maxTile.length;
            mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; //stores the map numbers here 
                    
            br.close();
            
        }
        catch(IOException e){
            System.out.println("Exception!");
        }
        
        loadMap("/maps/world01.txt");    //edit in future to be //loadMap("/maps/sample.txt, 0");
        
        //loadMap("/maps/world01.txt");
    }
    
    public void getTileImage(){
        
        for(int i = 0; i < fileNames.size(); i++){
            
            String fileName;
            boolean collision;
            
            //Get a file name
            fileName = fileNames.get(i);
            
            //Get a collision status
            if(collisionStatus.get(i).equals("true")){
                collision = true;
            }
            else{
                collision = false;
            }
            
            setup(i, fileName, collision);
            
        }
    }
    public void setup(int index, String imageName, boolean collision){
        
        UtilityTool uTool = new UtilityTool();
        
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/" + imageName));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;  //blueprint for tile collision
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    public void loadMap(String filePath){
        
        try{
            InputStream is = getClass().getResourceAsStream(filePath); // Get the path of the map file and open it as a stream of data. //InputStream is: This creates a "pipe" (stream) connected to that file. It doesn't read the text yet; it just opens the door so you can start pulling data out of it.
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  //format to read the textFile that is used to place the map
            
            int col = 0;
            int row = 0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                
                String line = br.readLine();    //.readLine(); is used to read a line of text
                
                while(col < gp.maxWorldCol){
                    
                    String numbers[] = line.split(" "); //Split the String into a space
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;  //this stores the extracted numbers intothe mapTileNum[][]
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close(); //closes the bufferReader because its not needed here
            
        }
        catch(Exception e){

        }
    }
    
    public void draw(Graphics2D g2){
        
        int worldCol = 0;
        int worldRow = 0;
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            int tileNum = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            //makes it so that it only prints tiles within the player screen boundary
            if( worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            
            worldCol++;
    
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
