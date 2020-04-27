{-
import Data.List
solveRPN :: (Num a, Read a) => String -> a
solveRPN = head . foldl foldingFunction [] . words
    where   foldingFunction (x:y:ys) "*" = (x * y):ys
            foldingFunction (x:y:ys) "+" = (x + y):ys
            foldingFunction (x:y:ys) "-" = (y - x):ys
            foldingFunction xs numberString = read numberString:xs
-}

--data Op = Val Int | Add | Sub | Mul | Div deriving ( Show , Eq )
--data Op = Val Int | Add | Sub | Mul | Div deriving ( Show , Eq )
data Op = Num Int|Add|Sub|Mul|Div

type Ex = [Op]
instance Show Op where
  show (Num x) = show x
  show Add = "+"
  show Sub = "-"
  show Mul = "*"
  show Div = "/"
instance Eq Op where
  (==) Add Add = True
  (==) Sub Sub = True
  (==) Mul Mul = True
  (==) Div Div = True
  (==) _ _ = False

eval :: Ex -> Int
eval exp = head (evalAux exp [])
    where
      evalAux:: Ex -> [Int] -> [Int]
      evalAux [] stk@[x] = stk
      evalAux ((Num x):ss) stk = evalAux ss (x:stk)
      evalAux (op:ss) (r:l:stk)
        |op == Add = ((l+r):stk)
        |op == Sub = ((l-r):stk)
        |op == Mul = ((l*r):stk)
        |op == Div = ((l `div` r):stk)
        -- |otherwise evalAux ss stk
