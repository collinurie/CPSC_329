package visitor;

public class PrintVisitor implements ExpressionVisitor<String> {

	@Override
	public String visit ( Add exp ) {
		return "(" + exp.getLeft().accept(this) + "+" + exp.getRight().accept(this) + ")";		
	}

	@Override
	public String visit ( Multiply exp ) {
		return "(" + exp.getLeft().accept(this) + "*" + exp.getRight().accept(this) + ")";		
	}

	@Override
	public String visit ( Value exp ) {
		return "" + exp.getValue();
	}
}
