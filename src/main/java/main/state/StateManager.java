package main.state;

import main.app.Configuration;
import main.state.nodes.add.Add;
import main.state.nodes.add.book.*;
import main.state.nodes.add.user.*;
import main.state.nodes.delete.Delete;
import main.state.nodes.delete.book.DeleteBook;
import main.state.nodes.delete.book.DeleteBookCheckTitle;
import main.state.nodes.delete.user.DeleteUser;
import main.state.nodes.delete.user.DeleteUserCheckEmail;
import main.state.nodes.edit.Edit;
import main.state.nodes.edit.book.*;
import main.state.nodes.edit.user.*;
import main.state.nodes.Home;
import main.state.nodes.list.List;
import main.state.nodes.list.book.ListBook;
import main.state.nodes.list.user.ListUser;

public class StateManager {

    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void goBack() {
        setState(getState().getCallback());
        getState().ask();
    }

    public void build() {
        if (Configuration.user.getAdmin() == 0) {
            state = buildStudentStates();
        } else {
            state = buildAdminStates();
        }
    }

    private State buildStudentStates() {
        return null;
    }

    private State buildAdminStates() {
        State home = new Home("home", "What do you want to do");

        State add = new Add("add", "What do you want to add", home);

        State addUser = new AddUser("user", "You are about to add a new user, type anything to continue", add);
        State addUserName = new AddUserName("name", "Enter user name", addUser);
        State addUserEmail = new AddUserEmail("email", "Enter user email", addUser);
        State addUserPassword = new AddUserPassword("password", "Enter user password", addUser);
        State addUserAdmin = new AddUserAdmin("admin", "Enter user admin status", addUser);

        State addBook = new AddBook("book", "You are about to add a new book, type anything to continue", add);
        State addBookTitle = new AddBookTitle("title", "Enter book title", addBook);
        State addBookDescription = new AddBookDescription("description", "Enter book description", addBook);
        State addBookAuthor = new AddBookAuthor("author", "Enter book author", addBook);
        State addBookStock = new AddBookStock("stock", "Enter book stock", addBook);

        State edit = new Edit("edit", "What do you want to edit", home);

        State editUser = new EditUser("user", "You are about to edit a user, type anything to continue", edit);
        State editUserCheckEmail = new EditUserCheckEmail("check-email", "Enter the user's e-mail", editUser);
        State editUserName = new EditUserName("name", "Enter new user name", editUser);
        State editUserEmail = new EditUserEmail("email", "Enter new user email", editUser);
        State editUserPassword = new EditUserPassword("password", "Enter new user password", editUser);
        State editUserAdmin = new EditUserAdmin("admin", "Enter new user admin", editUser);

        State editBook = new EditBook("book", "You are about to edit a book, type anything to continue", edit);
        State editBookCheckTitle = new EditBookCheckTitle("check-title", "Enter the book's title", editBook);
        State editBookTitle = new EditBookTitle("title", "Enter new book title", editBook);
        State editBookDescription = new EditBookDescription("description", "Enter new book description", editBook);
        State editBookAuthor = new EditBookAuthor("author", "Enter new book author", editBook);
        State editBookStock = new EditBookStock("stock", "Enter new book stock", editBook);

        State delete = new Delete("delete", "What do you want to delete", home);

        State deleteUser = new DeleteUser("user", "You are about to delete a user, type anything to continue", delete);
        State deleteUserCheckEmail = new DeleteUserCheckEmail("check-email", "Enter the user's e-mail", deleteUser);

        State deleteBook = new DeleteBook("book", "You are about to delete a book, type anything to continue", delete);
        State deleteBookCheckTitle = new DeleteBookCheckTitle("check-title", "Enter the book's title", deleteBook);

        State list = new List("list", "What do you want to list", home);

        State listUser = new ListUser("user", "You are about to list users, type anything to continue", list);

        State listBook = new ListBook("book", "You are about to list books, type anything to continue", list);

        addUser.setChildren(addUserName, addUserEmail, addUserPassword, addUserAdmin);
        addBook.setChildren(addBookTitle, addBookDescription, addBookAuthor, addBookStock);
        add.setChildren(addUser, addBook);

        editUser.setChildren(editUserCheckEmail, editUserName, editUserEmail, editUserPassword, editUserAdmin);
        editBook.setChildren(editBookCheckTitle, editBookTitle, editBookDescription, editBookAuthor, editBookStock);
        edit.setChildren(editUser, editBook);

        deleteUser.setChildren(deleteUserCheckEmail);
        deleteBook.setChildren(deleteBookCheckTitle);
        delete.setChildren(deleteUser, deleteBook);

        list.setChildren(listUser, listBook);

        home.setChildren(add, edit, delete, list);

        return home;
    }
}
