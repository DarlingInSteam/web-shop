using Refit;
using web_shop_frontend.data.service;

namespace web_shop_frontend.data.client;

public class SellerClient
{
    public ISellerService SellerService;

    public SellerClient()
    {
        SellerService = RestService.For<ISellerService>("http://localhost:8080");
    }
}