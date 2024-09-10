
-- CSCE 314 [Sections 598, 599] Programming Languages, Fall 2024
-- Homework Assignment 2 (Total 100 points)

-- Problem 1 (5 points)
-- This is head comment (single line comment should be preceded by two dashes)
-- Student Name: (PUT YOUR NAME HERE)
-- UIN: (PUT YOUR UIN HERE)
-- Resources: (ACKNOWLEDGE ANY HELP RECEIVED HERE)

-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit

import System.Exit


-- Example:
-- Write a recursive function mySum that sums all the numbers
-- in a list without using the prelude function sum.
mySum :: [Int] -> Int  -- type signature of mySum. mySum accepts a list of Ints
                       -- as its argument and returns an Int
mySum []     = 0  -- def.1
mySum (x:xs) = x + mySum xs -- def.2

{- Block comment over multiple lines is enclosed within {- and -}
Explanation:
The type of mySum tells us that mySum takes a list of Ints as an argument
and returns an Int that is the sum of all the Ints in the argument list.

Def.1 of mySum is the base case of the recursion, that is,
the argument list is empty, for which case the function value is 
defined as zero (summation identity).

Def.2 is when the argument list contains at least one element, 
namely x, in which case the function is defined as the sum of x 
and the result of the recursive call of mySum applied to the rest of 
the argument list, namely xs.
-}


type Set a = [a]

-- Problem 2 (10 points)
isElem :: Eq a => a -> [a] -> Bool
isElem a [] = False
isElem a (x:xs)
  | a == x    = True
  | otherwise = isElem a xs


-- Problem 3 (15 points)
-- Using isElem (from Problem 2) in the definition is required
toSet :: Eq a => [a] -> Set a
toSet [] = []
toSet (x:xs)
  | isElem x xs  = toSet xs
  | otherwise    = x : toSet xs


-- Problem 4 (15 points)
-- Using isElem (from Problem 2) in the definition is required
subset :: Eq a => Set a -> Set a -> Bool
subset [] _ =  True
subset (x:xs) ys
  | isElem x ys = subset xs ys
  | otherwise   = False


-- Problem 5 (10 points)
-- Using subset (from Problem 4) in the definition is required
setEqual :: Eq a => Set a -> Set a -> Bool
setEqual xs ys = subset xs ys && subset ys xs


-- Problem 6 (20+10 = 30 points)
powerset :: Set a -> Set (Set a)
---- Question 6.1  (20 points)
---- Using direct recursion and list comprehenson is required
powerset [] = [[]]
powerset (x:xs) = sets ++ [x:subset | subset <- sets]
  where sets = powerset xs

---- Question 6.2 (10 points)
{- Write your answer for Question 6.2 within this block comment.

-}



-- Problem 7 (10+5 = 15 points)
scalarproduct :: [Int] -> [Int] -> Int
---- Question 7.1  (10 points)
---- Using list comprehenson and the zip prelude function is required
scalarproduct = undefined

---- Question 7.2 (5 points)
{- Write your answer for Question 7.2 within this block comment.

-}



myTestList =
  TestList [

      "isElem 1" ~: (isElem 'h' "happy") ~=? True
    , "isElem 2" ~: (isElem 'o' "happy") ~=? False
    , "isElem 3" ~: (isElem 'p' "happy") ~=? True

    , "toSet 1" ~: length (toSet "aardvark") ~=? 5
    , "toSet 2" ~: length (toSet "BartBart") ~=? 4

    , "subset 1" ~: subset [] [1,2] ~=? True
    , "subset 2" ~: subset [1,2] [] ~=? False
    , "subset 3" ~: subset [2,3] [1,2] ~=? False
    , "subset 4" ~: subset [2,3] [3,1,2] ~=? True
    , "subset 5" ~: subset [2,3] [2,1,4] ~=? False

    , "setEqual 1" ~: setEqual "abc" "bca" ~=? True
    , "setEqual 2" ~: setEqual [1,2] [2,1] ~=? True
    , "setEqual 3" ~: setEqual [1,2,3] [1,2,3,4] ~=? False
    , "setEqual 4" ~: setEqual [2,3,1] [1,2,3] ~=? True

    , "powerset 1" ~: length (powerset ([]::[Int])) ~=? 1
    , "powerset 2" ~: length (powerset [5]) ~=? 2
    , "powerset 3" ~: length (powerset [3,2,5,1,4]) ~=? 32
    , "powerset 4" ~: length (powerset "abc") ~=? 8

    , "scalarproduct 1" ~: scalarproduct [4,5,6] [1,2,3] ~=? 32
    , "scalarproduct 2" ~: scalarproduct [2,3] [1,-1] ~=? -1
    , "scalarproduct 3" ~: scalarproduct [1..10] [1..5] ~=? 55

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

