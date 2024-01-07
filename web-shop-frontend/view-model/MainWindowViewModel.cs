using System;
using System.Collections.ObjectModel;
using System.Windows.Input;
using System.Windows.Media;
using Microsoft.Xaml.Behaviors.Core;
using web_shop_frontend.data.api_request;
using web_shop_frontend.data.secure;
using web_shop_frontend.model.entity.product;
using web_shop_frontend.model.entity.supplier;
using web_shop_frontend.model.entity.warehouse;

namespace web_shop_frontend.view_model;

public class MainWindowViewModel : ViewModelBase
{
    private ObservableCollection<Product> _products;
    private ObservableCollection<Supplier> _suppliers;
    private ObservableCollection<Warehouse> _warehouses;
    private SecureDataStorage _secureDataStorage;
    private Product _selectedProduct;
    private Supplier _selectedSupplier;
    private Warehouse _selectedWarehouse;
    private int _newCount;
    private bool _newCountVisible;
    private bool _visabilityAddProduct;
    private string _productName;
    private int _quantityInStock;
    private int _price;
    
    public ICommand DeleteProductCommand { get; }
    public ICommand CreateProductCommand { get; }
    public ICommand AddNewCountToProduct { get; }

    public MainWindowViewModel(SecureDataStorage secureDataStorage)
    {
        _products = new ObservableCollection<Product>();
        _suppliers = new ObservableCollection<Supplier>();
        _warehouses = new ObservableCollection<Warehouse>();
        _newCountVisible = false;
        VisabilityAddProduct = false;
        _newCount = 0;
        _secureDataStorage = secureDataStorage;
        AddNewCountToProduct = new RelayCommand<object>(AddCount);
        CreateProductCommand = new RelayCommand<object>(ChangeVisability);
        GetAllProducts();
        GetAllSuppliers();
        GetAllWarehouses();
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
    
    public bool VisabilityAddProduct
    {
        get => _visabilityAddProduct;

        set
        {
            _visabilityAddProduct = value;
            OnPropertyChange(nameof(VisabilityAddProduct));
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
            GetAllProducts();
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