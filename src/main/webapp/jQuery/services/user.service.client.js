function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.login = login;
    this.register = register;
    this.getProfile = getProfile;
    this.getURLParameters = getURLParameters;
    this.URL = '/api/user';
    this.LOGIN_URL = '/api/login';
    this.REGISTER_URL = '/api/register';
    this.PROFILE_URL = '/jQuery/components/profile/profile.template.client.html';
    var self = this;

    function findAllUsers()
    {
        return fetch(self.URL)
                .then(function (response) {
                return response.json();
                });
    }

    function createUser(user)
    {
        return fetch(self.URL, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function deleteUser(userId) {
        return fetch(self.URL + '/' + userId, {
            method: 'delete'
        })
    }

    function findUserById(userId) {
        return fetch(self.URL + '/' + userId)
            .then(function(response){
                return response.json();
            });
    }

    function login(username, password) {
        return fetch(self.LOGIN_URL, {
            method: 'post',
            body: JSON.stringify({username:username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(function(response){
                return response.json();
            })
            .catch(function (error) {
                console.log("Login promise error ::"+error);
                return null;
            });
    }

    function register(username, password) {
        return fetch(self.REGISTER_URL, {
            method: 'post',
            body: JSON.stringify({username:username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(function(response){
                return response.json();
            })
            .catch(function (error) {
                console.log("Register promise error ::"+error);
                return null;
            });
    }

    function updateUser(userId, user) {
        return fetch(self.URL + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
            .then(function(response){
                return response.json();
            })
            .catch(function (error) {
                console.log("Update user promise error :: "+error);
                return null;
            });
    }

    /*
    * Credits: http://www.jquerybyexample.net/2012/06/get-url-parameters-using-jquery.html
    * */
    function getURLParameters(currentLocation,sParam) {
        var sPageURL = decodeURIComponent(currentLocation.search.substring(1)),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : sParameterName[1];
            }
        }
    }

    function getProfile(user) {
        return fetch(self.PROFILE_URL)
            .then(function (response) {
                return response.url + '?uid=' + user.id;
            });
    }

}