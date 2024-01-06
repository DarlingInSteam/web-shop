package com.shadow_garden.webshopbackend.service.seller;

import com.shadow_garden.webshopbackend.dto.seller.CreateSellerRequest;
import com.shadow_garden.webshopbackend.dto.seller.SellerDto;
import com.shadow_garden.webshopbackend.entity.seller.SellerEntity;
import com.shadow_garden.webshopbackend.exception.user.UserNotFoundException;
import com.shadow_garden.webshopbackend.repository.seller.SellerRepository;
import com.shadow_garden.webshopbackend.repository.store.StoreRepository;
import com.shadow_garden.webshopbackend.repository.user.UserRepository;
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

    public SellerDto getSellerById(long id) throws Exception {
        var seller = repository.findById(id)
                .orElseThrow(() -> new Exception("Seller not found"));

        return SellerDto.toDto(seller);
    }
}
