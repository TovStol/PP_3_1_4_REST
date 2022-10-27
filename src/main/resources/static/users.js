function userPage() {
    const url = '/api/user'

    fetch(url).then(response => response.json()).then(user => {
        let rolesList = ''
        for (const rolesListElement of user.roles) {
            rolesList = rolesList + rolesListElement.name + ' '
        }
        $('#navbarEmail').text(`${user.email}`)
        $('#navbarRole').text(`${rolesList}`)

        let trHtml =
            `<tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${rolesList}</td>
                </tr>`
        $('#userTableBody').html(trHtml)
    })
}
userPage()
