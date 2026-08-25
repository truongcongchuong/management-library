/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.bookstore.book_management.Service;

import org.springframework.stereotype.Service;
import com.bookstore.book_management.Repository.RoleRepository;
import com.bookstore.book_management.Entity.Role;
import com.bookstore.book_management.Dto.ApiResponse;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(
            RoleRepository roleRepository
    ) {
        this.roleRepository = roleRepository;
    }

    public ApiResponse<?> getAllRoles() {

        return ApiResponse.ok(
                roleRepository.findAll()
        );
    }

    public ApiResponse<?> createRole(
            Role role
    ) {

        try {

            Role saved =
                    roleRepository.save(role);

            return ApiResponse.created(
                    saved,
                    "Role created successfully"
            );

        } catch (Exception e) {

            return ApiResponse.internalServerError();
        }
    }

    public ApiResponse<?> getRoleById(
            Long id
    ) {

        Role role =
                roleRepository.findById(id)
                        .orElse(null);

        if (role == null) {

            return ApiResponse.notFound(
                    "Role not found"
            );
        }

        return ApiResponse.ok(role);
    }

    public ApiResponse<?> updateRole(
            Long id,
            Role updatedRole
    ) {

        Role existingRole =
                roleRepository.findById(id)
                        .orElse(null);

        if (existingRole == null) {

            return ApiResponse.notFound(
                    "Role not found"
            );
        }

        try {

            existingRole.setName(
                    updatedRole.getName()
            );

            Role saved =
                    roleRepository.save(
                            existingRole
                    );

            return ApiResponse.ok(
                    saved,
                    "Role updated successfully"
            );

        } catch (Exception e) {

            return ApiResponse.internalServerError();
        }
    }
    @Transactional
    public ApiResponse<?> deleteRole(
            Long id
    ) {

        Role role =
                roleRepository.findById(id)
                        .orElse(null);

        if (role == null) {

            return ApiResponse.notFound(
                    "Role not found"
            );
        }

        try {

            roleRepository.delete(role);

            return ApiResponse.noContent();

        } catch (Exception e) {

            return ApiResponse.internalServerError();
        }
    }
}
