"use strict";

function currentUser() {
    $.get(`/users/current`, function (user) {

        let userTbody =
            "<tr><td>" + user.id + "</td>" +
            "<td>" + user.username + "</td>" +
            "<td>" + user.age + "</td>" +
            "<td>" + user.role + "</td></tr>";
        $("#currentUser").html(userTbody);
    })
}

function currentUsername() {
    $.get(`/users/current`, function (user) {
        let currentUsername = user.username;
        $("#currentUsername").html(currentUsername);
    })
}

function currentRoles() {
    $.get(`/users/current`, function (user) {

        let currentUserRoles = user.role;
        $("#currentUserRoles").html(currentUserRoles);
    })
}


$(document).ready(function () {
    currentUser();
    currentUsername();
    currentRoles();
})