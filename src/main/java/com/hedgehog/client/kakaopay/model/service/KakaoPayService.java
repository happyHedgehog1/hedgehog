package com.hedgehog.client.kakaopay.model.service;
import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.auth.model.dto.LoginUserDTO;
import com.hedgehog.client.basket.model.dto.CartSelectDTO;
import com.hedgehog.client.kakaopay.model.dao.KakaoPayMapper;
import com.hedgehog.client.kakaopay.model.dto.OrderPayment;
import com.hedgehog.client.kakaopay.model.dto.ReadyResponse;
import com.hedgehog.client.kakaopay.model.dto.ApproveResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@SessionAttributes({"tid", "orderPayment"})
public class KakaoPayService {


//    @Autowired
    private final KakaoPayMapper kakaoMapper;



    private static final String HOST = "https://kapi.kakao.com";

    public KakaoPayService(KakaoPayMapper kakaoMapper) {
        this.kakaoMapper = kakaoMapper;
    }
    ReadyResponse readyResponse;



    public ReadyResponse payReady(String name, String phone, String email,
                                  int savedPoint, int originalTotalOrder,
                                  int deliveryPrice, int AllOriginalTotalOrder,
                                  int usingPoint, String deliveryName, String deliveryPhone,
                                  String deliveryRequest, LoginDetails loginDetails,
                                  List<Integer> productCode, List<Integer> count) {



        log.info("======================================> 서비스 시작 ");

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        //parameters에 결제에 필요한 정보를 추가

        String orderId = "100";

        parameters.add("cid", "TC0ONETIME"); //이거 테스트할땐 무조건 고정값으로 써야됨
        parameters.add("partner_order_id", "4"); //주문번호
        parameters.add("partner_user_id", "1in가구"); //아이디로 가야겠네
        parameters.add("item_name", "수납장");
        parameters.add("quantity", "2");
        parameters.add("total_amount", String.valueOf(AllOriginalTotalOrder));
        parameters.add("tax_free_amount", "0"); //비과세
        parameters.add("approval_url", "http://localhost:8080/kakao/pay/complete"); // 결제승인시 넘어갈 url
        parameters.add("cancel_url", "http://localhost:8080/clientOrder/orderFailed"); // 결제취소시 넘어갈 url
        parameters.add("fail_url", "http://localhost:8080/clientOrder/orderFailed");

        log.info("파트너주문아이디:"+ parameters.get("partner_order_id")) ;

        log.info("=================================================> parameters : " + parameters);

        //파라미터, 헤더
        // HttpEntity를 생성하여 외부 url에 요청을 보낼 준비를 합니다.
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // RestTemplate을 사용하여 외부 API에 POST 요청을 보냅니다.
        RestTemplate template = new RestTemplate();

        String url = "https://kapi.kakao.com/v1/payment/ready";
        log.info("============================================= id : " + parameters.get("partner_user_id"));
        log.info("=================================================url : " + url);
        log.info("=============================================== requestEntity : " +requestEntity);
        log.info("======================================================= ReadyResponse.class : " + ReadyResponse.class);

        ReadyResponse readyResponse = template.postForObject(url, requestEntity, ReadyResponse.class);

        log.info("결재준비 응답객체: " + readyResponse);

        log.info("===============================================================> 서비스 첫 리턴 ");

        // 응답을 로깅하고 결과를 반환
        //응답을 ReadyResponse.class 형태로 받아옵니다.
        return readyResponse;
    }

    //카카오api에 결제 승인 요청을 보내는 부
    public ApproveResponse payApprove(String tid, String pgToken){





        log.info("=================================================================> 서비스 응답 시작 ");

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();


        parameters.add("cid", "TC0ONETIME");
        parameters.add("tid", tid);
        parameters.add("partner_order_id", "4"); // 주문아이ㅅ
        parameters.add("partner_user_id", "1in가구");
        parameters.add("pg_token", pgToken);

        log.info("tid값이 여기서 받아지냐 =========" + tid);
        log.info("pgToken은 여기서 받아지냐 ======= " + pgToken);



        log.info("=================================================================> 서비스 중간 ");
        // 하나의 map안에 header와 parameter값을 담아줌.
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        log.info("==================================================================> 여기 ? " );
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";
        // 보낼 외부 url, 요청 메시지(header,parameter), 처리후 값을 받아올 클래스.

        log.info("============================================= id : " + parameters.get("partner_user_id"));
        log.info("=================================================url : " + url);
        log.info("=============================================== requestEntity : " +requestEntity);
        log.info("======================================================= ReadyResponse.class : " + ReadyResponse.class);

        ApproveResponse approveResponse = template.postForObject(url, requestEntity, ApproveResponse.class);
        log.info("결재승인 응답객체: " + approveResponse);
        log.info("tid왜 안나오냐" + tid);
        log.info("tid왜 안나오냐" + approveResponse.getTid());


        log.info("======================================================================= 서비스 마지막 리턴 :");
        return approveResponse;
    }

    //header 셋팅
    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "c84521fef561a0b8f63c5438a75390a6");
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }


    public void saveOrderDetail (int userCode, OrderPayment orderpayment){

        log.info("이건 나오냐" + orderpayment);
        log.info("유저코드이거나오냐"+ userCode);
        orderpayment.setUserCode(userCode);
        log.info("productcode나오냐"+ orderpayment.getProductCode());
        log.info("pointusage이거나오냐" + orderpayment.getPointUsage());


        log.info("주문코드요==" + orderpayment.getOrderCode());//주문전이라 안나옴

        kakaoMapper.saveOrderDetail(userCode, orderpayment);

    }


//    public void saveAllOrderInfo(OrderPayment orderpayment) {
//        log.info("배송비 나오냐 여기서? : " + orderpayment.getDeliveryCharge());
//        log.info("총금액 : " + orderpayment.getFinalPrice());
//        kakaoMapper.saveAllOrderInfo(orderpayment);
//    }
}
