using web_shop_frontend.model.enums;

namespace web_shop_frontend.data.secure;

public class ConfidentialData
{
    public byte[] _jwtToken;
    public byte[] _refreshToken;
    public byte[] _username;
    public Role _role;
}