using Newtonsoft.Json;

namespace web_shop_frontend.model.entity.store;

public class Store
{
    [JsonProperty("id")] public long Id { get; set; }
    [JsonProperty("warehouseId")] public long WarehouseId { get; set; }
    [JsonProperty("storeName")] public string StoreName { get; set; }
    [JsonProperty("location")] public string Location { get; set; }

    public Store(long id, long warehouseId, string storeName, string location)
    {
        Id = id;
        WarehouseId = warehouseId;
        StoreName = storeName;
        Location = location;
    }
}