package postman;

import java.awt.*;

public enum City {
    NY("ny", BorderLayout.WEST),
    London("london", BorderLayout.NORTH),
    Rome("rome", BorderLayout.EAST),
    Paris("paris", BorderLayout.SOUTH)
    ;


    private String name;
    private String borderLayout;

    City(String name, String borderLayout) {
        this.name = name;
        this.borderLayout = borderLayout;

    }

    public String getName(){
        return this.name;
    }

    public String getBorderLayout(){
        return this.borderLayout;
    }

 }
