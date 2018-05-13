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

    function registerUser() {
        console.log("register user");
    }
})();

