package com.shadow_garden.webshopbackend.service;

import com.shadow_garden.webshopbackend.dto.RevenueDto;
import com.shadow_garden.webshopbackend.repository.revenue.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RevenueService {
    @Autowired
    private RevenueRepository repository;

    public List<RevenueDto> getAllRevenue() {
        var revenues = repository.findAll();
        var a = new ArrayList<RevenueDto>();

        for (var r : revenues) {
            a.add(
                    RevenueDto
                            .builder()
                            .id(r.getId())
                            .revenueAmount(r.getRevenue_amount())
                            .saleDate(r.getSale_date())
                            .sellerId(r.getSeller().getId())
                            .storeId(r.getStore().getId())
                            .build()
            );
        }

        return a;
    }
}
