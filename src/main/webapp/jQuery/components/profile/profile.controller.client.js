(function() {

    var $staticUsername, $firstName, $lastName;
    var $phoneNumber, $email, $role, $dob;
    var userID,password;
    var $updateBtn, $logoutBtn, $updateSuccessID, $updateFailureID;
    var userService;

    $(init);

    /*
     * Executes on document load,after browser is done parsing the html
     * */
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
        userID = parseInt(userService.getURLParameters(window.location,"uid"));
        findUserById(parseInt(userID));
    }

    /*
    * Finds user by user id and populates user profile page
    * */
    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }

    /*
    * Handles update button event.
    * Updates user profile on server and shows appropriate message.
    * */
    function updateUser() {
        var user = new User($staticUsername.val(),password,$firstName.val(),$lastName.val(),
                            $role.val(),$phoneNumber.val(),$email.val(),$dob.val());
        userService
            .updateUser(userID, user)
            .then(toggleAlertMessage);
    }

    /*
    * Logs out current user and redirects to login page
    * */
    function logoutUser() {
        password = "";
        userID = 0;
        window.location = '/jQuery/components/login/login.template.client.html';
    }

    /*
    * Display/hide appropriate message based on response.
    * */
    function toggleAlertMessage(response) {
        if(response === null) {
            $updateSuccessID.hide();
            $updateFailureID.show();
        } else {
            $updateSuccessID.show();
            $updateFailureID.hide();
        }
    }

    /*
    * Accepts user as a parameter and renders user profile
    * */
    function renderUser(user) {
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
