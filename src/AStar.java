import java.util.ArrayList;
import java.util.Date;


public class AStar {
    ArrayList<CellInfo> open;
    ArrayList<CellInfo> closed;

    public AStar() {
        open = new ArrayList<>();
        closed = new ArrayList<>();
    }

    //will set the parents so using backtrack you can get to the starter cell
    public void findAStar(int n, int m) {
        //first we create the map and the obstacles

        CellInfo[][] map = new CellInfo[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = new CellInfo[m];
            for (int j = 0; j < m; j++) {
                map[i][j] = new CellInfo(j, i);
            }
        }

//        map[0][3].setTraversable(false);
//
//        map[1][1].setTraversable(false);
//        map[1][5].setTraversable(false);
//        map[1][7].setTraversable(false);
//
//        map[2][0].setTraversable(false);
//        map[2][2].setTraversable(false);
//        map[2][3].setTraversable(false);
//        map[2][4].setTraversable(false);
//        map[2][5].setTraversable(false);
//
//        map[3][2].setTraversable(false);
//        map[3][5].setTraversable(false);
//
//        map[4][2].setTraversable(false);
//        map[4][3].setTraversable(false);
//        map[4][4].setTraversable(false);
//        map[4][5].setTraversable(false);
//
//        map[5][2].setTraversable(false);
//        map[5][7].setTraversable(false);
//
//        map[6][4].setTraversable(false);
//        map[6][5].setTraversable(false);
//        map[6][6].setTraversable(false);
//        map[6][7].setTraversable(false);

        //now set the start point and choose an end point
        //and set its g and h
        map[0][0].setG(0);
        map[0][0].distanceH(m - 1, n - 1);
        open.add(map[0][0]);

        CellInfo endPoint = map[n - 1][m - 1];

        while (open.size()!=0) {
//            CellInfo current = leastF();
            CellInfo current = mostF();
//            System.out.println("\033[0;34m  y="+current.getY()+"  x="+current.getX()+"\033[0m");
            open.remove(current);
            closed.add(current);
            if (current.getX()==m-1 && current.getY()==n-1) {
                break;
            }
            //now add acceptable neighbors to the open list
            int xCurrent = current.getX();
            int yCurrent = current.getY();
            if (yCurrent > 0 && map[yCurrent - 1][xCurrent].isTraversable()) {
                //up
                if (!closed.contains(map[yCurrent - 1][xCurrent])) {
                    if (!open.contains(map[yCurrent - 1][xCurrent])) {
                        //first set its g and h
                        map[yCurrent - 1][xCurrent].distanceH(m - 1, n - 1);
                        map[yCurrent - 1][xCurrent].distanceG(current.getG());
                        map[yCurrent - 1][xCurrent].setParent(current);
                        open.add(map[yCurrent - 1][xCurrent]);
                    } else if (current.getF() > map[yCurrent - 1][xCurrent].getF()) {
                        //and if this node was already in the open list means that already had a g (not the h cause that ones just constant) settled so
                        // we make sure the ones we are setting are shorter
                        map[yCurrent - 1][xCurrent].distanceG(current.getG());
                        map[yCurrent - 1][xCurrent].setParent(current);
                    }
                }
            }
            if (yCurrent != n - 1 && map[yCurrent + 1][xCurrent].isTraversable()) {
                //down
                if (!closed.contains(map[yCurrent + 1][xCurrent])) {
                    if (!open.contains(map[yCurrent + 1][xCurrent])) {
                        //first set its g and h
                        map[yCurrent + 1][xCurrent].distanceH(m - 1, n - 1);
                        map[yCurrent + 1][xCurrent].distanceG(current.getG());
                        map[yCurrent + 1][xCurrent].setParent(current);
                        open.add(map[yCurrent + 1][xCurrent]);
                    } else if (current.getF() > map[yCurrent + 1][xCurrent].getF()) {
                        //and if this node was already in the open list means that already had a g (not the h cause that ones just constant) settled so
                        // we make sure the ones we are setting are shorter
                        map[yCurrent + 1][xCurrent].distanceG(current.getG());
                        map[yCurrent + 1][xCurrent].setParent(current);
                    }
                }
            }
            if (xCurrent != m - 1 && map[yCurrent][xCurrent + 1].isTraversable()) {
                //right
                if (!closed.contains(map[yCurrent][xCurrent + 1])) {
                    if (!open.contains(map[yCurrent][xCurrent + 1])) {
                        //first set its g and h
                        map[yCurrent][xCurrent + 1].distanceH(m - 1, n - 1);
                        map[yCurrent][xCurrent + 1].distanceG(current.getG());
                        map[yCurrent][xCurrent + 1].setParent(current);
                        open.add(map[yCurrent][xCurrent + 1]);
                    } else if (current.getF() > map[yCurrent][xCurrent + 1].getF()) {
                        //and if this node was already in the open list means that already had a g (not the h cause that ones just constant) settled so
                        // we make sure the ones we are setting are shorter
                        map[yCurrent][xCurrent + 1].distanceG(current.getG());
                        map[yCurrent][xCurrent + 1].setParent(current);
                    }
                }
            }
            if (xCurrent != 0 && map[yCurrent][xCurrent - 1].isTraversable()) {
                //left
                if (!closed.contains(map[yCurrent][xCurrent - 1])) {
                    if (!open.contains(map[yCurrent][xCurrent - 1])) {
                        //first set its g and h
                        map[yCurrent][xCurrent - 1].distanceH(m - 1, n - 1);
                        map[yCurrent][xCurrent - 1].distanceG(current.getG());
                        map[yCurrent][xCurrent - 1].setParent(current);
                        open.add(map[yCurrent][xCurrent - 1]);
                    } else if (current.getF() > map[yCurrent][xCurrent - 1].getF()) {
                        //and if this node was already in the open list means that already had a g (not the h cause that ones just constant) settled so
                        // we make sure the ones we are setting are shorter
                        map[yCurrent][xCurrent - 1].distanceG(current.getG());
                        map[yCurrent][xCurrent - 1].setParent(current);
                    }
                }
            }
        }
//
//        System.out.println("gonna print the way found:");
//        System.out.println("n= "+n+"  m="+m);
//        System.out.println("start:  y="+0+"  x="+0);
//        System.out.println("end :   y="+(n-1)+"  x="+(m-1));
//        System.out.println("\033[0;31m"+"rah:"+"\033[0m");
//        printBacktracking(map[n-1][m-1]);
    }

    private CellInfo leastF(){
        if(open.size()!=0){
            CellInfo leastF = open.get(0);
            for(int i=1; i<open.size(); i++){
                if(leastF.getF()>open.get(i).getF()){
                    leastF=open.get(i);
                }else if(leastF.getF()==open.get(i).getF() &&  leastF.getH()>open.get(i).getH()){
                    leastF=open.get(i);
                }
            }
            return leastF;
        }
        return null;
    }

    private CellInfo mostF(){
        if(open.size()!=0){
            CellInfo mostF = open.get(0);
            for(int i=1; i<open.size(); i++){
                if(mostF.getF()<open.get(i).getF()){
                    mostF=open.get(i);
                }else if(mostF.getF()==open.get(i).getF() &&  mostF.getH()<open.get(i).getH()){
                    mostF=open.get(i);
                }
            }
            return mostF;
        }
        return null;
    }
//
//    private void printBacktracking(CellInfo end){
//        System.out.println("y= "+end.getY()+"  x= "+end.getX());
//        if(end.getParent()!=null) {
//            printBacktracking(end.getParent());
//        }
//    }

}

class Handler{

    public static void main(String[] args) {
        AStar aStar = new AStar();
        Date newTime = new Date();
        aStar.findAStar(8, 8);
        Date afterTime = new Date();
        System.out.println(""+(afterTime.getTime()-newTime.getTime()));
    }

}


class CellInfo {
    private int g = -1;//not able to get there so the distance is -1
    private int h = -1;//not able to get there so the distance is -1
    //    g = the movement cost to move from the starting point to a given square on the grid, following the path generated to get there.
    //    h = the estimated movement cost to move from that given square on the grid to the final destination.

    private final int x;
    private final int y;

    private CellInfo parent;

    private boolean isTraversable = true;

    public CellInfo(int thisX, int thisY) {
        x = thisX;
        y = thisY;
    }

    public CellInfo(int thisX, int thisY, boolean isTraversable) {
        x = thisX;
        y = thisY;
        this.isTraversable = isTraversable;
    }


    public void distanceG(int gBefore) {
        this.g = gBefore+1;
    }

    public void distanceH(int xFinal, int yFinal) {
        this.h = ( (xFinal-x)*(xFinal-x) ) + ( (yFinal-y)*(yFinal-y) );
    }

    /**
     * this method is used to set the g of the start point as 0
     * @param g
     */
    public void setG(int g) {
        this.g = g;
    }

    public void setTraversable(boolean traversable) {
        isTraversable = traversable;
    }

    public void setParent(CellInfo parent) {
        this.parent = parent;
    }

    public int getF(){
        if(h==-1 || g==-1){
            System.out.println("\033[0;31m"+"eshteb darE mizani"+"\033[0m");
//            return -999;
        }
        return h+g;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CellInfo getParent() {
        return parent;
    }

    public boolean isTraversable() {
        return isTraversable;
    }
}