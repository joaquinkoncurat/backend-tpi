package com.tpi.backend.requests_service.clients;

import com.tpi.backend.requests_service.dto.ContenedorRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "containers-service", url = "http://localhost:8084") // cambiar puerto según tu config
public interface ContainersClient {
    @PostMapping("/contenedores")
    Long crearContenedor(@RequestBody ContenedorRequest request);
}
