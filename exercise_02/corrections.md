# Exercise 2 Corrections

You missed the deadline for this exercise. Since it is only the second 
exercise, I decided to look at the commits that came after the deadline as 
well. Note that this is en exception and we will not consider any more commits 
after the deadline in future exercises.

The following comments correspond to the tasks listed in `exercise_02.md`.

1. You added straightforward implementations for all required fields.

2. You did implement the tests as required.

3. Your implementation passes the provided tests.

4. You did include the new squares in the `Game.main()` method.

5. Your JavaDoc comments are decent. You often repeat the same sentences 
   twice, once as the first one, and once more in the `@return` line. You 
   could rephrase this, for example, you could write something like this:
```
/**
 * Tests whether the square is the first on the board.
 *
 * @return true if the square is the first square on the game board, false 
 *         otherwise
 */
public boolean isFirstSquare();
```

6. Your use a lot of filler words and sentences in the class comments. A 
   sentence like "This is the rollAgain class" is completely useless as a 
   summary. You also write things like "The methods are inherited from Square 
   ..." in various classes, which again, is useless. Both these things are 
   clear from the class definition (that is, the first line of the class code, 
   for example, `public class RollAgainSquare extends Square`). Also, `@param` 
   statements are for method parameters and do not belong in the class comment 
   like this. Describe the data on a more abstract level (for example, write 
   something like "Keeps track of the game the instance belongs to.".

   You need to rewrite your comments. Make sure to fix the issues I mentioned 
   and make sure that you follow the styleguide from the mandatory reading 
   material.

## Status

Revise.

You solved most of the tasks, but your class comments are not good. Fix them 
before **Thursday, 16 March, 13:00** according to the comments above, the 
mandatory reading material, and the presented examples in the presentations. 
You also need to write a JavaDoc class/interface comment for `ISquare`.

### Update 25 March

Accepted (ok).

Your fixes are very minimal and mostly bad. The comment for `ISquare` is just 
horrible. Nevertheless, since you at least wrote *something*, you will get a 
very generous "ok".
