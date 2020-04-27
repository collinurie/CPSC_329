{-
 CPSC 271 (Topics: Functional Programming)

 Code from class on Wednesday, 01/30/2019 and 02/01/2019.  Primarily, this is
 an illustration of function definition syntax, in particular the use of
 point-free definitions, lambdas, and the where and let forms for local
 declarations.  There is some use of lists here, but we won't get to those until
 the next class.
-}

lyFull::Int -> Bool
lyFull y = div4 y && (notDiv100 y || div400 y)
    where
        -- You can always include a type annotation with a definition, even a
        -- nested one.  This one isn't necessary, but it doesn't hurt, either.
        divby::Int -> Int -> Bool
        divby b  = \a -> (a `mod` b == 0)
        div4 = divby 4
        div400 = divby 400
        -- notDiv100 x = not (divby 100 x)
        -- The composition operator helps us to define a shorter, point-free version:
        notDiv100 = not.(divby 100)

valid:: Int -> Int -> Int -> Bool
valid y m d = gregorian y && monthOK m && dayOKforM d
    where
        gregorian y' = y' >= 1582
        monthOK m' = 1 <= m' && m' <= 12 -- m' `elem` [1..12]
        dayOKforM = dayOK y m

        dayOK y m d = d `elem` [1..(maxDay y m)]
            {-
              Should dayOK be defined in a nested where instead of the way it's
              done here, simultaneously with dayOKforM?  We can do it either way.
              The best choice depends on the visibility we want for dayOK.  As
              given here, it is usable in the definitions of gregorian, monthOK,
              and dayOKforM, though we only need it for the last of those.  If
              we put it under a where clause, then its definition can only be
              used in the right hand side of the dayOKforM definition.
            -}
            where
                maxDay y m =
                    -- Here's a let form, for contrast
                    let febLength y = if lyFull y then 29 else 28
                        mlens = [31,(febLength y), 31,30,31,30,31,31,30,31,30,31]
                    in
                        mlens !! (m-1)
                        -- In Java, this would be:  return mlans[m-1];
{-
yZ  = y - ((14-m) `div` 12)
mZ  = m +(12*((14-m) `div` 12))
b = yZ `mod` 100
c = yZ `div` 100
leapDays = (b `div` 4)+ (c `div` 4) + 5*c
-}
dow y m d = (d + ( ((mZ+1) * 26) `div` 10) + b + leapDays + 6) `mod` 7
        where
          yZ  = y - ((14-m) `div` 12)
          mZ  = m +(12*((14-m) `div` 12))
          b = yZ `mod` 100
          c = yZ `div` 100
          leapDays = (b `div` 4)+ (c `div` 4) + 5*c

dateformat y m d
        | not (valid y m d) = "Not a valid date"
        | otherwise = (days !! (dow y m d -1))++", "++(mname !! (m-1)) ++ " " ++ (show d) ++ ", " ++ (show y)
        where
            mname = ["January", "February","March","April","May","June","July",
                     "August","September","October","November","December"]
            days = ["Monday", "Tuesday","Wednesday","Thursday", "Friday", "Saturday","Sunday"]
