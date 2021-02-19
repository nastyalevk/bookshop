package nastya.BookShop.service.impl;

import nastya.BookShop.config.DateFormatter;
import nastya.BookShop.dto.assortment.AssortmentClassification;
import nastya.BookShop.dto.assortment.AssortmentDto;
import nastya.BookShop.dto.classification.ClassificationParent;
import nastya.BookShop.dto.shop.ShopClassification;
import nastya.BookShop.exception.NoAccessException;
import nastya.BookShop.model.Assortment;
import nastya.BookShop.model.AssortmentId;
import nastya.BookShop.repository.AssortmentRepository;
import nastya.BookShop.repository.BookRepository;
import nastya.BookShop.repository.ClassificationRepository;
import nastya.BookShop.repository.ShopRepository;
import nastya.BookShop.service.api.AssortmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class AssortmentServiceImpl implements AssortmentService {

    private final AssortmentRepository assortmentRepository;
    private final BookRepository bookRepository;
    private final ShopRepository shopRepository;
    private final ClassificationRepository classificationRepository;

    @Autowired
    public AssortmentServiceImpl(AssortmentRepository assortmentRepository, BookRepository bookRepository,
                                 ShopRepository shopRepository, ClassificationRepository classificationRepository) {
        this.assortmentRepository = assortmentRepository;
        this.bookRepository = bookRepository;
        this.shopRepository = shopRepository;
        this.classificationRepository = classificationRepository;
    }

    @Override
    public int getPriceByBookShop(Integer bookId, Integer shopId) {
        AssortmentDto assortmentDto =
                transfer(assortmentRepository.findByAssortmentIdBookIdAndAssortmentIdShopId(bookId, shopId));
        return assortmentDto.getPrice();
    }

    @Override
    public AssortmentDto update(AssortmentDto assortmentDto, String username) throws ParseException {
        if(assortmentDto.getPrice()<=0){
            throw new IllegalArgumentException("Price cant be zero or negative!");
        }
        Assortment assortment = transfer(assortmentDto);
        if (assortmentRepository.existsByAssortmentId(assortment.getAssortmentId())) {
            if (!assortment.getAssortmentId().getShop().getUser().getUsername().equals(username)) {
                throw new NoAccessException("You dont have access for this action!");
            }
        }
        return transfer(assortmentRepository.save(transfer(assortmentDto)));
    }

    @Override
    public AssortmentDto save(AssortmentDto assortmentDto) throws ParseException {
        return transfer(assortmentRepository.save(transfer(assortmentDto)));
    }

    @Override
    public boolean existsByBook(Integer bookId, Integer shopId) {
        return assortmentRepository.existsByAssortmentId(new AssortmentId(bookRepository.getOne(bookId),
                shopRepository.getOne(shopId)));
    }

    @Override
    public void delete(Integer bookId, Integer shopId) {
        assortmentRepository.deleteByAssortmentId(new AssortmentId(bookRepository.getOne(bookId),
                shopRepository.getOne(shopId)));
    }

    @Override
    public AssortmentDto getOne(Integer bookId, Integer shopId) {
        return transfer(assortmentRepository.getAssortmentByAssortmentId(new AssortmentId(bookRepository.getOne(bookId),
                shopRepository.getOne(shopId))));
    }

    @Override
    public List<AssortmentDto> getByBook(Integer bookId) {
        return transfer(assortmentRepository.findByAssortmentIdBookIdAndAndAssortmentIdShopClassificationName(bookId,
                ShopClassification.OPEN.toString()));
    }

    private AssortmentDto transfer(Assortment assortment) {
        AssortmentDto assortmentDto = new AssortmentDto();
        assortmentDto.setBookId(assortment.getAssortmentId().getBook().getId());
        assortmentDto.setShopId(assortment.getAssortmentId().getShop().getId());
        assortmentDto.setQuantity(assortment.getQuantity());
        assortmentDto.setPrice(assortment.getPrice());
        assortmentDto.setCreationDate(new DateFormatter().formatDate(assortment.getCreationDate()));
        assortmentDto.setClassification(AssortmentClassification
                .valueOf(assortment.getClassification().getName().toUpperCase()));
        return assortmentDto;
    }

    private Assortment transfer(AssortmentDto assortmentDto) throws ParseException {
        Assortment assortment = new Assortment();
        assortment.setAssortmentId(new AssortmentId(bookRepository.getOne(assortmentDto.getBookId()),
                shopRepository.getOne(assortmentDto.getShopId())));
        assortment.setQuantity(assortmentDto.getQuantity());
        assortment.setPrice(assortmentDto.getPrice());
        assortment.setCreationDate(new DateFormatter().formatDate(assortmentDto.getCreationDate()));
        assortment.setClassification(classificationRepository.getClassificationByNameAndAndClassificationName(
                assortmentDto.getClassification().getName(), ClassificationParent.ASSORTMENT.getName()));
        return assortment;
    }

    private List<AssortmentDto> transfer(List<Assortment> assortments) {
        List<AssortmentDto> assortmentDtos = new ArrayList<>();
        for (Assortment i : assortments) {
            assortmentDtos.add(transfer(i));
        }
        return assortmentDtos;
    }
}
