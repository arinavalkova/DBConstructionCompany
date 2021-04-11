package domain.rows.organizations;

import domain.rows.Row;

import java.util.ArrayList;

public class OrganizationsAndManagementRow implements Row {

    private final int id;
    private final int organizationId;
    private final int managementId;

    public OrganizationsAndManagementRow(int id, int organizationId, int managementId) {
        this.id = id;
        this.organizationId = organizationId;
        this.managementId = managementId;
    }

    public OrganizationsAndManagementRow(ArrayList<String> list) {
        this.id = Integer.parseInt(list.get(0));
        this.organizationId = Integer.parseInt(list.get(1));
        this.managementId = Integer.parseInt(list.get(2));
    }

    public int getId() {
        return id;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public int getManagementId() {
        return managementId;
    }
}
