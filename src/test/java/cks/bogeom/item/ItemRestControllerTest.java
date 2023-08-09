package cks.bogeom.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) //통합테스트(SF-F-DS(Handler, ExHandler)-C-S-R-PC-DB) 다 뜬다.
    public class ItemRestControllerTest {
//        @Test
//        @DisplayName("전체 상품 목록 조회")
//        public void findAll_test() throws Exception {
//            // given teardown.sql
//
//            // when
//            ResultActions resultActions = mvc.perform(
//                    get("/products")
//            );
//
//            // eye (눈으로 본다.)
//    //        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
//    //        System.out.println("테스트 : "+responseBody);
//
//            // verify
//            resultActions.andExpect(jsonPath("$.success").value("true"));
//            resultActions.andExpect(jsonPath("$.response[0].id").value(1));
//            resultActions.andExpect(jsonPath("$.response[0].productName").value("기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전"));
//            resultActions.andExpect(jsonPath("$.response[0].description").value(""));
//            resultActions.andExpect(jsonPath("$.response[0].image").value("/images/1.jpg"));
//            resultActions.andExpect(jsonPath("$.response[0].price").value(1000));
//            resultActions.andDo(MockMvcResultHandlers.print()).andDo(document); //restDoc
//        }
}
