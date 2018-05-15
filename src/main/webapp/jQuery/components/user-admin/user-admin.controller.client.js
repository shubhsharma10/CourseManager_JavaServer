//IIFE
(function () {
    var tbody, template, currentUserID;
    var userService;

    $(main);

    /*
    * Executes on document load,after browser is done parsing the html
    * */
    function main() {
        tbody = $('tbody');
        template = $('.template');
        userService = new UserServiceClient();
        currentUserID = 0;
        $('.wbdv-create').click(createUser);
        $('.wbdv-update').click(updateUser);

        findAllUsers();

    }

    /**
     * Reset form to placeholder values
     */
    function emptyUserForm() {
        $('#usernameFld').val('');
        $('#passwordFld').val('');
        $('#firstNameFld').val('');
        $('#lastNameFld').val('');
        $('#roleFld').val('');
        currentUserID = 0;
    }

    /*
    * Handles create user event when clicked on plus icon
    * */
    function createUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();

        var user = new User(username,password,firstName,lastName,role,null,null,null);

        userService
            .createUser(user)
            .then(findAllUsers)
            .then(emptyUserForm);
    }

    /*
    * Called whenever user list needs to be refreshed
    * */
    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    /*
    * Handles update user event when user clicks on check mark icon.
    * */
    function updateUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();
        var user = new User(username,password,firstName,lastName,role,null,null,null);
        userService
            .updateUser(currentUserID,user)
            .then(findAllUsers)
            .then(emptyUserForm);

    }

    /*
    * Accepts user object as parameter and updates the form with user properties.
    * */
    function renderUser(user) {
        $('#usernameFld').val(user.username);
        $('#passwordFld').val(user.password);
        $('#firstNameFld').val(user.firstName);
        $('#lastNameFld').val(user.lastName);
        $('#roleFld').val(user.role);
    }

    /*
    * Handles edit button on selected user
    * */
    function editUser(event) {
        var editButton = $(event.currentTarget);
        var userId = editButton
            .parent()
            .parent()
            .parent()
            .attr('id');
        userId = parseInt(userId);
        currentUserID = userId;
        userService
            .findUserById(userId)
            .then(renderUser);
    }

    /*
    * Handles delete button on selected user.
    * */
    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .parent()
            .attr('id');
        userId = parseInt(userId);
        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    /*
    * Accepts list of user instances and populates table body with them.
    * */
    function renderUsers(users) {
        tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();
            clone.attr('id', user.id);
            clone.find('.wbdv-delete').click(deleteUser);
            clone.find('.wbdv-edit').click(editUser);
            clone.find('.wbdv-username')
                .html(user.username);
            clone.find('.wbdv-first-name')
                .html(user.firstName);
            clone.find('.wbdv-last-name')
                .html(user.lastName);
            clone.find('.wbdv-role')
                .html(user.role);
            tbody.append(clone);
        }
    }

})();