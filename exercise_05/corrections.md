# Exercises 5 and 6 Corrections

Overall, I really like your solution. You have a good class layout and split 
classes by their responsibilities. You wrote nice JavaDoc where 
appropriate/needed, and you use assertions to check contracts, well done! 
There isn't really that much I can complain about, you should be set up nicely 
for exercise 7. Nevertheless, I will write a couple of comments on the 
individual parts in the following sections.

## Parser

Your parser is very clean, you have methods for the individual parts of the 
specification. I like that your main parsing method takes a string and not a 
file, but you also provide a helper method for parsing files directly.

The methods tend to be rather long. In some cases, this is not entirely 
avoidable, but in others it may read better if you further split them up. But 
in the case of your parser, I don't really see too much room for improvement 
there...

You have thorough tests for the parser that cover everything and are 
reasonable from a functional standpoint. Good job testing both scenarios with 
valid and invalid input. I also like that you mention that you need the 
project root set as working directory in the test where you depend on the 
file.


## Renderer

The renderer is straightforward, I guess there's not much to comment here. The 
tests are nice as well, but maybe you could have mocked some more things, but 
it's also ok like this, as long as the tests are compact and do not depend on 
too much complicated code that is not under test.


## Game/Player

Again, short and clear methods, well done.

In the tests, you have `for` loops to test all tiles. You could avoid this 
with the `assertArrayEquals` method, which essentially achieves the same 
thing.

You implemented tile types with a single class with flags for the different 
types (well, with one flag `isWall`). This is ok-ish, but I find it nicer to 
have a separate class here. Consider the case where you have more tile types. 
Then, your `Tile` class blows up and gets overly complicated. Already in the 
current implementation, you have to distinguish cases, for example, in the 
`toString` method. A class `WallTile` could simply override the `toString` and 
`isWall` methods and does not require checking a particular flag.

**I suggest to have separate classes for the different tiles in exercise 7, as 
you may want to somehow represent the goal tiles (lowercase letters in the 
input format) differently with altered behaviour.**


# Status

Wow.

A very nice solution with little to complain about. Good job!
