
-- CSCE 314 [Sections 598, 599] Programming Languages Fall 2024
-- Hyunyoung Lee
-- Homework Assignment 3 (Total 100 points) Due on Monday 9/23/24 at 11:59 p.m.

-- Problem 1 (5 points)
-- Student Name: (PUT YOUR NAME HERE)
-- UIN: (PUT YOUR UIN HERE)
-- Resources: (ACKNOWLEDGE ANY HELP RECEIVED HERE)

-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit
import System.Exit
import Data.List
import Data.Char

-- *** Read textbook Chapters 5, 6, and 7, and the problem specifications
-- *** and requirements in hw3.pdf carefully!

-- Problem 2 (Chapter 6, Exercise 7) (10 points)
merge :: Ord a => [a] -> [a] -> [a]
merge [] ys = ys
merge xs [] = xs 
merge (x:xs) (y:ys)
  | x <= y   = x : merge xs (y:ys)
  | otherwise = y : merge (x:xs) ys

{-
The merge function has two base cases: when xs is empty we return
ys, and when ys is empty we return xs. When two populated lists are passed in,
we use list pattern matching to deconstruct both xs and ys into their heads and tails.
We then compare the heads of each list. If the head of the first list is greater,
we use the cons operator to append the recursive call of the merge function (passing in the tail of xs
and the entire ys list) to the head of the xs list. Otherwise, we do the opposite. The function will return 
a single sorted list of type a (a being of the Ord subtype)


-}


-- Problem 3 (Chapter 6, Exercise 8) (5+10=15 points)
---- Question 3.1 (5 points)
halve :: [a] -> ([a], [a])
halve [] = ([], [])
halve xs = splitAt (length xs `div` 2) xs

{-
The halve function takes a list of type a and returns a tuple of lists
also of type a. When you pass in an empty list,
and a tuple containing two empty lists are returned. When xs is populated,
it uses the splitAt prelude function (which takes an index argument and a list argument), passing
in half of the length of the list as the first argument and xs as the second argument. The function
returns a tuple with the first element being the first half of the list, and the second element being the
second half of the list.

-}

---- Question 3.2 (10 points)
msort :: Ord a => [a] -> [a]
msort [] = []
msort [x] = [x]
msort xs = merge (msort left) (msort right)
  where
    (left, right) = halve xs

{-
The msort function takes a list filled with values of type a (which must be of the ord type), and
returns a list also filled with values of type a. The function has two base cases, when an empty list
is passed in an empty list is returned, and when a list with a single element is passed in we return the
list containing the single element back because it is already sorted. When the list has more than 1 element,
we use the previously defined halve function to define left and right (because merge sort uses the divide
and conquer method), and we recursively call the msort function on the left and right half and pass in the results of these
recursive calls to the previously defined merge function. We return a single sorted list.
-}

-- Problem 4 (10+10+10=30 points)
---- Question 4.1 (10 points) 
mergeBy :: (a -> a -> Bool) -> [a] -> [a] -> [a]
mergeBy _ [] ys = ys
mergeBy _ xs [] = xs 
mergeBy comp (x:xs) (y:ys)
  | comp x y  = x : mergeBy comp xs (y:ys)
  | otherwise = y : mergeBy comp (x:xs) ys

{-
The mergeby function takes in a predicate function (a comparison function that returns a Boolean value),
and two lists filled with values of type a. It returns a merged, sorted list also fillde with values of type a.
The base cases are the exact same as the merge function. When both lists are populated,
we use the exact same logic as the merge function as well except instead of using > and otherwise, we are using the predicate
function that is passed in.
-}



---- Question 4.2 (10 points) 
msortBy :: (a -> a -> Bool) -> [a] -> [a]
msortBy _ [] = []
msortBy _ [x] = [x]
msortBy comp xs = mergeBy comp (msortBy comp left) (msortBy comp right)
  where
    (left, right) = halve xs


---- Question 4.3 (10 points)
{- Write your answer for Question 4.3 within this block comment.
-- Should be detailed step-by-step.

msortBy > [7, 5, 1, 4, 2] (initial call) will do the following:

Split into left = [7, 5] right = [1, 4, 2]

left = [7] right = [5]

mergeBy > [7] [5] returns [7, 5]

right = [1] left = [4, 2]

right = [4] left = [2]

mergeBy > [4] [2] returns [4, 2]

mergeBy > [4, 2] [1] returns [4, 2, 1]

mergeBy > [7, 5] [4, 2, 1] returns [7, 5, 4, 2, 1]




-}


-- Problem 5 (10+5+10=25 points)
---- Question 5.1 (10 points)
myInsert :: Ord a => a -> [a] -> [a]
myInsert x [] = [x]
myInsert a (x:xs)
  | a <= x    = a:x:xs
  | otherwise = myInsert 

---- Question 5.2 (5 points)
mySort :: Ord a => [a] -> [a]
mySort = undefined

---- Question 5.3 (10 points)
{- Write your answer for Question 5.3 within this block comment.
-- Should be detailed step-by-step.

-}



-- Problem 6 (Chapter 7, Exercise 9) (10+5=15 points)
---- Question 6.1 (10 points)
altMap :: (a -> b) -> (a -> b) -> [a] -> [b]
altMap = undefined

---- Question 6.2 (5 points)
{- Write your answer for Question 6.2 within this block comment.
-- Should be detailed step-by-step.

-}




    
myTestList =
  TestList [

      "merge 1" ~: merge "EGG" "ABCDEFGH" ~=? "ABCDEEFGGGH" 
    , "merge 2" ~: merge "Hello" "e" ~=? "Heello"
    , "merge 3" ~: merge [1,3,5,7,9] [2,4,6] ~=? [1,2,3,4,5,6,7,9]

    , "halve 1" ~: halve "" ~=? ("","")
    , "halve 2" ~: halve "halves" ~=? ("hal","ves")
    , "halve 3" ~: halve "halve" ~=? ("ha","lve")

    , "msort 1" ~: msort "Howdy all!" ~=? " !Hadllowy"
    , "msort 2" ~: msort "" ~=? ""
    , "msort 3" ~: msort "Mississippi" ~=? "Miiiippssss"
    , "msort 4" ~: msort [3,2,1,5,4] ~=? [1,2,3,4,5]

    , "mergeBy 1" ~: mergeBy (>) "FED" "GBA" ~=? "GFEDBA"
    , "mergeBy 2" ~: mergeBy (<) "Howdy" "Maui" ~=? "HMaouiwdy"
    , "mergeBy 3" ~: mergeBy (>) [5,1] [6,4,3] ~=? [6,5,4,3,1]
      
    , "msortBy 1" ~: msortBy (<) "gig 'em" ~=? " 'eggim" 
    , "msortBy 2" ~: msortBy (>) "Jack be nimble" ~=? "nmlkieecbbaJ  "
    , "msortBy 3" ~: msortBy (<) "" ~=? ""
    , "msortBy 4" ~: msortBy (>) [3,2,1,5,4] ~=? [5,4,3,2,1]

    , "myInsert 1" ~: myInsert 'o' "Hw are you?" ~=? "How are you?"
    , "myInsert 2" ~: myInsert 'c' "abdefg" ~=? "abcdefg"
    , "myInsert 3" ~: myInsert 3 [2,4,6] ~=? [2,3,4,6]

    , "mySort 1" ~: mySort "Jack be quick" ~=? "  Jabcceikkqu"
    , "mySort 2" ~: mySort "Howdy all!" ~=? " !Hadllowy"

    , "altMap 1" ~: altMap (* 10) (* 100) [1,2,3,4,5] ~=? [10,200,30,400,50]
    , "altMap 2" ~: and (altMap even odd [1..10]) ~=? False
    , "altMap 3" ~: altMap toLower toUpper "Haskell IS fun!" ~=? "hAsKeLl iS FuN!"
    ]

main = do c <- runTestTT myTestList
          putStrLn $ show c
          let errs = errors c
              fails = failures c
          exitWith (codeGet errs fails)
          
codeGet errs fails
 | fails > 0       = ExitFailure 2
 | errs > 0        = ExitFailure 1
 | otherwise       = ExitSuccess
