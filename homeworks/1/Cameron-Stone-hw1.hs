
-- CSCE 314 [Sections 598, 599] Programming Languages, Fall 2024
-- Homework Assignment 1 (Total 105 points)

-- Problem 1 (5 points)
-- This is head comment (single line comment should be preceded by two dashes)
-- Student Name: Cameron Stone
-- UIN: 832007843
-- Resources: (ACKNOWLEDGE ANY HELP RECEIVED HERE)

-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit  -- if this line causes compile error, try the following
                   -- command at the terminal prompt > cabal v1-install HUnit
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


-- Problem 2 (5+15 = 20 points)
qsort1 :: Ord a => [a] -> [a]
---- Question 2.1 (5 points)
qsort1 [] = []
qsort1 (x:xs) = qsort1 larger ++ [x] ++ qsort1 smaller
                where 
                  smaller = [a | a <- xs, a <= x]
                  larger = [b | b <- xs, b > x]


---- Question 2.2 (15 points)
{- Write your answer for Question 2.2 within this block comment.

When qsort1 is invoked with the input [15, 8, 11, 6, 10], it 
goes through the following call stack:

x = 15, larger = [], smaller = [8,11,6,10]
  - Call 1: qsort1 [] (for larger)
  - Call 2: qsort1 [8,11,6,10] (for smaller)

x = 8, larger = [11,10], smaller = [6]
  - Call 3: qsort1 [11,10] (for larger)
  - Call 4: qsort1 [6] (for smaller)

x = 11, larger = [], smaller = [10]
  - Call 5: qsort1 [] (for larger)
  - Call 6: qsort1 [10] (for smaller)

x = 10, larger = [], smaller = []
  - Call 7: qsort1 [] (for larger)
  - Call 8: qsort1 [] (for smaller)

 x = 6, larger = [], smaller = []
  - Call 9: qsort1 [] (for larger)
  - Call 10: qsort1 [] (for smaller)

for a total of 10 times not including the initial call.
-}


-- Problem 3 (10 points)
lucas :: Int -> Int
lucas 0 = 2
lucas 1 = 1
lucas n 
  | n > 1 = lucas (n-1) + lucas (n-2)
  | otherwise = error "no negative numbers"

{-
The lucas function computes the n-th Lucas number. Lucas numbers are similar 
to Fibonacci numbers, but start with 2 and 1 instead of 0 and 1. 

The function has two base cases:
1. When n is 0, it returns 2
2. When n is 1, it returns 1

For n > 1, the function recursively computes the n-th Lucas number by summing 
the (n-1)th and (n-2)th Lucas numbers: lucas(n) = lucas(n-1) + lucas(n-2).
-}

-- Problem 4 (10 points)
factorial :: Int -> Int
factorial 0 = 1
factorial n = n * factorial (n-1)


-- Problem 5 (5+10+10=25 points)
---- Question 5.1 (5 points)

semifactorial :: Int -> Int
semifactorial n
  | n < 0     = error "enter a positive number"
  | n == 0    = 1
  | n == 1    = 1
  | otherwise = n * semifactorial (n-2)

{- The semifactorial function has 3 base cases:
  when n < 1, throw an error
  when n == 0, return 1
  when n == 1, return 1
  in any other case, multiply n * a recursive call of the semifactorial function with n-2
-}

---- Question 5.2 (10 points)
{-
semifactorial 14 would invoke the following recursive calls:

1. 14 * semifactorial 12
2. 12 * semifactorial 10
3. 10 * semifactorial 8
4. 8 * semifactorial 6
5. 6 * semifactorial 4
6. 4 * semifactorial 2
7. 2 * semifactorial 0

It would make a total of 7 recursive calls without counting the first 
invocation of semifactorial 14, and return 14*12*10*8*6*4*2*1 = 645120.
-}

---- Question 5.3 (10 points)
myfactorial :: Int -> Int
myfactorial 0 = 1
myfactorial n = semifactorial n * semifactorial (n-1)



-- Problem 6 (10+15+10=35 points)
---- Question 6.1 (10 points)
term :: Num a => Int -> a -> a
term n x
  | n == 1    = x
  | n == 0    = 1
  | otherwise = x * term (n-1) x

{-
Line 1: The term function has a type signature of Num a => Int -> a -> a
meaning that the type a is of the subclass Num,
the first input is an integer, the second input is of type a, 
and the function returns a value of type a.

Line 2: term takes two inputs, n (power) and x (base number)

Line 3: when n is 1, return x

line 4: when n is 0, return 1 because anything to the power of 0 is 1

line 5: otherwise, multiply x with a recursive call of the term function with 
inputs (n-1) and x.
-}

---- Question 6.2 (15 points)
polynaive :: Num a => [a] -> Int -> a -> a
polynaive [] _ _ = 0
polynaive (a:as) n x = a * term n x + polynaive as (n-1) x

{-
Line 1: The polynaive function has a type signature of Num a => [a] -> Int -> a -> a
meaning that a is a subclass of type Num, the first input is an array of values with type a,
the second input must be an integer, the third input is a value of type a, and 
the function returns a value of type a.

Line 2: base case. When an empty array is passed in, return 0.

Line 3: Given a list of coefficients, recursively sum a * term n x + the rest of the polynomial 

-}


---- Question 6.3 (10 points)
{- Write your answer for Question 6.3 within this block comment.

for the input [2, 4, -1, 1] 3 4:

as (the list containing the coefficients) is [2, -4, -1, 1]
n (the degree of the polynomial) is 3 
x (the number we are plugging into the polynomial) is 4

For every number a in the coefficient array, the function multiples a with x^n 
(by using the term function) and recursively adds the rest of the polynomial.

1. 2 * 4^3 + polynaive [-4, -1, 1] 2 4
2. -4 * 4^2 + polynaive [-1, 1] 1 4
3. -1 * 4^1 + polynaive [1] 0 4
4. 1 * 4^0 + polynaive []

2 * 4^3 + -4 * 4^2 + -1 * 4^1 + 1 * 4^0 = 61

-}
myTestList = 
  TestList [

      "qsort1 1" ~: qsort1 [3, 2, 5, 1, 8] ~=? [8,5,3,2,1]
    , "qsort1 2" ~: qsort1 "howdy" ~=? "ywohd"
    , "qsort1 3" ~: qsort1 "Oh, happy day!" ~=? "yypphhdaaO,!  "

    , "lucas 1" ~: lucas 0 ~=? 2
    , "lucas 2" ~: lucas 1 ~=? 1    
    , "lucas 3" ~: lucas 4 ~=? 7
    
    , "factorial 1" ~: factorial 3 ~=? 6
    , "factorial 2" ~: factorial 5 ~=? 120
    , "factorial 3" ~: factorial 10 ~=? 3628800

    , "semifactorial 1" ~: semifactorial 3 ~=? 3
    , "semifactorial 2" ~: semifactorial 5 ~=? 15
    , "semifactorial 3" ~: semifactorial 10 ~=? 3840

    , "myfactorial 1" ~: myfactorial 3 ~=? 6
    , "myfactorial 2" ~: myfactorial 5 ~=? 120
    , "myfactorial 3" ~: myfactorial 10 ~=? 3628800

    , "term 1" ~: term 3 2 ~=? 8
    , "term 2" ~: term 4 5 ~=? 625
    , "term 3" ~: term 10 3 ~=? 59049

    , "polynaive 1" ~: polynaive [2,-1,3,5] 3 2 ~=? 23
    , "polynaive 2" ~: polynaive [1,3,0,7,2] 4 5 ~=? 1037
    , "polynaive 3" ~: polynaive [(1/3),1,-2,0,2,1,(1/2)] 6 3 ~=? 345.5
    , "polynaive 4" ~: polynaive [3,-4,2,7] 3 2 ~=? 19

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