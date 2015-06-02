package arrayPractice;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Created by Jaco on 6/1/15. */

public class JigglingShapes extends GraphicsProgram {
    List<GObject> shapes;
    Random rand = new Random();
    private static final int JIGGLE_AMOUNT = 60, JIGGLE_BOX_SIZE = 60;

    @Override
    public void run() {
        int numberOfShapes = readInt("Number of shapes: ");
        shapes = new ArrayList<>(numberOfShapes);
        initializeShapes(numberOfShapes);
        jiggleShapes(10);
    }

    private void initializeShapes(int n) {
        for (int i = 0; i < n; i++) {
            switch (rand.nextInt(4)) {
                case 0:
                    shapes.add(new GOval(50,50));
                    break;
                case 1:
                    shapes.add(new GRect(50,50));
                    break;
                case 2:
                    shapes.add(new GOval(25,75));
                    break;
                case 3:
                    shapes.add(new GRect(25,75));
                    break;
            }
            GObject current = shapes.get(i);
            double wRange = getWidth() - current.getWidth() - JIGGLE_BOX_SIZE;
            double hRange = getHeight() - current.getHeight() - JIGGLE_BOX_SIZE;
            current.setLocation(rand.nextDouble() * wRange, rand.nextDouble() * hRange);
            add(current);
        }
    }

    private void jiggleShapes(int time) {
        double[][] originalPositions = new double[shapes.size()][2];
        for (GObject shape : shapes) {
            originalPositions[shapes.indexOf(shape)][0] = shape.getX();
            originalPositions[shapes.indexOf(shape)][1] = shape.getY();
        }
        while (true) {
            double dx = 5, dy = 5;
            for (GObject shape : shapes) {
                do {
                    dx = rand.nextDouble() * JIGGLE_AMOUNT;
                    dy = rand.nextDouble() * JIGGLE_AMOUNT;
                } while (shape.getX() + dx > originalPositions[shapes.indexOf(shape)][0] + JIGGLE_BOX_SIZE / 2
                    || shape.getY() + dy > originalPositions[shapes.indexOf(shape)][1] + JIGGLE_BOX_SIZE / 2);
                shape.setLocation(shape.getX() + dx, shape.getY() + dy);
            }
            pause(time);
            for (GObject shape : shapes) {
                shape.setLocation(shape.getX() - dx, shape.getY() - dy);
            }
            pause(time);
        }
    }
}
