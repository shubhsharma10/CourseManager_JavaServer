<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet"/>
<title>Insert title here</title>
  <script
          src="https://code.jquery.com/jquery-3.3.1.min.js"
          integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
          crossorigin="anonymous"></script>

  <script src="user-admin.controller.client.js"></script>

</head>
<body>
<div class="container">
  <h1 id="title">User Admin</h1>
  <h1 class="red">Red</h1>
  <h1 class="red">Also Red</h1>
  <h1 class="green">Green</h1>
  <h1 class="green">Also green</h1>
  <h1 class="blue">Blue</h1>
  <table class="table">
    <thead>
      <tr>
        <th>Username</th>
        <th>Password</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th></th>
      </tr>
      <tr>
        <th><input class="form-control" placeholder="jdoe"/></th>
        <th><input class="form-control" type="password"/></th>
        <th><input class="form-control" placeholder="John"/></th>
        <th><input class="form-control" placeholder="Doe"/></th>
        <th><button class="btn btn-success">Create</button></th>
      </tr>
    </thead>
    <tbody>
      <tr class="template">
        <td class="username">alice</td>
        <td>****</td>
        <td>Alice</td>
        <td>Wonderland</td>
        <th><button class="btn btn-danger">Delete</button></th>
      </tr>
    </tbody>
    <tfoot></tfoot>
  </table>

  <h1>THis is another h1</h1>
  </div>
</body>
</html>