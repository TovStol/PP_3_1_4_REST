SELECT users.name, roles.name FROM users
    JOIN users_roles ON (users.id = users_roles.user_id)
    JOIN roles ON (roles.id = users_roles.roles_id)