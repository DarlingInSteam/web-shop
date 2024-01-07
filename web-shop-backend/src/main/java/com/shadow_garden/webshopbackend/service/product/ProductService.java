package com.shadow_garden.webshopbackend.service.product;

import com.shadow_garden.webshopbackend.dto.product.CreateProductRequest;
import com.shadow_garden.webshopbackend.dto.product.ProductDto;
import com.shadow_garden.webshopbackend.dto.product.SellProductRequest;
import com.shadow_garden.webshopbackend.entity.product.ProductEntity;
import com.shadow_garden.webshopbackend.entity.revenue.RevenueEntity;
import com.shadow_garden.webshopbackend.repository.product.ProductRepository;
import com.shadow_garden.webshopbackend.repository.revenue.RevenueRepository;
import com.shadow_garden.webshopbackend.repository.seller.SellerRepository;
import com.shadow_garden.webshopbackend.repository.store.StoreRepository;
import com.shadow_garden.webshopbackend.repository.supplier.SupplierRepository;
import com.shadow_garden.webshopbackend.repository.warehouse.WarehouseRepository;
import org.aspectj.weaver.bcel.ExceptionRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private RevenueRepository revenueRepository;

    public String createProduct(CreateProductRequest request) throws Exception {
        var a = ProductEntity
                .builder()
                .product_name(request.getProductName())
                .warehouse(
                        warehouseRepository.findById(request.getWarehouseId())
                                .orElseThrow(() -> new Exception("Warehouse not found"))
                )
                .price(request.getPrice())
                .quantity_in_stock(request.getQuantityInStock())
                .supplier(
                        supplierRepository.findById(request.getSupplierId())
                                .orElseThrow(() -> new Exception("Supplier not found"))
                )
                .build();

        repository.save(a);

        var b = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new Exception("Warehouse not found"));

        var p = b.getProductEntities();

        p.add(a);

        b.setProductEntities(p);

        warehouseRepository.save(b);

        return "Product create";
    }

    public ProductDto getProduct(long id) throws Exception {
        var a = repository.findById(id)
                .orElseThrow(() -> new Exception("Product not found"));

        return ProductDto.toDto(a);
    }

    public String sellProduct(SellProductRequest request) throws Exception {
        var a = repository.findById(request.getProductId())
                .orElseThrow(() -> new Exception("Product not found"));

        if (a.getQuantity_in_stock() - request.getQuantity() >= 0) {
            a.setQuantity_in_stock(a.getQuantity_in_stock() - request.getQuantity());

            repository.save(a);

            var rev = RevenueEntity
                    .builder()
                    .store(
                        storeRepository.findByWarehouse(a.getWarehouse())
                                .orElseThrow(() -> new Exception("Store not found"))
                    )
                    .seller(
                        sellerRepository.findById(request.getSellerId())
                                .orElseThrow(() -> new Exception("Seller not found"))
                    )
                    .revenue_amount(request.getQuantity() * a.getPrice())
                    .sale_date("Date")
                    .build();

            repository.save(a);
            revenueRepository.save(rev);

            return "Product sell";
        }

        return "U can't sell this quantity of products";
    }

    public String addNewStockInProduct(int stock, long id) throws Exception {
        var a = repository.findById(id)
                .orElseThrow(() -> new Exception("Product not found"));

        a.setQuantity_in_stock(a.getQuantity_in_stock() + stock);

        repository.save(a);

        return "Nice";
    }
}
