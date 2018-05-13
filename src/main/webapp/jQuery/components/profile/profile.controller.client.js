(function() {
    $(init);

    var $staticEmail;
    var $firstName;
    var $lastName;
    var $updateBtn;
    var userService;

    function init() {
        $staticEmail = $("#staticEmail");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
        $updateBtn = $("#updateBtn")
            .click(updateUser);
        userService = new UserServiceClient();
        findUserById(22);
    }

    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }

    function updateUser() {
        var user = {
            firstName: $firstName.val(),
            lastName: $lastName.val()
        };

        userService
            .updateUser(22, user)
            .then(success);
    }

    function success(response) {
        console.log(response);
        console.log(response.data);
        if(response === null) {
            alert('unable to update')
        } else {
            alert('success');
        }
    }

    function renderUser(user) {
        console.log(user);
        $staticEmail.val(user.username);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
    }
})();
