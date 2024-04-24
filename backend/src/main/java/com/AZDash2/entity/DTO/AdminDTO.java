package com.AZDash2.entity.DTO;
import com.AZDash2.entity.Admin;

public class AdminDTO {
    private Long id_admin;
    private String admin_name;

    public AdminDTO(Admin admin) {
        this.id_admin = admin.getId_admin();
        this.admin_name = admin.getAdmin_name();
    }

    public Long getId_admin() {
        return id_admin;
    }

    public String getAdmin_name() {
        return admin_name;
    }
}
