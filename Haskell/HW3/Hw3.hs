
{-
foldr f z [] = z
foldr f z (x:xs) = f x (foldr f z xs)
-----------------------------------------------------
foldl f z [] = z
foldl f z (x:xs) = foldl f (f z x) xs
-}


-- 1
-- length` = foldr (\_ -> succ) 0
length':: [a] -> Int
length' [] = 0
length' (x:xs) =  (\_ -> succ) x (length' xs)

-- 2
--elem' x = foldr ((||).(x ==)) False
elem'::Eq a => a -> [a] -> Bool
elem' _ [] = False
elem' x (y:ys) = ((||).(x == )) y (elem' x ys)

-- 3
-- map' f = foldr ((:).f) []
map' :: (a -> b) -> [a] -> [b]
map' _ [] = []
map' f (x:xs) = ((:).f) x (map' f xs)

-- 4
-- map'' f = foldl ((++).f) []
map'':: (a -> b) -> [a] -> [b]
map'' f ls = fAux f ls
  where
    fAux:: (a -> b) -> [a] -> [b]
    fAux _ [] = []
    fAux f (x:xs) = ((:).f) x (fAux f xs)

--5
-- unzip' = foldr(\(a,b) acc -> (a:(fst acc), b:(snd acc))) ([],[])
unzip' ::[(a,b)] -> ([a],[b])
unzip' [] = ([],[])
unzip' ((x,y):xs) = (\(a,b) acc -> (a:(fst acc), b:(snd acc))) (x,y) (unzip' xs)
