

-- CSCE 314 [Sections 598, 599] Programming Languages Fall 2024
-- Homework Assignment 4 (Total 100 points)
-- Due on Friday, October 4, 2024

-- Problem 1 (5 points)
-- Student Name: Cameron Stone
-- UIN: 832007843
-- 
-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit
import System.Exit

-- *** Read Chapters 8 and 16 ***

data Tree a b = Leaf a | Branch b (Tree a b) (Tree a b)

---- Tree objects to be used to test your functions in Problems 2 and 3
-- Use tree1 to show the step-by-step of your function in Problem 3.2
tree1 :: Tree Int String
tree1 = Branch "*" 
            (Branch "+"
               (Branch "*" (Leaf 5) (Leaf 1))
               (Branch "+" (Leaf 2) (Leaf 6)))
            (Branch "*"
               (Branch "+"
                  (Branch "*" (Leaf 8) (Leaf 4))
                  (Leaf 9))
               (Branch "+" (Leaf 7) (Leaf 3)))

-- Another example Tree object
tree2 :: Tree Int String 
tree2 = Branch "+"  
            (Leaf 1)
            (Branch "*" (Leaf 2) (Leaf 3))

-- Yet another Tree object
tree3 :: Tree Int String
tree3 = Branch "+" 
            (Branch "*" 
               (Leaf 3)
               (Leaf 4))
            (Branch "+"
               (Branch "*" (Leaf 5) (Leaf 2))
               (Leaf 1))

-- Yet another...
tree4 :: Tree Int String
tree4 = Branch "A" 
            (Branch "B" 
               (Leaf 1) 
               (Leaf 2)) 
            (Leaf 3)
---------------

-- Problem 2 (15 points)
instance (Show a, Show b) => Show (Tree a b) where
    show tree = showTree tree 0

showTree :: (Show a, Show b) => Tree a b -> Int -> String
showTree (Leaf x) indent = replicate indent '\t' ++ show x
showTree (Branch value left right) indent = 
    replicate indent '\t' ++ show value ++ "\n" ++
    showTree left (indent+1) ++ "\n" ++
    showTree right (indent+1)

{- 
    The show function makes a call to the showTree function, passing in the tree and 0.

    The showTree function takes in a tree filled with values that are of the type Show, an int
    which will be used for tracking indentation, and returns a string.

    This function uses the replicate prelude function which is used to show indentation. 
    it's adding a number of tabs equal to the current indentation level.

    When the function is called and a leaf is passed in as the tree, it will print out the
    current indentation, and show the value of the leaf.

    When the function is called with a Branch, it will first show the indentation and value of the branch,
    then make recursive calls to show the left and right sub branches.

    This will show the tree with the correct indentation levels.

-}



-- Problem 3 (15 + 10 = 25 points)
---- Problem 3.1 (5 + 5 + 5 = 15 points)
preorder  :: (a -> c) -> (b -> c) -> Tree a b -> [c]  -- 5 points
preorder f g (Leaf x) = [f x]
preorder f g (Branch value left right) = [g value] ++ preorder f g left ++ preorder f g right

{-
    The preorder traversal visits the current root first, then left, then right, recursively.
    The function takes in two functions, a value of type tree a b, and returns a list of type [c].
    
    The two functions are used to transform type from either a or b to the final type, c.
    This is done because the tree could be made up of values of different types, and we want to have
    a list filled with values of the same time at the end.

    The function has two cases:
    1. when you pass in a leaf, we return th efunction f applied to the value of the leaf, then wrap it in a list.

    2. When you pass in a branch, we return the function g applied to the value wrapped in a list,
    then recursively visit the left branch by calling the preorder function again, then recursively visit the right.

    This will return a list of elements in preorder traversal order.
-}


inorder   :: (a -> c) -> (b -> c) -> Tree a b -> [c]  -- 5 points
inorder f g (Leaf x) = [f x]
inorder f g (Branch value left right) = inorder f g left ++ [g value] ++ inorder f g right

{-

    The inorder function has the same type signature as preorder. It also uses functions f and g to 
    transform type from a and b to a final type c.

    Inorder traversal visits the left subtree, and when it reaches a leaf, it visits the current root
    x, then visits the right sub tree, recursively.

    Inorder function has the same case as preorder with the left, applying f to x and wrapping it in a list.
    However, when a branch is passed in, we visit the left element first by recursively calling inorder,
    visit the parent node (apply g to the value and wrap in a list), then recursively visit the right node.

    This will return a list of elements in inorder traversal order.
-}

postorder  :: (a -> c) -> (b -> c) -> Tree a b -> [c]  -- 5 points
postorder f g (Leaf x) = [f x]
postorder f g (Branch value left right) = postorder f g left ++ postorder f g right ++ [g value]


{-
    The postorder function has the same type signature as preorder and inorder.

    Postorder traversal will visit the left subtree first, and when it reaches a leaf, it then visits
    the right subtree, and then the current root x.

    Postorder function has the same case as preorder and inorder with the leaf, applying f to x and wrapping it in a list.
    When a branch is passed in, we visit the left element first by recursively calling postorder and passing in the left subtree,
    we then recursively visit the right subtree, and then visit the parent (apply g to the value and wrap in a list)

    This will return a list of elements in postorder traversal order. 

-}

---- Problem 3.2 (10 points)
{-- Explain the step-by-step of the following expression.
    Your answer must be in detail step-by-step using your definition
    for inorder.

> inorder show id tree1

    Inorder traversal first visits the left subtree, then parent, then right.

    tree1 visualized:

                *
         /             \
        +               *
     /     \         /     \
    *       +       +       +
   / \     / \     / \     / \
  5   1   2   6   2   *   7   3
                     / \
                    8   4

Starting with: inorder show id tree1

Root: Branch "*"

Function calls: inorder f g left ++ [g value] ++ inorder f g righ

Left subtree: Branch "+"
Recurse left: inorder f g left
Left-left subtree: Branch "*"
Recurse left: inrder f g left


Leaf 5
Base case: inorder f g (Leafx ) = [f x]
Applyshow to 5: ["5"]


Return to Branch "*"
Add [g value]: ["5", "*"]

Recurse right on Branch "*"

Process Leaf 1: [f x] = ["1"]
Combine: ["5", "*", "1"]


Return to left Branch "+"
Add [g value]: ["5", "*", "1", "+"]

Recurse right on left Branch "+"

Process Branch "+"

Leaf 2: ["2"]
Add "+": ["2","+"]
Leaf 6: ["2", "+", "6"]

Combine: ["5", "*","1", "+", "2", "+", "6"]

Return to root Branch "*"
Add [g value]: ["5", "", "1", "+", "2","+", "6", ""]

Process right subtree: Branch "*"
Recurse left: Branch "+"

Leaf 2: ["2"]
Add "+": ["2","+"]
Process Branch "*"

Leaf 8: ["8"]
Add "" -> ["8", ""]
Leaf 4: ["8", "*","4"]


Combine: ["2", "+", "8", "*", "4"]

Return to right Branch "*"
Add [g value]: ["2", "+", "8", "", "4", ""]

Process right-right Branch "+"

Leaf 7: ["7"]
Add "+": ["7", "+"]
Leaf 3: ["7", "+", "3"]


Final combination at root:
Left result ++ [root value] ++ Right result
["5", "", "1", "+", "2", "+", "6"] ++ [""] ++ ["2", "+", "8", "", "4", "", "7", "+", "3"]

Final result: ["5","", "1", "+", "2", "+", "6","", "2", "+", "8", "", "4", "", "7", "+", "3"]

--}
                          

-- Problem 4 (40 points) Chapter 8, Exercise 9 Modified
data Expr = Val Int | Add Expr Expr | Subt Expr Expr | Mult Expr Expr

type Cont = [Op]

data Op = EVALA Expr | ADD Int | EVALS Expr | SUBT Int | EVALM Expr | MULT Int

eval :: Expr -> Cont -> Int
-- Give four definitions for eval.
-- First two definitions,
-- 1) for (Val n) and c as arguments and
-- 2) for (Add x y) and c as arguments
-- are already given in the text Section 8.7, but
-- you need to modify the second definition slightly
-- and give the third and fourth definitions for
-- (Subt x y) and (Mult x y)
eval (Val n) c =  exec c n
eval (Add x y) c = eval x (EVALA y : c)
eval (Subt x y) c = eval x (EVALS y : c)
eval (Mult x y) c = eval x (EVALM y : c)

{-
    The eval function takes an expression (Expr) and a continuation (Cont) \
    and returns an integer result. Its purpose is to break down complex expressions 
    into simpler operations that can we use with the exec function.

    For simple values (Val n), it just passes the value to exec.
    For compound expressions (Add, Subt, Mult), it does two things:
    a. It evaluates the left subexpression first.
    b. It adds an operation to the continuation to evaluate the right subexpression later.

-}
exec :: Cont -> Int -> Int
-- Give seven definitions for exec, one for an empty list and
-- one for each of the six constructors of the data type Op
-- Some of these are already given in the text Section 8.7.
exec [] n = n
exec (EVALA y : c) n = eval y (ADD n : c)
exec (ADD n : c) m = exec c (n + m)
exec (EVALS y : c) n = eval y (SUBT n : c)
exec (SUBT n : c) m = exec c (n - m)
exec (EVALM y : c) n = eval y (MULT n : c)
exec (MULT n : c) m = exec c (n * m)

{-
    The exec function takes a Cont and an integer, and returns an integer result. 
    It actually perform sthe calculations set up by eval.

    eval breaks down the expression and builds up the continuation.
    exec then processes and find sthe final result.
    They call each other recursively, eval calls exec when it reaches a simple value, 
    and exec calls eval when it needs to actually evaluate.
-}

value :: Expr -> Int
value e = eval e []

-- Following expressions are to test your eval and exec definitions
-- (2 + 3) + 4 = 9
e1 = (Val 3)    -- 3
e2 = (Add (Val 4) (Val 2))  -- 4 + 2 = 6
e3 = (Mult (Val 4) (Val 3))  -- 4 * 3 = 12
e4 = (Add (Subt (Val 5) (Val 3)) (Val 4))  -- (5 - 3) + 4 = 6
e5 = (Mult (Mult (Val 2) (Val 3)) (Val 4))  -- (2 * 3) * 4 = 24
e6 = (Mult (Add (Val 2) (Val 3)) (Val 4))  -- (2 + 3) * 4 = 20
e7 = (Mult (Subt (Val 3) (Val 1)) (Val 4))  -- (3 - 1) * 4 = 8
e8 = (Add (Mult (Val 2) (Val 3)) (Val 4))  -- (2 * 3) + 4 = 10
e9 = (Subt (Mult (Val 4) (Val 5)) (Add (Val 2) (Val 3))) -- (4 * 5) - (2 + 3) = 15
e10 = (Mult (Subt (Val 10) (Val 3)) (Add (Val 4) (Val 5))) -- (10 - 3) * (4 + 5) = 63
e11 = (Add (Mult (Add (Val 2) (Val 3)) (Mult (Val 4) (Val 5))) (Mult (Val 3) (Subt (Val 4) (Val 7)))) -- ((2 + 3) * (4 * 5)) + (3 * (4 - 7)) = 91


-- Problem 5 (15 points)
-- Show the step-by-step of the following application of value.
-- > value e9
{-- Your answer goes here. Your answer must be in detail step-by-step showing
    every function call according to your implementation.

    Start with value e9 = eval e9 []
    expand  e9: eval (Subt (Mult (Val 4)(Val 5)) (Add (Val 2)(Val 3))) []
    eval the Subt: eval (Mult (Val 4) (Val 5)) [EVALS (Add (Val 2)(Val 3))]
    eval the Mult expression: eval(Val 4) [EVLM (Val 5), EVALS (Add (Val 2) (Val 3))]
    eval Val 4: exec [EVALM (Val 5), EVALS (Add (Val 2) (Val 3))] 4
    execute EVALM: eval (Val 5)[MULT 4, EVALS (Add (Val 2) (Val 3))]
    eval Val 5: exec [MULT 4,EVALS (Add (Val 2) (Val 3))] 5
    execute  MULT: exec [EVALS (Add (Val 2)(Val 3))] 20
    execute  EVALS: eval (Add (Val 2) (Val 3)) [SUB 20]
    eval the Add express.: eval (Val 2) [EVALA (Val 3),SUBT 20]
    eval Val 2: exec [EVALA (Val 3), SUBT 20] 2
    EVALA: eval (Val 3) [ADD 2, SUBT 20]
    Val 3: exec [AD 2, SUBT 20] 3
    ADD: exec [SUBT 20] 5
    SUBT: exec [] 15

    Final result: 15
--}



myTestList = 
  TestList [
  
    "preorder 1"  ~: (concat (preorder show id tree1)) ~=? "*+*51+26*+*849+73"
  , "inorder 1"   ~: (concat (inorder show id tree1))  ~=? "5*1+2+6*8*4+9*7+3"
  , "postorder 1" ~: (concat (postorder show id tree1)) ~=? "51*26++84*9+73+**"
  , "preorder 2"  ~: (concat (preorder show id tree2)) ~=? "+1*23"
  , "inorder 2"   ~: (concat (inorder show id tree2))  ~=? "1+2*3"
  , "postorder 2" ~: (concat (postorder show id tree2))  ~=? "123*+"
  , "preorder 3"  ~: (concat (preorder show id tree3)) ~=? "+*34+*521"
  , "inorder 3"   ~: (concat (inorder show id tree3))  ~=? "3*4+5*2+1"
  , "postorder 3" ~: (concat (postorder show id tree3))  ~=? "34*52*1++"

  , "value 1"  ~: value e1 ~=? 3
  , "value 2"  ~: value e2 ~=? 6
  , "value 3"  ~: value e3 ~=? 12
  , "value 4"  ~: value e4 ~=? 6
  , "value 5"  ~: value e5 ~=? 24
  , "value 6"  ~: value e6 ~=? 20
  , "value 7"  ~: value e7 ~=? 8
  , "value 8"  ~: value e8 ~=? 10
  , "value 9"  ~: value e9 ~=? 15
  , "value 10" ~: value e10 ~=? 63
  , "value 11" ~: value e11 ~=? 91
  
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

