package visitor;

/**
 * Simple demo program for the expression tree.
 */
public class ExpressionMain {

	public static void main ( String[] args ) {
		Expression expr =
		    new Multiply(new Add(new Value(3),new Value(5)),new Value(7));

		PrintVisitor pv = new PrintVisitor();
		EvaluateVisitor ev = new EvaluateVisitor();
		System.out.println(expr.accept(pv) + " = " + expr.accept(ev));
	}

}
