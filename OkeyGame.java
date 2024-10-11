import java.util.Random;

public class OkeyGame {

    Player[] players;
    Tile[] tiles;

    Tile lastDiscardedTile;

    int currentPlayerIndex = 0;

    public OkeyGame() {
        players = new Player[4];
    }

    public void createTiles() {
        tiles = new Tile[112];
        int currentTile = 0;

        // two copies of each color-value combination, no jokers
        for (int i = 1; i <= 7; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[currentTile++] = new Tile(i,'Y');
                tiles[currentTile++] = new Tile(i,'B');
                tiles[currentTile++] = new Tile(i,'R');
                tiles[currentTile++] = new Tile(i,'G');
            }
        }
    }

    /*
     * TODO: distributes the starting tiles to the players
     * player at index 0 gets 15 tiles and starts first
     * other players get 14 tiles
     * this method assumes the tiles are already shuffled
     */
    public void distributeTilesToPlayers() {
        int count = 0;
        for(int i = 0; i < 14; i++){
            players[0].playerTiles[i] = tiles[count];
            tiles[count] = null;
            count++;

            players[1].playerTiles[i] = tiles[count];
            tiles[count] = null;
            count++;

            players[2].playerTiles[i] = tiles[count];
            tiles[count] = null;
            count++;

            players[3].playerTiles[i] = tiles[count];
            tiles[count] = null;
            count++;
        }
        players[0].playerTiles[14] = tiles[count];
        players[0].numberOfTiles = 15;
        players[1].numberOfTiles = 14;
        players[2].numberOfTiles = 14;
        players[3].numberOfTiles = 14;

        //Sorting player's hands
        players[0].sortHand();
        players[1].sortHand();
        players[2].sortHand();
        players[3].sortHand();
        slideTiles();
    }
    public void slideTiles(){

        for(int i = 57; i < 112; i++){
            tiles[i - 57] = tiles[i];
            tiles[i] = null;
        }
    }
    /*
     * TODO: get the last discarded tile for the current player
     * (this simulates picking up the tile discarded by the previous player)
     * it should return the toString method of the tile so that we can print what we picked
     */
    public String getLastDiscardedTile()
<<<<<<< HEAD
    {   
        int currentPlayerIndex;
        Player currentP;
        currentPlayerIndex = getCurrentPlayerIndex();
        currentP = this.players[currentPlayerIndex];
        currentP.addTile(lastDiscardedTile);
        return lastDiscardedTile.toString();
=======
    {
        return null;
>>>>>>> origin/main
    }

    /*
     * TODO: get the top tile from tiles array for the current player
     * that tile is no longer in the tiles array (this simulates picking up the top tile)
     * it should return the toString method of the tile so that we can print what we picked
     */
    public String getTopTile() 
    {
        return null;
    }

    /*
     * TODO: should randomly shuffle the tiles array before game starts
     */
    public void shuffleTiles() {
        Random rand = new Random();
        Tile temp;
        Tile randomTile;

        for(int i = 0; i < 112; i++){
            temp = tiles[i];
            randomTile = tiles[rand.nextInt(112)];
            tiles[i] = randomTile;
            randomTile = temp;
        }

    }

    /*
     * TODO: check if game still continues, should return true if current player
     * finished the game, use isWinningHand() method of Player to decide
     */
    public boolean didGameFinish() {
        return false;
    }

    /*
     * TODO: Pick a tile for the current computer player using one of the following:
     * - picking from the tiles array using getTopTile()
     * - picking from the lastDiscardedTile using getLastDiscardedTile()
     * You should consider if the discarded tile is useful for the computer in
     * the current status. Print whether computer picks from tiles or discarded ones.
     */
    public void pickTileForComputer()
    {
        // if chain value > 2 and not duplicate get else getTopTile
        int compIndex,
            chainValue;
        Player computer;
        Tile[] computerTiles;
        boolean isTDuplicate;
    
        compIndex = getCurrentPlayerIndex();
        computer = this.players[compIndex];
        computerTiles = computer.getTiles();
        isTDuplicate = isDuplicate(computerTiles, lastDiscardedTile, -1);

        chainValue = calculateChainValue(computerTiles, lastDiscardedTile, -1);
        if(chainValue > 2 && !isTDuplicate)
        {
          // pick from last discarded
        }
        else
        {
            //get Top tile
        }

   

    }

    /*
     * TODO: Current computer player will discard the least useful tile.
     * this method should print what tile is discarded since it should be
     * known by other players. You may first discard duplicates and then
     * the single tiles and tiles that contribute to the smallest chains.
     */
    public void discardTileForComputer()
    {
        int arrLen,
            compIndex,
            currentChain,
            leastChain,
            leastChainIndex;
        Player computer;
        int[] chains;
        Tile[] computerTiles;
        Tile currentTile;
        
        compIndex = getCurrentPlayerIndex();
        computer = this.players[compIndex];
        computerTiles = computer.getTiles();
        arrLen = computerTiles.length;
        chains = new int[arrLen];

        for(int i = 0; i<arrLen;i++)
        {   
            currentTile = computerTiles[i];
            
                //DUPLICATE
            if(isDuplicate(computerTiles, currentTile, i))
            {
                discardTile(i);
                displayDiscardInformation();
                return;
            }
            else
            {
                currentChain = calculateChainValue(computerTiles, currentTile, i);
                chains[i] = currentChain;
            }       
        }
        //compare the chains
        leastChain = chains[0];
        leastChainIndex = 0;
        for(int i = 0 ; i<arrLen; i++)
        {
            currentChain = chains[i];
            if(leastChain > currentChain)
            {
                leastChain = currentChain;
                leastChainIndex = i;
            }
        }
        //discard least chain index
        discardTile(leastChainIndex);
        displayDiscardInformation();    
        

    }

    // !!!
    public boolean isDuplicate(Tile[] tileList,Tile currentTile,int tileIndex)
    {
        int size;
        Tile nextTile;
        boolean isChain;
        size = tileList.length;

        isChain = true;
        for(int i = 0; i<size && isChain;i++)
        {
            nextTile = tileList[i];
            if(!currentTile.canFormChainWith(nextTile))
            {  
                if((currentTile.getColor() == nextTile.getColor()) || (currentTile.getValue() == nextTile.getValue()))
                {
                    if(i != tileIndex)
                    {
                        return true;
                    }
                }
                else
                {
                    isChain = false;
                } 
            }
        }
        return false;
    }


    // !!! -1 tile index if not needed
    public int calculateChainValue(Tile[] tileList,Tile currentTile,int tileIndex)
    {
        int size,
            chainCount;
        boolean isChain;
        Tile nextTile;

        isChain = true;
        chainCount = 1;
        size = tileList.length;

        for(int i = 0; i<size && isChain; i++)
        {
            nextTile = tileList[i];
            if(currentTile.canFormChainWith(nextTile))
            {
                chainCount += 1;
            }
            else
            {
                if(i != tileIndex)
                isChain = false;
            }
        }
        return chainCount;
    }

    /*
     * TODO: discards the current player's tile at given index
     * this should set lastDiscardedTile variable and remove that tile from
     * that player's tiles
     */
    public void discardTile(int tileIndex) {
        lastDiscardedTile = players[getCurrentPlayerIndex()].getAndRemoveTile(tileIndex);
        
    }

    public void displayDiscardInformation() {
        if(lastDiscardedTile != null) {
            System.out.println("Last Discarded: " + lastDiscardedTile.toString());
        }
    }

    public void displayCurrentPlayersTiles() {
        players[currentPlayerIndex].displayTiles();
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

      public String getCurrentPlayerName() {
        return players[currentPlayerIndex].getName();
    }

    public void passTurnToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 4;
    }

    public void setPlayerName(int index, String name) {
        if(index >= 0 && index <= 3) {
            players[index] = new Player(name);
        }
    }

}
