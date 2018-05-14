function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.login = login;
    this.register = register;
    this.setLoggedUserID = setLoggedUserID;
    this.getLoggedUserID = getLoggedUserID;
    this.resetLoggedUserID = resetLoggedUserID;
    this.URL = '/api/user';
    this.LOGIN_URL = '/api/login';
    this.REGISTER_URL = '/api/register';
    this.loggedUserID = 0;
    var self = this;

    function findAllUsers()
    {
        return fetch(self.URL)
                .then(function (response) {
                return response.json();
                });
    }

    function setLoggedUserID(userID) {
        self.loggedUserID = userID;
    }

    function getLoggedUserID() {
        return self.loggedUserID;
    }

    function resetLoggedUserID() {
        self.loggedUserID = 0;
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

}