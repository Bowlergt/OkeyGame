public class Player {
    String playerName;
    Tile[] playerTiles;
    int numberOfTiles;

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String Reset = "\u001B[0m";

    public Player(String name) {
        setName(name);
        playerTiles = new Tile[15]; // there are at most 15 tiles a player owns at any time
        numberOfTiles = 0; // currently this player owns 0 tiles, will pick tiles at the beggining of the game
    }

    /*
     * TODO: removes and returns the tile in given index
     */
    public Tile getAndRemoveTile(int index) {
        Tile tile = playerTiles[index];
        playerTiles[index] = null;
        for(int i = index + 1; i < playerTiles.length; i++){
            Tile temp = playerTiles[i];
            playerTiles[i - 1] = temp;
            playerTiles[i] = null;
        }
        return tile;
    }
    public void sortHand(){

        Tile temp;
        for (int i = 0; i < playerTiles.length - 1; i++) {
            
            for (int j = 0; j < playerTiles.length - i - 1; j++) {
                if(playerTiles[j] != null && playerTiles[j + 1] != null){
                    if (playerTiles[j].getValue() > playerTiles[j + 1].getValue()) {
                    
                        temp = playerTiles[j];
                        playerTiles[j] = playerTiles[j + 1];
                        playerTiles[j + 1] = temp;                
                    }
                }
                
            }         
        }
    }

    /*
     * TODO: adds the given tile to the playerTiles in order
     * should also update numberOfTiles accordingly.
     * make sure playerTiles are not more than 15 at any time
     */

    public void addTile(Tile t) {

       int TilesValue = t.getValue();
       int count = 0;
       boolean check = true;
       for(int i = 0; i < playerTiles.length && check ; i++){
            if(playerTiles[i] != null){

                if(playerTiles[i].getValue() <= TilesValue){
                count++;
                }
                else{
                check = false;
                }
           }
        }
        
        for(int i = playerTiles.length; i  >  count; i--){
            playerTiles[i] = playerTiles[i - 1];
        }
        playerTiles[count] = t;
    }

    /*
     * TODO: checks if this player's hand satisfies the winning condition
     * to win this player should have 3 chains of length 4, extra tiles
     * does not disturb the winning condition
     * @return
     */
    public boolean isWinningHand() 
    {
        return false;
    }



    public int findPositionOfTile(Tile t) {
        int tilePosition = -1;
        for (int i = 0; i < numberOfTiles; i++) {
            if(playerTiles[i].compareTo(t) == 0) {
                tilePosition = i;
            }
        }
        return tilePosition;
    }

    public void displayTiles() {

        System.out.println(playerName + "'s Tiles:");
        for (int i = 0; i < numberOfTiles; i++) {
            if(playerTiles[i] != null){

                if(playerTiles[i].colorNameToInt() == 2){
                    System.out.print(RED + playerTiles[i].toString() + " " + Reset);
                }
                else if(playerTiles[i].colorNameToInt() == 1){
                    System.out.print(BLUE +  playerTiles[i].toString() + " " + Reset);
                }
                else if(playerTiles[i].colorNameToInt() == 0){
                    System.out.print(YELLOW + playerTiles[i].toString() + " " + Reset);
                }
                else if(playerTiles[i].colorNameToInt() == 3){
                    System.out.print(GREEN + playerTiles[i].toString() + " " +  Reset);
                    
                }

            }
            
        }
        System.out.println();
    }

    public Tile[] getTiles() {
        return playerTiles;
    }

    public void setName(String name) {
        playerName = name;
    }

    public String getName() {
        return playerName;
    }

    
}
