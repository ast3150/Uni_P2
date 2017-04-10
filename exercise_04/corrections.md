# Exercise 4 Corrections

The following comments correspond to the tasks listed in `exercise_04.md`.

## Create an IDie interface and tests that use Mockito and MockDie

Overall, this looks good. One minor comment: Your `IDie` interface comment is 
wrong; the interface does not represent die behaviour for rolling random 
numbers. For example, the `MockDie` is not random.


## Write a short paragraph about mocking approaches

I agree with your points.


## Write tests for all squares in your game

Looks ok at a first glance. However, you missed some squares. You do not seem 
to have tests for `FirstSquare` and `LastSquare`.


## Use a coverage tool and if you do not cover everything, state why

You cover most of the code, but there are some methods that are not covered. 
Why?


# Status

Ok.

Most of the things are there, and since this exercise was due a while ago, I 
do not ask you to fix the remaining things here. In the future, however, you 
should make sure to carefully read all tasks and make sure you implement them. 
If you can not do some of the tasks, write a comment somewhere so we can see 
why.
