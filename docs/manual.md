# User Manual
This document serves as a user manual to the Library Management System application.

## Table of Contents
1. [Startup](#startup)
2. [Admin](#admin)
   - [Add](#add)
   - [Edit](#edit)
   - [Delete](#delete)
   - [List](#list)
3. [Student](#student)
   - [Show](#show)
   - [Edit](#edit)
4. [Built-in Commands](#built-in-commands)

## Startup
The application starts by asking for the database password (if it is not defined) for creating a connection.

Then, it requires the user to login into their account.
It asks for their e-mail and password.
After successful login, the user is redirected to the entry point of the application.

The application's entry point varies depending on the user's admin status.
There are two admin statuses:
- `admin` - this user has access to all database entries and can modify them
- `student` - this user has access only to its `profile` and `orders`

## Admin
After successful login to the `admin` account, the user find themselves in the entry state of the application.

The user can select between four different actions.
- `add` - the user wants to **add** a new entry to the database
- `edit` - the user wants to **edit** an existing entry in the database
- `delete` - the user wants to **delete** an existing entry in the database
- `list` - the user wants to **list** an array of entries in the database

### Add
After navigating to the `add` state, the user can choose from three different entries to add.
- `user` - the user wants to **add** a new **user** to the database
- `book` - the user wants to **add** a new **book** to the database
- `order` - the user wants to **add** a new **order** to the database

After navigating to any of these states, the user will be asked to enter the desired values for the new entry and a new entry will be added to the database.

### Edit
After navigating to the `edit` state, the user can choose from three different entries to edit.
- `user` - the user wants to **edit** an existing **user** in the database
- `book` - the user wants to **edit** an existing **book** in the database
- `order` - the user wants to **edit** an existing **order** in the database

After navigating to any of these states, the user will be asked to enter the desired values for the entry to be edited.

### Delete
After navigating to the `delete` state, the user can choose from three different entries to delete.
- `user` - the user wants to **delete** an existing **user** from the database
- `book` - the user wants to **delete** an existing **book** from the database
- `order` - the user wants to **delete** an existing **order** from the database

After navigating to any of these states, the user will be asked to enter the identification key(s) of the entry to be deleted.

### List
After navigating to the `list` state, the user can choose from three different entries to list.
- `user` - the user wants to **list** **users** in the database
- `book` - the user wants to **list** **books** in the database
- `order` - the user wants to **list** **orders** in the database

After navigating to any of these states, the corresponding entries will be listed with their corresponding total count.

## Student
After successful login to the `student` account, the user find themselves in the entry state of the application.

The user can select between four different actions.
- `show` - the user wants to **show** his profile or orders
- `edit` - the user wants to **edit** his profile

### Show
After navigating to the `show` state, the user can choose from two different values to show.
- `profile` - the user wants to **show** their **profile** information
- `order` - the user wants to **show** their current and past **orders**

After navigating to any of these states, the corresponding information will be outputed to the user.

### Edit
After navigating to the `edit` state, the user can choose from three entries to edit.
- `name` - the user wants to **edit** their profile **name**
- `email` - the user wants to **edit** their profile **e-mail**
- `password` - the user wants to **edit** their profile **password**

After navigating to any of these states, the user will be asked to enter the desired values for the entry to be edited.

## Built-in Commands
There are several built-in commands the user can execute at any given point to execute a general action.
- `:back` - this action takes the user to the last state he was in, more specifically to the **fail** state of the current state
- `:home` - this action takes to user back to the entry state of the application
- `:exit` - this action exits the application

Note that the built-in commands always start with the `:` prefix to distinguish them from other commands.

by [Tomas Boda](https://github.com/TomasBoda)