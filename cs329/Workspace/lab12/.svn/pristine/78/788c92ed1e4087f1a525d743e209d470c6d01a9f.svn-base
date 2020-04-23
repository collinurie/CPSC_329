package decorator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author cu5988
 *
 */
public class ResizedBall extends DecoratedBall {

	private double sizeChange_;
	
	/**
	 * @param b
	 */
	public ResizedBall ( Ball b, double sizeChange ) {
		super(b);
		sizeChange_ = sizeChange;
	}
	
	@Override
	public double getRadius(){
		return ball_.getRadius()+sizeChange_;
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
