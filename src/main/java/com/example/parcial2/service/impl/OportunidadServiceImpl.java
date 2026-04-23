package com.example.parcial2.service.impl;

import com.example.parcial2.dto.request.OportunidadRequest;
import com.example.parcial2.dto.response.OportunidadResponse;
import com.example.parcial2.entity.Cliente;
import com.example.parcial2.entity.Oportunidad;
import com.example.parcial2.entity.User;
import com.example.parcial2.exception.custom.ResourceNotFoundException;
import com.example.parcial2.mapper.OportunidadMapper;
import com.example.parcial2.repository.ClienteRepository;
import com.example.parcial2.repository.OportunidadRepository;
import com.example.parcial2.repository.UserRepository;
import com.example.parcial2.service.OportunidadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OportunidadServiceImpl implements OportunidadService {

    private final OportunidadRepository oportunidadRepository;
    private final ClienteRepository clienteRepository;
    private final UserRepository userRepository;
    private final OportunidadMapper oportunidadMapper;

    public OportunidadServiceImpl(OportunidadRepository oportunidadRepository,
                                  ClienteRepository clienteRepository,
                                  UserRepository userRepository,
                                  OportunidadMapper oportunidadMapper) {
        this.oportunidadRepository = oportunidadRepository;
        this.clienteRepository = clienteRepository;
        this.userRepository = userRepository;
        this.oportunidadMapper = oportunidadMapper;
    }

    @Override
    @Transactional
    public OportunidadResponse crearOportunidad(Long clienteId, OportunidadRequest request) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente with id " + clienteId + " not found"));

        User creadoPor = userRepository.findById(request.getCreadoPorId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario with id " + request.getCreadoPorId() + " not found"));

        Oportunidad oportunidad = oportunidadMapper.toEntity(request);
        oportunidad.setCliente(cliente);
        oportunidad.setCreadoPor(creadoPor);

        return oportunidadMapper.toResponse(oportunidadRepository.save(oportunidad));
    }
}
