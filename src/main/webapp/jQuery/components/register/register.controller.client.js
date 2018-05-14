(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn, $passwordAlertID, $userSuccessID, $userFailureID, userService;

    $(main);

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

    function emptyForm() {
        $usernameFld.val('');
        $passwordFld.val('');
        $verifyPasswordFld.val('');
    }

    function handleRegisterResponse(response) {
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
                .then(handleRegisterResponse)
                .then(emptyForm);
        }
    }
})();

