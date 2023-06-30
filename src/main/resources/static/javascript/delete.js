'use strict';

let deleteForm = document.forms["deleteForm"]

async function deleteModal(id) {
    const modal = new bootstrap.Modal(document.querySelector('#delete'));
    await dataToModal(deleteForm, modal, id);
    switch (deleteForm.roles.value) {
        case '1':
            deleteForm.roles.value = 'ADMIN';
            break;
        case '2':
            deleteForm.roles.value = 'USER';
            break;
    }
    deleteUser()
}

function deleteUser() {
    deleteForm.addEventListener("submit", ev => {
        ev.preventDefault();
        fetch("/users/" + deleteForm.username.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
            getTableUser();
        });
    });
}