(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn, userService;
    $(main);

    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $verifyPasswordFld = $('#verifyPasswordFld');
        $registerBtn = $('#registerBtn');
        $registerBtn.click(registerUser);
        userService = new UserServiceClient();
    }

    function emptyForm() {
        $usernameFld.val('');
        $passwordFld.val('');
        $verifyPasswordFld.val('');
    }


    function registerUser() {
        userService
            .register($usernameFld.val(),$passwordFld.val())
            .then(emptyForm());
    }
})();

