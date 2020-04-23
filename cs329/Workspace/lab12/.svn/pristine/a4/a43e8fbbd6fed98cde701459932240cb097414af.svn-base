package decorator;

import java.awt.geom.Line2D;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall {

	public final static int NONE = 0, LEFT = 1, RIGHT = 2, TOP = 3, BOTTOM = 4;

	private double left_, top_, width_, height_;

	/**
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 */
	public Wall ( double left, double top, double width, double height ) {
		left_ = left;
		top_ = top;
		width_ = width;
		height_ = height;
	}

	public double getLeft () {
		return left_;
	}

	public double getRight () {
		return left_ + width_;
	}

	public double getTop () {
		return top_;
	}

	public double getBottom () {
		return top_ + height_;
	}

	public int isHitBy ( Ball ball ) {
		double bottom = top_ + height_, right = left_ + width_;
		if ( Line2D.linesIntersect(left_ - ball.getRadius(),top_,
		                           right + ball.getRadius(),top_,
		                           ball.getX() - ball.getXVelocity(),
		                           ball.getY() - ball.getYVelocity(),ball.getX(),
		                           ball.getY())
		    && ball.getYVelocity() > 0 ) {
			return TOP;
		}
		if ( Line2D.linesIntersect(left_ - ball.getRadius(),bottom,
		                           right + ball.getRadius(),bottom,
		                           ball.getX() - ball.getXVelocity(),
		                           ball.getY() - ball.getYVelocity(),ball.getX(),
		                           ball.getY())
		    && ball.getYVelocity() < 0 ) {
			return BOTTOM;
		}
		if ( Line2D.linesIntersect(left_,top_ - ball.getRadius(),left_,
		                           bottom + ball.getRadius(),
		                           ball.getX() - ball.getXVelocity(),
		                           ball.getY() - ball.getYVelocity(),ball.getX(),
		                           ball.getY())
		    && ball.getXVelocity() > 0 ) {
			return LEFT;
		}
		if ( Line2D.linesIntersect(right,top_ - ball.getRadius(),right,
		                           bottom + ball.getRadius(),
		                           ball.getX() - ball.getXVelocity(),
		                           ball.getY() - ball.getYVelocity(),ball.getX(),
		                           ball.getY())
		    && ball.getXVelocity() < 0 ) {
			return RIGHT;
		}

		return NONE;
	}

	public double getWidth () {
		return width_;
	}

	public double getHeight () {
		return height_;
	}

	public void draw ( GraphicsContext g ) {
		g.setFill(Color.DARKGRAY);
		g.fillRect(getLeft(),getTop(),getWidth(),getHeight());
		g.setStroke(Color.BLACK);
		g.strokeRect(getLeft(),getTop(),getWidth(),getHeight());
	}
}
