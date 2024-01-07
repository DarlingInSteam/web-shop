using Refit;
using web_shop_frontend.data.service;

namespace web_shop_frontend.data.client;

public class AuthenticationClient
{
    public IAuthenticationService authenticationService;
    
    public AuthenticationClient()
    {
        authenticationService = RestService.For<IAuthenticationService>("http://localhost:8080");
    }
}