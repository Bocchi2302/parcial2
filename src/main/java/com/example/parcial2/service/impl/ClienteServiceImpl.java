package com.example.parcial2.service.impl;

import com.example.parcial2.dto.request.ClienteRequest;
import com.example.parcial2.dto.request.ContactoRequest;
import com.example.parcial2.dto.response.ClienteResponse;
import com.example.parcial2.dto.response.ContactoResponse;
import com.example.parcial2.entity.Cliente;
import com.example.parcial2.entity.Contacto;
import com.example.parcial2.entity.User;
import com.example.parcial2.exception.custom.DuplicateResourceException;
import com.example.parcial2.exception.custom.ResourceNotFoundException;
import com.example.parcial2.exception.custom.UnauthorizedAccessException;
import com.example.parcial2.mapper.ClienteMapper;
import com.example.parcial2.mapper.ContactoMapper;
import com.example.parcial2.repository.ClienteRepository;
import com.example.parcial2.repository.ContactoRepository;
import com.example.parcial2.repository.UserRepository;
import com.example.parcial2.service.ClienteCommandService;
import com.example.parcial2.service.ClientePermissionService;
import com.example.parcial2.service.ClienteQueryService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteCommandService, ClienteQueryService {

    private final ClienteRepository clienteRepository;
    private final ContactoRepository contactoRepository;
    private final UserRepository userRepository;
    private final ClienteMapper clienteMapper;
    private final ContactoMapper contactoMapper;
    private final ClientePermissionService clientePermissionService;

    public ClienteServiceImpl(ClienteRepository clienteRepository,
                              ContactoRepository contactoRepository,
                              UserRepository userRepository,
                              ClienteMapper clienteMapper,
                              ContactoMapper contactoMapper,
                              ClientePermissionService clientePermissionService) {
        this.clienteRepository = clienteRepository;
        this.contactoRepository = contactoRepository;
        this.userRepository = userRepository;
        this.clienteMapper = clienteMapper;
        this.contactoMapper = contactoMapper;
        this.clientePermissionService = clientePermissionService;
    }

    @Override
    @Transactional
    public ClienteResponse crearCliente(ClienteRequest request) {
        validateDuplicateCorreo(request.getCorreo(), null);
        User usuarioAsignado = findUser(request.getUsuarioAsignadoId());

        Cliente cliente = clienteMapper.toEntity(request);
        cliente.setUsuarioAsignado(usuarioAsignado);

        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Override
    @Transactional
    public ClienteResponse actualizarCliente(Long id, ClienteRequest request) {
        Cliente cliente = findCliente(id);
        validateDuplicateCorreo(request.getCorreo(), id);
        User usuarioAsignado = findUser(request.getUsuarioAsignadoId());

        clienteMapper.updateEntity(cliente, request);
        cliente.setUsuarioAsignado(usuarioAsignado);

        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Override
    @Transactional
    public ContactoResponse asociarContacto(Long clienteId, ContactoRequest request) {
        Cliente cliente = findCliente(clienteId);
        Contacto contacto = contactoMapper.toEntity(request);
        cliente.addContacto(contacto);
        Contacto saved = contactoRepository.save(contacto);
        return contactoMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void eliminarCliente(Long id) {
        Cliente cliente = findCliente(id);
        User user = getAuthenticatedUser();
        clientePermissionService.validarPermisoEliminarCliente(user);
        clienteRepository.delete(cliente);
    }

    @Override
    public ClienteResponse obtenerClientePorId(Long id) {
        return clienteMapper.toResponse(findCliente(id));
    }

    @Override
    public List<ClienteResponse> listarClientes() {
        return clienteRepository.findAll().stream().map(clienteMapper::toResponse).toList();
    }

    private Cliente findCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente with id " + id + " not found"));
    }

    private User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario with id " + id + " not found"));
    }

    private void validateDuplicateCorreo(String correo, Long excludeId) {
        clienteRepository.findByCorreoIgnoreCase(correo).ifPresent(existing -> {
            if (excludeId == null || !existing.getId().equals(excludeId)) {
                throw new DuplicateResourceException("Cliente with email " + correo + " already exists");
            }
        });
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof User user)) {
            throw new UnauthorizedAccessException("No se pudo identificar el usuario autenticado");
        }
        return user;
    }
}
