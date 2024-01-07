using System.Threading.Tasks;
using Refit;
using web_shop_frontend.model.entity;

namespace web_shop_frontend.data.service;

public interface IAuthenticationService
{
    [Post("/auth/login")]
    Task<AuthenticationResponse> Login([Body] AuthenticationRequest request);
	
	
}