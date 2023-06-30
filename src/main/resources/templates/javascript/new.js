'use strict';


let form = document.forms["new"];
createNewUser()
function createNewUser() {
    form.addEventListener("submit", ev => {
        ev.preventDefault();
        let roles = [];
        for (let i = 0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected) roles.push({
                id: form.roles.options[i].value,
                role: "ROLE_" + form.roles.options[i].text
            });
        }

        fetch("/users/addNew", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: form.username.value,
                age: form.age.value,
                password: form.password.value,
                roles: roles
            })
        }).then(() => {
            form.reset();
            $('#userTable').click();
            getUsersTable()
        });
    });
}