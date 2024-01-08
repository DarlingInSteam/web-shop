using System;
using System.Collections.ObjectModel;
using System.Windows.Input;
using System.Windows.Media;
using Microsoft.Xaml.Behaviors.Core;
using web_shop_frontend.data.api_request;
using web_shop_frontend.data.secure;
using web_shop_frontend.model.entity;
using web_shop_frontend.model.entity.product;
using web_shop_frontend.model.entity.supplier;
using web_shop_frontend.model.entity.warehouse;
using web_shop_frontend.model.enums;

namespace web_shop_frontend.view_model;

public class MainWindowViewModel : ViewModelBase
{
    private ObservableCollection<Product> _products;
    private ObservableCollection<Supplier> _suppliers;
    private ObservableCollection<Warehouse> _warehouses;
    private ObservableCollection<Revenue> _revenues;

    private SecureDataStorage _secureDataStorage;
    private Product _selectedProduct;
    private Supplier _selectedSupplier;
    private Warehouse _selectedWarehouse;
    private int _newCount;
    private bool _newCountVisible;
    private bool _visibilityAdminPanel;
    private string _productName;
    private int _quantityInStock;
    private int _price;
    private int _sellProduct;
    private long _sellerId;
    private long _storeId;
    private int _revenueSeller;
    private int _revenueStore;
    
    public ICommand DeleteProductCommand { get; }
    public ICommand CreateProductCommand { get; }
    public ICommand AddNewCountToProduct { get; }
    public ICommand SellProductButton { get; }

    public ICommand CalculateSeller { get; }
    public ICommand CalculateStore { get; }


    public MainWindowViewModel(SecureDataStorage secureDataStorage)
    {
        _products = new ObservableCollection<Product>();
        _suppliers = new ObservableCollection<Supplier>();
        _warehouses = new ObservableCollection<Warehouse>();
        _revenues = new ObservableCollection<Revenue>();
        _newCountVisible = false;
        _newCount = 0;
        _secureDataStorage = secureDataStorage;

        SetVisibilityBasedOnRole();
        
        if (VisibilityAdminPanel == true)
        {
            AddNewCountToProduct = new RelayCommand<object>(AddCount);
            CalculateSeller = new RelayCommand<object>(CalculatingSeller);
            CalculateStore = new RelayCommand<object>(CalculatingStore);
            GetAllProductAdmin();
            GetAllRevenue();
        }
        else
        {
            AddNewCountToProduct = new RelayCommand<object>(AddCount);
            CreateProductCommand = new RelayCommand<object>(ChangeVisability);
            SellProductButton = new RelayCommand<object>(SellButton);
            GetAllProducts();
            GetAllSuppliers();
            GetAllWarehouses();
        }
        
    }

    private void CalculatingSeller(object r)
    {
        int sum = 0;
        
        foreach (var revenue in Revenues)
        {
            if (revenue.SellerId == SellerId) sum += revenue.RevenueAmount;
        }

        RevenueSeller = sum;
    }

    private void CalculatingStore(object r)
    {
        int sum = 0;

        foreach (var revenue in Revenues)
        {
            if (revenue.StoreId == StoreId) sum += revenue.RevenueAmount;
        }

        RevenueStore = sum;
    }

    public int RevenueSeller
    {
        get => _revenueSeller;

        set
        {
            _revenueSeller = value;
            
            OnPropertyChange(nameof(RevenueSeller));
        }
    }

    public int RevenueStore
    {
        get => _revenueStore;

        set
        {
            _revenueStore = value;
            OnPropertyChange(nameof(RevenueStore));
        }
    }
    
    public long SellerId
    {
        get => _sellerId;

        set
        {
            _sellerId = value;
            OnPropertyChange(nameof(SellerId));
        }
    }

    public long StoreId
    {
        get => _storeId;

        set
        {
            _storeId = value;
            OnPropertyChange(nameof(StoreId));
        }
    }
    
    public ObservableCollection<Supplier> Suppliers
    {
        get => _suppliers;

        set
        {
            _suppliers = value;
            OnPropertyChange(nameof(Suppliers));
        }
    }
    
    public ObservableCollection<Product> Products
    {
        get => _products;

        set
        {
            _products = value;
            OnPropertyChange(nameof(Products));
        }
    }

    public ObservableCollection<Warehouse> Warehouses
    {
        get => _warehouses;

        set
        {
            _warehouses = value;
            OnPropertyChange(nameof(Warehouses));
        }
    }

    public string ProductName
    {
        get => _productName;

        set
        {
            _productName = value;
            OnPropertyChange(nameof(ProductName));
        }
    }

    public string SellProduct
    {
        get => _sellProduct.ToString();

        set
        {
            _sellProduct = Convert.ToInt32(value);
            OnPropertyChange(SellProduct);
        }
    }
    
    public string QuantityInStock
    {
        get => _quantityInStock.ToString();

        set
        {
            _quantityInStock = Convert.ToInt32(value);
            OnPropertyChange(nameof(QuantityInStock));
        }
    }
    
    public string Price
    {
        get => _price.ToString();

        set
        {
            _price = Convert.ToInt32(value);
            OnPropertyChange(nameof(Price));
        }
    }
    
    public Supplier SelectedSupplier
    {
        get => _selectedSupplier;

        set
        {
            _selectedSupplier = value;
            OnPropertyChange(nameof(SelectedSupplier));
        }
    }

    public Warehouse SelectedWarehouse
    {
        get => _selectedWarehouse;

        set
        {
            _selectedWarehouse = value;
            OnPropertyChange(nameof(SelectedWarehouse));
        }
    }

    public ObservableCollection<Revenue> Revenues
    {
        get => _revenues;

        set
        {
            _revenues = value;
            OnPropertyChange(nameof(Revenues));
        }
    }
    
    public bool VisibilityAdminPanel
    {
        get => _visibilityAdminPanel;

        set
        {
            _visibilityAdminPanel = value;
            OnPropertyChange(nameof(VisibilityAdminPanel));
        }
    }

    private void SetVisibilityBasedOnRole()
    {
        if (_secureDataStorage.Role == Role.ADMIN)
        {
            VisibilityAdminPanel = true;
        }
        else
        {
            VisibilityAdminPanel = false;
        }
    }
    
    public Product SelectedProduct
    {
        get => _selectedProduct;

        set
        {
            _selectedProduct = value;
            OnPropertyChange(nameof(SelectedProduct));
        }
    }

    private async void GetAllProductAdmin()
    {
        try
        {
            var req = new SellerRequest(_secureDataStorage);
            var res = await req.GetAllProduct();

            Products.Clear();
            
            foreach (var r in res)
            {
                Products.Add(r);
            }
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }
    
    private async void SellButton(object parameter)
    {
        try
        {
            var req = new SellerRequest(_secureDataStorage);
            var resp = await req.SellProduct(new SellProductRequest(_selectedProduct.Id, 0, _sellProduct));
            
            GetAllProducts();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }
    
    private async void ChangeVisability(object parameter)
    {
        try
        {
            var req = new SellerRequest(_secureDataStorage);
            var resp = await req.CreateProduct(new CreateProductRequest(_selectedSupplier.Id, ProductName, _quantityInStock,
                _price, _selectedWarehouse.Id));
            
            GetAllProducts();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }
    
    private async void AddCount(object parameter)
    {
        try
        {
            var req = new SellerRequest(_secureDataStorage);
            var resp = await req.AddCount(_selectedProduct.Id, 100);
            if (_secureDataStorage.Role != Role.ADMIN) GetAllProducts();
            else GetAllProductAdmin();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }

    private async void GetAllRevenue()
    {
        try
        {
            var req = new SellerRequest(_secureDataStorage);
            var res = await req.GetAllRevenue();

            foreach (var r in res)
            {
                Revenues.Add(r);
            }
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }
    
    private async void GetAllWarehouses()
    {
        try
        {
            var req = new SellerRequest(_secureDataStorage);
            var resp = await req.GetWarehouse();
            
            Warehouses.Add(resp);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }
    
    private async void GetAllSuppliers()
    {
        try
        {
            var req = new SellerRequest(_secureDataStorage);
            var resp = await req.GetAllSuppliers();

            foreach (var re in resp)
            {
                Suppliers.Add(re);
            }
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }
    
    private async void GetAllProducts()
    {
        try
        {
            var req = new SellerRequest(_secureDataStorage);
            var resp = await req.GetAllProducts();
            
            Products.Clear();
            
            foreach (var r in resp)
            {
                Products.Add(r);    
            }
            
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }
}   