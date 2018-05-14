function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.login = login;
    this.register = register;
    this.URL = '/api/user';
    this.LOGIN_URL = '/api/login';
    this.REGISTER_URL = '/api/register';
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
                console.log("register Promise error "+error);
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
                console.log("updateUser Promise error "+error);
                return null;
            });
    }

}