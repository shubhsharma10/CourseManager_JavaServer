(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn, $passwordAlertID, $userSuccessID, $userFailureID, userService;

    $(main);

    /*
     * Executes on document load,after browser is done parsing the html
     * */
    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $verifyPasswordFld = $('#verifyPasswordFld');
        $registerBtn = $('#registerBtn');
        $passwordAlertID = $('#wbdv-password-alert');
        $userSuccessID = $('#wbdv-user-success');
        $userFailureID = $('#wbdv-user-failure');
        $registerBtn.click(registerUser);
        userService = new UserServiceClient();
    }

    /*
    * Resets the form values
    * */
    function emptyForm() {
        $usernameFld.val('');
        $passwordFld.val('');
        $verifyPasswordFld.val('');
    }

    /*
    * Display or hide appropriate alert message based on response from register promise
    * */
    function toggleAlertMessages(response) {
        if(response === null)
        {
            $userFailureID.show();
            $userSuccessID.hide();
        }
        else
        {
            $userSuccessID.show();
            $userFailureID.hide();
        }
    }

    /*
    * Handles Sign up button event
    * Checks for same password and if same then try signup.
    * */
    function registerUser() {
        var passwordVal = $passwordFld.val();
        var verifyPasswordVal = $verifyPasswordFld.val();
        if(passwordVal !== verifyPasswordVal)
        {
            $passwordAlertID.show();
        }
        else
        {
            $passwordAlertID.hide();
            userService
                .register($usernameFld.val(),passwordVal)
                .then(toggleAlertMessages)
                .then(emptyForm);
        }
    }
})();

