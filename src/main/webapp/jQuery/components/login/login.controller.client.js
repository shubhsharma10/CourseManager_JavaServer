(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn, $loginFailureID, userService;
    $(main);

    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $loginBtn = $('#loginBtn');
        $loginFailureID = $('#wbdv-login-failure');
        $loginBtn.click(loginUser);
        userService = new UserServiceClient();
    }

    function emptyForm() {
        $usernameFld.val('');
        $passwordFld.val('');
    }

    function handleLoginResponse(response) {
        if(response === null)
        {
            $loginFailureID.show();
        }
        else
        {
            $loginFailureID.hide();
            console.log(response);
        }
    }

    function loginUser() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();

        userService
            .login(username,password)
            .then(handleLoginResponse)
            .then(emptyForm);
    }
})();

