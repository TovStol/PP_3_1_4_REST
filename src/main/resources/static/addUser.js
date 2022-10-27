function addUser() {
    let roleList = $('#inputRoles').val()
    let temp = []
    for (const role of roleList) {
        temp.push({id: role})
    }

    let user = {
        firstName: $('#inputFirstName').val(),
        lastName: $('#inputLastName').val(),
        age: $('#inputAge').val(),
        email: $('#inputEmail').val(),
        password: $('#inputPassword').val(),
        roles: temp
    }

    window.location.href = '/admins'

    const url = '/api/users'
    const method = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        body: JSON.stringify(user)
    }
    fetch(url, method)

    window.location.href = '/admins'
}