isEven :: Int -> Bool
isEven a
        | a `mod` 2 ==0 = True
        |otherwise = False

filter' :: (Int -> Bool) -> [Int] -> [Int]
filter' f ls
        | ls == [] = []
        | f (ls !! 0) = (ls !! 0) : (filter' f (tail ls))
        |otherwise = filter' f (tail ls)


merge :: [Int] -> [Int] -> [Int]
merge ls xs
        | ls == [] && xs == [] = []
        | ls == [] = [xs !! 0] ++ merge ls (tail xs)
        | xs == [] = [ls !! 0] ++ merge (tail ls) xs
        | (xs !! 0) > (ls !! 0) = [ls !! 0] ++ merge (tail ls) xs
        | otherwise = [xs !! 0] ++ merge ls (tail xs)
