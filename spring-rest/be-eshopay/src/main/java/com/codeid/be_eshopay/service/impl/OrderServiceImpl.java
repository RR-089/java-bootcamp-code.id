package com.codeid.be_eshopay.service.impl;

import com.codeid.be_eshopay.constant.PaymentType;
import com.codeid.be_eshopay.constant.UpdateProductOnOrderType;
import com.codeid.be_eshopay.exception.BadRequestException;
import com.codeid.be_eshopay.exception.InternalServerException;
import com.codeid.be_eshopay.exception.NotFoundException;
import com.codeid.be_eshopay.model.dto.CartItemKeyDTO;
import com.codeid.be_eshopay.model.dto.OrderDetailKeyDTO;
import com.codeid.be_eshopay.model.dto.request.cartitem.BulkDeleteItemsRequestDTO;
import com.codeid.be_eshopay.model.dto.request.order.CreateOrderDTO;
import com.codeid.be_eshopay.model.dto.request.product.BulkUpdateProductsOnOrderDTO;
import com.codeid.be_eshopay.model.dto.request.product.UpdateProductOnOrderDTO;
import com.codeid.be_eshopay.model.dto.response.order.*;
import com.codeid.be_eshopay.model.entity.*;
import com.codeid.be_eshopay.repository.OrderRepository;
import com.codeid.be_eshopay.service.*;
import com.codeid.be_eshopay.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final LocationService locationService;
    private final ProductService productService;

    private GetOrderByIdResponseDTO mapToOrderResponseDTO(Order entity) {
        Supplier supplier = entity.getOrderDetails().get(0).getProduct().getSupplier();

        OrderSupplierDTO supplierDTO =
                OrderSupplierDTO.builder()
                                .id(supplier.getId())
                                .name(supplier.getCompanyName())
                                .build();

        User user = entity.getUser();

        OrderUserDTO userDTO = OrderUserDTO.builder()
                                           .id(user.getId())
                                           .name(user.getName())
                                           .location(
                                                   OrderLocationDTO.builder()
                                                                   .id(user.getLocation().getId())
                                                                   .city(user.getLocation().getCity())
                                                                   .streetAddress(user.getLocation().getStreetAddress())
                                                                   .stateProvince(user.getLocation().getStateProvince())
                                                                   .postalCode(user.getLocation().getPostalCode())
                                                                   .countryId(user.getLocation().getCountryId())
                                                                   .build())
                                           .build();

        OrderLocationDTO locationDTO = OrderLocationDTO.builder()
                                                       .id(entity.getLocation().getId())
                                                       .city(entity.getLocation().getCity())
                                                       .streetAddress(entity.getLocation().getStreetAddress())
                                                       .stateProvince(entity.getLocation().getStateProvince())
                                                       .postalCode(entity.getLocation().getPostalCode())
                                                       .countryId(entity.getLocation().getCountryId())
                                                       .build();

        OrderShipmentDTO shipmentDTO = OrderShipmentDTO.builder()
                                                       .name(entity.getShipName())
                                                       .freight(entity.getFreight())
                                                       .build();


        List<CreatedOrderItemDTO> itemDTOS = entity.getOrderDetails()
                                                   .stream()
                                                   .map((this::mapOrderDetailToCreatedOrderItemDTO))
                                                   .toList();

        return GetOrderByIdResponseDTO.builder()
                                      .id(entity.getId())
                                      .paymentType(entity.getPaymentType())
                                      .supplier(supplierDTO)
                                      .user(userDTO)
                                      .location(locationDTO)
                                      .shipment(shipmentDTO)
                                      .items(itemDTOS)
                                      .totalPrice(NumberUtil.roundedDouble(entity.getTotalPrice()))
                                      .totalDiscount(NumberUtil.roundedDouble(entity.getTotalDiscount()))
                                      .finalPrice(NumberUtil.roundedDouble(entity.getTotalPrice() - entity.getTotalDiscount()))
                                      .build();
    }

    @Override
    public GetOrderByIdResponseDTO findById(Long id) {
        Order foundOrder = orderRepository.findById(id)
                                          .orElseThrow(() ->
                                                  new NotFoundException("Order not " +
                                                          "found", null)
                                          );
        return this.mapToOrderResponseDTO(foundOrder);
    }

    @Override
    public GetOrderResponseDTO createOrderData(CreateOrderDTO dto) {
        //TODO: Fetch this from auth later
        Long userId = 303L;

        //TODO: make shipment as separate entity
        OrderShipmentDTO orderShipment = OrderShipmentDTO.builder()
                                                         .name("JNT EE")
                                                         .freight(10D)
                                                         .build();

        boolean isSameSupplier =
                cartItemService.isCartItemsSameSupplier(dto.getCartItemIds());

        if (!isSameSupplier) {
            throw new BadRequestException("All items must belong to the same supplier", null);
        }

        List<CartItem> cartItems = cartItemService
                .findCartItemByIds(dto.getCartItemIds());

        List<OrderItemDTO> items = cartItems
                .stream()
                .map(this::mapCartItemToOrderItemDTO)
                .toList();


        Supplier supplier = cartItems.get(0).getProduct()
                                     .getSupplier();

        OrderSupplierDTO orderSupplier = OrderSupplierDTO.builder()
                                                         .id(supplier.getId())
                                                         .name(supplier.getCompanyName())
                                                         .build();

        User user = cartService.findCartByUserId(userId).getUser();

        OrderUserDTO orderUser = OrderUserDTO.builder()
                                             .id(user.getId())
                                             .name(user.getName())
                                             .location(
                                                     OrderLocationDTO
                                                             .builder()
                                                             .id(user.getLocation().getId())
                                                             .city(user.getLocation().getCity())
                                                             .postalCode(user.getLocation().getPostalCode())
                                                             .stateProvince(user.getLocation().getStateProvince())
                                                             .streetAddress(user.getLocation().getStreetAddress())
                                                             .countryId(user.getLocation().getCountryId())
                                                             .build()
                                             )
                                             .build();

        Location foundLocation = locationService.findById(dto.getLocationId());

        OrderLocationDTO orderLocation =
                (dto.getLocationId() != null) ?
                        OrderLocationDTO.builder()
                                        .id(foundLocation.getId())
                                        .city(foundLocation.getCity())
                                        .postalCode(foundLocation.getPostalCode())
                                        .stateProvince(foundLocation.getStateProvince())
                                        .streetAddress(foundLocation.getStreetAddress())
                                        .countryId(foundLocation.getCountryId())
                                        .build()
                        : orderUser.getLocation();


        double totalPrice = 0D;
        double totalDiscount = 0D;
        double finalPrice = 0D;

        for (OrderItemDTO orderItem : items) {
            totalPrice += orderItem.getTotalPrice();
            totalDiscount += orderItem.getTotalDiscount();
            finalPrice += orderItem.getFinalPrice();
        }

        if (totalPrice - totalDiscount != finalPrice) {
            throw new InternalServerException("Formula pre order price is incorrect",
                    null);
        }

        totalPrice += orderShipment.getFreight();
        finalPrice += orderShipment.getFreight();

        return GetOrderResponseDTO
                .builder()
                .paymentType(dto.getPaymentType() != null ? dto.getPaymentType() : PaymentType.TRANSFER)
                .supplier(orderSupplier)
                .user(orderUser)
                .location(orderLocation)
                .shipment(orderShipment)
                .items(items)
                .totalPrice(NumberUtil.roundedDouble(totalPrice))
                .totalDiscount(NumberUtil.roundedDouble(totalDiscount))
                .finalPrice(NumberUtil.roundedDouble(finalPrice))
                .build();

    }

    @Override
    @Transactional
    public GetOrderByIdResponseDTO saveOrderData(CreateOrderDTO dto) {
        GetOrderResponseDTO poData = this.createOrderData(dto);

        productService.bulkUpdateAddProductsOnOrder(
                BulkUpdateProductsOnOrderDTO
                        .builder()
                        .type(UpdateProductOnOrderType.INCREMENT)
                        .data(
                                poData.getItems()
                                      .stream()
                                      .map(orderItem -> {
                                          return UpdateProductOnOrderDTO.builder()
                                                                        .id(orderItem.getId().getProductId())
                                                                        .quantity(orderItem.getQuantity())
                                                                        .build();
                                      })
                                      .toList()
                        )
                        .build()
        );


        Order newOrder =
                Order.builder()
                     .id(null)
                     .requiredDate(LocalDate.now().plusDays(5))
                     .shippedDate(null)
                     .shipVia(null)
                     .shipName(poData.getShipment().getName())
                     .freight(poData.getShipment().getFreight())
                     .totalDiscount(poData.getTotalDiscount())
                     .totalPrice(poData.getTotalPrice())
                     .paymentType(poData.getPaymentType())
                     .transaction(null) //TODO
                     .transactionDate(null) //TODO
                     .location(Location.builder()
                                       .id(poData.getLocation().getId())
                                       .city(poData.getLocation().getCity())
                                       .countryId(poData.getLocation().getCountryId())
                                       .build())
                     .user(User.builder().id(poData.getUser().getId()).build())
                     .build();

        Order createdOrder = orderRepository.save(newOrder);

        List<OrderDetail> orderDetails =
                poData.getItems()
                      .stream()
                      .map(orderItemDTO -> {
                          return OrderDetail.builder()
                                            .id(OrderDetailKey
                                                    .builder()
                                                    .orderId(createdOrder.getId())
                                                    .productId(orderItemDTO.getId().getProductId())
                                                    .build())
                                            .order(Order.builder().id(createdOrder.getId()).build())
                                            .product(Product.builder()
                                                            .id(orderItemDTO.getId().getProductId())
                                                            .name(orderItemDTO.getName())
                                                            .build())
                                            .unitPrice(orderItemDTO.getUnitPrice())
                                            .quantity(orderItemDTO.getQuantity())
                                            .discount(orderItemDTO.getDiscount())
                                            .build();
                      })
                      .toList();

        List<OrderDetail> createdOrderDetails =
                orderDetailService.createAll(orderDetails);

        cartItemService.bulkDeleteItems(
                BulkDeleteItemsRequestDTO.builder()
                                         .cartItemIds(
                                                 poData.getItems()
                                                       .stream()
                                                       .map((OrderItemDTO::getId))
                                                       .toList()
                                         )
                                         .build()
        );


        return GetOrderByIdResponseDTO.builder()
                                      .id(newOrder.getId())
                                      .paymentType(newOrder.getPaymentType())
                                      .supplier(poData.getSupplier())
                                      .user(poData.getUser())
                                      .location(poData.getLocation())
                                      .shipment(poData.getShipment())
                                      .items(
                                              createdOrderDetails
                                                      .stream()
                                                      .map(this::mapOrderDetailToCreatedOrderItemDTO)
                                                      .toList()
                                      )
                                      .totalPrice(poData.getTotalPrice())
                                      .totalDiscount(poData.getTotalDiscount())
                                      .finalPrice(poData.getFinalPrice())
                                      .build();
    }


    private OrderItemDTO mapCartItemToOrderItemDTO(CartItem cartItem) {

        Double discountedPrice = CartItemServiceImpl.calculateDiscountedPrice(
                cartItem.getUnitPrice(), cartItem.getDiscount());

        Integer quantity = cartItem.getQuantity();

        double totalPrice = cartItem.getQuantity() * cartItem.getUnitPrice();

        double finalPrice = quantity * discountedPrice;

        double totalDiscount =
                (cartItem.getUnitPrice() * quantity) - (discountedPrice * quantity);

        return OrderItemDTO
                .builder()
                .id(CartItemKeyDTO.builder()
                                  .cartId(cartItem.getId().getCartId())
                                  .productId(cartItem.getId().getProductId())
                                  .build())
                .name(cartItem.getProduct().getName())
                .thumbnailPicture(cartItem.getProduct().getThumbnailPicture())
                .ItemImages(cartItem.getProduct()
                                    .getProductImages()
                                    .stream()
                                    .map((productImage
                                            -> productImage.getFileMetaData()
                                                           .getFileUri()))
                                    .toList()
                )
                .quantity(quantity)
                .unitPrice(NumberUtil.roundedDouble(cartItem.getUnitPrice()))
                .discount(NumberUtil.roundedDouble(cartItem.getDiscount()))
                .discountedUnitPrice(NumberUtil.roundedDouble(discountedPrice))
                .totalPrice(NumberUtil.roundedDouble(totalPrice))
                .finalPrice(NumberUtil.roundedDouble(finalPrice))
                .totalDiscount(NumberUtil.roundedDouble(totalDiscount))
                .build();
    }


    private CreatedOrderItemDTO mapOrderDetailToCreatedOrderItemDTO(OrderDetail orderDetail) {

        Double discountedPrice = CartItemServiceImpl.calculateDiscountedPrice(
                orderDetail.getUnitPrice(), orderDetail.getDiscount());

        Integer quantity = orderDetail.getQuantity();

        double totalPrice = orderDetail.getQuantity() * orderDetail.getUnitPrice();

        double finalPrice = quantity * discountedPrice;

        double totalDiscount =
                (orderDetail.getUnitPrice() * quantity) - (discountedPrice * quantity);

        return CreatedOrderItemDTO
                .builder()
                .id(
                        OrderDetailKeyDTO.builder()
                                         .orderId(orderDetail.getId().getOrderId())
                                         .productId(orderDetail.getId().getProductId())
                                         .build()
                )
                .name(orderDetail.getProduct().getName())
                .thumbnailPicture(orderDetail.getProduct().getThumbnailPicture())
                .ItemImages(orderDetail.getProduct()
                                       .getProductImages()
                                       .stream()
                                       .map((productImage
                                               -> productImage.getFileMetaData()
                                                              .getFileUri()))
                                       .toList()
                )
                .quantity(quantity)
                .unitPrice(NumberUtil.roundedDouble(orderDetail.getUnitPrice()))
                .discount(NumberUtil.roundedDouble(orderDetail.getDiscount()))
                .discountedUnitPrice(NumberUtil.roundedDouble(discountedPrice))
                .totalPrice(NumberUtil.roundedDouble(totalPrice))
                .finalPrice(NumberUtil.roundedDouble(finalPrice))
                .totalDiscount(NumberUtil.roundedDouble(totalDiscount))
                .build();
    }
}
