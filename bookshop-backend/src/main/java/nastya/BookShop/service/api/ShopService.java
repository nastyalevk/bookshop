package nastya.BookShop.service.api;

import nastya.BookShop.dto.shop.ShopDto;

import java.util.List;

public interface ShopService {

    ShopDto saveShop(ShopDto shopDto, String username);

    List<ShopDto> userShops(String username);

    List<ShopDto> getShopsByBook(Integer id);

    ShopDto getOne(Integer id);

}
