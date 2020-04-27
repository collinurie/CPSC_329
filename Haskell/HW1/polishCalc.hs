
data Sym = Num Int|Add|Sub|Mul|Div
type Expr = [Sym]
instance Show Sym where
  show (Num x) = show x
  show Add = "+"
  show Sub = "-"
  show Mul = "*"
  show Div = "/"

ex = [Num 3, Num 4, Add]

eval:: Expr -> Int
eval exp = evalAux exp []
    where
      evalAux [] [Num x] = x
      evalAux ((num x):ss) stk = evalAux ss (x:stk)
      evalAux (op:ss) (r:l:stk)
        |op == Add = ((l+r):stk)
        |op == Sub = ((l-r):stk)
        |op == Mul = ((l*r):stk)
        |op = Div = ((l/r):stk)
        
