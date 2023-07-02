export async function getAuthUser() {
    let response = await fetch("/users/current")
    return response.ok
        ? response.json()
        : null
}

export async function getAuthUsername() {
    return await getAuthUser()
        .then(authUser => authUser.username)
        .then(username => {
            return username === undefined ? null : username
        })
}

export async function updateUserInfo() {
    let authUser = await getAuthUser()
    if (authUser != null) {
        $("#username").text(authUser.username)
        let rolesText = " with roles: "
        for (let role of authUser.roles) {
            rolesText += `${role.role.substring(5)} `
        }
        $("#userRoles").text(rolesText)
    }
}