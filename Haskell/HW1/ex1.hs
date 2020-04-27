takeWhile'::(a -> Bool) -> [a] -> [a]
takeWhile' f ls
        | f (ls !! 0) = [ls !! 0] ++ (takeWhile' f (tail ls))
        |otherwise = []
