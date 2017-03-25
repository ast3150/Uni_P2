# Exercise 3 Corrections

The following comments correspond to the tasks listed in `exercise_03.md`.

## Implement the specified commands

You provide classes for each command. Your use of reflective capabilities for 
the parsing stage is interesting. However, it makes things rather difficult to 
read and there is no real need for using reflection for this tasks; there are 
plenty of other ways to design an elegant and efficient way to do this. 
Nevertheless, this is an interesting approach.


## Split parsing and execution of programs in separate classes / objects

Although you do the parsing within the individual command classes, you 
nevertheless split the two steps nicely by first using the `parseFromString` 
methods to collect all instances of `ICommand`, and only then calling 
`executeOn` on each constructed command.


## Explicitly state pre- and postconditions

Not done.


## Check pre- and postconditions using the `assert` keyword

Not done.


## Check class invariants

Not done.


## Deal with all input programs

Works.


## Draw UML diagrams

The class diagram seems to be ok, but the sequence diagram is very minimal.


# Status

Fail.

Considering that design by contract was the main topic of this exercise, you 
fail the exercise as there is nothing present in that regard.
