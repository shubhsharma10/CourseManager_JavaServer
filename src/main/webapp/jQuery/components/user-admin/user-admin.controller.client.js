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
        $('.wbdv-create').click(createUser);
        $('.wbdv-update').click(updateUser);
        $('.wbdv-search').click(searchUser);

        findAllUsers();

    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }
    
    function emptyUserForm() {
        $('#usernameFld').val('');
        $('#passwordFld').val('');
        $('#firstNameFld').val('');
        $('#lastNameFld').val('');
        $('#roleFld').val('');
        currentUserID = 0;
        
    }

    function createUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };

        userService
            .createUser(user)
            .then(findAllUsers)
            .then(emptyUserForm);
    }

    function updateUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();
        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
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
        $('#roleFld').val(user.role);
    }

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
            .then(populateUser);
    }

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