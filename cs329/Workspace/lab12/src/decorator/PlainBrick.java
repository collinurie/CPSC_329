package decorator;

import java.awt.geom.Line2D;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlainBrick implements Brick {
	private double left_, top_, width_, height_;

	private Color color_;

	private int points_;

	private int hitsLeft_;

	/**
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 * @param color
	 * @param points
	 */
	public PlainBrick ( double left, double top, double width, double height,
	               Color color, int points, int hits ) {
		left_ = left;
		top_ = top;
		width_ = width;
		height_ = height;
		color_ = color;
		points_ = points;
		hitsLeft_ = hits;
	}

	@Override
	public int getHitsLeft () {
		return hitsLeft_;
	}

	@Override
	public double getLeft () {
		return left_;
	}

	@Override
	public double getRight () {
		return left_ + width_;
	}

	@Override
	public double getTop () {
		return top_;
	}

	@Override
	public double getBottom () {
		return top_ + height_;
	}

	@Override
	public int getPoints () {
		return points_;
	}

	private void hit () {
		if ( hitsLeft_ > 0 ) {
			color_ =
			    new Color((1.0 - color_.getRed()) / hitsLeft_ + color_.getRed(),
			              (1.0 - color_.getGreen()) / hitsLeft_ + color_.getGreen(),
			              (1.0 - color_.getBlue()) / hitsLeft_ + color_.getBlue(),1);
			hitsLeft_--;
		}
	}

	@Override
	public int isHitBy ( Ball ball ) {
		double bottom = top_ + height_, right = left_ + width_;
		if ( Line2D.linesIntersect(left_ - ball.getRadius(),top_,
		                           right + ball.getRadius(),top_,
		                           ball.getX() - ball.getXVelocity(),
		                           ball.getY() - ball.getYVelocity(),ball.getX(),
		                           ball.getY())
		    && ball.getYVelocity() > 0 ) {
			hit();
			return TOP;
		}
		if ( Line2D.linesIntersect(left_ - ball.getRadius(),bottom,
		                           right + ball.getRadius(),bottom,
		                           ball.getX() - ball.getXVelocity(),
		                           ball.getY() - ball.getYVelocity(),ball.getX(),
		                           ball.getY())
		    && ball.getYVelocity() < 0 ) {
			hit();
			return BOTTOM;
		}
		if ( Line2D.linesIntersect(left_,top_ - ball.getRadius(),left_,
		                           bottom + ball.getRadius(),
		                           ball.getX() - ball.getXVelocity(),
		                           ball.getY() - ball.getYVelocity(),ball.getX(),
		                           ball.getY())
		    && ball.getXVelocity() > 0 ) {
			hit();
			return LEFT;
		}
		if ( Line2D.linesIntersect(right,top_ - ball.getRadius(),right,
		                           bottom + ball.getRadius(),
		                           ball.getX() - ball.getXVelocity(),
		                           ball.getY() - ball.getYVelocity(),ball.getX(),
		                           ball.getY())
		    && ball.getXVelocity() < 0 ) {
			hit();
			return RIGHT;
		}

		return NONE;
	}

	@Override
	public double getHeight () {
		return height_;
	}

	@Override
	public double getWidth () {
		return width_;
	}

	@Override
	public void draw ( GraphicsContext g ) {
		g.setFill(color_);
		g.fillRect(left_ + 1,top_ + 1,width_ - 2,height_ - 2);
		g.setStroke(Color.BLACK);
		g.strokeRect(left_ + 1,top_ + 1,width_ - 2,height_ - 2);
	}

	@Override
	public Ball decorate ( Ball ball ) {
		// plain bricks don't do anything special
		return ball;
	}

}
