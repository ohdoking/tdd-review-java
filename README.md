# TDD Review In Java

## The red-green-refactor process

1. Write a test
2. Run all the tests and confirm that the last one is failing
3. Write the implementation code
4. Run all the tests
5. Refactor(Optional)
6. Repeat

## Test Kind

- Unit tests tests try to verify small units of functionality
- Functional and acceptance tests have a job to verify that the application we're building works as expected, as a whole.
- Integration tests intend to verify that separate units, modules, applications, or even whole systems are properly integrated with each othe

## JaCoCo

Java Code Coverage (JaCoCo) is a well-known tool for measuring test coverage.

### How to use Jacoco

add *build.gradle*

```
apply plugin: 'jacoco'
// need to over JDK 11(https://github.com/vaskoz/core-java9-impatient/issues/11)
jacoco {
    toolVersion = "0.8.2"
}
```


![JaCoCo](https://user-images.githubusercontent.com/4298268/52576042-a6dee480-2e1f-11e9-98ea-2bfa5b94f47a.png)

## Design principles

### You Ain't Gonna Need It

YAGNI is the acronym for the You Ain't Gonna Need It principle.
It aims to erase all unnecessary code and focuses on the current functionalities, not the future ones.
The less code you have, the less code you're going to maintain and the less probability that bugs are introduced.

### Don't Repeat Yourself

The idea behind the Don't Repeat Yourself (DRY) principle is to reuse the code you previously wrote instead of repeating it.
The benefits are less code to maintain and the use of code that you know that already works, which is a great thing.
It helps you to discover new abstraction levels inside your code.

### Keep It Simple, Stupid

This principle has the confusing acronym of KISS and states that things perform their function better if they are kept simple rather than complicated.
It was coined by Kelly Johnson.

### Occam's Razor
Although Occam's Razor is not a software engineering principle but a philosophical one, it is still applicable to what we do, and is very similar to the previous principle with the main derived statement being as follows:

```
    "When you have two competing solutions to the same problem, the simpler one is the better."
```

### SOLID

The word SOLID is an acronym invented by Robert C.
Martin for the five basic principles of object-oriented programming.
By following these five principles, a developer is more likely to create a great, durable, and maintainable application:

• Single Responsibility Principle: A class should have only a single reason to change.
• Open-Closed Principle: A class should be open for extension and closed for modification. This is attributed to Bertrand Meyer.
• Liskov Substitution Principle: This was created by Barbara Liskov, and she says a class should be replaceable by others that extend that class.
• Interface Segregation Principle: A few specific interfaces are preferable than one general-purpose interface.
• Dependency Inversion Principle: A class should depend on abstraction instead of implementation. This means that class dependencies must be focused on what is done and forget about how it is done.

## MOCKING

### TERM

Test doubles is a generic name for all of the following types:
• Dummy object's purpose is to act as a substitute for a real method argument
• Test Stub can be used to replace a real object with a test-specific object that feeds the desired indirect inputs into the system under test
• Test spy captures the indirect output calls made to another component by the system under test (SUT) for later verification by the test
• Mock Object replaces an object the system under test (SUT) depends on, with a test-specific object that verifies that it is being used correctly by the SUT
• Fake object replaces a component that the system under test (SUT) depends on with a much lighter-weight implementation

### Mockito

Mockito is a mocking framework with a clean and simple API.

#### METHOD

• mock(): This is used to create mocks. Optionally, we can specify how those mocks behave with when() and given().
• spy(): This can be used for partial mocking. Spied objects invoke real methods unless we specify otherwise. As which mock(), behavior can be set for every public or protected method (excluding static). The major difference is that mock() creates a fake of the whole object, while spy() uses the real object.
• verify(): This is used to check whether methods were called with given arguments. It is a form of assert.


## TIP

### Use descriptive names for test methods

One of the benefits is that it helps to understand the objective of tests.
Using method names that describe tests is beneficial when trying to figure out why some tests failed or when the coverage should be increased with more tests. It should be clear what conditions are set before the test, what actions are performed, and what the expected outcome is.
There are many different ways to name test methods. My preferred method is to name them using the given/when/then syntax used
in BDD scenarios. Given describes (pre)conditions, When describes actions, and Then describes the expected outcome. If a test does not have preconditions (usually set using the @Before and @BeforeClass annotations), Given can be skipped.
Do NOT rely only on comments to provide information about test objectives. Comments do not appear when tests are executed from your favorite IDE, nor do they appear in reports generated by the CI or build tools.

### Write the test before writing the implementation code

The benefits of doing this are as follows: it ensures that testable code is written and ensures that every line of code gets tests written for it.
By writing or modifying the test first, the developer is focused on requirements before starting to work on a code. This is
the main difference when compared to writing tests after the implementation is done. An additional benefit is that with tests first, we are avoiding the danger that the tests work as quality checking instead of quality assurance.

### Rerun all the tests every time the implementation code changes.

    This ensures that there is no unexpected side-effect caused by code changes.
    Every time any part of the implementation code changes, all tests should be run. Ideally, tests are fast to execute and can be run by a developer locally. Once code is submitted to the version control, all tests should be run again to ensure that there was no problem due to code merges. This is especially important when more than one developer is working on the code. Continuous Integration tools such as Jenkins, Hudson, Travind, Bamboo and Go-CD should be used to pull the code from the repository, compile it, and run tests.

### Write the simplest code to pass the test. This ensures a cleaner and clearer design and avoids unnecessary features

    The idea is that the simpler the implementation, the better and easier it is to maintain the product.
    The idea adheres to the KISS principle. It states that most systems work best if they are kept simple rather than made complex; therefore, simplicity should be a key goal in design and unnecessary complexity should be avoided.

### Refactor only after all the tests have passed.

    Benefits: refactoring is safe

    If all the implementation code that can be affected has tests and if they are all passing, it is relatively safe to refactor.
    In most cases, there is no need for new tests;
    small modifications to existing tests should be enough.
    The expected outcome of refactoring is to have all the tests passing both before and after the code is modified.

### Using mocks

    Benefits include reduced code dependency and faster tests execution.

    Mocks are prerequisites for the fast execution of tests and the ability to concentrate on a single unit of functionality.
    By mocking dependencies external to the method that is being tested, the developer is able to focus on the task at hand without spending time setting them up.
    In a case of bigger or multiple teams working together, those dependencies might not even be developed.
    Also, execution of tests without mocks tends to be slow.
    Good candidates for mocks are databases, other products, services, and so on.

### Use setup and teardown methods

    Benefits: these allow preparation or setup and disposal or teardown code to be executed before and after the class or each test method.

    In many cases, some code needs to be executed before the test class or each method in a class.
    For this purpose, JUnit has the @BeforeClass and @Before annotations that should be used in the setup phase.
    The @ BeforeClass executes the associated method before the class is loaded (before the first test method is run).
    @Before executes the associated method before each test is run. Both should be used when there are certain preconditions required by tests.
    The most common example is setting up test data in the (hopefully in-memory) database.
    On the opposite end, are the @After and @AfterClass annotations that should be used as the teardown phase.
    Their main purpose is to destroy the data or state created during the setup phase or by tests themselves.
    Each test should be independent from others. Moreover, no test should be affected by the others.
    The teardown phase helps maintaining the system as if no test was previously executed.

### Tests should run fast

    Benefits: tests are used often

    If it takes a lot of time to run tests, developers will stop using them or run only a small subset related to the changes they are making.
    One benefit of fast tests, besides fostering their usage, is fast feedback.
    The sooner the problem is detected, the easier it is to fix it.
    Knowledge about the code that produced the problem is still fresh.
    If a developer has already started working on the next feature while waiting for the completion of the execution of tests, he might decide to postpone fixing the problem until that new feature is developed.
    On the other hand, if he drops his current work to fix the bug, time is lost in context switching.

### All tests should pass before a new test is written

    Benefits: focus is maintained on a small unit of work, and implementation code is (almost) always in a working condition.

    It is sometimes tempting to write multiple tests before the actual implementation.
    In other cases, developers ignore problems detected by the existing tests and move towards new features.
    This should be avoided whenever possible.
    In most cases, breaking this rule will only introduce technical debt that will need to be paid with interest.
    One of the goals of TDD is ensuring that the implementation code is (almost) always working as expected.
    Some projects, due to pressures to reach the delivery date or maintain the budget, break this rule and dedicate time to new features, leaving the fixing of the code associated with failed tests for later.
    Those projects usually end up postponing the inevitable.