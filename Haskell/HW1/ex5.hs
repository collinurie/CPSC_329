checksorted::[a]->(a->a->Bool)->Bool
--checksorted [] f = True
checksorted ls f
        | length ls == 1 = True
        | f (ls !! 0) (ls !! 1) = checksorted (tail ls) f
        | otherwise = False
