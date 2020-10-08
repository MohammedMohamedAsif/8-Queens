import java.util.Random;

public class Instance {

        private int heuristic = 0;
        private int neighbor= 0;
        private int[][] board = new int[8][8];
        private int[] pos= new int[8];
        private int[][] queenHPos = new int [2][8];
        private int[] movingQueen = new int [3];
        private boolean solutionFound = false;
        private boolean reset= true;


        public void getHeuristicsAndPos(){
            for(int i=0 ; i < 8; i++){
                for(int j =0 ; j < 2; j++){
                }
            }
            System.out.println("");
        }
        //All the Getter and Setters
        public int getHeuristic(){
            return this.heuristic;
        }

        public boolean getSolutionFound(){
            return this.solutionFound;
        }

        public boolean getReset(){
            return this.reset;
        }

        public int getNeighbor(){
            return this.neighbor;
        }

        public void setReset(boolean reset) {
            this.reset = reset;
        }
        //Method to clear the board
        public void clearBoard(){
            for (int i = 0; i < 8; i++){
                for(int j= 0; j < 8; j++){
                    this.board[i][j] = 0;
                }
            }
        }

        // Print ths whole board
        public void printBoard(){
            for(int i = 0; i<this.board.length; i++){
                for( int j = 0; j<this.board.length; j++){
                    System.out.print(this.board[i][j]);
                }
                System.out.print('\n');
            }
        }

        // Randomize the board
        public void randomize(){
            Random ran = new Random();
            for (int j = 0; j < 8; j++) {
                int rand_int1 = ran.nextInt(8);
                this.board[rand_int1][j] = 1;
                this.pos[j] = rand_int1;
            }
        }

        // Find the total heuristic
        public void findTotalH(){
            for(int i = 0; i < 8; i++){
                for(int j = i+1; j < 8; j++) {
                    if (pos[i] == pos[j]) {
                        this.heuristic += 1;
                    }else if(diagonalCheck(pos[i], i, pos[j], j)){
                        this.heuristic +=1;
                    }
                }
            }
        }

    //Formula to see if there are any diagonal conflicts
        public boolean diagonalCheck(int x1,int y1, int x2, int y2){
            if(y2 > y1 || y2 < y1){
                int y = Math.abs(x1 - x2);
                int x = Math.abs(y1 - y2);
                if (y == x){
                    return true;
                }
            }
            return false;
        }

        //Check queen heuristic at a particular position
        private int findQueenH(int y, int x){
            int currentH = 0;
            for(int i = 0; i < 8; i++){
                if(x != i){
                    if (y == pos[i]){
                        currentH += 1;
                    }else if(diagonalCheck(x,y,i,pos[i])){
                        currentH += 1;
                    }
                }
            }
            return currentH;
        }

        //Find queens Heuristic at all of positions
        private void findAllQueenH(int x){

            int lowestQueenHeuruistic = findQueenH(pos[x],x);

            int lowestQueenPos = pos[x];
            for(int i = 0; i < 8; i++){
                if (!(pos[x] == i)){

                    if( findQueenH(i,x) < lowestQueenHeuruistic){
                        lowestQueenHeuruistic = findQueenH(i,x);
                        lowestQueenPos = i;
                    }
                }
            }
            this.queenHPos[0][x] = lowestQueenHeuruistic - findQueenH(pos[x],x);
            this.queenHPos[1][x] = lowestQueenPos;
        }



        //Find all neighboring states
        public void findNeighborStates(){
            int lowestH = 0;
            for (int i=0; i< 8; i++){
                if(this.queenHPos[0][i] < lowestH){
                    lowestH = this.queenHPos[0][i];
                    this.movingQueen[0] = this.queenHPos[1][i];
                    this.movingQueen[1] = this.pos[i];
                    this.movingQueen[2] = i;
                }
            }
            for(int i =0; i<8; i++){
                if(this.queenHPos[0][i] == lowestH && lowestH != 0){
                    this.neighbor ++;
                }
            }
            if(this.heuristic == 0 && this.neighbor ==0){
                this.solutionFound = true;

            }else if(this.heuristic != 0 && this.neighbor == 0){
                this.reset = true;
            }
        }



        // Search every queens Heuristic
        public void runAllQueenSearch(){
            for(int i = 0; i < 8; i++) {
                findAllQueenH(i);
            }
        }

        //Move the queen to lower the conflicts
        public void moveQueen(){
            int y = this.movingQueen[0];
            int oldRow = this.movingQueen[1];
            int x = this.movingQueen[2];
            this.pos[x] = y;
            this.board[oldRow][x] = 0;
            this.board[y][x] = 1;
            this.neighbor=0;
            this.heuristic=0;
        }


}
