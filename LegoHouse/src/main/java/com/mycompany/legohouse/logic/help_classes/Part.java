
package com.mycompany.legohouse.logic.help_classes;

import java.io.Serializable;

/**
 *
 * @author Simon Asholt Norup
 */
public class Part implements Serializable{
    
    private final Brick type;
    private final int side1;
    private final int side2;
    private final int side3;
    private final int side4;
    
    public Part(Brick type, int length_amount, int width_amount) {
        if (type == null || length_amount < 0 || width_amount < 0){
            throw new IllegalArgumentException("There was an issue concerning part object initialization. Please contact support.");
        }
        this.type = type;
        side1 = length_amount;
        side2 = width_amount;
        side3 = length_amount;
        side4 = width_amount;
    }

    public String getType() {
        return type.getType();
    }

    public int getSide1() {
        return side1;
    }

    public int getSide2() {
        return side2;
    }

    public int getSide3() {
        return side3;
    }

    public int getSide4() {
        return side4;
    }
    
    public int getTotal() {
        return side1 + side2 + side3 + side4;
    }
    
}
