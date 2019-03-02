package postman.ui;

import java.awt.*;

public enum City {
    NY("ny", BorderLayout.WEST),
    Rome("rome", BorderLayout.EAST),
    Paris("paris", BorderLayout.SOUTH),
    London("london", BorderLayout.NORTH)
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
