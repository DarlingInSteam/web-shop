﻿using Newtonsoft.Json;

namespace web_shop_frontend.model.entity.product;

public class Product
{
    [JsonProperty("id")] public long Id { get; set; }
    [JsonProperty("supplierId")] public long SupplierId { get; set; }
    [JsonProperty("productName")] public string ProductName { get; set; }
    [JsonProperty("quantityInStock")] public int QuantityInStock{ get; set; } 
    [JsonProperty("price")] public int Price { get; set; }
    [JsonProperty("warehouseId")] public long WarehouseId { get; set; }

    public Product(long id, long supplierId, string productName, int quantityInStock, int price, long warehouseId)
    {
        Id = id;
        SupplierId = supplierId;
        ProductName = productName;
        QuantityInStock = quantityInStock;
        Price = price;
        WarehouseId = warehouseId;
    }
}