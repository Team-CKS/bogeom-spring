package cks.bogeom.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemRestController {
    private final ItemService itemService;

//    //주문 결과 확인
//    public OrderResponse.FindAllDTO findById(int id) {
//        Order order = orderJPARepository.findById(id).orElseThrow(
//                () -> new Exception400("존재하지않는 orderId 입니다")
//        );
//        List<Item> itemList = itemJPARepository.findByOrderId(id);
//        return new OrderResponse.FindAllDTO(order, itemList);
//    }


}
