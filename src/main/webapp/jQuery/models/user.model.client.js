function User(username,password,firstName,lastName,role,phone,email,dateOfBirth) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.phone = phone;
    this.email = email;
    this.dateOfBirth = dateOfBirth;

    function getUsername() {
        return username;
    }
    function setUsername(  username) {
        this.username = username;
    }
    function getPassword() {
        return password;
    }
    function setPassword(password) {
        this.password = password;
    }
    function getFirstName() {
        return firstName;
    }
    function setFirstName(firstName) {
        this.firstName = firstName;
    }
    function getLastName() {
        return lastName;
    }
    function setLastName(lastName) {
        this.lastName = lastName;
    }
    function getRole() {
        return role;
    }
    function setRole(role) {
        this.role = role;
    }

    function getPhone() {
        return phone;
    }

    function setPhone(phone) {
        this.phone = phone;
    }

    function getEmail() {
        return email;
    }

    function setEmail(email) {
        this.email = email;
    }

    function getDateOfBirth() {
        return dateOfBirth;
    }

    function setDateOfBirth(dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
