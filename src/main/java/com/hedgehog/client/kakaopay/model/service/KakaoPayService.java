package com.hedgehog.client.kakaopay.model.service;
import com.hedgehog.client.kakaopay.model.dto.ReadyResponse;
import com.hedgehog.client.kakaopay.model.dto.ApproveResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class KakaoPayService {



    private static final String HOST = "https://kapi.kakao.com";

    private ReadyResponse readyResponse;

    public ReadyResponse payReady(int totalAmount, String name, int totalPrice,
                                  int discountPrice, String tel,
                                  String email, String savedPoint, int originalTotalOrder, int deliveryPrice) {




        log.info("======================================> 서비스 시작 ");

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        //parameters에 결제에 필요한 정보를 추가

        parameters.add("cid", "TC0ONETIME");
        parameters.add("partner_order_id", "4");
        parameters.add("partner_user_id", "member0001");
        parameters.add("item_name", "수납장");
        parameters.add("quantity", "2");
        parameters.add("total_amount", String.valueOf(totalAmount));
        parameters.add("tax_free_amount", "0");

        parameters.add("approval_url", "http://localhost:8080/clientOrder/orderCompleted"); // 결제승인시 넘어갈 url
        parameters.add("cancel_url", "http://localhost:8080/clientOrder/orderFailed"); // 결제취소시 넘어갈 url
        parameters.add("fail_url", "http://localhost:8080/clientOrder/orderFailed");

        log.info("파트너주문아이디:"+ parameters.get("partner_order_id")) ;

        log.info("=================================================> parameters : " + parameters);

        //파라미터, 헤더
        // HttpEntity를 생성하여 요청을 보낼 준비를 합니다.
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

    public ApproveResponse payApprove(String tid, String pgToken){

        log.info("=================================================================> 서비스 응답 시작 ");

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("tid", tid);
        parameters.add("partner_order_id", "4"); // 주문명
        parameters.add("partner_user_id", "admin");
        parameters.add("pg_token", pgToken);


        log.info("=================================================================> 서비스 중간 ");
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

        log.info("======================================================================= 서비스 마지막 리턴 :");
        return approveResponse;
    }

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "c84521fef561a0b8f63c5438a75390a6");
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }


}
