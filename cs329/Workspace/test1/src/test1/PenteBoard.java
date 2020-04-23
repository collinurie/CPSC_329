package test1;

public class PenteBoard {

	public static final int NUMROWS = 19, NUMCOLS = 19;
	
	public int grid_[][];
	/**
	 * I chose to change the code and create this method to make reusable code and reduce repeated code. 
	 * This one method replaces the two methods that were in the original code blackCap and whiteCap.
	 * 
	 * This method can be called the same as blackCap or whiteCap could be called by just adding the color 
	 * of the piece as a parameter. 
	 * 
	 * I also changed the name of the variable n_ to numCaptures and made it a local variable instead of
	 * an instance variable because that name n_ did not reveal intent. I made it a local variable because
	 * it had no use as an instance variable and was being reset every time the method called it anyway. 
	 * 
	 * @param x - the x value that the capture check is happening from.
	 * @param y - the y value that the capture check is happening from. 
	 * @param color - the color of the piece that the the capture check is being initiated for.
	 * @return
	 */
	private int capture(int x, int y, int color) {
		int numCaptures = 0; // this used to be the instance variable n_
		
		int otherColor; // the color that is being captured 
		if(color == 1) {otherColor = 2;}
		else { otherColor = 1;}
		
		if(y +3 < NUMROWS && grid_[y][x]==color && grid_[y+1][x]==otherColor
				&& grid_[y+2][x]==otherColor && grid_[y+3][x]==color) {
			grid_[y+1][x] = 0;
			grid_[y+2][x] = 0;
			numCaptures++;
		}
		if(y +3 < NUMROWS && x+3 < NUMCOLS && grid_[y][x]==color && grid_[y+1][x+1]==otherColor
				&& grid_[y+2][x+2]==otherColor && grid_[y+3][x+3]==color) {
			grid_[y+1][x+1] = 0;
			grid_[y+2][x+2] = 0;
			numCaptures++;
		}
		if(y +3 < NUMROWS && grid_[y][x]==color && grid_[y][x+1]==otherColor
				&& grid_[y][x+2]==otherColor && grid_[y][x+3]==color) {
			grid_[y][x+1] = 0;
			grid_[y][x+2] = 0;
			numCaptures++;
		}
		if(y - 3 >= 0 && x+3 < NUMCOLS && grid_[y][x]==color && grid_[y- 1][x+1]==otherColor
				&& grid_[y-2][x+2]==otherColor && grid_[y- 3][x+3]==color) {
			grid_[y-1][x+1] = 0;
			grid_[y-2][x+2] = 0;
			numCaptures++;
		}
		if(y - 3 >= 0 && grid_[y][x]==color && grid_[y- 1][x]==otherColor
				&& grid_[y-2][x]==otherColor && grid_[y- 3][x]==color) {
			grid_[y-1][x] = 0;
			grid_[y-2][x] = 0;
			numCaptures++;
		}
		if(y - 3 >= 0&& x-3 >= 0 && grid_[y][x]==color && grid_[y- 1][x-1]==otherColor
				&& grid_[y-2][x-2]==otherColor && grid_[y- 3][x-3]==color) {
			grid_[y-1][x-1] = 0;
			grid_[y-2][x-2] = 0;
			numCaptures++;
		}
		if(y + 3 < NUMROWS && x-3 >= 0 && grid_[y][x]==color && grid_[y+1][x-1]==otherColor
				&& grid_[y+2][x-2]==otherColor && grid_[y+3][x-3]==color) {
			grid_[y+1][x-1] = 0;
			grid_[y+2][x-2] = 0;
			numCaptures++;
		}
		
		return numCaptures;
	}
	
	
}
