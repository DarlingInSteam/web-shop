using Newtonsoft.Json;
using web_shop_frontend.model.enums;

namespace web_shop_frontend.model.entity;

public class AuthenticationResponse
{
    [JsonProperty("accessToken")]
    public string AccessToken { get; set; }
    
    [JsonProperty("refreshToken")]
    public string RefreshToken { get; set; }
    
    [JsonProperty("username")]
    public string Username { get; set; }
    
    [JsonProperty("role")]
    public Role Role { get; set; }
    
    public AuthenticationResponse(string accessToken, string refreshToken, string username, Role role)
    {
        AccessToken = accessToken;
        RefreshToken = refreshToken;
        Username = username;
        Role = role;
    }
}