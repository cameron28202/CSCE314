
-- CSCE 314 [Sections 598, 599] Programming Languages, Fall 2024
-- Homework Assignment 2 (Total 100 points)

-- Problem 1 (5 points)
-- This is head comment (single line comment should be preceded by two dashes)
-- Student Name: Cameron Stone
-- UIN: 832007843
-- Resources: the notes and textbook

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

{-
Line 1: isElem function takes in a value of type a (which must
be of type Eq), and a list filled with values of type a. The function
returns a boolean.

Line 2: This is the first definition of the isElem function, the base 
case for recursion. When an empty list is passed in, false is returned.

Line 3: isElem takes a value of type a and a list xs. It assigns x to the head
and xs to the tail using pattern matching.

Line 4: First guard, if a is the head of the list that was passed in, we return true

Line 5: Otherwise, use recursion to search the rest of the list for a.
-}


-- Problem 3 (15 points)
-- Using isElem (from Problem 2) in the definition is required
toSet :: Eq a => [a] -> Set a
toSet [] = []
toSet (x:xs)
  | isElem x xs  = toSet xs
  | otherwise    = x : toSet xs

{-
Line 1: toSet takes a list filled with  values of type a (which must be of type Eq),
and returns a Set filled with values of type a.

Line 2: This is the base case of the toSet function, the base case
for recursion. When an empty list is passed in an empty set is returned.

Line 3: The function assigns the head of the list passed in to x and the tail to xs
using pattern matching.

Line 4: Using the isElem function, we are checking if the current head of xs
is already in the list, and if it is, we skip adding it to our set (because
  sets cannot have duplicates). We recursively call toSet again with the current list

Line 5: Otherwise, add it to the set by using recursion and the cons operator.
-}



-- Problem 4 (15 points)
-- Using isElem (from Problem 2) in the definition is required
subset :: Eq a => Set a -> Set a -> Bool
subset [] _ =  True
subset (x:xs) ys
  | isElem x ys = subset xs ys
  | otherwise   = False

{-
  Line 1: The subset function takes in a Set filled with values of type a (which must be of the Eq
  subclass), and another set also filled with values of type a. The function returns a boolean.

  Line 2: This is the base case of recursion. If the first set passed in is empty, return true.
  By using the wildcard (_), we are able to ignore the second input to the function. This will be
  useful for recursion.

  Line 3: Using pattern matching, the function assigns the head to x and the tail to xs. The 
  function also takes another set called ys.

  Line 4: This is the first guard. Here, we are checking if the current head is in the second
  set (ys) by using the previously defined isElem function. If it is found, we recursively call
  the subset function again passing in the tail (xs) and the unchanged ys list. We are doing
  this to check the remaining elements. 

  Line 5: If x is not found in the ys list, the function immediately returns false, meaning that
  xs is not a subset of ys. If all elements of the first set are found in the second set, 
  the function returns True (line 4).
-}


-- Problem 5 (10 points)
-- Using subset (from Problem 4) in the definition is required
setEqual :: Eq a => Set a -> Set a -> Bool
setEqual xs ys = subset xs ys && subset ys xs
{-
Line 1: The setequal function takes a set filled with values of type a (which must be from the Eq subclass),
and another set also filled with values of type a. The function returns a boolean.

Line 2: Given two lists, xs and ys, we use the previously defined subset function to determine
if they are equal. We know that if A is a subset of B, and B is a subset of A, then the sets
are equal. We implement this logic by using the and operator (&&), returning true if xs
is a subset of ys, and ys is a subset of xs. Otherwise, they are not equal sets.
-}


-- Problem 6 (20+10 = 30 points)
powerset :: Set a -> Set (Set a)
---- Question 6.1  (20 points)
---- Using direct recursion and list comprehenson is required
powerset [] = [[]]
powerset (x:xs) = sets ++ [x:subset | subset <- sets]
  where sets = powerset xs

{-
Line 1: The powerset function takes a set of type a, and returns a set filled with sets of type a.
This is how we will create a powersets.

Line 2: This is the base case of recursion for the function. If an empty set is passed in, we return
a set filled with an empty set.

Line 3 and 4: We first use pattern matching to break the input set into x, the head, and xs, the tail. We
are then using recursion to first create a stack of operations. By using the where keyword, we are setting
sets equal to a recursive call of the powerset function with the tail of the set passed in. By using list comprehension,
we will append every x to each subset in sets. We then combine the powerset of xs with the new subsets that include x.
The function will return the powerset of the set.
-}

---- Question 6.2 (10 points)
{-
  When invoked with the input [2, 3], the powerset function first builds a recursive
  stack of sets. For the first call, x = 2 and xs = [3]. The function then calls powerset 
  [3], where x = 3 and xs = []. The function then calls powerset [] which returns [[]]. Now 
  that we have reached the base case, we go back to powerset [3]. The function uses list comprehension 
  to add 3 to each subset in sets, which at the moment is just [[]], producing [[3]]. We then combine
  sets ++ [[3]] to get [[], [3]]. Now at the top of the stack with powerset [2, 3], the function
  uses list comprehension to add 2 to each subset in sets. [2:[]] ++ [2:[3]], giving us [[2], [2, 3]].
  It lastly combines sets ++ [[2], [2, 3]] for a final result of [[], [3], [2], [2,3]].
-}



-- Problem 7 (10+5 = 15 points)
scalarproduct :: [Int] -> [Int] -> Int
---- Question 7.1  (10 points)
---- Using list comprehenson and the zip prelude function is required
scalarproduct xs ys = sum [x * y | (x, y) <- zip xs ys]

{-
Line 1: The scalarproduct function takes in two lists of ints, and returns an int.

Line 2: Given two lists xs and ys, we compute the scalar product of the lists. We do this by 
using list comprehension. We then use the zip function, which pairs elements from two lists sequentially.
For example, given  xs = [1, 2] and ys = [3, 4], zip xs ys would produce [(1, 3), (2, 4)].
It stops when the shorter list is exhausted. Then the first block of our list comprehension states that we
will multiply the x and y value from each pair. We then use the sum function to sum up this list of products,
giving us the scalar product. We then return this integer value.
-}

---- Question 7.2 (5 points)
{- 
  After calling scalarproduct [4, 3, 5, 2] [1..], We start with the sum function. Then we enter the list comprehension evaluation,
  where the zip function is called on xs and ys to produce the first pair. It returns a pair
  of the first element from each list. In this case, it returns the pair (4, 1). We then use
  list comprehension to multiply the x and y value of the pair, passing the result (4) to the sum
  function. This process is then repeated for the 2nd element: (3, 2), 3 * 2 = 6, then sum adds 6 (4 + 6 = 10).
  and the 3rd element (5, 3), 5 * 3 = 15, 10 + 15 = 25. Lastly, we repeat for the 4th element (2, 4), 2 * 4 = 8,
  25 + 8 = 33. We get a final value of 33, which is returned.
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

