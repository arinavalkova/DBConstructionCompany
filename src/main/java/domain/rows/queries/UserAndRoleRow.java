package domain.rows.queries;

import domain.rows.Row;

public class UserAndRoleRow implements Row {

    private final String userName;
    private final String password;
    private final String roleName;
    private final int roleId;

    public UserAndRoleRow(String userName, String password, int roleId, String role) {
        this.userName = userName;
        this.password = password;
        this.roleId = roleId;
        this.roleName = role;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    @Override
    public String toString() {
        return userName;
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public int getId() {
        return roleId;
    }
}
