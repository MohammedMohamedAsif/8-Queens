public class Main {

        public static void main(String[] args) {

            Instance instance = new Instance();
            int statChange = 0;
            int restartChange = 0;

           // Looping until we find a solution
            while(!instance.getSolutionFound()){
                if(instance.getReset()){
                    instance.clearBoard();
                    instance.randomize();
                    instance.setReset(false);

                }
                instance.findTotalH();
                System.out.println("The current H is: " + instance.getHeuristic());
                System.out.println("Current State:");
                instance.printBoard();
                instance.runAllQueenSearch();
                instance.findNeighborStates();
                System.out.println("Neighbors Found with Lower Heuristic: " + instance.getNeighbor());
                if(instance.getSolutionFound()){
                    System.out.println("Solution Found");
                    System.out.println("State Changes: " + statChange);
                    System.out.println("Restarts: " + restartChange);
                    break;
                }else if(instance.getReset()){
                    System.out.println("Restart");
                    instance.setReset(true);
                    restartChange++;
                }
                instance.moveQueen();
                instance.getHeuristicsAndPos();
                System.out.println("");
                statChange++;
            }


        }

}
