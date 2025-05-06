# Strategy Pattern

## UML Diagram

```mermaid
classDiagram
    %% Context
    class Duck {
        #flyBehavior FlyBehavior
        #quackBehavior QuackBehavior
        +Duck()
        +swim() void
        +display() void*
        +performFly() void
        +performQuack() void
        +setFlyBehavior(FlyBehavior) void
        +setQuackBehavior(QuackBehavior) void
    }
    
    %% Concrete Context Classes
    class MallardDuck {
        +MallardDuck()
        +display() void
    }
    
    class RedheadDuck {
        +RedheadDuck()
        +display() void
    }
    
    class RubberDuck {
        +RubberDuck()
        +display() void
    }
    
    class DecoyDuck {
        +DecoyDuck()
        +display() void
    }
    
    class ModelDuck {
        +ModelDuck()
        +display() void
    }
    
    %% Strategy Interfaces
    class FlyBehavior {
        <<interface>>
        +fly() void
    }
    
    class QuackBehavior {
        <<interface>>
        +quack() void
    }
    
    %% Concrete Flying Strategies
    class FlyWithWings {
        +fly() void
    }
    
    class FlyNoWay {
        +fly() void
    }
    
    class FlyRocketPowered {
        +fly() void
    }
    
    %% Concrete Quacking Strategies
    class Quack {
        +quack() void
    }
    
    class Squeak {
        +quack() void
    }
    
    class MuteQuack {
        +quack() void
    }
    
    %% Relationships
    Duck <|-- MallardDuck
    Duck <|-- RedheadDuck
    Duck <|-- RubberDuck
    Duck <|-- DecoyDuck
    Duck <|-- ModelDuck
    
    Duck o-- FlyBehavior
    Duck o-- QuackBehavior
    
    FlyBehavior <|.. FlyWithWings
    FlyBehavior <|.. FlyNoWay
    FlyBehavior <|.. FlyRocketPowered
    
    QuackBehavior <|.. Quack
    QuackBehavior <|.. Squeak
    QuackBehavior <|.. MuteQuack
```

## Strategy Pattern Explanation

The Strategy Pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

### Key Components

1. **Context (Duck)**: Maintains a reference to a Strategy object and delegates to it instead of implementing the behavior directly.
2. **Strategy Interface (FlyBehavior, QuackBehavior)**: Defines an interface common to all supported algorithms.
3. **Concrete Strategies (FlyWithWings, FlyNoWay, Quack, Squeak, etc.)**: Implements the algorithm using the Strategy interface.

### Implementation

In the Duck example from Head First Design Patterns:

- `Duck` is the abstract base class that delegates flying and quacking behavior to strategy objects
- Each concrete duck (MallardDuck, RubberDuck, etc.) can be instantiated with different behavior implementations
- `FlyBehavior` and `QuackBehavior` are the strategy interfaces
- Concrete implementations like `FlyWithWings`, `FlyNoWay`, `Quack`, and `MuteQuack` provide specific behaviors
- Behaviors can be changed at runtime using the setter methods in the Duck class

### Benefits

- Eliminates conditional statements for different behaviors
- Makes it easy to add new behaviors without changing existing code
- Allows behavior switching at runtime
- Isolates the behavior implementation from the classes that use it
