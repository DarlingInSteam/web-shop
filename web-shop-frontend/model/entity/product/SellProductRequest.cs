using Newtonsoft.Json;

namespace web_shop_frontend.model.entity.product;

public class SellProductRequest
{
    [JsonProperty("productId")] public long ProductId { get; set; }
    [JsonProperty("sellerId")] public long SellerId { get; set; }
    [JsonProperty("quantity")] public int Quantity { get; set; }

    public SellProductRequest(long productId, long sellerId, int quantity)
    {
        ProductId = productId;
        SellerId = sellerId;
        Quantity = quantity;
    }
}