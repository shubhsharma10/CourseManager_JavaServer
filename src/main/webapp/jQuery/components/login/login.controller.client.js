(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn, $loginFailureID, userService;

    $(main);

    /*
     * Executes on document load,after browser is done parsing the html
     * */
    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $loginBtn = $('#loginBtn');
        $loginFailureID = $('#wbdv-login-failure');
        $loginBtn.click(loginUser);
        userService = new UserServiceClient();
    }

    /*
    * Resets the form values
    * */
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
            loadProfilePage(response);
        }
    }

    function loadProfilePage(user) {
        userService
            .getProfile(user)
            .then(redirectToProfile);
    }

    function redirectToProfile(profilePage) {
        window.location = profilePage
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

