package decorator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle {

	private double x_, y_;

	private double width_, height_;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Paddle ( double x, double y, double width, double height ) {
		super();
		x_ = x;
		y_ = y;
		width_ = width;
		//width_ = 700;
		height_ = height;
	}

	public double getTop () {
		return y_ - height_ / 2;
	}

	public double getBottom () {
		return y_ + height_ / 2;
	}

	public double getLeft () {
		return x_ - width_ / 2;
	}

	public double getRight () {
		return x_ + width_ / 2;
	}

	public void moveLeft ( double amount ) {
		x_ -= amount;
	}

	public void moveRight ( double amount ) {
		x_ += amount;
	}

	public boolean containsPoint ( double x, double y ) {
		return (x >= getLeft() && x <= getRight() && y >= getTop()
		    && y <= getBottom());
	}

	public boolean isHitBy ( Ball ball ) {
		return getLeft() <= ball.getX() && getRight() >= ball.getX()
		    && ball.getY() + ball.getRadius() >= getTop();
	}

	public double getHeight () {
		return height_;
	}

	public double getWidth () {
		return width_;
	}

	public void draw ( GraphicsContext g ) {
		g.setFill(Color.GRAY);
		g.fillRect(getLeft(),getTop(),getWidth(),getHeight());
		g.setStroke(Color.BLACK);
		g.strokeRect(getLeft(),getTop(),getWidth(),getHeight());
	}
}
