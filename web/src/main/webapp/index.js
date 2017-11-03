function getUser() {
    $.ajax({
        url: 'http://localhost:8080/voter/user/get/1',
        type: 'GET',
        success: function (e) {
            console.log(e);
        },
        error: function () {
            alert('error');
        }
    });
}

function addUser() {
    $.ajax({
        url: "http://localhost:8080/voter/user/add",
        data: JSON.stringify(this.user),
        contentType: "application/json",
        type: 'POST',
        success: function (e) {
            console.log(e);
        },
        error: function () {
            alert('error');
        }
    });
}

var user = {
    login: 'user2',
    password: 'password2'
};