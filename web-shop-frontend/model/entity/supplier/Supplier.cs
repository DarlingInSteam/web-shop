using Newtonsoft.Json;

namespace web_shop_frontend.model.entity.supplier;

public class Supplier
{
    [JsonProperty("id")] public long Id { get; set; }
    [JsonProperty("organizationName")] public string OrganizationName { get; set; }
    [JsonProperty("address")] public string Address { get; set; }
    [JsonProperty("directorName")] public string DirectorName { get; set; }
    [JsonProperty("directorPhone")] public string DirectorPhone { get; set; }
    [JsonProperty("bankName")] public string BankName { get; set; }
    [JsonProperty("checkingAccount")] public string CheckingAccount { get; set; }
    [JsonProperty("inn")] public string Inn { get; set; }

    public Supplier(long id, string organizationName, string address, string directorName, string directorPhone, string bankName, string checkingAccount, string inn)
    {
        Id = id;
        OrganizationName = organizationName;
        Address = address;
        DirectorName = directorName;
        DirectorPhone = directorPhone;
        BankName = bankName;
        CheckingAccount = checkingAccount;
        Inn = inn;
    }
}