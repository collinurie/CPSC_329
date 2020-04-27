piglatin::String->String
piglatin word = translate word
    where
      voul = ['a','e','i','o','u']
      translate str
              | elem (str !! 0) voul = str ++ "yay"
              |otherwise = moveCons str ++ "ay"
                  where
                    moveCons :: String -> String
                    moveCons string
                            |elem (string !! 0) voul = string
                            |otherwise = moveCons ((tail string) ++ [(string !! 0)] )
