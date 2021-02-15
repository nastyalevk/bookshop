package nastya.BookShop.service.impl;

import nastya.BookShop.dto.classification.ClassificationParent;
import nastya.BookShop.dto.shop.ShopClassification;
import nastya.BookShop.dto.shop.ShopDto;
import nastya.BookShop.exception.NoAccessException;
import nastya.BookShop.model.Assortment;
import nastya.BookShop.model.Shop;
import nastya.BookShop.repository.AssortmentRepository;
import nastya.BookShop.repository.ClassificationRepository;
import nastya.BookShop.repository.ShopRepository;
import nastya.BookShop.repository.UserRepository;
import nastya.BookShop.service.api.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ClassificationRepository classificationRepository;
    private final UserRepository userRepository;
    private final AssortmentRepository assortmentRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, ClassificationRepository classificationRepository,
                           UserRepository userRepository, AssortmentRepository assortmentRepository) {
        this.shopRepository = shopRepository;
        this.classificationRepository = classificationRepository;
        this.userRepository = userRepository;
        this.assortmentRepository = assortmentRepository;
    }

    @Override
    public ShopDto saveShop(ShopDto shopDto, String username) {
        if(shopRepository.existsById(shopDto.getId())){
            if(!shopRepository.getOne(shopDto.getId()).getUser().getUsername().equals(username)){
                throw new NoAccessException("You dont have access for this action!");
            }
        }
        return transfer(shopRepository.save(transfer((shopDto))));
    }

    @Override
    public List<ShopDto> userShops(String username) {
        return transfer(shopRepository.findAllByUserUsername(username));
    }

    @Override
    public List<ShopDto> getShopsByBook(Integer id) {
        List<ShopDto> result = new ArrayList<>();
        List<Assortment> assortments = assortmentRepository.
                findByAssortmentIdBookIdAndAndAssortmentIdShopClassificationName(id, ShopClassification.OPEN.getName());
        for (Assortment i : assortments) {
            result.add(transfer(i.getAssortmentId().getShop()));
        }
        return result;
    }

    @Override
    public ShopDto getOne(Integer id) {
        return transfer(shopRepository.getOne(id));
    }

    private List<ShopDto> transfer(List<Shop> shops) {
        List<ShopDto> shopDtos = new ArrayList<>();
        for (Shop i : shops) {
            shopDtos.add(transfer(i));
        }
        return shopDtos;
    }

    private ShopDto transfer(Shop shop) {
        ShopDto shopDto = new ShopDto();
        shopDto.setId(shop.getId());
        shopDto.setShopName(shop.getShopName());
        shopDto.setCountry(shop.getCountry());
        shopDto.setCity(shop.getCity());
        shopDto.setAddress(shop.getAddress());
        shopDto.setDescription(shop.getDescription());
        shopDto.setClassification(ShopClassification.valueOf(shop.getClassification().getName().toUpperCase()));
        shopDto.setUserId(shop.getUser().getId());
        return shopDto;
    }

    private Shop transfer(ShopDto shopDto) {
        Shop shop = new Shop();
        shop.setId(shopDto.getId());
        shop.setShopName(shopDto.getShopName());
        shop.setCountry(shopDto.getCountry());
        shop.setCity(shopDto.getCity());
        shop.setAddress(shopDto.getAddress());
        shop.setDescription(shopDto.getDescription());
        shop.setClassification(classificationRepository.getClassificationByNameAndAndClassificationName(
                shopDto.getClassification().getName(), ClassificationParent.SHOP.getName()));
        shop.setUser(userRepository.getOne(shopDto.getUserId()));
        return shop;
    }
}
