package com.tpi.backend.depots_service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.tpi.backend.depots_service.models.Deposito;
import com.tpi.backend.depots_service.repositories.DepositoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositoService {

    private final DepositoRepository depositoRepository;

    public List<Deposito> obtenerTodos() {
        return depositoRepository.findAll();
    }

    public Deposito obtenerPorId(Long id) {
        return depositoRepository.findById(id).orElseThrow();
    }

    public Deposito crear(Deposito d) {
        return depositoRepository.save(d);
    }

}
