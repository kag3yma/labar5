package data;

public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String parentLegion;
    private int marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
    private String world; //Поле не может быть null

    public Chapter(String name, String parentLegion, int marinesCount, String world){
        this.name = name;
        this.parentLegion = parentLegion;
        this.marinesCount = marinesCount;
        this.world = world;
    }
    public Chapter(){}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getParentLegion(){return parentLegion;}
    public void setParentLegion(String parentLegion){this.parentLegion = parentLegion;}
    public long getMarinesCount(){return marinesCount;}
    public void setMarinesCount(int marinesCount){this.marinesCount = marinesCount;}
    public String getWorld(){return world;}
    public void setWorld(String world){this.world = world;}
    @Override
    public String toString() {
        String infoChap = "";
        infoChap += "\n Орден: " + name + " (" + marinesCount + " солдат)";
        infoChap += "\n        Появившийся из " + parentLegion;
        infoChap += "\n        В мире: " + world;
        infoChap += "\n";
        return infoChap;
    }
}
