package domain.rows.queries;

import domain.rows.Row;

public class UserAndRoleRow implements Row {

    private final String userName;
    private final String roleName;
    private final int roleId;

    public UserAndRoleRow(String userName, int roleId, String role) {
        this.userName = userName;
        this.roleId = roleId;
        this.roleName = role;
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
        return userName + " " + roleName;
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
