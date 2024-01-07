using System;
using Newtonsoft.Json;
using web_shop_frontend.model.enums;

namespace web_shop_frontend.model.entity;

public class RegisterRequest
{
    [JsonProperty("username")]
    public string Username { get; set; }
    
    [JsonProperty("password")]
    public string Password { get; set; }
    
    [JsonProperty("role")]
    public Role Role { get; set; }
    
    public RegisterRequest(string username, string password, Role role)
    {
        if (string.IsNullOrEmpty(username))
            throw new ArgumentException("Username can't be null or empty");
        if (string.IsNullOrEmpty(password))
            throw new ArgumentException("Password can't be null or empty");
        
        Username = username;
        Password = password;
        this.Role = role;
    }
}