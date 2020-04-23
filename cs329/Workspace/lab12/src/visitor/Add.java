package visitor;

/**
 * The + operator.
 */
public class Add extends BinaryOperator {

	/**
	 * @param left
	 *          the left operand
	 * @param right
	 *          the right operand
	 */
	public Add ( Expression left, Expression right ) {
		super(left,right);
	}
//
//	/**
//	 * Compute and return the value of this expression.
//	 * 
//	 * @return the value of the expression
//	 */
//	public double evaluate () {
//		return left_.evaluate() + right_.evaluate();
//	}
//
//	/**
//	 * Return a string representation of this expression (infix format).
//	 * 
//	 * @return a string representation of the expression (infix format)
//	 */
//	public String toString () {
//		return "(" + left_ + "+" + right_ + ")";
//	}

	
	public <T> T accept ( ExpressionVisitor<T> visitor ) {
	      return visitor.visit(this);
	  }
}
