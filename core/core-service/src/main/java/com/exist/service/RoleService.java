package com.exist.service;

import com.exist.dao.RoleDao;
import com.exist.dto.RoleDto;
import com.exist.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    private List<RoleDto> roles = new ArrayList<>();

    public RoleDto getRole(int roleId) {
        return roleDao.getRoleById(roleId).toDto();
    }

    public List<RoleDto> getRoles() {
        roles = new ArrayList<>();
        for (Role role : roleDao.getRoles()) {
            roles.add(role.toDto());
        }
        return roles;
    }

    private List<RoleDto> getRoleDto() {
        return new ArrayList<>(roles);
    }

    public void addRole(String roleName) {
        Role role = new Role(roleName);
        roleDao.addRole(role);
    }

    public void updateRole(RoleDto role) {
        roleDao.updateRole(convertToEntity(role));
    }

    public void deleteRole(RoleDto role) {
        roleDao.deleteRole(convertToEntity(role));
    }

    private Role convertToEntity(RoleDto role) {
        return new Role(role.getId(), role.getRoleName());
    }

    private boolean isRoleNameExist(String roleName) {
        for (RoleDto role : roles) {
            if (role.getRoleName().toLowerCase().equals(roleName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
