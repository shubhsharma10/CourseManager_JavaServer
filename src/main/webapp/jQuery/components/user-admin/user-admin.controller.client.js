//IIFE
(function () {

    jQuery(main);

    var tbody;
    var template;
    var userService;
    var currentUserID;

    function main() {
        tbody = $('tbody');
        template = $('.template');
        userService = new UserServiceClient();
        currentUserID = 0;
        $('.createUser').click(createUser);
        $('.updateUser').click(updateUser);
        $('#searchUser').click(searchUser);

        findAllUsers();

    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }
    
    function emptyUserForm() {
        $('#usernameFld').reset();
        $('#passwordFld').reset();
        $('#firstNameFld').reset();
        $('#lastNameFld').reset();
        currentUserID = 0;
        
    }

    function createUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName
        };

        userService
            .createUser(user)
            .then(findAllUsers);
    }

    function updateUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName
        };
        userService
            .updateUser(currentUserID,user)
            .then(findAllUsers)
            .then(emptyUserForm);

    }
    
    function searchUser() {
        
    }

    function populateUser(user) {
        $('#usernameFld').val(user.username);
        $('#passwordFld').val(user.password);
        $('#firstNameFld').val(user.firstName);
        $('#lastNameFld').val(user.lastName);
    }

    function editUser(event) {
        var editButton = $(event.currentTarget);
        var userId = editButton
            .parent()
            .parent()
            .attr('id');
        currentUserID = userId;
        userService
            .findUserById(userId)
            .then(populateUser);
    }

    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .attr('id');
        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function renderUsers(users) {
        tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();
            clone.attr('id', user.id);
            clone.find('.delete').click(deleteUser);
            clone.find('.edit').click(editUser);
            clone.find('.username')
                .html(user.username);
            clone.find('.password')
                .html(user.password);
            clone.find('.firstName')
                .html(user.firstName);
            clone.find('.lastName')
                .html(user.lastName);
            tbody.append(clone);
        }
    }

})();