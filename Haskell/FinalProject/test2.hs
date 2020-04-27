import System.IO
import Data.Char(toUpper, isLetter)
import Data.List.Split(splitOneOf)

main :: IO ()
main = do
    putStr "File: "
    hFlush stdout   -- manually flush output stream (since there’s no \n)
    inFile <- getLine
    inh <- readFile inFile ReadMode
    outh <- openFile "output.txt" WriteMode -- makes Handle with the output file
    --mainloop inh outh
    hClose inh -- closes Handle
    hClose outh -- closes Handle

{-
Some of this comes from the same page as the main function
the rest was modified my me to do what my application requires
-}
