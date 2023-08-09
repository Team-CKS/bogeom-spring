package cks.bogeom.item;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class ItemResponse {
//    @Getter
//    @Setter
//    public static class FindAllDTO {
//        private int id;
//        private List<OrderResponse.FindAllDTO.ProductDTO> products;
//        private int totalPrice;
//
//        public FindAllDTO(Order order, List<Item> itemList) {
//            this.id = order.getId();
//            this.products = itemList.stream()
//                    // 중복되는 상품 걸러내기
//                    .map(item -> item.getOption().getProduct()).distinct()
//                    .map(product -> new ProductDTO(product, itemList)).collect(Collectors.toList());
//            this.totalPrice = itemList.stream().mapToInt(item -> item.getOption().getPrice() * item.getQuantity()).sum();
//        }
//    }
}
