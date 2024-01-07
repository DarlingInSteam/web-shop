using Newtonsoft.Json;

namespace web_shop_frontend.model.entity.warehouse;

public class Warehouse
{
    [JsonProperty("id")] public long Id { get; set; }
    [JsonProperty("quantityInStock")] public int QuantityInStock { get; set; }

    public Warehouse(long id, int quantityInStock)
    {
        Id = id;
        QuantityInStock = quantityInStock;
    }
}