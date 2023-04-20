# Documentation
This document serves as documentation to the Library Management System source code.

## Table of Contents
1. [State Manager](#state-manager)
   - [State](#state)
      - [Option State](#option-state)
      - [Input State](#input-state)
      - [Action State](#action-state)
   - [Example](#example---adding-a-new-book-to-the-database)
2. [Api](#api)
   - [Response](#response)
   - [Example](#example---get-all-books-from-the-database)
5. [Library Model](#library-model)
   - [User Model](#user-model)
   - [Book Model](#book-model)
   - [Order Model](#order-model)
9. [Hashing Using MD5](#hashing-using-md5)
   - [Hash Generation](#hash-generation)
   - [ID Generation](#id-generation)

## State Manager
The application is an interactive console application which consists of many actions and states.
Therefore, it requires a proper state management.
For this purpose, I opted for a tree structure where each node represents one state and the user can move between the stages by moving from one node to another.

The `StateManager` is a simple class, holding the current state and modifying its value.
```java
public class StateManager {

    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
```
The main `App` component utilizes the `StateManager` by reading the user input and with each new input, it moves onto the next state in the `StateManager`.
```java
public class App {

    private interface CommandExecutor {
        void execute(String command);
    }
    
    public static StateManager manager;
    
    public void start() {
        manager.getState().ask();
        
        CommandExecutor executor = (line) -> {
            State next = manager.getState().process(line);
            manager.setState(next);
            manager.getState().ask();
        };       
    }
}
```

### State
the `State` class is an abstract class representing a state.
It has four main properties, upon which it operates.
```java
public abstract class State {

    private String command;
    private String message;

    private State[] children;
    private State callback;
}
```
- `command` - a value (user input) using which the state can be accessed
- `message` - a message that the state echoes when triggered
- `children` - an array of child states
- `callback` - a reference to a **fail** state - a state which to go to when the current state fails

The `State` class consists of two main abstract methods implemented in each state individually.
```java
public abstract class State {
    
    public abstract State next(String command);

    public abstract void ask();
}
```
- `next` - returns the next state to go to based on the user input command
- `ask` - echoes the state's message

This `State` class serves as a state template and is used to implement the three main state types
- [OptionState](#option-state)
- [InputState](#input-state)
- [ActionState](#action-state)

### Option State
This state represents a transition state, from which the user can navigate to any of its child states.
It supports no particular custom action, rather serves as a transition.

It implements the `next` method in a way that it loops through its children and moves to a child with the corresponding user command.
```java
public abstract class OptionState extends State {
    
    @Override
    public State next(String command) {
        for (State child : getChildren()) {
            if (child.getCommand().equals(command)) {
                return child;
            }
        }

        Console.println("Command not found");
        return this;
    }
}
```
It also implements the `ask` method by printing the current breadcrumbs, message and available child states.
```java
public abstract class OptionState extends State {
    
    @Override
    public void ask() {
        Console.divider();
        Console.println(getBreadcrumbs());
        Console.print(getMessage() + " " + getOptions() + ": ");
    }
}
```

### Input State
Before understanding the `ActionState`, we first need to understand the `InputState`, since these two work hand in hand.

The `InputState` does nothing in particular. It only takes the user input and stores it in the parent `ActionState`.
The storing process of the `InputState` input value is implemented in the `ActionState`, rather than in `InputState`.
Therefore, the `next` method of the `InputState` is implemented by calling the parent `ActionState` and triggering its `next` method.

```java
public abstract class InputState extends State {
    
    @Override
    public State next(String command) {
        return ((ActionState) getCallback()).nextChild(command);
    }
}
```

### Action State
This state represents a state where a custom action is executed after its successful walk through.
Its children are of type `InputState`, whose inputs are stored in the `ActionState` and are processed after successful retrieval of all user inputs.

It implements the `next` method in the following way.
```java
public abstract class ActionState extends State {
    
    @Override
    public State next(String command) {
        if (currentStateIndex == 0) {
            return nextChild(command);
        }

        return getChildren()[currentStateIndex].next(command);
    }
}
```
It triggers the child's `next` method, which triggers the `ActionState` `nextChild` method implemented in the following way.
```java
public abstract class ActionState extends State {

    public State nextChild(String command) {
        if (values == null) {
            execute();
            currentStateIndex = 0;
            return getCallback();
        }

        values[currentStateIndex] = command;

        if (isAtEnd()) {
            execute();
            currentStateIndex = 0;
            return getCallback();
        }

        return getChildren()[currentStateIndex++];
    }
}
```
If the `values` variable is `null`, meaning the `ActionState` has no children, it executes its action and returns to its parent state.
Otherwise, it saves the child's value.

If the current child is its last child, it also executes its action and returns to its parent state.
Otherwise, it moves to the next `InputState` child by calling `return getChildren()[currentStateIndex++]`.

In this way, the action state goes through all its `InputState` children, retrieves their values from the user input and performs a custom action with these values.
Therefore, it implements the following abstract method `execute`.
```java
public abstract class ActionState extends State {

    public abstract void execute();
}
```
This method is mainly used for adding new entries to the database, updating current database entries or deleting database entries by their keys or properties.

With these three state types defined, we can build a state tree, which the user can navigate and perform corresponding actions.

### Example - Adding a New Book to the Database
Below is the implementation of adding a new book to the database using a parent `ActionState` and its `InputState` children.
```java
public class AddBook extends ActionState {

    @Override
    public void execute() {
        String title = getValues()[1];
        String description = getValues()[2];
        String author = getValues()[3];
        String stock = getValues()[4];
        
        // check format of the values if needed

        Response response = App.api.books().add(new Book(title, description, author, Integer.parseInt(stock)));
        Console.println(response.getMessage());
    }
}

class AddBookTitle extends InputState {}
class AddBookDescription extends InputState {}
class AddBookAuthor extends InputState {}
class AddBookStock extends InputState {}
```
The `InputState` children do not need any custom handling, since they are only reading the user input.

These states are then added to the state tree in the `StateManager` class in the following way.
```java
public class StateManager {
    
    private State buildAdminStates() {
        State home = new main.state.nodes.admin.Home("home", "What do you want to do");
        
        State add = new Add("add", "What do you want to add", home);
        
        // ...

        State addBook = new AddBook("book", "You are about to add a new book, type anything to continue", add);
        State addBookTitle = new AddBookTitle("title", "Enter book title", addBook);
        State addBookDescription = new AddBookDescription("description", "Enter book description", addBook);
        State addBookAuthor = new AddBookAuthor("author", "Enter book author", addBook);
        State addBookStock = new AddBookStock("stock", "Enter book stock", addBook);
        
        // ...
        
        addBook.setChildren(addBookTitle, addBookDescription, addBookAuthor, addBookStock);
        add.setChildren(addBook);
        
        home.setChildren(add);
        
        return home;
    }
}
```
The `buildAdminStates` returns the root of the state tree.

## Api
The `Api` class serves as an object for querying the database and returning the obtained values.

It utilizes the `Response` class which represents a response of an `Api` call.

### Response
The `Response` class is a generic class representing an `Api` call response and its obtained data.
```java
public class Response<T> {

    private int status;
    private String message;
    private T data;
}
```
- `status` - a status code of the `Api` call result
- `message` - a custom message representing a detailed textual representation of the `Api` call result
- `data` - a generic value holding the obtained `Api` call data

### Example - Get All Books From the Database
Below is the implementation of an `Api` call for retrieving all books from the database.
```java
public class Books {

    public Response<Book[]> getAll() {
        String query = "SELECT * FROM books";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            ArrayList<Book> books = new ArrayList<>();

            while (result.next()) {
                String id = result.getString("id");
                String title = result.getString("title");
                String description = result.getString("description");
                String author = result.getString("author");
                int stock = result.getInt("stock");
                
                books.add(new Book(id, title, description, author, stock));
            }

            Book[] bookArray = books.toArray(Book[]::new);
            return new Response(200, "Books loaded successfully", bookArray);
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }
}
```
The data can be obtained from the response in the following way.
```java
public class SomeClass {
    
    public void someMethod() {
        Response<Book[]> response = new Api().books().getAll();
        
        int statusCode = response.getStatus();
        String message = response.getMessage();
        Book[] books = response.getData();
        
        if (statusCode == 200) {
            Console.println("Books retrieved successfully");
            Console.println(message);
        }
    }
}
```

## Library Model
The `model` package contains classes representing the library model (tables).
It contains the [User](#user-model) model, [Book](#book-model) model and the [Order](#order-model) model.

### User Model
The `User` model represents a **user** stored in the database.
```java
public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private int admin;

    public User(String id, String name, String email, String password, int admin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }
}
```

### Book Model
The `Book` model represents a **book** stored in the database.
```java
public class Book {

    private String id;
    private String title;
    private String description;
    private String author;
    private int stock;

    public Book(String id, String title, String description, String author, int stock) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.stock = stock;
    }
}
```

### Order Model
The `Order` model represents an **order** stored in the database.
```java
public class Order {

    private String userId;
    private String bookId;
    private Date createdDate;
    private Date expirationDate;

    public Order(String userId, String bookId, Date createdDate, Date expirationDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.createdDate = createdDate;
        this.expirationDate = expirationDate;
    }
}
```

## Hashing Using MD5
The application implements a hashing module for generating IDs and safely storing hashed passwords in the database.
It uses the `MD5` generator to generate fixed-length hashes of the given string.

### Hash Generation
Below is the implementation of the hashing algorithm, which uses the `MD5` generator.
```java
public class Generator {

    public static String getHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(input.getBytes());
            byte[] digest = md.digest();

            StringBuilder builder = new StringBuilder();
            for (byte b : digest) builder.append(String.format("%02x", b & 0xff));

            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            Console.error("Error generating a hash");
            return null;
        }
    }
}
```

### ID Generation
The IDs are generated using this algorithm using a randomly generated string for unique values.
```java
public class Generator {
    
    public static String getId() {
        String randomString = Long.toString(System.nanoTime());
        return getHash(randomString);
    }
}
```

by [Tomas Boda](https://github.com/TomasBoda)