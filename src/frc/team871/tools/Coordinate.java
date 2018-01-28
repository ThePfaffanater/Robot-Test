package frc.team871.tools;

/**
 * @author TP-Laptop on 1/29/2018.
 * @project Robo 2018 ALPHA TEST
 */
public class Coordinate<E extends Number> {

    private E gridX;
    private E gridY;
    private E gridZ;

    public Coordinate( E gridX,  E gridY,  E gridZ){
        this.gridX = gridX;
        this.gridY = gridY;
        this.gridZ = gridZ;

    }

    public Coordinate( E gridX,  E gridY){
        this(gridX,gridY, (E)new Integer(0));

    }

    public E getX() {
        return gridX;
    }

    public E getY() {
        return gridY;
    }

    public E getZ() {
        return gridZ;
    }

    public void setX(E gridX){
        this.gridX = gridX;
    }

    public void setY(E gridY){
        this.gridY = gridY;
    }

    public void setZ(E gridZ){
        this.gridZ = gridZ;
    }

}
