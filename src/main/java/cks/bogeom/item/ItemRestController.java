package cks.bogeom.item;

import cks.bogeom._core.utils.ApiUtils;
import cks.bogeom.item.ItemResponse.searchAllDTO;
import cks.bogeom.item.ItemResponse.searchAllDTO.ItemDTO;
import cks.bogeom.item.ItemResponse.searchAllDTO.ShopDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemRestController {
    private final ItemService itemService;

    @Value("${ai.server.url}")
    private String aiServerUrl;

    private static final RestTemplate REST_TEMPLATE;

    static {
        // RestTemplate 기본 설정을 위한 Factory 생성
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory(); //HTTP 요청 인터페이스
        //타임아웃 설정
        factory.setConnectTimeout(16000); //연결시간초과 16초(그 이후에는 예외)
        factory.setReadTimeout(16000); //읽기시간초과 16초
//        factory.setBufferRequestBody(false);
        REST_TEMPLATE = new RestTemplate(factory); //인스턴스 생성
    }

    //가격표 사진을 받아 검색
    @PostMapping("/api/search/camera")
//    public ResponseEntity<?> search(@RequestBody @Valid ItemRequest.ImageDTO imageDTO , Errors errors) {
    public ResponseEntity<?> search(@RequestParam("image") MultipartFile image, @RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {

//        userService.sameCheckEmail(emailCheckDTO.getEmail());
        String filename = image.getOriginalFilename();
        System.out.println(filename); //파일 이름
        System.out.println(latitude + ", " + longitude);

        //1. 이미지 전송-응답
        ItemResponse.searchDTO searchDTO = itemService.searchWithImage(image, latitude, longitude);
        String productName = searchDTO.getItemName();
        String productPrice = searchDTO.getItemPrice();



        //2. url 크롤링
        try{
            System.out.println("search 서비스 실행");
            searchAllDTO dto = itemService.searchDetail(productName);
            return ResponseEntity.ok(ApiUtils.success(dto));
        } catch (Exception e){
            return new ResponseEntity<>(ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

//        return ResponseEntity.ok(ApiUtils.success(null));
    }

    //텍스트 검색
    @GetMapping("/api/search/home")
    public ResponseEntity<?> search(@RequestParam("search") String productName) {

        //2. url 크롤링
        try{
            System.out.println("search 서비스 실행");
            searchAllDTO dto = itemService.searchDetail(productName);
            return ResponseEntity.ok(ApiUtils.success(dto));
        } catch (Exception e){
            return new ResponseEntity<>(ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

//    //Test용 응답
//    @PostMapping("/response")
////    public ResponseEntity<?> search(@RequestBody @Valid ItemRequest.ImageDTO imageDTO , Errors errors) {
//    public ResponseEntity<?> searchResponse(@RequestParam("image") MultipartFile image, @RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
//
////        userService.sameCheckEmail(emailCheckDTO.getEmail());
//        String filename = image.getOriginalFilename();
//        System.out.println(filename); //파일 이름
//        System.out.println(latitude + ", " + longitude);
//        ItemResponse.testDTO testDTO = new ItemResponse.testDTO();
//        testDTO.setItemName("해피바스 정말순한바디로션 400ml");
//        return ResponseEntity.ok(ApiUtils.success(testDTO));
//    }


    //    //주문 결과 확인
//    public OrderResponse.FindAllDTO findById(int id) {
//        Order order = orderJPARepository.findById(id).orElseThrow(
//                () -> new Exception400("존재하지않는 orderId 입니다")
//        );
//        List<Item> itemList = itemJPARepository.findByOrderId(id);
//        return new OrderResponse.FindAllDTO(order, itemList);
//    }
}
