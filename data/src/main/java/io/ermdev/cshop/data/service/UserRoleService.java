package io.ermdev.cshop.data.service;

import io.ermdev.cshop.data.entity.Role;
import io.ermdev.cshop.data.entity.User;
import io.ermdev.cshop.data.exception.EntityException;
import io.ermdev.cshop.data.repository.RoleRepository;
import io.ermdev.cshop.data.repository.UserRepository;
import io.ermdev.cshop.data.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository, UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<Role> findRolesByUserId(Long userId) throws EntityException {
        List<Role> roles = userRoleRepository.findRolesByUserId(userId);
        if (roles != null) {
            return roles;
        } else {
            throw new EntityException("No role found");
        }
    }

    public Role findUserRoleById(Long userId, Long roleId) throws EntityException {
        Role role = userRoleRepository.findUserRoleById(userId, roleId);
        if (role != null) {
            return role;
        } else {
            throw new EntityException("No role found");
        }
    }

    public Role addRoleToUser(Long userId, Long roleId) throws EntityException {
        final User user = userRepository.findById(userId);
        final Role role = roleRepository.findById(roleId);
        if (user == null) {
            throw new EntityException("No user found");
        }
        if(role == null) {
            throw new EntityException("No role found");
        }
        userRoleRepository.addRoleToUser(userId, roleId);
        return role;
    }

    public Role deleteRoleFromUser(Long userId, Long roleId) throws EntityException {
        final User user = userRepository.findById(userId);
        final Role role = roleRepository.findById(roleId);
        if (user == null) {
            throw new EntityException("No user found");
        }
        if(role == null) {
            throw new EntityException("No role found");
        }
        userRoleRepository.deleteRoleFromUser(userId, roleId);
        return role;
    }
}
