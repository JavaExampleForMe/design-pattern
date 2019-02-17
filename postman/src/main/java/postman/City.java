package postman;

import java.awt.*;

public enum City {
    NY("ny", BorderLayout.WEST, new Point(500, 200)),
    London("london", BorderLayout.NORTH, new Point(500, 0)),
    Rome("rome", BorderLayout.EAST, new Point(0, 200)),
    Paris("paris", BorderLayout.SOUTH, new Point(200,200))
    ;

    private  Point point;
    private String name;
    private String borderLayout;

    City(String name, String borderLayout, Point point) {
        this.name = name;
        this.borderLayout = borderLayout;
        this.point = point;
    }

    public String getName(){
        return this.name;
    }

    public String getBorderLayout(){
        return this.borderLayout;
    }

 }
