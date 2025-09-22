package com.mywork.dairy360.dto;

import com.mywork.dairy360.entity.AdminEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
public class AdminAuditDTO {
    private Integer id;

    private AdminEntity admin;

    private String adminName;

    private LocalDateTime loginTime;

    private LocalDateTime logoutTime;

    public Date getLoginTimeAsDate() {
        return loginTime == null ? null :
                Date.from(loginTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getLogoutTimeAsDate() {
        return logoutTime == null ? null :
                Date.from(logoutTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}