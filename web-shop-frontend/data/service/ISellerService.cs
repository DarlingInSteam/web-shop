using System.Collections.Generic;
using System.Threading.Tasks;
using Refit;
using web_shop_frontend.model.entity;
using web_shop_frontend.model.entity.product;
using web_shop_frontend.model.entity.seller;
using web_shop_frontend.model.entity.store;
using web_shop_frontend.model.entity.supplier;
using web_shop_frontend.model.entity.warehouse;

namespace web_shop_frontend.data.service;

public interface ISellerService
{
    [Post("/product/create")]
    Task<string> CreateProduct([Body] CreateProductRequest request);

    [Delete("/product/sell_product")]
    Task<string> SellProduct([Body] SellProductRequest request);

    [Get("/product/{id}")]
    Task<Product> GetProduct(long id);

    [Post("/product/{id}/add_count")]
    Task<string> AddCount(long id, [Query] int stock);

    [Get("/seller/get_store")]
    Task<Store> GetStore([Query] long id);

    [Get("/seller/get_seller_by_user")]
    Task<Seller> GetSellerByUser([Query] string username);

    [Get("/store/all_stores/{id}/products")]
    Task<List<Product>> GetAllProductsInStore(long id);

    [Get("/supplier/all_suppliers")]
    Task<List<Supplier>> GetAllSuppliers();

    [Get("/supplier/all_suppliers/{id}")]
    Task<Supplier> GetSupplier(long id);

    [Get("/user/all_users/{username}")]
    Task<User> GetUserByUsername(string username);

    [Get("/warehouse/all_warehouse")]
    Task<List<Warehouse>> GetAllWarehouse();
    
    [Get("/warehouse/all_warehouses/get")]
    Task<Warehouse> GetWarehouse([Query] long id);
}