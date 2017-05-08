# Exercises 7 Corrections

Your design stayed good and I like your solutions to the new tasks. Here are a 
few remarks.

- You replaced your existing parser with one that handles the new format. A 
  better way would have been to add a `Parser` interface and then provide two 
  implementations, one for each format. This way, you could easily swap 
  parsers and, for example, even implement a strategy for detecting the 
  correct format by trying all parsers one after another.

- Good tests, well done.

- Very nice documentation and clean code, good job!


# Status

Wow.

Again a very nice solution, but after your submissions of exercises 5 and 6 
this was what I expected from you. Well done!
