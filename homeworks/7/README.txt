
/* Written by Hyunyoung Lee for CSCE 314 Students Homework 7 Problem 1

   Student Name: Cameron Stone
   UIN: 832007843
   Acknowledgements: N/a
*/

===================
=====   Part 1   ======
===================

=== Problem 2
To compile: javac Market.java Main.java
To run: java Main

Expected output:
Here's what I bought
Apple
Gala
Apple
Apple
Gala
Fruit
Enjoy!


=== Problem 3
To compile: javac SimBox.java SimMain.java
To run: java SimMain

2 example outputs:

From Homer to Marge: My doctor said don't walk.
From Marge to Homer: That was a traffic signal!
From Bart to Homer: There?s a 4:30 in the morning now?
From Homer to Bart: D'oh!
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...

From Homer to Marge: My doctor said don't walk.
From Marge to Homer: That was a traffic signal!
From Bart to Homer: There?s a 4:30 in the morning now?
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Bart to Homer: flooding the message queue...
From Homer to Bart: D'oh!

The outputs differ because the messages are processed by multiple 
threads running concurrently. the timing of when each thread 
processes messages can vary between runs, leading to
different orderings of msges.

===================
=====   Part 2   ======
===================

in Market, wildcards allow for more 
flexible type relationships. e.g. for the sell method, using 
Collection<? extends T> allows us to accept collections containing T 
or any subtype of T. This means a Market<Fruit> can accept collections 
of Fruits, Apples, or Galas. For the buy method, Collection<? super T> 
ensures the provided collection can hold our stock items - a Market<Apple> 
can put items into collections of Apple, Fruit, or Object.

Without wildcards, we would be restricted to exact type matches. 
A Market<Fruit> could only sell from Collection<Fruit> and buy into 
Collection<Fruit>, thiswould prevent selling a Collection<Apple> to 
a Market<Fruit> or buying from a Market<Apple> into a Collection<Fruit>, 
which really limits the flexibility of the class. that is why we use
wildcards!


===================
=====   Part 3   ======
===================


Synchronization of the message queues is necessary because multiple 
SimBox instances (Homer, Marge, and Bart) are concurrently accessing 
the shared messages queue, and w/o synchronization, concurrent 
changes could lead tolost messages or corrupted data.
e.g. while Bart is flooding homer's queue with messages, Homer's
SimBox is simultaneously trying to move his messages to mymessages. 
without synchronization, some messages might be lost or processed 
multiple times. we don't want that!

my implementation avoids deadlock by always acquiring locks in a 
consistent order: messages first, then myMessages. whenwe need both 
locks e.g. in the run() method, we always acquire them in this same order. 
This prevents circular wait conditions where one thread holds the messages
lock and waits for myMessages while another thread holds myMessages and
waits for messages. if wefp;;pw this strict ordering, we ensure that
circular dependencies cannot occur, which prevents deadlocks!



