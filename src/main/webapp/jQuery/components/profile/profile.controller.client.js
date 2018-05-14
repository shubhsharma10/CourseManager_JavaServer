(function() {
    $(init);

    var $staticUsername, $firstName, $lastName;
    var $phoneNumber, $email, $role, $dob;
    var userID,password;
    var $updateBtn, $logoutBtn, $updateSuccessID, $updateFailureID;
    var userService;

    function init() {
        $staticUsername = $("#staticUsernameFId");
        $firstName = $("#firstNameFId");
        $lastName = $("#lastNameFId");
        $phoneNumber = $("#phoneNumberFId");
        $email = $("#emailFId");
        $role = $("#roleFId");
        $dob = $("#dateOfBirthFId");
        $updateBtn = $("#updateBtn")
            .click(updateUser);
        $logoutBtn = $("#logoutBtn")
            .click(logoutUser);
        $updateSuccessID = $('#wbdv-update-success');
        $updateFailureID = $("#wbdv-update-failure");
        userService = new UserServiceClient();
        userID = userService.getLoggedUserID();
        findUserById(userID);
    }

    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }

    function updateUser() {
        var user = new User($staticUsername.val(),password,$firstName.val(),$lastName.val(),
                            $role.val(),$phoneNumber.val(),$email.val(),$dob.val());

        userService
            .updateUser(userID, user)
            .then(success);
    }

    function logoutUser() {
        userService.logout();
        userService.resetLoggedUserID();
        userID = 0;
        password = "";
    }

    function success(response) {
        console.log(response);
        console.log(response.data);
        if(response === null) {
            $updateSuccessID.hide();
            $updateFailureID.show();
        } else {
            $updateSuccessID.show();
            $updateFailureID.hide();
        }
    }

    function renderUser(user) {
        console.log(user);
        password = user.password;
        $staticUsername.val(user.username);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
        $phoneNumber.val(user.phone);
        $email.val(user.email);
        $role.val(user.role);
        $dob.val(user.dateOfBirth);
    }
})();
