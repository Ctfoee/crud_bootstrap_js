async function getUser(id) {
    let url = "/users/" + id;
    let response = await fetch(url);
    return await response.json();
}

async function dataToModal(form, modal, id) {
    modal.show();
    let user = await getUser(id);
    form.id.value = user.id;
    form.username.value = user.username;
    form.age.value = user.age;
    form.roles.value = user.roles;
}