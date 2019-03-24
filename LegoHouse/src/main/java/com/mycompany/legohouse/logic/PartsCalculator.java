/*
 */
package com.mycompany.legohouse.logic;

import com.mycompany.legohouse.data.DataAccessException;
import com.mycompany.legohouse.data.OrderDAO;
import com.mycompany.legohouse.data.SQL_Impl.OrderDAO_SQL;
import com.mycompany.legohouse.logic.help_classes.Brick;
import com.mycompany.legohouse.logic.help_classes.House;
import com.mycompany.legohouse.logic.help_classes.Part;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Simon Asholt Norup
 */
public class PartsCalculator {

    private final ArrayList<Brick> brick_types;

    public PartsCalculator() throws DataAccessException {
        OrderDAO dao = new OrderDAO_SQL();
        brick_types = dao.getValidBrickTypes();
    }
    
    public LinkedList<Part> getParts(House house) throws DataAccessException {
        return getParts(house, "normal");
    }

    public LinkedList<Part> getParts(House house, String method) throws DataAccessException {
        LinkedList<Part> parts = new LinkedList<>();

        int current_length, current_width;
        if (method.equals("normal")){
            current_length = 0;
            current_width = 4;
        } else { // "switch"
            current_length = 4;
            current_width = 0;
        }

        HashMap<Brick, Integer> length_bricks = new HashMap<>();
        addBricksToSide(current_length, house.getLength(), length_bricks, brick_types);

        HashMap<Brick, Integer> width_bricks = new HashMap<>();
        addBricksToSide(current_width, house.getWidth(), width_bricks, brick_types);

        for (Brick type : brick_types) {
            Integer length_amount = length_bricks.get(type);
            Integer width_amount = width_bricks.get(type);
            if (length_amount == null) length_amount = 0;
            if (width_amount == null) width_amount = 0;
            parts.add(new Part(type, length_amount, width_amount));
        }

        return parts;
    }

    private void addBricksToSide(int current, int goal, HashMap<Brick, Integer> bricks, ArrayList<Brick> brick_types) {
        while (current < goal) {
            Brick brick = getValidBrick(current, goal, brick_types);
            Integer amount = bricks.get(brick);
            if (amount == null){
                amount = 0;
            }
            bricks.put(brick, amount + 1);
            current += brick.getLength();
        }
        if (current != goal) throw new UnsupportedOperationException("There was a serious issue in the calculation process. Contact support.");
    }

    private Brick getValidBrick(int current, int goal, ArrayList<Brick> brick_types) {
        Brick result = new Brick(0, 0);
        int remaining = goal - current;

        for (Brick type : brick_types) {
            int type_length = type.getLength();
            if (type_length > result.getLength() && type_length <= remaining) {
                
                result = type;
                
            }
        }
        return result;
    }

}
