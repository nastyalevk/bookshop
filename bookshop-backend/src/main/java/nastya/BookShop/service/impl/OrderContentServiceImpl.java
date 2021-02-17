package nastya.BookShop.service.impl;

import nastya.BookShop.dto.assortment.AssortmentClassification;
import nastya.BookShop.dto.classification.ClassificationParent;
import nastya.BookShop.dto.orderContent.OrderContentDto;
import nastya.BookShop.exception.NotEnoughItemsException;
import nastya.BookShop.model.Assortment;
import nastya.BookShop.model.AssortmentId;
import nastya.BookShop.model.OrderContent;
import nastya.BookShop.model.OrderContentId;
import nastya.BookShop.repository.AssortmentRepository;
import nastya.BookShop.repository.BookRepository;
import nastya.BookShop.repository.ClassificationRepository;
import nastya.BookShop.repository.OrderContentRepository;
import nastya.BookShop.repository.OrderRepository;
import nastya.BookShop.service.api.OrderContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrderContentServiceImpl implements OrderContentService {

    private final OrderContentRepository orderContentRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final AssortmentRepository assortmentRepository;
    private final ClassificationRepository classificationRepository;

    @Autowired
    public OrderContentServiceImpl(OrderContentRepository orderContentRepository, BookRepository bookRepository,
                                   OrderRepository orderRepository, AssortmentRepository assortmentRepository,
                                   ClassificationRepository classificationRepository) {
        this.orderContentRepository = orderContentRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.assortmentRepository = assortmentRepository;
        this.classificationRepository = classificationRepository;
    }

    @Override
    public List<OrderContentDto> findAll() {
        return transfer(orderContentRepository.findAll());
    }

    @Override
    public List<OrderContentDto> getOrderContent(Integer id) {
        return transfer(orderContentRepository.findAllByOrderContentIdOrderId(id));
    }

    @Override
    @Transactional()
    public OrderContentDto saveOrderContent(OrderContentDto orderContentDto) {
        OrderContent orderContent = transfer(orderContentDto);
        Assortment assortment = assortmentRepository.getAssortmentByAssortmentId(
                new AssortmentId(orderContent.getOrderContentId().getBook(),
                        orderRepository.getOne(orderContentDto.getOrderId()).getShop()));
        if (assortment.getQuantity() - orderContentDto.getQuantity() < 0) {
            throw new NotEnoughItemsException("Not enough items at store");
        } else {
            assortment.setQuantity(assortment.getQuantity() - orderContentDto.getQuantity());
        }
        if (assortment.getQuantity() == 0) {
            assortment.setClassification(classificationRepository.getClassificationByNameAndAndClassificationName(
                    AssortmentClassification.WAITING.getName(), ClassificationParent.ASSORTMENT.getName()));
        }
        assortmentRepository.save(assortment);
        return transfer(orderContentRepository.save(orderContent));
    }

    @Override
    public OrderContentDto updateOrderContent(OrderContentDto orderContentDto) {
        OrderContent orderContentNew = transfer(orderContentDto);
        OrderContent orderContentOld = orderContentRepository.getByOrderContentId(orderContentNew.getOrderContentId());
        Assortment assortment = assortmentRepository.getAssortmentByAssortmentId(
                new AssortmentId(orderContentNew.getOrderContentId().getBook(),
                        orderRepository.getOne(orderContentDto.getOrderId()).getShop()));
        if (assortment.getQuantity() - orderContentNew.getQuantity() + orderContentOld.getQuantity() < 0) {
            throw new NotEnoughItemsException("Not enough items at store");
        } else {
            assortment.setQuantity(assortment.getQuantity() -
                    orderContentNew.getQuantity() + orderContentOld.getQuantity());
        }
        if (assortment.getQuantity() == 0) {
            assortment.setClassification(classificationRepository.getClassificationByNameAndAndClassificationName(
                    AssortmentClassification.WAITING.getName(), ClassificationParent.ASSORTMENT.getName()));
        }
        assortmentRepository.save(assortment);
        return transfer(orderContentRepository.save(orderContentNew));
    }

    @Override
    public OrderContentDto getOrderContent(Integer orderId, Integer bookId) {
        return transfer(this.orderContentRepository.getByOrderContentId(
                new OrderContentId(orderRepository.getOne(orderId), bookRepository.getOne(bookId))));
    }

    @Override
    public void deleteOrderContent(OrderContentDto orderContent) {
        orderContentRepository.delete(transfer(orderContent));
    }


    private OrderContentDto transfer(OrderContent orderContent) {
        OrderContentDto orderContentDto = new OrderContentDto();
        orderContentDto.setOrderId(orderContent.getOrderContentId().getOrder().getId());
        orderContentDto.setBookId(orderContent.getOrderContentId().getBook().getId());
        orderContentDto.setQuantity(orderContent.getQuantity());
        orderContentDto.setPrice(orderContent.getPrice());
        return orderContentDto;
    }

    private List<OrderContentDto> transfer(List<OrderContent> orderContents) {
        List<OrderContentDto> orderContentDtos = new ArrayList<>();
        for (OrderContent i : orderContents) {
            orderContentDtos.add(transfer(i));
        }
        return orderContentDtos;
    }

    private OrderContent transfer(OrderContentDto orderContentDto) {
        OrderContent orderContent = new OrderContent();
        orderContent.setOrderContentId(new OrderContentId(
                orderRepository.getOne(orderContentDto.getOrderId()),
                bookRepository.getOne(orderContentDto.getBookId())));
        orderContent.setQuantity(orderContentDto.getQuantity());
        orderContent.setPrice(orderContentDto.getPrice());
        return orderContent;
    }
}
