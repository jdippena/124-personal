/**
 * Created by Jaco on 5/26/15.
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class OlympicRings extends GraphicsProgram {
    int mWidth;
    int mHeight;

    @Override
    public void run() {
        setTitle("Olympic Rings");
        mWidth = getWidth();
        mHeight = getHeight();

        add(new Ring(Color.BLUE, 0));
        add(new Ring(Color.YELLOW, 1));
        add(new Ring(Color.BLACK, 2));
        add(new Ring(Color.GREEN, 3));
        add(new Ring(Color.RED, 4));
    }

    private class Ring extends GOval {
        Ring(Color color, int pos) {
            super(100, 100);
            setColor(color);
            int r = Math.round((float) getHeight() / 2);

            int xOffset = Math.round((float) (mWidth - 34/5 * r) / 2);
            int yOffset = Math.round((float) (mHeight - 16/5 * r) / 2);
            switch (pos) {
                case 0: setLocation(xOffset, yOffset);
                    break;
                case 1: setLocation(xOffset + (double) 6/5 * r, yOffset + (double) 6/5 * r);
                    break;
                case 2: setLocation(xOffset + (double) 12/5 * r, yOffset);
                    break;
                case 3: setLocation(xOffset + (double) 18/5 * r, yOffset + (double) 6/5 * r);
                    break;
                case 4: setLocation(xOffset + (double) 24/5 * r, yOffset);
                    break;
                default: setLocation(xOffset, yOffset);
            }
        }
    }
}
