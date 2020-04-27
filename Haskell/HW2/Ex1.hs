
data List a = Empty | Cons a (List a)

instance (Show a) => Show (List a) where
    show ls = showAux ls
        where
            showAux Empty = "[:]"
            showAux (Cons x xs) = (show x) ++ ";" ++ (showAux xs)

instance (Eq a) => Eq (List a) where
    (==) Empty Empty             = True
    (==) Empty _                 = False
    (==) _  Empty                = False
    (==) (Cons x xs) (Cons y ys) = if x == y then xs == ys else False

toList::[a] -> List a
toList [] = Empty
toList (x:xs) = Cons x (toList xs)

-- 1
length'::List a -> Int
length' Empty = 0
length' (Cons a (xs)) =  1 + length' xs

-- 2
get::List a -> Int -> a
get (Cons a (xs)) 0 = a
get (Cons a (xs)) i = get xs (i-1)

-- 3
elem'::Eq a => a -> List a -> Bool
elem' x Empty = False
elem' x (Cons a (xs)) = if x == a then True else elem' x xs

-- 4
map'::(a -> b) -> List a -> List b
map' f Empty = Empty
map' f (Cons a (xs)) = (Cons (f a) (map' f xs))

-- 5
take'::Int -> List a -> List a
take' 0 ls = Empty
take' i (Cons a (xs)) = (Cons a (take' (i-1) xs))

-- 6
drop'::Int -> List a -> List a
drop' 0 ls = ls
drop' i (Cons a (xs)) = drop'(i-1) xs

-- 7
concat'::List a -> List a -> List a
concat' Empty ls = ls
concat' (Cons a (ys)) ls = (Cons a (concat' ys ls))

-- 8
filter'::(a -> Bool) -> List a -> List a
filter' _ Empty = Empty
filter' f (Cons a (xs)) = if f a then (Cons a (filter' f xs))
  else filter' f xs

-- 9
zip'::List a -> List b -> List (a,b)
zip' Empty _ = Empty
zip' _ Empty = Empty
zip' (Cons a (xs)) (Cons b (ys)) = (Cons (a,b) (zip' xs ys))

-- 10

unzip'::List (a,b) -> (List a,List b)
unzip' Empty = (Empty,Empty)
unzip' (Cons (a,b) (xs)) = ((Cons (a) (fst (unzip' xs))),((Cons (b)) (snd(unzip' xs))))



data InfiniteSequence a = Seq (a, () -> (InfiniteSequence a))
instance (Show a) => Show (InfiniteSequence a) where
    show (Seq (x,rest)) = "<<" ++ (show x) ++ ", (rest)>>"

natsByK::Int -> Int -> InfiniteSequence Int
natsByK k i = Seq (i, \() -> natsByK k (i+k))

natsFrom::Int -> InfiniteSequence Int
natsFrom = natsByK 1

evens = natsByK 2 0
odds = natsByK 2 1


-- 11
takeSeq::Int -> InfiniteSequence b -> [b]
takeSeq 0 _ = []
takeSeq a (Seq (x,rest)) = x:(takeSeq (a-1) (rest()))

-- 12
mapSeq::(a -> a) -> InfiniteSequence a -> InfiniteSequence a
mapSeq f (Seq (x, rest)) = Seq(f x, \() -> (mapSeq f (rest())))

-- 13
filterSeq::(a -> Bool) -> InfiniteSequence a -> InfiniteSequence a
filterSeq f (Seq (x, rest))
    |f x == True = Seq(x, \() -> (filterSeq f (rest())))
    |otherwise = filterSeq f (rest())












---
