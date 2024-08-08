package solver;

import java.util.*;

public class Bot {
    public static char[][] copyMap(char[][] original) {
        int rows = original.length;
        int cols = original[0].length;
        char[][] copy = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, cols);
        }

        return copy;
    }

    public boolean isSolved(State state){
        for (char[] mapRow : state.getFullMapData()) {
            for (char c : mapRow) {
                if (c == '$') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isLegal(int playerX, int playerY, char[][] fullMap, char move) {
        int newX = playerX;
        int newY = playerY;

        switch (move) {
            case 'u' -> newY--;
            case 'd' -> newY++;
            case 'l' -> newX--;
            case 'r' -> newX++;
        }

        if (fullMap[newY][newX] == '#'){
            return false;
        }

        // Check if player is pushing a box into a wall or into another box
        if (fullMap[newY][newX] == '$' || fullMap[newY][newX] == '*'){
            switch (move){
                case 'u':
                    if (fullMap[newY-1][newX] == '#' || fullMap[newY - 1][newX] == '$' || fullMap[newY - 1][newX] == '*'){return false;}
                    break;
                case 'd':
                    if (fullMap[newY+1][newX] == '#' || fullMap[newY + 1][newX] == '$' || fullMap[newY + 1][newX] == '*'){return false;}
                    break;
                case 'l':
                    if (fullMap[newY][newX - 1] == '#' || fullMap[newY][newX - 1] == '$' || fullMap[newY][newX - 1] == '*'){return false;}
                    break;
                case 'r':
                    if (fullMap[newY][newX + 1] == '#' || fullMap[newY][newX + 1] == '$' || fullMap[newY][newX + 1] == '*'){return false;}
                    break;
            }
        }

        return true;
    }

    public boolean isDeadlocked(char[][] fullMap){
        for (int y = 0; y < fullMap.length; y++){
            for(int x = 1; x < fullMap[y].length; x++){
                if (fullMap[y][x] == '$') {
                    if(fullMap[y-1][x] == '#' && fullMap[y][x-1] == '#') {
                        return true;
                    }
                    if(fullMap[y-1][x] == '#' && fullMap[y][x+1] == '#') {
                        return true;
                    }
                    if(fullMap[y+1][x] == '#' && fullMap[y][x-1] == '#') {
                        return true;
                    }
                    if(fullMap[y+1][x] == '#' && fullMap[y][x+1] == '#') {
                        return true;
                    }
                    if(fullMap[y+1][x] == '$' && fullMap[y][x-1] == '#' && fullMap[y+1][x-1] == '#') {
                        return true;
                    }
                    if(fullMap[y+1][x] == '$' && fullMap[y][x+1] == '#' && fullMap[y+1][x+1] == '#') {
                        return true;
                    }
                    if(fullMap[y][x+1] == '$' && fullMap[y-1][x] == '#' && fullMap[y-1][x+1] == '#') {
                        return true;
                    }
                    if(fullMap[y][x+1] == '$' && fullMap[y+1][x] == '#' && fullMap[y+1][x+1] == '#') {
                        return true;
                    }
                    if(fullMap[y+1][x] == '$' && fullMap[y][x+1] == '$' && fullMap[y-1][x+1] == '$') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public char[][] updateFullMap(int oldPlayerX, int oldPlayerY, char[][] fullMapData, char move){
        int newY = oldPlayerY;
        int newX = oldPlayerX;
        char currentPlayerNotation = fullMapData[oldPlayerY][oldPlayerX];
        char destination;

        switch (move) {
            case 'u' -> newY--;
            case 'd' -> newY++;
            case 'l' -> newX--;
            case 'r' -> newX++;
        }

        destination = fullMapData[newY][newX];

        if (destination == '$'){
            switch (move){
                case 'u' -> {
                    if (currentPlayerNotation == '@' && fullMapData[newY - 1][newX] == ' '){
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY - 1][newX] = '$';
                    }
                    else if (currentPlayerNotation == '@' && fullMapData[newY - 1][newX] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY - 1][newX] = '*';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY - 1][newX] == ' ') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY - 1][newX] = '$';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY - 1][newX] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY - 1][newX] = '*';
                    }
                }
                case 'd' -> {
                    if (currentPlayerNotation == '@' && fullMapData[newY + 1][newX] == ' '){
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY + 1][newX] = '$';
                    }
                    else if (currentPlayerNotation == '@' && fullMapData[newY + 1][newX] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY + 1][newX] = '*';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY + 1][newX] == ' ') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY + 1][newX] = '$';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY + 1][newX] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY + 1][newX] = '*';
                    }
                }
                case 'l' -> {
                    if (currentPlayerNotation == '@' && fullMapData[newY][newX - 1] == ' '){
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY][newX - 1] = '$';
                    }
                    else if (currentPlayerNotation == '@' && fullMapData[newY][newX - 1] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY][newX - 1] = '*';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY][newX - 1] == ' ') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY][newX - 1] = '$';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY][newX - 1] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY][newX - 1] = '*';
                    }
                }
                case 'r' -> {
                    if (currentPlayerNotation == '@' && fullMapData[newY][newX + 1] == ' '){
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY][newX + 1] = '$';
                    }
                    else if (currentPlayerNotation == '@' && fullMapData[newY][newX + 1] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY][newX + 1] = '*';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY][newX + 1] == ' ') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY][newX + 1] = '$';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY][newX + 1] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '@';
                        fullMapData[newY][newX + 1] = '*';
                    }
                }
            }
        }

        else if (destination == '*') {
            switch (move){
                case 'u' -> {
                    if (currentPlayerNotation == '@' && fullMapData[newY - 1][newX] == ' '){
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY - 1][newX] = '$';
                    }
                    else if (currentPlayerNotation == '@' && fullMapData[newY - 1][newX] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY - 1][newX] = '*';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY - 1][newX] == ' ') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY - 1][newX] = '$';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY - 1][newX] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY - 1][newX] = '*';
                    }
                }
                case 'd' -> {
                    if (currentPlayerNotation == '@' && fullMapData[newY + 1][newX] == ' '){
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY + 1][newX] = '$';
                    }
                    else if (currentPlayerNotation == '@' && fullMapData[newY + 1][newX] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY + 1][newX] = '*';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY + 1][newX] == ' ') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY + 1][newX] = '$';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY + 1][newX] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY + 1][newX] = '*';
                    }
                }
                case 'l' -> {
                    if (currentPlayerNotation == '@' && fullMapData[newY][newX - 1] == ' '){
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY][newX - 1] = '$';
                    }
                    else if (currentPlayerNotation == '@' && fullMapData[newY][newX - 1] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY][newX - 1] = '*';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY][newX - 1] == ' ') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY][newX - 1] = '$';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY][newX - 1] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY][newX - 1] = '*';
                    }
                }
                case 'r' -> {
                    if (currentPlayerNotation == '@' && fullMapData[newY][newX + 1] == ' '){
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY][newX + 1] = '$';
                    }
                    else if (currentPlayerNotation == '@' && fullMapData[newY][newX + 1] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = ' ';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY][newX + 1] = '*';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY][newX + 1] == ' ') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY][newX + 1] = '$';
                    }
                    else if (currentPlayerNotation == '+' && fullMapData[newY][newX + 1] == '.') {
                        fullMapData[oldPlayerY][oldPlayerX] = '.';
                        fullMapData[newY][newX] = '+';
                        fullMapData[newY][newX + 1] = '*';
                    }
                }
            }
        }

        else if (destination == '.') {
            switch (currentPlayerNotation) {
                case '@' -> {
                    fullMapData[oldPlayerY][oldPlayerX] = ' ';
                    fullMapData[newY][newX] = '+';
                }
                case '+' -> {
                    fullMapData[oldPlayerY][oldPlayerX] = '.';
                    fullMapData[newY][newX] = '+';
                }
            }
        }

        else if (destination == ' '){
            switch (currentPlayerNotation) {
                case '@' -> {
                    fullMapData[oldPlayerY][oldPlayerX] = ' ';
                    fullMapData[newY][newX] = '@';
                }
                case '+' -> {
                    fullMapData[oldPlayerY][oldPlayerX] = '.';
                    fullMapData[newY][newX] = '@';
                }
            }
        }


        return fullMapData;
    }

    public int manhattan(int cost, char[][] itemsData){
        int m_distance;
        int[] targetPos = new int[2];
        int[] cratePos = new int[2];

        for(int x = 0; x < itemsData.length; x++) {
            for (int y = 0; y < itemsData[0].length; y++) {
                if (itemsData[x][y] == '.') {
                    targetPos[0] = x;
                    targetPos[1] = y;
                    break;
                }

            }
        }
        for(int x = 0; x < itemsData.length; x++) {
            for (int y = 0; y < itemsData[0].length; y++) {
                if (itemsData[x][y] == '$') {
                    cratePos[0] = x;
                    cratePos[1] = y;
                    break;
                }
            }
        }

        m_distance = Math.abs(targetPos[0] - cratePos[0]) + Math.abs(targetPos[1] - cratePos[1]);

        return cost + m_distance;
    }

    public static String mapToString(char[][] map) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : map) {
            sb.append(row);
        }
        return sb.toString();
    }

    public String solveSokobanPuzzle(int width, int height, char[][] mapData, char[][] itemsData) {
        final char[] moves = {'u', 'l', 'd', 'r'};
        LinkedList<State> frontierStates = new LinkedList<>();
        HashSet<String> visitedStates = new HashSet<>();
        char[][] fullMapData = new char[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (mapData[y][x] == '#') {
                    fullMapData[y][x] = '#';
                }
                else if (mapData[y][x] == '.' && itemsData[y][x] == '@') {
                    fullMapData[y][x] = '+';
                }
                else if (mapData[y][x] == '.' && itemsData[y][x] == '$') {
                    fullMapData[y][x] = '*';
                }
                else if (mapData[y][x] == '.' && itemsData[y][x] == ' ') {
                    fullMapData[y][x] = '.';
                }
                else {
                    fullMapData[y][x] = itemsData[y][x];
                }
            }
        }

        int initialPlayerX = 0;
        int initialPlayerY = 0;
        State parentState;


        for (int y = 0; y < fullMapData.length; y++) {
            for (int x = 1; x < fullMapData[y].length; x++) {
                if (itemsData[y][x] == '@' || itemsData[y][x] == '+') {
                    initialPlayerY = y;
                    initialPlayerX = x;
                }
            }
        }

        State initialState = new State(initialPlayerX, initialPlayerY, "", fullMapData, manhattan(0, fullMapData));
        frontierStates.addFirst(initialState);

        while (!frontierStates.isEmpty()){
            parentState = frontierStates.pollFirst();
            for (char move : moves){
                assert parentState != null;
                char[][] updatedFullMapData;
                if (!isLegal(parentState.getPlayerX(), parentState.getPlayerY(), parentState.getFullMapData(), move)){continue;}
                updatedFullMapData = updateFullMap(parentState.getPlayerX(), parentState.getPlayerY(), copyMap(parentState.getFullMapData()), move);

                if (isDeadlocked(updatedFullMapData)){continue;}

                String mapId = mapToString(updatedFullMapData);
                if (visitedStates.contains(mapId)){continue;}
                visitedStates.add(mapId);

                int computedAStarScore = manhattan(parentState.getMoveSequence().length() + 1, updatedFullMapData);
                State newState = new State(parentState.getPlayerX(), parentState.getPlayerY(), parentState.getMoveSequence(), updatedFullMapData, computedAStarScore, move);

                int insertIndex = 0;
                while (!frontierStates.isEmpty() && frontierStates.get(0).getAStarScore() < computedAStarScore && insertIndex < frontierStates.size()){
                    insertIndex++;
                }
                if (insertIndex < frontierStates.size()){
                    frontierStates.add(insertIndex, newState);
                } else{
                    frontierStates.addLast(newState);
                }
                if (isSolved(newState)){
                    return newState.getMoveSequence();
                }
                frontierStates.addLast(newState);
            }

        }


        try {
            Thread.sleep(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "r";
    }


}
