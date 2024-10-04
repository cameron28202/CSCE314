

fz xs = sum [ x | (z, x) <- zip [1..] xs, z == x]

safetailCond :: [a] -> [a]
safetailCond xs = if null xs  then [] else tail xs

safetailGuard :: [a] -> [a]
safetailGuard xs
    | null xs  = []
    | otherwise = tail xs

safetailPattern :: [a] -> [a]
safetailPattern [] = []
safetailPattern (_:xs) = xs


eq3 = ("Howdy,":"all":[])
-- type: [Char]

--eq4
f x = x `div` 2
-- type: Integral a => a -> a

--eq5
-- \x y -> x <= y
-- type: Ord => a -> a -> Bool

--eq6
xs = [3, 2, 1]
ans = zip xs (tail xs)
-- zip [3, 2, 1] [2, 1]
-- ans = [(3, 2), (2, 1)]

--eq7
g = filter even . map (+1)

-- a) what is the type of g?

-- [Integer] -> [Integer]

-- b) what is the result of g [1..5]?

-- first you apply map (+1) -> [2, 3, 4, 5, 6]
-- then apply filter even -> [2, 4, 6]
-- ans: [2, 4, 6]

--eq8
data Person = Person String Int

-- What is the type of constructor Person?
-- String -> Int -> Person



