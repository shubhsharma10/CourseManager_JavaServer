(function() {
    $(init);

    var $staticUsername, $firstName, $lastName;
    var $phoneNumber, $email, $role, $dob;
    var userID,password;
    var $updateBtn, $logoutBtn, $updateSuccessID, $updateFailureID;
    var userService;

    function init() {
        console.log("init called: ");
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
        userID = parseInt(userService.getURLParameters(window.location,"uid"));
        findUserById(parseInt(userID));
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
        //userService.logout();
        password = "";
        userID = 0;
        window.location = '/jQuery/components/login/login.template.client.html'
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
        var dateOfBirth = user.dateOfBirth.split('T')[0];
        $dob.val(dateOfBirth);
    }
})();
