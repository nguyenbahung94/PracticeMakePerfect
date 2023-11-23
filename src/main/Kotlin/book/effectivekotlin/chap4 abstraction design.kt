package book.effectivekotlin

/*Similarly, in programming, we use abstractions mainly to:
• Hide complexity
• Organize our code
• Give creators the freedom to change
This is a general rule - functions should be small and have a minimal number of responsibilities
 Extracting constant
• Wrapping behavior into a function
• Wrapping function into a class
• Hiding a class behind an interface
• Wrapping universal objects into specialistic
search for a balance in every project. Having too many or too little abstractions would not be an ideal situation.
in programming, we much prefer stable and possibly standard Application Programming Interfaces (API)
in this system, we compose version number from 3 parts: MAJOR.MINOR.PATCH
 - MAJOR version when you make incompatible API changes.
 - MINOR version when you add functionality in a backward-compatible manner.
 - PATCH version when you make backward-compatible bug fixes.
 When we increment MAJOR, we set MINOR and PATCH to 0. When we increment MINOR we set PATCH to 0
 Minimize elements visibility
 these are 4 visibility modifiers we can use together with their behavior:
• public (default) - visible everywhere, for clients who see the declaring class.
• private - visible inside this class only.
• protected - visible inside this class and in subclasses.
• internal - visible inside this module, for clients who see the declaring class.
have 3 visibility modifiers:
• public (default) - visible everywhere.
• private - visible inside the same file only.
• internal - visible inside this module.
The rule of thumb is that: Elements visibility should be as restrictive as possible
The general problem is that when the behavior is not documented and the element name is not clear, developers will depend on current implementation instead of on the abstraction we intended to create.
 We solve this problem by describing what behavior can be expected.
 Defining a contract:
    - Names
    - Comments and documentation - the most powerful way as it can describe everything that is needed.
    - Types : When we see a function, information about return type and arguments types are very meaningful
 */