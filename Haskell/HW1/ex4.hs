primeslist::Integer->[Integer]
primeslist a  = findPrimesB a
    where
      y = a
      findPrimesB x
              | x == 1 = []
              | isPrime x = findPrimesB (x - 1) ++ [x]
              | otherwise = findPrimesB (x- 1)
                where
                  isPrime k = null [ x | x <- [2.. k - 1], k `mod` x == 0]
