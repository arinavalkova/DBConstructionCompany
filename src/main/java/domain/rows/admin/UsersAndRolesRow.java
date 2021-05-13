package domain.rows.admin;

import domain.rows.Row;

import java.util.ArrayList;

public class UsersAndRolesRow implements Row {

    private final int id;
    private final String userName;
    private final String password;
    private final int roleId;

    public UsersAndRolesRow(int id, String userName, String password, int roleId) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roleId = roleId;
    }

    public UsersAndRolesRow(ArrayList<String> list) {
        this.id = Integer.parseInt(list.get(0));
        this.userName = list.get(1);
        this.password = list.get(2);
        this.roleId = Integer.parseInt(list.get(3));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getTableName() {
        return "us_and_rol";
    }

    @Override
    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public int getRoleId() {
        return roleId;
    }

    @Override
    public String toString() {
        return "id=" + id + ", user_name=" + userName + ", role_id=" + roleId;
    }
}
