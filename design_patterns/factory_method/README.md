# Factory Method Pattern

## UML Diagram

```mermaid
classDiagram
    %% Abstract Creator
    class PizzaStore {
        <<abstract>>
        +createPizza(type) Pizza
        +orderPizza(type) Pizza
    }
    
    %% Concrete Creators
    class NYPizzaStore {
        +createPizza(type) Pizza
    }
    
    class ChicagoPizzaStore {
        +createPizza(type) Pizza
    }
    
    class CaliforniaPizzaStore {
        +createPizza(type) Pizza
    }
    
    %% Abstract Product
    class Pizza {
        <<abstract>>
        #name String
        #dough String
        #sauce String
        #toppings ArrayList
        +prepare() void
        +bake() void
        +cut() void
        +box() void
        +getName() String
    }
    
    %% Concrete Products - NY Style
    class NYStyleCheesePizza {
        +NYStyleCheesePizza()
    }
    
    class NYStylePepperoniPizza {
        +NYStylePepperoniPizza()
    }
    
    class NYStyleClamPizza {
        +NYStyleClamPizza()
    }
    
    class NYStyleVeggiePizza {
        +NYStyleVeggiePizza()
    }
    
    %% Concrete Products - Chicago Style
    class ChicagoStyleCheesePizza {
        +ChicagoStyleCheesePizza()
        +cut() void
    }
    
    class ChicagoStylePepperoniPizza {
        +ChicagoStylePepperoniPizza()
        +cut() void
    }
    
    class ChicagoStyleClamPizza {
        +ChicagoStyleClamPizza()
        +cut() void
    }
    
    class ChicagoStyleVeggiePizza {
        +ChicagoStyleVeggiePizza()
        +cut() void
    }
    
    %% Relationships
    PizzaStore <|-- NYPizzaStore
    PizzaStore <|-- ChicagoPizzaStore
    PizzaStore <|-- CaliforniaPizzaStore
    
    Pizza <|-- NYStyleCheesePizza
    Pizza <|-- NYStylePepperoniPizza
    Pizza <|-- NYStyleClamPizza
    Pizza <|-- NYStyleVeggiePizza
    
    Pizza <|-- ChicagoStyleCheesePizza
    Pizza <|-- ChicagoStylePepperoniPizza
    Pizza <|-- ChicagoStyleClamPizza
    Pizza <|-- ChicagoStyleVeggiePizza
    
    PizzaStore ..> Pizza : creates
```

## Factory Method Pattern Explanation

The Factory Method Pattern defines an interface for creating an object, but lets subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses.

### Key Components

1. **Abstract Creator (PizzaStore)**: Declares the factory method that returns an object of the product type.
2. **Concrete Creators (NYPizzaStore, ChicagoPizzaStore)**: Override the factory method to return a specific concrete product.
3. **Abstract Product (Pizza)**: Defines the interface for products created by the factory method.
4. **Concrete Products (NYStyleCheesePizza, ChicagoStyleCheesePizza, etc.)**: Implement the abstract product interface.

### Implementation

In the pizza store example from Head First Design Patterns:

- The `PizzaStore` abstract class defines the `createPizza()` factory method
- Subclasses like `NYPizzaStore` and `ChicagoPizzaStore` implement the factory method to create region-specific pizzas
- All pizzas share the same interface defined by the `Pizza` abstract class
- Concrete pizza classes implement specific styles for each region
