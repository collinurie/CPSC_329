dropWhile'::(a->Bool)->[a]->[a]
dropWhile' f ls
        | f (ls !! 0) = dropWhile' f (tail ls)
        | otherwise = ls
