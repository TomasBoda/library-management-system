package main.state;

import main.state.nodes.*;
import main.state.nodes.add.Add;
import main.state.nodes.add.book.AddBook;
import main.state.nodes.add.book.AddBookAuthor;
import main.state.nodes.add.book.AddBookDescription;
import main.state.nodes.add.book.AddBookTitle;
import main.state.nodes.add.user.AddUser;
import main.state.nodes.add.user.AddUserEmail;
import main.state.nodes.add.user.AddUserName;
import main.state.nodes.delete.Delete;
import main.state.nodes.list.List;
import main.state.nodes.list.book.ListBook;
import main.state.nodes.list.user.ListUser;

public class StateManager {

    private State state;

    public StateManager() { this.state = build(); }

    public State getState() { return state; }

    public void setState(State state) { this.state = state; }

    private State build() {
        State home = new Home("home", "What do you want to do");

        State add = new Add("add", "What do you want to add", home);

        State addUser = new AddUser("user", "You are about to add a new user", add);
        State addUserName = new AddUserName("name", "Enter user name", addUser);
        State addUserEmail = new AddUserEmail("email", "Enter user email", addUser);

        State addBook = new AddBook("book", "You are about to add a new book", add);
        State addBookTitle = new AddBookTitle("title", "Enter book title", addBook);
        State addBookDescription = new AddBookDescription("description", "Enter book description", addBook);
        State addBookAuthor = new AddBookAuthor("author", "Enter book author", addBook);

        State list = new List("list", "What do you want to list", home);

        State listUser = new ListUser("user", "Listing users", list);

        State listBook = new ListBook("book", "Listing books", list);

        State delete = new Delete("delete", "What do you want to delete", home);

        addUser.setChildren(addUserName, addUserEmail);
        addBook.setChildren(addBookTitle, addBookDescription, addBookAuthor);

        add.setChildren(addUser, addBook);

        list.setChildren(listUser, listBook);

        home.setChildren(add, list, delete);

        return home;
    }
}
