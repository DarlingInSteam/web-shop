package com.shadow_garden.webshopbackend.service.warehouse;

import com.shadow_garden.webshopbackend.dto.warehouse.WarehouseDto;
import com.shadow_garden.webshopbackend.entity.warehouse.WarehouseEntity;
import com.shadow_garden.webshopbackend.repository.warehouse.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository repository;

    public String createWarehouse(int stock) {
        var a = WarehouseEntity
                .builder()
                .quantityInStock(stock)
                .build();

        repository.save(a);

        return "Warehouse created";
    }

    public List<WarehouseDto> getAllWarehouses() {
        var a = repository.findAll();

        return a.stream().map(WarehouseDto::toDto).toList();
    }

    public WarehouseDto getWarehouse(long id) throws Exception {
        var a = repository.findById(id)
                .orElseThrow(() -> new Exception("Warehouse not found"));

        return WarehouseDto.toDto(a);
    }
}
