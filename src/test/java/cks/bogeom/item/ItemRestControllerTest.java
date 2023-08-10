package cks.bogeom.item;

import cks.bogeom._core.utils.ApiUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@AutoConfigureMockMvc //MockMvc 사용
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) //통합테스트(SF-F-DS(Handler, ExHandler)-C-S-R-PC-DB) 다 뜬다.
    public class ItemRestControllerTest {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ItemJPARepository itemJPARepository;
    @Test
    @DisplayName("/search/camera")
    public void searchCamera() throws Exception{
        //given
        ItemRequest.ImageDTO imageDTO = new ItemRequest.ImageDTO();
        imageDTO.setImage("image");

        double latitude = -35.993338732355994;
        double longitude = 121.91824486330819;
        ItemRequest.ImageDTO.Gps gps = new ItemRequest.ImageDTO.Gps(latitude, longitude);
        imageDTO.setGps(gps);

        String requestBody = om.writeValueAsString(imageDTO);
        System.out.println("requestBody : " + requestBody);

        //when
        ResultActions resultActions = mvc.perform(
                    post("/api/search/camera")
                            .content(requestBody)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
            );

        //then(verify)
        resultActions.andExpect(jsonPath("$.success").value("true"));
    }


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

