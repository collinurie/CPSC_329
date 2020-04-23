package visitor;

/**
 * @author cu5988
 *
 */

public class EvaluateVisitor implements ExpressionVisitor<Double> {

	@Override
	public Double visit ( Add exp ) {
		return exp.getLeft().accept(this) + exp.getRight().accept(this);
	}

	@Override
	public Double visit ( Multiply exp ) {
		return exp.getLeft().accept(this) * exp.getRight().accept(this);
	}

	@Override
	public Double visit ( Value exp ) {
		return exp.getValue();
	}
  
}
