package com.shadow_garden.webshopbackend.service.store;

import com.shadow_garden.webshopbackend.dto.product.ProductDto;
import com.shadow_garden.webshopbackend.dto.seller.SellerDto;
import com.shadow_garden.webshopbackend.dto.store.CreateStoreRequest;
import com.shadow_garden.webshopbackend.dto.store.StoreDto;
import com.shadow_garden.webshopbackend.entity.store.StoreEntity;
import com.shadow_garden.webshopbackend.repository.store.StoreRepository;
import com.shadow_garden.webshopbackend.repository.warehouse.WarehouseRepository;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    private StoreRepository repository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public String createStore(CreateStoreRequest request) throws Exception {
        var a = StoreEntity
                .builder()
                .storeName(request.getStoreName())
                .location(request.getLocation())
                .warehouse(
                    warehouseRepository.findById(request.getWarehouseId())
                            .orElseThrow(() -> new Exception("Warehouse not found"))
                )
                .build();

        repository.save(a);

        return "Store create";
    }

    public List<SellerDto> getAllSellers(long id) throws Exception {
        var a = repository.findById(id)
                .orElseThrow(() -> new Exception("Store not found"));

        var b = a.getSellerEntities().stream().map(SellerDto::toDto).toList();

        return b;
    }

    public StoreDto getStore(long id) throws Exception {
        var a = repository.findById(id)
                .orElseThrow(() -> new Exception("Store not found"));

        return StoreDto.toDto(a);
    }

    public List<StoreDto> getAllStores() {
        var a = repository.findAll();

        return a.stream().map(StoreDto::toDto).toList();
    }

    public List<ProductDto> getAllProductsInStore(long id) throws Exception {
        var a = repository.findById(id)
                .orElseThrow(() -> new Exception("Store not found"));

        return a.getWarehouse().getProductEntities().stream().map(ProductDto::toDto).toList();
    }
}
