package main.state;

import main.api.Response;
import main.app.App;
import main.app.Configuration;
import main.state.nodes.admin.add.Add;
import main.state.nodes.admin.add.book.*;
import main.state.nodes.admin.add.order.*;
import main.state.nodes.admin.add.user.*;
import main.state.nodes.admin.delete.Delete;
import main.state.nodes.admin.delete.book.DeleteBook;
import main.state.nodes.admin.delete.book.DeleteBookCheckTitle;
import main.state.nodes.admin.delete.order.DeleteOrder;
import main.state.nodes.admin.delete.order.DeleteOrderCheckBook;
import main.state.nodes.admin.delete.order.DeleteOrderCheckUser;
import main.state.nodes.admin.delete.user.DeleteUser;
import main.state.nodes.admin.delete.user.DeleteUserCheckEmail;
import main.state.nodes.admin.edit.Edit;
import main.state.nodes.admin.edit.book.*;
import main.state.nodes.admin.edit.order.EditOrder;
import main.state.nodes.admin.edit.order.EditOrderCheckBook;
import main.state.nodes.admin.edit.order.EditOrderCheckUser;
import main.state.nodes.admin.edit.order.EditOrderExpirationDate;
import main.state.nodes.admin.edit.user.*;
import main.state.nodes.admin.list.List;
import main.state.nodes.admin.list.book.ListBook;
import main.state.nodes.admin.list.order.ListOrder;
import main.state.nodes.admin.list.user.ListUser;
import main.state.nodes.student.edit.email.EditCheckEmail;
import main.state.nodes.student.edit.email.EditEmail;
import main.state.nodes.student.edit.name.EditCheckName;
import main.state.nodes.student.edit.name.EditName;
import main.state.nodes.student.edit.password.EditNewPassword;
import main.state.nodes.student.edit.password.EditOldPassword;
import main.state.nodes.student.edit.password.EditPassword;
import main.state.nodes.student.edit.password.EditRepeatNewPassword;
import main.state.nodes.student.show.Show;
import main.state.nodes.student.show.order.ShowOrder;
import main.state.nodes.student.show.profile.ShowProfile;
import main.utils.Console;

public class StateManager {

    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getRootState() {
        State currentState = getState();

        while (currentState.getCallback() != null) {
            currentState = currentState.getCallback();
        }

        return currentState;
    }

    public void build() {
        Response<Integer> response = App.api.auth().isAdmin(App.userId);

        if (response.getStatus() != 200) {
            Console.println("Couldn't load the user");
            App.exit();
        }

        int admin = response.getData();

        if (admin == 0) {
            state = buildStudentStates();
        } else {
            state = buildAdminStates();
        }
    }

    private State buildStudentStates() {
        State home = new main.state.nodes.student.Home("home", "What do you want to do");

        State show = new Show("show", "What do you want to show", home);

        State showProfile = new ShowProfile("profile", "You are about to show your profile, type anything to continue", show);
        State showOrder = new ShowOrder("order", "You are about to show your orders, type anything to continue", show);

        State edit = new Edit("edit", "What do you want to edit", home);

        State editName = new EditName("name", "You are about to edit your profile name, type anything to continue", edit);
        State editCheckName = new EditCheckName("check-name", "Enter your new profile name", editName);

        State editEmail = new EditEmail("email", "You are about to edit your profile e-mail, type anything to continue, edit", edit);
        State editCheckEmail = new EditCheckEmail("check-email", "Enter your new profile e-mail", editEmail);

        State editPassword = new EditPassword("password", "You are about to edit your profile password, type anything to continue", edit);
        State editOldPassword = new EditOldPassword("old-password", "Enter your current password", editPassword);
        State editNewPassword = new EditNewPassword("new-password", "Enter your new password", editPassword);
        State editRepeatNewPassword = new EditRepeatNewPassword("repeat-password", "Repeat your new password", editPassword);

        show.setChildren(showProfile, showOrder);

        editName.setChildren(editCheckName);
        editEmail.setChildren(editCheckEmail);
        editPassword.setChildren(editOldPassword, editNewPassword, editRepeatNewPassword);
        edit.setChildren(editName, editEmail, editPassword);

        home.setChildren(show, edit);

        return home;
    }

    private State buildAdminStates() {
        State home = new main.state.nodes.admin.Home("home", "What do you want to do");

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

        State addOrder = new AddOrder("order", "You are about to add a new order, type anything to continue", add);
        State addOrderUser = new AddOrderUser("user", "Enter user e-mail", addOrder);
        State addOrderBook = new AddOrderBook("book", "Enter book title", addOrder);
        State addOrderCreatedDate = new AddOrderCreatedDate("created-date", "Enter order created date", addOrder);
        State addOrderExpirationDate = new AddOrderExpirationDate("expiration-date", "Enter order expiration date", addOrder);

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

        State editOrder = new EditOrder("order", "You are about to edit an order, type anything to continue", edit);
        State editOrderCheckUser = new EditOrderCheckUser("check-user", "Enter the order's user e-mail", editOrder);
        State editOrderCheckBook = new EditOrderCheckBook("check-book", "Enter the order's book title", editOrder);
        State editOrderExpirationDate = new EditOrderExpirationDate("expirationDate", "Enter new expiration date", editOrder);

        State delete = new Delete("delete", "What do you want to delete", home);

        State deleteUser = new DeleteUser("user", "You are about to delete a user, type anything to continue", delete);
        State deleteUserCheckEmail = new DeleteUserCheckEmail("check-email", "Enter the user's e-mail", deleteUser);

        State deleteBook = new DeleteBook("book", "You are about to delete a book, type anything to continue", delete);
        State deleteBookCheckTitle = new DeleteBookCheckTitle("check-title", "Enter the book's title", deleteBook);

        State deleteOrder = new DeleteOrder("order", "You are about to delete an order, type anything to continue", delete);
        State deleteOrderCheckUser = new DeleteOrderCheckUser("check-user", "Enter the order's user e-mail", deleteOrder);
        State deleteOrderCheckBook = new DeleteOrderCheckBook("check-book", "Enter the order's book title", deleteOrder);

        State list = new List("list", "What do you want to list", home);

        State listUser = new ListUser("user", "You are about to list users, type anything to continue", list);
        State listBook = new ListBook("book", "You are about to list books, type anything to continue", list);
        State listOrder = new ListOrder("order", "You are about to list orders, type anything to continue", list);

        addUser.setChildren(addUserName, addUserEmail, addUserPassword, addUserAdmin);
        addBook.setChildren(addBookTitle, addBookDescription, addBookAuthor, addBookStock);
        addOrder.setChildren(addOrderUser, addOrderBook, addOrderCreatedDate, addOrderExpirationDate);
        add.setChildren(addUser, addBook, addOrder);

        editUser.setChildren(editUserCheckEmail, editUserName, editUserEmail, editUserPassword, editUserAdmin);
        editBook.setChildren(editBookCheckTitle, editBookTitle, editBookDescription, editBookAuthor, editBookStock);
        editOrder.setChildren(editOrderCheckUser, editOrderCheckBook, editOrderExpirationDate);
        edit.setChildren(editUser, editBook, editOrder);

        deleteUser.setChildren(deleteUserCheckEmail);
        deleteBook.setChildren(deleteBookCheckTitle);
        deleteOrder.setChildren(deleteOrderCheckUser, deleteOrderCheckBook);
        delete.setChildren(deleteUser, deleteBook, deleteOrder);

        list.setChildren(listUser, listBook, listOrder);

        home.setChildren(add, edit, delete, list);

        return home;
    }
}
