package com.shadow_garden.webshopbackend.service.supplier;

import com.shadow_garden.webshopbackend.dto.supplier.CreateSupplierRequest;
import com.shadow_garden.webshopbackend.dto.supplier.SupplierDto;
import com.shadow_garden.webshopbackend.entity.supplier.SupplierEntity;
import com.shadow_garden.webshopbackend.repository.supplier.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository repository;

    public String createSupplier(CreateSupplierRequest request) {
        var a = SupplierEntity
                .builder()
                .address(request.getAddress())
                .inn(request.getInn())
                .bank_name(request.getBankName())
                .checking_account(request.getCheckingAccount())
                .director_name(request.getDirectorName())
                .organization_name(request.getOrganizationName())
                .director_phone(request.getDirectorPhone())
                .build();

        repository.save(a);

        return "Supplier create";
    }

    public SupplierDto getSupplier(long id) throws Exception {
        var a = repository.findById(id)
                .orElseThrow(() -> new Exception("Supplier not found"));

        return  SupplierDto.toDto(a);
    }

    public List<SupplierDto> getAllSuppliers() {
        var a = repository.findAll().stream().map(SupplierDto::toDto).toList();

        return a;
    }
}
