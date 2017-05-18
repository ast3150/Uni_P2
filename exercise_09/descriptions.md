# Exercise 9

##task 9.1
We used the examples from the Exercise file and the pool hour. Our service locator uses the DefaultServiceLocator as the name says for the default game and in test case the TestServiceLocator. For having two different Renderer we implemented an Interface IRenderer.

##task 9.2
We created a new Parser Interface to avoid dependencies in the Driver class.

##task 9.3
We used the service locator and implemented a new method getPrintStream() wich sets the PrintStream to System.out in the game case.
If we are testing the programm uses a File Output Stream to test. The SilentRenderer does the Job for us.

We've made two branches: one for the dependency injection and one for the service locator, for the tests we used the service locator so 9.3 builds up on 9.1.