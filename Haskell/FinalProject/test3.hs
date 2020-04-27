-- file: ch07/toupper-imp.hs
import System.IO
import Data.Char(toUpper, isLetter)
import Data.List.Split(splitOneOf)
import System.Environment


{-
Some of the main function is from:
http://book.realworldhaskell.org/read/io.html
file: ch07/toupper-imp.hs
Most of it has been altered though so not much of the actual code is not mine.

This application takes a file from the args [String] when the application is run
If it is not run as an application then it will not get a file to read so that program
will not run.
-}
main :: IO ()
main = do
       args <- getArgs
       inh <- openFile (head args) ReadMode
       {-
       if you uncomment the line below and commenting out the two lines above
       the program input file will then be hardcoded into the program
       you can change the input file via the source code.
       -}
       --inh <- openFile "input.txt" ReadMode -- makes Handle with the input file
       outh <- openFile "output.txt" WriteMode -- makes Handle with the output file
       mainloop inh outh
       hClose inh -- closes Handle
       hClose outh -- closes Handle
------------------------------------------------------------------------------------------
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
                       ***** explination of the next two lines of code *****
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
------------------------------------------------------------------------------------------


{-
these two functions are interchangable. One does a simple split on white space
the other does a split while removing punctuation.
I was unable to get a split including punctuation, unfortunately this
translator only workd on strings with only words and no punctuation. :(
-}

{- splits a string into a list of words -}
toSplitList :: String -> [String]
toSplitList "" = []
toSplitList s =
    let word = takeWhile (/=' ') $ dropWhile (==' ') s
        (_, rest) = splitAt (length word) s
    in word : toSplitList (dropWhile (==' ') rest)
{- this does split but removing all punctuation -}
toSplitList':: String -> [String]
toSplitList' s = splitOneOf " ,.:;?!" s
--toSplitList'  = (map (\c -> if not (isLetter c || (c == '\n')) then ' ' else c))

------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------

{-
takes a list (of words) and turns it into a string
-}
listToStr:: [String] -> String
listToStr [] = ""
listToStr (x:xs) = x ++" "++(listToStr xs)
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------

{-
this is my piglatin function from HW1
(only works with single words at a time )
I am not using this function anymore because it has some bugs.
-}

{-
piglatin::String->String
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

 -}

{-
This is the pigLatin function that is used, I developed it from a number of different
implementations I saw while researching how to get rid of the bug from my HW1 function.

I reveived the full versionin from the HW1 answers but chose to stick with this
version because I wanted to use mostly my own work.
-}
piglatin word = back word ++ front word ++ suffix word
   where front word = fst (breakWord word)
         back word  = snd (breakWord word)
         suffix word = if front word == "" then "hay"
                                     else "ay"
         breakWord word = span (`notElem` "aeiou") word
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------

--just here to make sure everything works before I try to do it with files
{- Takes a string (with words) and returns a string in piglatin -}
bigPig:: String -> String
bigPig s = pAux (toSplitList s)
    where pAux ls = listToStr(map piglatin ls )
-- it works!!!!
