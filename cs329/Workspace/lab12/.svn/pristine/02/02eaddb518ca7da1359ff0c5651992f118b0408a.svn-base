package decorator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlainBall implements Ball{

	private double x_, y_;

	private double radius_;

	private double xvelocity_, yvelocity_;

	/**
	 * @param x
	 * @param y
	 * @param radius
	 * @param xvelocity
	 * @param yvelocity
	 */
	public PlainBall ( double x, double y, double radius, double xvelocity,
	              double yvelocity ) {
		super();
		x_ = x;
		y_ = y;
		radius_ = radius;
		xvelocity_ = xvelocity;
		yvelocity_ = yvelocity;
	}

	@Override
	public double getRadius () {
		return radius_;
	}

	@Override
	public void move () {
		x_ += xvelocity_;
		y_ += yvelocity_;
	}

	@Override
	public void bounceX () {
		xvelocity_ = -xvelocity_;
	}

	@Override
	public void bounceY () {
		yvelocity_ = -yvelocity_;
	}

	@Override
	public double getXVelocity () {
		return xvelocity_;
	}

	@Override
	public double getYVelocity () {
		return yvelocity_;
	}

	@Override
	public void setPosition ( double x, double y ) {
		x_ = x;
		y_ = y;
	}

	@Override
	public double getX () {
		return x_;
	}

	@Override
	public double getY () {
		return y_;
	}

	@Override
	public void draw ( GraphicsContext g ) {
		g.setFill(Color.BLUE);
		g.fillOval(getX() - getRadius(),getY() - getRadius(),2 * getRadius(),
		           2 * getRadius());
		g.setStroke(Color.BLACK);
		g.strokeOval(getX() - getRadius(),getY() - getRadius(),2 * getRadius(),
		             2 * getRadius());
	}

}
