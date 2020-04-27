-- file: ch07/toupper-imp.hs
import System.IO
import Data.Char(toUpper, isLetter)
import Data.List.Split(splitOneOf)

{-
this function is from:
http://book.realworldhaskell.org/read/io.html
file: ch07/toupper-imp.hs

-}
main :: IO ()
main = do
       putStr "Name of text file: "
       hFlush stdout   -- manually flush output stream (since there’s no \n)
       inh <- openFile "input.txt" ReadMode -- makes Handle with the input file
       outh <- openFile "output.txt" WriteMode -- makes Handle with the output file
       mainloop inh outh
       hClose inh -- closes Handle
       hClose outh -- closes Handle
------------------------------------------------------------------------------------------
{-
Some of this comes from the same page as the main function
the rest was modified my me to do what my application requires
-}
mainloop :: Handle -> Handle -> IO ()
mainloop inh outh =
    do ineof <- hIsEOF inh -- determines if there is more file to read
       if ineof -- if there isnt -> finish
           then return ()
           else do inpStr <- hGetLine inh -- otherwise gets the next line of the file

                    {-
                       ***** explination of the nect two lines of code *****
                       takes the input line and splits it into words
                       maps pig latin onto all the words
                       turns that list into a string
                       prints that string to the out file
                       calls the function again to recursively go through every line
                    -}
                   --putStrLn (listToStr (map piglatin (toSplit inpStr))) -- here for testing purpose
                   hPutStrLn outh (listToStr (map piglatin (toSplitList' inpStr)))
                   mainloop inh outh
------------------------------------------------------------------------------------------

{-
splits a string into a list of words
-}
toSplitList :: String -> [String]
toSplitList "" = []
toSplitList s =
    let word = takeWhile (/=' ') $ dropWhile (==' ') s
        (_, rest) = splitAt (length word) s
    in word : toSplitList (dropWhile (==' ') rest)

toSplitList':: String -> [String]
toSplitList' s = splitOneOf " ,.:;?!" s
--toSplitList'  = (map (\c -> if not (isLetter c || (c == '\n')) then ' ' else c))

------------------------------------------------------------------------------------------

{-
takes a list (of words) and turns it into a string
-}
listToStr:: [String] -> String
listToStr [] = ""
listToStr (x:xs) = x ++" "++(listToStr xs)
------------------------------------------------------------------------------------------

{-
this is my piglatin function from HW1
(only works with single words at a time )
-}
piglatin::String->String
--piglatin "" = ""
piglatin word = translate word
    where
      voul = ['a','e','i','o','u','y']
      translate str
              | str =="" = ""
              | elem (str !! 0) voul = str ++ "yay" -- if it starts with a vowel
              |otherwise = moveCons str ++ "ay" -- if it doesnt
                  where
                    moveCons :: String -> String
                    -- moves the consonants to the end of the word
                    moveCons string
                            |elem (string !! 0) voul = string -- if it starts witha vowel leave it
                            |otherwise = moveCons ((tail string) ++ [(string !! 0)] ) --move cons to the end
------------------------------------------------------------------------------------------
{-
-- just here to make sure everything works before I try to do it with files
{-
Takes a string (with words) and returns a string in piglatin
-}
bigPig:: String -> String
bigPig s = pAux (toSplitList s)
    where pAux ls = listToStr(map piglatin ls )
-- it works!!!!
-}
