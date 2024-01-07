using System.Threading.Tasks;
using web_shop_frontend.data.client;
using web_shop_frontend.data.secure;
using web_shop_frontend.data.service;
using web_shop_frontend.model.entity;

namespace web_shop_frontend.data.api_request;

public class AuthenticationRequest
{
    private AuthenticationClient _authenticationClient;
    private IAuthenticationService _api;
    private SecureDataStorage _secureDataStorage;
    
    public AuthenticationRequest(SecureDataStorage secureDataStorage)
    {
        _secureDataStorage = secureDataStorage;
        _authenticationClient = new AuthenticationClient();
        _api = _authenticationClient.authenticationService;
    }
    
    public async Task<AuthenticationResponse> LoginUser(web_shop_frontend.model.entity.AuthenticationRequest request)
    {
        AuthenticationResponse response = await _api.Login(request);
        UpdateSecureStorage(response);
        return response;
    }
    
    private void UpdateSecureStorage(AuthenticationResponse response)
    {
        _secureDataStorage.Role = response.Role;
        _secureDataStorage.JwtToken = response.AccessToken;
        _secureDataStorage.RefreshToken = response.RefreshToken;
        _secureDataStorage.Username = response.Username;
    }
}