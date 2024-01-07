using System.Text.Json;
using Newtonsoft.Json;

namespace web_shop_frontend.model.entity.seller;

public class Seller
{
    public Seller(long id, long userId, string name, int salary, string hireDate)
    {
        Id = id;
        UserId = userId;
        Name = name;
        Salary = salary;
        HireDate = hireDate;
    }
    
    [JsonProperty("id")] public long Id { get; set; }
    [JsonProperty("userId")] public long UserId { get; set; }
    [JsonProperty("name")] public string Name { get; set; }
    [JsonProperty("salary")] public int Salary { get; set; }
    [JsonProperty("hireDate")] public string HireDate { get; set; }
}