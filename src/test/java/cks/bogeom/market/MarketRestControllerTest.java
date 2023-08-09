package cks.bogeom.market;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) //통합테스트(SF-F-DS(Handler, ExHandler)-C-S-R-PC-DB) 다 뜬다.
public class MarketRestControllerTest {
//    @WithUserDetails(value = "ssarmango@nate.com") //시큐리티 인증
//    @DisplayName("장바구니 담기(저장) 실패 - 중복된 옵션 입력")
//    @Test
//    public void addCartList_test_if_duplicated_option() throws Exception {
//        // given -> optionId [1,2,16]이 teardown.sql을 통해 들어가 있음
//        // [ { optionId:3, quantity:5 }, { optionId:3, quantity:5 } ]
//        List<CartRequest.SaveDTO> requestDTOs = new ArrayList<>();
//        CartRequest.SaveDTO item = new CartRequest.SaveDTO();
//        item.setOptionId(3);
//        item.setQuantity(5);
//        requestDTOs.add(item);
//        //동일한 옵션
//        CartRequest.SaveDTO item2 = new CartRequest.SaveDTO();
//        item2.setOptionId(3);
//        item2.setQuantity(5);
//        requestDTOs.add(item2);
//
//        String requestBody = om.writeValueAsString(requestDTOs);
//        System.out.println("요청 데이터 : " + requestBody);
//
//        // when
//        ResultActions resultActions = mvc.perform(
//                post("/carts/add")
//                        .content(requestBody)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//        );
//
//        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
//        System.out.println("테스트 : " + responseBody);
//
//        // verify
//        resultActions.andExpect(jsonPath("$.success").value("false"));
//        resultActions.andExpect(status().isBadRequest()); //400번 에러
//
//        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
//    }


}
