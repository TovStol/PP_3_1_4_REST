async function tableAdmin() {
    const url = '/api/users'
    const container = $('#adminTable');
    const method = {
        dataType: 'json',
        type: 'GET'
    }
    fetch(url, method).then(response => response.json()).then(user => {

        let html = ''
        let rolesList = ''
        user.forEach(user => {
            for (const rolesListElement of user.roles) {
                rolesList = rolesList + rolesListElement.name + ' '
            }
            let trHtml =
                `<tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${rolesList}</td>
                    <td>
                        <button id="btnEditUser" onclick="fillModelEditUserById(${user.id})" type="button" class="btn btn-primary" data-bs-toggle="modal">
                              Edit
                        </button>
                    </td>
                    <td>
                        <button id="btnDeleteUser" onclick="fillModelDeletUserById(${user.id})" type="button" class="btn bg-danger text-white" data-bs-toggle="modal">
                              Delete
                        </button>
                    </td>
                </tr>`
            html += trHtml
            container.html(html)
            rolesList = ''
        })
    })
}

tableAdmin()

function fillModelDeletUserById(id) {
    const urluserByid = '/api/users/' + id
    const method = {
        dataType: 'json',
        type: 'GET'
    }
    fetch(urluserByid, method).then(response => response.json()).then(user => {
        console.log(user)
        $('#deleteId').val(`${user.id}`)
        $('#deleteFirstName').val(`${user.firstName}`)
        $('#deleteLastName').val(`${user.lastName}`)
        $('#deleteAge').val(`${user.age}`)
        $('#deleteEmail').val(`${user.email}`)
        let role = ''
        for (const role1 of user.roles) {
            role = role + `<option> ${role1.name} </option>`
        }
        $('#deleteRoles').html(role)
        viewOpenModalDeleteUser()
    })
}

function deleteUserById(id) {
    const url = '/api/users/' + id
    const method = {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        }
    }
    fetch(url, method).then(() => {
        tableAdmin()
    })
}

function viewOpenModalDeleteUser() {
    const viewHtmlDelete = document.getElementById('deleteModal')
    viewHtmlDelete.style.display = "block"
}

function fillModelEditUserById(id) {
    const urluserByid = '/api/users/' + id
    const method = {
        dataType: 'json',
        type: 'GET'
    }
    fetch(urluserByid, method).then(response => response.json()).then(user => {
        console.log(user)
        $('#editId').val(`${user.id}`)
        $('#editFirstName').val(`${user.firstName}`)
        $('#editLastName').val(`${user.lastName}`)
        $('#editAge').val(`${user.age}`)
        $('#editEmail').val(`${user.email}`)
        $('#editPassword').val(`${user.password}`)
        let role = ''
        for (const role1 of user.roles) {
            role = role + `<option value="${role1.id}" selected> ${role1.name} </option>`
        }
        $('#editRoles').html(role)
        viewOpenModalEditUser()
    })
}

function viewOpenModalEditUser() {
    const viewHtmlDelete = document.getElementById('editModal')
    viewHtmlDelete.style.display = "block"
}

function updateUser() {
    let roleList = $('#editRoles').val()
    let temp = []
    for (const role of roleList) {
        temp.push({id: role})
    }
    let user = {
        id: $('#editId').val(),
        firstName: $('#editFirstName').val(),
        lastName: $('#editLastName').val(),
        age: $('#editAge').val(),
        email: $('#editEmail').val(),
        password: $('#editPassword').val(),
        roles: temp
    }
    const url = '/api/users'
    const method = {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        body: JSON.stringify(user)
    }
    fetch(url, method).then(() => tableAdmin())
}






