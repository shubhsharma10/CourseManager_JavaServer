//IIFE
(function () {

    jQuery(main);

    var tbody;
    var template;
    var userService;

    function main() {
        tbody = $('tbody');
        template = $('.template');
        userService = new UserServiceClient();
        $('#createUser').click(createUser);

        findAllUsers();

    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function createUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();

        console.log(username);

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

    function editUser(event) {
        console.log('editUser');
        console.log(event);
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
            tbody.append(clone);
        }
    }

})();