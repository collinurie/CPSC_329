package decorator;

import javafx.scene.canvas.GraphicsContext;

/**
 * @author ak5158
 *
 */
public abstract class DecoratedBrick implements Brick {
	
	protected Brick brick_;
	
	public DecoratedBrick(Brick brick){
		brick_ = brick;
	}

	@Override
	public int getHitsLeft () {
		return brick_.getHitsLeft();
	}

	@Override
	public double getLeft () {
		return brick_.getLeft();
	}

	@Override
	public double getRight () {
		return brick_.getRight();
	}

	@Override
	public double getTop () {
		return brick_.getTop();
	}

	@Override
	public double getBottom () {
		return brick_.getBottom();
	}

	@Override
	public int getPoints () {
		return brick_.getPoints();
	}

	@Override
	public int isHitBy ( Ball ball ) {
		return brick_.isHitBy(ball);
	}

	@Override
	public double getHeight () {
		return brick_.getHeight();
	}

	@Override
	public double getWidth () {
		return brick_.getWidth();
	}

	@Override
	public void draw ( GraphicsContext g ) {
		brick_.draw(g);
	}

	@Override
	public Ball decorate ( Ball ball ) {
		brick_.decorate(ball);
		return ball;

	}

}