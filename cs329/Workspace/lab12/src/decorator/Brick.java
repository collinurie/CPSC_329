package decorator;

import javafx.scene.canvas.GraphicsContext;

/**
 * @author ak5158
 *
 */
public interface Brick {

	int BOTTOM = 4;
	int LEFT = 1;
	int NONE = 0;
	int RIGHT = 2;
	int TOP = 3;

	public int getHitsLeft ();

	public double getLeft ();

	public double getRight ();

	public double getTop ();

	public double getBottom ();

	public int getPoints ();

	public int isHitBy ( Ball ball );

	public double getHeight ();

	public double getWidth ();

	public void draw ( GraphicsContext g );
	
	public Ball decorate(Ball ball);
	}

