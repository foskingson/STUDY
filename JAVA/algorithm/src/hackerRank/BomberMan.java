package hackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BomberMan {
  public static List<String> bomberMan(int n, List<String> grid) {
        List<String> holeGrid = new ArrayList<String>();
        int row = grid.size();
        int col = grid.get(0).length();
        char [][] newGrid = new char[row][col];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                newGrid[i][j] = 'O';
            }
        }
        for(char[] line: newGrid){
            String newLine = new String(line);
            holeGrid.add(newLine);
        }
        
        List<String> grid1 = nextState(grid);
        List<String> grid2 = nextState(grid1);
        List<String> grid3 = nextState(grid2);
        
        if(n == 1){
            return grid;
        }
        if(n == 3){
            return grid1;
        }
        if(n%2 == 0){
            return holeGrid;
        }
        
        int flipTime = (n-1)/2;
        if(flipTime%2 == 1){
            return grid3;
        }
        
        return grid2;
        
    }
    
    public static List<String> nextState(List<String> grid){
        List<String> nextGrid = new ArrayList<String>();
        int row = grid.size();
        int col = grid.get(0).length();
        char [][] newGrid = new char[row][col];
        int[][] directions = {{0, 0},{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                newGrid[i][j] = 'O';
            }
        }
        
        for(int i=0; i<grid.size(); i++){
            for(int j=0; j<grid.get(0).length(); j++){
                if(grid.get(i).charAt(j) == 'O'){
                    for(int[] direction: directions){
                        int j_new = j + direction[0];
                        int i_new = i + direction[1];
                        if(j_new<col && j_new>=0 && i_new<row && i_new>=0){
                            newGrid[i_new][j_new] = '.';
                        }
                    }
                }
            }
        }
        for(char[] line: newGrid){
            String newLine = new String(line);
            nextGrid.add(newLine);
        }
        return nextGrid;
    }

    public static void main(String[] args) {
        bomberMan(5, Arrays.asList("...", ".O.", "..."));
    }
}
