package com.shadow_garden.webshopbackend.service.seller;

import com.shadow_garden.webshopbackend.dto.seller.CreateSellerRequest;
import com.shadow_garden.webshopbackend.dto.seller.SellerDto;
import com.shadow_garden.webshopbackend.dto.store.StoreDto;
import com.shadow_garden.webshopbackend.entity.seller.SellerEntity;
import com.shadow_garden.webshopbackend.exception.user.UserNotFoundException;
import com.shadow_garden.webshopbackend.repository.seller.SellerRepository;
import com.shadow_garden.webshopbackend.repository.store.StoreRepository;
import com.shadow_garden.webshopbackend.repository.user.UserRepository;
import com.shadow_garden.webshopbackend.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    private SellerRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreService service;

    public String createSeller(CreateSellerRequest request) throws Exception {
        var a = SellerEntity
                .builder()
                .name(request.getName())
                .salary(request.getSalary())
                .hireDate(request.getHireDate())
                .user(
                        userRepository.findByUsername(request.getUsername())
                                .orElseThrow(() -> new UserNotFoundException("User not found"))
                )
                .build();

        repository.save(a);

        var b = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new Exception("Store not found"));

        var s = b.getSellerEntities();
        s.add(a);
        b.setSellerEntities(s);

        storeRepository.save(b);

        return "Seller create";
    }

    public StoreDto getStoreBySellerId(long id) throws Exception {
        var seller = repository.findById(id)
                .orElseThrow(() -> new Exception("Seller not found"));

        var stores = storeRepository.findAll();
        StoreDto storeDto = new StoreDto();
        for (var st : stores) {
            if (st.getSellerEntities().contains(seller)) {
                storeDto = StoreDto.toDto(st);
                storeDto.setWarehouseId(st.getWarehouse().getId());
                break;
            }
        }

        return storeDto;
    }

    public SellerDto getSellerById(long id) throws Exception {
        var seller = repository.findById(id)
                .orElseThrow(() -> new Exception("Seller not found"));

        return SellerDto.toDto(seller);
    }

    public SellerDto getSellerByUser(String username) throws Exception {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));

        var seller = repository.findByUser(user)
                .orElseThrow(() -> new Exception("Seller not found"));

        return SellerDto.toDto(seller);
    }
}
