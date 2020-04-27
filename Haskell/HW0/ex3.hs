ip = curry(sum.(map (uncurry (*))).(uncurry zip))

ip2 :: [Int] -> [Int] -> Int
ip2 as bs
        | as == [] || bs == [] = 0
        | otherwise = ((as !! 0)*(bs !! 0)) + ip2 (tail as) (tail bs)
--   zip' takes (as bs )
--   map' takes [(a,b)]
--   sum takes []
