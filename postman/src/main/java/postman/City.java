package postman;

import java.awt.*;

public enum City {
    London("london", BorderLayout.NORTH, new Point(500, 0)),
    Paris("paris", BorderLayout.SOUTH, new Point(200,200)),
    Rome("rome", BorderLayout.EAST, new Point(0, 200)),
    NY("ny", BorderLayout.WEST, new Point(500, 200));

    private  Point point;
    private String name;

    private String borderLayout;

    City(String name, String borderLayout, Point point) {
        this.name = name;
        this.borderLayout = borderLayout;
        this.point = point;
    }

    String getName(){
        return this.name;
    }

    String getBorderLayout(){
        return this.borderLayout;
    }

    Point getPoint(){
        return this.point;
    }
}
