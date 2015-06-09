package MouseEvents;

import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class BouncingBall extends GraphicsProgram {
    Ball dragBall;
    GPoint last;

    @Override
    public void init() {
        addMouseListeners();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        last = new GPoint(e.getPoint());
        dragBall = (Ball) getElementAt(last);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (dragBall != null) {
            dragBall.sendToFront();
            return;
        }
        double r = 40;
        Ball b = new Ball(e.getX() - r, e.getY() - r, getWidth(), getHeight(), r, 0.3, 0.2);
        add(b);
        b.active = true;
        new Thread(b).start();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragBall == null) {
            double r = 20;
            last = new GPoint(e.getPoint());
            dragBall = new Ball(last.getX() - r, last.getY() - r, getWidth(), getHeight(), r);
            add(dragBall);
        } else {
            dragBall.setActive(false);
            double dx = e.getX() - last.getX();
            double dy = e.getY() - last.getY();
            dragBall.move(dx, dy);
            dragBall.setVelocity(dx, dy);
            last = new GPoint(e.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragBall != null) {
            dragBall.setActive(true);
            new Thread(dragBall).start();
        }
    }

    class Ball extends GOval implements Runnable {
        boolean active = false;
        double dx, dy, windowWidth, windowHeight;
        private double gravity, friction;
        private static final int PAUSE_TIME = 16;

        Ball(double x, double y, double windowWidth, double windowHeight, double radius) {
            super(2 * radius, 2 * radius);
            setVelocity(0, 0);
            this.windowWidth = windowWidth;
            this.windowHeight = windowHeight;
            gravity = 0.5;
            friction = 0.15;
            setFilled(true);
            setFillColor(Color.RED);
            setLocation(x, y);
        }

        Ball(double x, double y, double windowWidth, double windowHeight, double radius, double gravity, double friction) {
            this(x, y, windowWidth, windowHeight, radius);
            this.gravity = gravity;
            this.friction = friction;
        }

        @Override
        public void run() {
            fall(windowWidth, windowHeight);
        }

        private void fall(double w, double h) {
            double r = getWidth() / 2;
            while (active) {
                if (getX() < 0 || getX() + 2 * r > w) setVelocity(-dx, dy);
                if (getY() < 0 || getY() + 2 * r > h) setVelocity(dx, -dy);
                applyFriction();
                setVelocity(dx, dy + gravity);
                if (getY() + getHeight() > h - 1) setLocation(getX(), h - getHeight());
                move(dx ,dy);
                pause(PAUSE_TIME);
            }
        }

        void setVelocity(double dx, double dy) {
            if (Math.abs(dx) < 0.0000001) dx = 0;
            if (Math.abs(dy) < 0.0000001) dy = 0;
            this.dx = dx;
            this.dy = dy;
        }

        private void applyFriction() {
            if (dy > 0) dy -= friction;
            if (dy < 0) dy += friction;
            if (dx > 0) dx -= friction;
            if (dx < 0) dx += friction;
        }

        public void setActive(boolean active) {
            this.active = active;
        }
    }

}
