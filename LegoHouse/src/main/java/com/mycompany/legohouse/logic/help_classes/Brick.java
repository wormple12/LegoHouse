/*
 */

package com.mycompany.legohouse.logic.help_classes;

/**
 * @author Simon Asholt Norup
 */
public class Brick {

    private final int width;
    private final int length;
    
    public Brick(int width, int length){
        this.width = width;
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
    
    public String getType() {
        return ""+getWidth()+"x"+getLength();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.width;
        hash = 37 * hash + this.length;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Brick other = (Brick) obj;
        if (this.width != other.width) {
            return false;
        }
        if (this.length != other.length) {
            return false;
        }
        return true;
    }
    

}
