package data;

public class Coordinates {
    private Float x;
    private float y; //Поле не может быть null

    public Coordinates(Float x, float y){
        this.x = x;
        this.y = y;
    }
    public Coordinates(){}

    public Float getX(){return x;}
    public void setX(Float x){this.x = x;}
    public float getY(){return y;}
    public void setY(float y){this.y = y;}
}
