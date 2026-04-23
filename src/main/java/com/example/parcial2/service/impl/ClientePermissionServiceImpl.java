package com.example.parcial2.service.impl;

import com.example.parcial2.entity.Role;
import com.example.parcial2.entity.User;
import com.example.parcial2.exception.custom.UnauthorizedAccessException;
import com.example.parcial2.service.ClientePermissionService;
import org.springframework.stereotype.Service;

@Service
public class ClientePermissionServiceImpl implements ClientePermissionService {

    @Override
    public void validarPermisoEliminarCliente(User user) {
        if (user == null || user.getRole() != Role.ROLE_ADMIN) {
            throw new UnauthorizedAccessException("Solo un ADMIN puede eliminar clientes");
        }
    }
}
