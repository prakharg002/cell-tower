class CellTower {
    int x;
    int y;
    int cost;

    public CellTower(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    public double distance(int x, int y) {
        // TO be completed by students
        // Make use of this in chooseCellTower in PointQuadtree.java
        int s=this.x;
        int t =this.y;
        double a = Math.sqrt((s-x)*(s-x)+(t-y)*(t-y));

        return a;
    }
}

public class PointQuadtreeNode {

    public CellTower celltower;
    public PointQuadtreeNode[] quadrants;

    public PointQuadtreeNode(CellTower a) {
        this.celltower = a;
        this.quadrants = new PointQuadtreeNode[4];
    }

}
