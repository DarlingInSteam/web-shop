using Newtonsoft.Json;

namespace web_shop_frontend.model.entity;

public class Revenue
{
    [JsonProperty("id")] public long Id { get; set; }
    [JsonProperty("sellerId")] public long SellerId { get; set; }
    [JsonProperty("storeId")] public long StoreId { get; set; }
    [JsonProperty("revenueAmount")] public int RevenueAmount { get; set; }
    [JsonProperty("saleDate")] public string SaleDate { get; set; }
    
    public Revenue(long id, long sellerId, long storeId, int revenueAmount, string saleDate)
    {
        Id = id;
        SellerId = sellerId;
        StoreId = storeId;
        RevenueAmount = revenueAmount;
        SaleDate = saleDate;
    }
}