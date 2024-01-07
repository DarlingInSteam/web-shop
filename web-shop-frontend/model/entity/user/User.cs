using Newtonsoft.Json;
using web_shop_frontend.model.enums;

namespace web_shop_frontend.model.entity;

public class User
{
    [JsonProperty("id")] public long Id { get; set; }
    [JsonProperty("username")] public string Username { get; set; }
    [JsonProperty("role")] public Role Role { get; set; }

    public User(string username, Role role, long id)
    {
        Username = username;
        this.Role = role;
        Id = id;
    }
}