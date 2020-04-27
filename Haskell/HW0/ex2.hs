{-
multOf::Int -> Int -> Bool
-- returns True iff n divides m
multOf n m = (m `mod` n) == 0
-}
multOf::Int -> Int -> Bool
multOf = \a -> \b -> (a `mod` b) == 0


{-
stripHeadMults::[Int] -> [Int]
-- returns the values in the argument list, with the first
-- element and all of its multiples omitted.
stripHeadMults ls
    if ls == [] then []
    else
      let x = head ls
          xs = tail ls
      in
        filter (\y -> not (multOf x y)) xs
-}

stripHeadMults::[Int] -> [Int]
stripHeadMults ls
        | ls == [] = []
        | otherwise  = filter (\y -> not (multOf (head ls) y)) (tail ls)




{-}
sieve::([Int],[Int]) -> ([Int],[Int])
sieve nums =
    let primes = fst nums
        candidates = snd nums
        in
            if candidates == [] then (primes,[])
            else
                (primes ++ take 1 candidates,
                 stripHeadMults candidates)
-}
sieve::([Int],[Int]) -> ([Int],[Int])
sieve nums =
    if candidates == [] then (primes,[])
    else
        (primes ++ take 1 candidates, stripHeadMults candidates)
        where
            primes = fst nums
            candidates = snd nums
