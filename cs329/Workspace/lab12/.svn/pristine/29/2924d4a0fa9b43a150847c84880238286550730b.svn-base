package decorator;

import javafx.scene.canvas.GraphicsContext;

/**
 * @author cu5988
 *
 */
public abstract class DecoratedBall implements Ball {
	protected Ball ball_; 
	
	public DecoratedBall(Ball b) {
		ball_ = b;
	}

	@Override
	public double getRadius () {
		return ball_.getRadius();
	}

	@Override
	public void move () {
		ball_.move();
	}

	@Override
	public void bounceX () {
		ball_.bounceX();
	}

	@Override
	public void bounceY () {
		ball_.bounceY();

	}

	@Override
	public double getXVelocity () {
		return ball_.getXVelocity();
	}

	@Override
	public double getYVelocity () {
		return ball_.getYVelocity();
	}

	@Override
	public void setPosition ( double x, double y ) {
		ball_.setPosition(x,y);
	}

	@Override
	public double getX () {
		return ball_.getX();
	}

	@Override
	public double getY () {
		return ball_.getY();
	}

	@Override
	public void draw ( GraphicsContext g ) {
		ball_.draw(g);
	}

}
