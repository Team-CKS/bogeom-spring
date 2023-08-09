package cks.bogeom.item;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    @Getter
    @Setter
    @Builder
    public static class searchAllDTO {
        private ItemDTO item;
        private ShopDTO shop;

        @Getter
        @Setter
        public static class ItemDTO {
            private String itemName;

            public ItemDTO(String itemName) {
                this.itemName = itemName;
            }
        }

        @Getter
        @Setter
        public static class ShopDTO {
            private ShopInfo enuri;
            private ShopInfo danawa;
            private ShopInfo naver;

            @Builder
            public ShopDTO(ShopInfo enuri, ShopInfo danawa, ShopInfo naver) {
                this.enuri = enuri;
                this.danawa = danawa;
                this.naver = naver;
            }

            @Getter
            @Setter
            public static class ShopInfo {
                private String detail;
                private String list;
                @Builder
                public ShopInfo(String detail, String list) {
                    this.detail = detail;
                    this.list = list;
                }
            }
        }

    }

    @Getter
    @Builder
    @ToString
    public static class productInfoDTO{
        private String url;
        private String productName;
        private int price;
    }

    @Getter
    @Setter
    public static class testDTO {
        private String itemName;
    }


}
