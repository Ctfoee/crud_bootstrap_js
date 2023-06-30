'use strict';


const tbody = $('#allUsers');
function getUsersTable() {
    tbody.empty()

    fetch("/admin")

        .then(res => res.json())
        .then(js => {
            js.forEach(user => {
                const users = `$(
                    <tr>
                        <td class="pt-3" id="userID">${user.id}</td>
                        <td class="pt-3" >${user.username}</td>
                        <td class="pt-3" >${user.age}</td>
                        <td class="pt-3" >${user.roles.map(r => ' ' + r.name.replaceAll('ROLE_', ' '))}</td>
                        <td>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#edit" onclick="editModal(${user.id})">
                            Edit
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete" onclick="deleteModal(${user.id})">
                                Delete
                            </button>
                        </td>
                    </tr>)`;
                tbody.append(users)
            })
        })
}

$(document).ready(function () {
    getUsersTable()
})