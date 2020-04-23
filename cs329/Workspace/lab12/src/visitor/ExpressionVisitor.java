package visitor;

public interface ExpressionVisitor<T> {
    public T visit (Add exp);
    
    public T visit (Multiply exp);
    
    public T visit (Value exp);
    

 }