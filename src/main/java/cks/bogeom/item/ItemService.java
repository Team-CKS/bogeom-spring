package cks.bogeom.item;

import cks.bogeom._core.utils.ApiUtils;
import cks.bogeom.item.ItemResponse.searchAllDTO.ItemDTO;
import cks.bogeom.item.ItemResponse.searchAllDTO.ShopDTO;
import cks.bogeom.market.MarketJPARepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemJPARepository itemJPARepository;

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

    //AI 서버에 이미지 전송 후 응답 받기
    public String searchWithImage(MultipartFile image, double latitude, double longitude) {
        try {
            ByteArrayResource resource = new ByteArrayResource(image.getBytes()) {
                @Override
                public String getFilename() {
                    return image.getOriginalFilename();
                }
            };

            // 요청 body
            LinkedMultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
            data.add("image", resource);
            data.add("latitude", latitude);
            data.add("longitude", longitude);

            // 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // 요청 객체 만들기
            HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(data, headers);

            // post 요청 보내고 응답 얻기
            JsonNode response = REST_TEMPLATE.postForObject("http://localhost:8080/response", requestEntity, JsonNode.class);

            System.out.println("전송 성공");
            System.out.println("response : " + response);

            //응답결과 파싱
            String productName = response.get("response").get("itemName").asText();
            System.out.println("productName : " + productName);

//            return ResponseEntity.ok(ApiUtils.success(response));
            return productName;
        } catch (HttpStatusCodeException e) {
            String errorResponse = e.getResponseBodyAsString();
            System.out.println("Error: " + errorResponse);
            return null;
//            return new ResponseEntity<>(ApiUtils.error(e.getResponseBodyAsString(), e.getStatusCode()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
//            return new ResponseEntity<>(ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    //크롤링 후 응답 생성하기
    public ItemResponse.searchAllDTO searchDetail(String productName) throws IOException {
        //Enuri - 동적 사이트
        String url = "https://m.enuri.com/m/search.jsp?keyword=" + productName;
        String eUrl = url.replace(" ", "%20");
        System.out.println("eUrl = " + eUrl);
        //요청 보내기
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36";
        Document document = Jsoup.connect(eUrl).userAgent(userAgent).get();

        //크롤링 하기
//        Element e = document.select(".listarea .listarea_sg ").first();
//        String url = e.select("a").attr("abs:href");
//        System.out.println("에누리 url = " + url);

//        Element e = document.select("div.lp__prod_list.prod__list > ul > li").first();
//        if (e != null) {
//            String url = e.select("a").attr("abs:href");
//            System.out.println("Overcharge url = " + url);
//        } else {
//            System.out.println("No matching element found.");
//        }


        //Danawa
        url = "https://search.danawa.com/mobile/dsearch.php?keyword=" + productName;
        String dUrl = url.replace(" ", "%20"); //띄어쓰기 바꾸기
        document = Jsoup.connect(dUrl).userAgent(userAgent).get(); //요청 보내기
        //기능 추가 - 3개이고 1개 옵션 있으면 그거 보여주기?
        Element e = document.select("div.goods-list__info").first();
        String dCurl = e.select("a").attr("abs:href");
        String dName = e.select("a > span").text();
        String dPrice = e.select("a div.goods-list__price em.number").text();
        int price = Integer.parseInt(dPrice.replace(",", "")); //"," -> "" 로 바꾸기
//        System.out.println("다나와 url = " + dCurl);
//        System.out.println("dName = " + dName);
//        System.out.println("price = " + price);

        // Naver
        url = "https://msearch.shopping.naver.com/search/all?frm=NVSHMDL&origQuery="+productName+"&pagingIndex=1&pagingSize=40&productSet=model&query="+productName+"&sort=rel&viewType=lst";
        String nUrl = url.replace(" ", "%20");
        document = Jsoup.connect(nUrl).userAgent(userAgent).get(); //요청 보내기

        //크롤링 하기
        e = document.select("div.product_item_inner__EIIz_").first();
        String nCurl = e.select("a").attr("abs:href");
        String craName = e.select("span.product_info_tit__c5_pb").text();
        String craPrice = e.select("span.product_num__iQsWh strong").text();
        price = Integer.parseInt(craPrice.replace(",", ""));
//        System.out.println("url = " + url);
//        System.out.println("craName = " + craName);
//        System.out.println("price = " + price);

        //응답 만들기
        ShopDTO.ShopInfo eShopInfo = new ShopDTO.ShopInfo(eUrl, null);
        ShopDTO.ShopInfo dShopInfo = new ShopDTO.ShopInfo(dUrl, dCurl); //다나와
        ShopDTO.ShopInfo nShopInfo = new ShopDTO.ShopInfo(nUrl, nCurl); //네이버

        ItemResponse.searchAllDTO dto = ItemResponse.searchAllDTO.builder()
                .item(new ItemDTO(productName))
                .shop(new ShopDTO(eShopInfo, dShopInfo, nShopInfo))
                .build();

        return dto;
    }

//    private final MarketJPARepository marketJPARepository;
//    @Transactional
//    public OrderResponse.FindAllDTO save(User sessionUser) {
//        // 사용자가 담은 장바구니가 없을때 예외처리
//        int userId = sessionUser.getId();
//        List<Cart> cartList = cartJPARepository.findByUserIdOrderByOptionIdAsc(userId); //Option, Product join fetch
//        if(cartList.isEmpty()){
//            throw new Exception400("담은 장바구니가 없습니다");
//        }
//        //1. 주문 생성하기
//        Order order = Order.builder().user(sessionUser).build();
//        orderJPARepository.save(order);
//        System.out.println("order 저장");
//
//        //2. 장바구니를 Item에 저장하기
//        List<Item> itemList = new ArrayList<>();
//        for (Cart cart : cartList) {
//            Item item = Item.builder().option(cart.getOption()).order(order)
//                    .quantity(cart.getQuantity()).price(cart.getPrice()).build();
//            itemJPARepository.save(item);
//            itemList.add(item);
//        }
//        //3. 장바구니 초기화하기
//        cartJPARepository.deleteByUserId(userId); //커스텀 쿼리 작성하여 필요없는 쿼리 줄임
//
//        //4. DTO 응답
//        return new OrderResponse.FindAllDTO(order, itemList);
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
