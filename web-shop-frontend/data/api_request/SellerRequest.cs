using System.Collections.Generic;
using System.Threading.Tasks;
using web_shop_frontend.data.client;
using web_shop_frontend.data.secure;
using web_shop_frontend.data.service;
using web_shop_frontend.model.entity.product;
using web_shop_frontend.model.entity.seller;
using web_shop_frontend.model.entity.store;
using web_shop_frontend.model.entity.supplier;
using web_shop_frontend.model.entity.warehouse;

namespace web_shop_frontend.data.api_request;

public class SellerRequest
{
    private SellerClient _sellerClient;
    private ISellerService _api;
    private SecureDataStorage _secureDataStorage;

    public SellerRequest(SecureDataStorage secureDataStorage)
    {
        _secureDataStorage = secureDataStorage;
        _sellerClient = new SellerClient();
        _api = _sellerClient.SellerService;
    }

    public async Task<List<Product>> GetAllProducts()
    {
        List<Product> products = new List<Product>();
        var a = await GetStoreBySeller();
        products = await _api.GetAllProductsInStore(a.Id);
        return products;
    }

    public async Task<Store> GetStoreBySeller()
    {
        var a = await GetSellerByUser();
        Store store = await _api.GetStore(a.Id);
        return store;
    }

    public async Task<Seller> GetSellerByUser()
    {
        Seller seller = await _api.GetSellerByUser(_secureDataStorage.Username);
        return seller;
    }

    public async Task<string> AddCount(long id, int stock)
    {
        string a = await _api.AddCount(id, stock);
        return a;
    }

    public async Task<List<Supplier>> GetAllSuppliers()
    {
        var a = await _api.GetAllSuppliers();
        return a;
    }

    public async Task<Warehouse> GetWarehouse()
    {
        var a = await GetStoreBySeller();
        var b = await _api.GetWarehouse(a.WarehouseId);
        return b;
    }

    public async Task<string> CreateProduct(CreateProductRequest request)
    {
        var a = await _api.CreateProduct(request);
        return a;
    }
}