package visitor;

/**
 * A binary operator.
 */
public abstract class BinaryOperator implements Expression {

	protected Expression left_, right_; // left and right operands

	/**
	 * @param left
	 *          left operand
	 * @param right
	 *          right operand
	 */
	protected BinaryOperator ( Expression left, Expression right ) {
		left_ = left;
		right_ = right;
	}

	/**
	 * @return the left operand for this expression
	 */
	public Expression getLeft () {
		return left_;
	}

	/**
	 * @return the right operand for this expression
	 */
	public Expression getRight () {
		return right_;
	}

}
