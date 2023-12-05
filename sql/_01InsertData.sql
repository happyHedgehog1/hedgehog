
/*권한목록*/
INSERT INTO tbl_authority(name) /*1. 슈퍼관리자*/
VALUES ('super_admin');
INSERT INTO tbl_authority(name) /*2. 일반관리자*/
VALUES ('admin');
INSERT INTO tbl_authority(name) /*3. 회원*/
VALUES ('member');
INSERT INTO tbl_authority(name) /*4. 비회원*/
VALUES ('guest');
/*사용자 + 권한리스트 + 고객 + 회원 + 탈퇴이력관리 */
/*1번 user는 1번 권한(슈퍼관리자)*/
INSERT INTO tbl_user(id, password, name, classify, connection_date, creation_date, withdraw_state)
VALUES ('root', '1234', '슈퍼관리자이름', '관리자', '2023-12-03 17:29:49', '2010-11-05 14:12:59', 'N');
INSERT INTO tbl_authority_list(authority_code, user_code)
VALUES (1, 1);

/*2번 user는 2번 권한(일반관리자)*/
INSERT INTO tbl_user(id, password, name, classify, connection_date, creation_date, withdraw_state)
VALUES ('root01', '1234', '관리자이름1', '관리자', '2023-12-02 17:29:49', '2015-11-05 14:12:59', 'N');
INSERT INTO tbl_authority_list(authority_code, user_code)
VALUES (2, 2);

/*3번 user는 2번 권한(일반관리자)*/
INSERT INTO tbl_user(id, password, name, classify, connection_date, creation_date, withdraw_state)
VALUES ('root02', '1234', '관리자이름2', '관리자', '2023-12-03 15:29:49', '2017-11-05 14:12:59', 'N');
INSERT INTO tbl_authority_list(authority_code, user_code)
VALUES (2, 3);

/*4번 user는 2번 권한(일반관리자). 탈퇴상태 => 슈퍼관리자가 탈퇴처리 하면 즉시 탈퇴*/
INSERT INTO tbl_user(id, password, name, classify, connection_date, creation_date, withdraw_state)
VALUES ('root03', '1234', '관리자이름3', '관리자', '2019-11-05 17:29:49', '2018-11-05 14:12:59', 'Y');
INSERT INTO tbl_authority_list(authority_code, user_code)
VALUES (2, 4);
INSERT INTO tbl_withdraw(user_code, state, cause, commit_date)
VALUES (4, 'Y', '관리자 탈퇴', '2019-11-05 17:40:50');

/*5번 user는 3번 권한(회원)*/
INSERT INTO tbl_user(id, password, name, classify, connection_date, creation_date, withdraw_state)
VALUES ('member01', '1234', '고객이름1', '고객', '2023-12-02 15:41:33', '2015-01-02 19:23:10', 'N');
INSERT INTO tbl_authority_list(authority_code, user_code)
VALUES (3, 5);
INSERT INTO tbl_customer(customer_code, email, member_state, phone) /*5번 user*/
VALUES (5, 'mmail5@gmail.com', 'Y', '01000000005');
INSERT INTO tbl_member(member_code, birthday, gender, email_consent, point)
VALUES (5, '1998-04-21', 'M', 'Y', 3000);

/*6번 user는 3번 권한(회원). 탈퇴상태는 아니지만 예전에 탈퇴이력은 있음*/
INSERT INTO tbl_user(id, password, name, classify, connection_date, creation_date, withdraw_state)
VALUES ('member02', '1234', '고객이름2', '고객', '2023-12-01 15:41:33', '2017-03-05 19:23:10', 'N');
INSERT INTO tbl_authority_list(authority_code, user_code)
VALUES (3, 6);
INSERT INTO tbl_customer(customer_code, email, member_state, phone)
VALUES (6, 'mmail6@gmail.com', 'Y', '01000000006');
INSERT INTO tbl_member(member_code, birthday, gender, email_consent, point)
VALUES (6, '1988-01-01', 'F', 'Y', 0);
INSERT INTO tbl_withdraw(user_code, state, cause, apply_date, cancel_date)
VALUES (6, 'N', '사이트가 맘에 들지 않는다.', '2019-11-05 17:40:50', '2019-11-06 18:30:20');

/*7번 user는 3번 권한(회원). 탈퇴 신청후 1주일동안 반응이 없어서 탈퇴처리된 상태*/
INSERT INTO tbl_user(id, password, name, classify, connection_date, creation_date, withdraw_state)
VALUES ('member03', '1234', '고객이름3', '고객', '2021-05-03 15:41:33', '2016-03-05 19:23:10', 'Y');
INSERT INTO tbl_authority_list(authority_code, user_code)
VALUES (3, 7);
INSERT INTO tbl_customer(customer_code, email, member_state, phone)
VALUES (7, 'mmail7@gmail.com', 'Y', '01000000007');
INSERT INTO tbl_member(member_code, birthday, gender, email_consent, point)
VALUES (7, '1986-03-02', 'M', 'Y', 1500);
INSERT INTO tbl_withdraw(user_code, state, cause, apply_date, cancel_date)
VALUES (7, 'Y', '서비스가 불친절하다.', '2022-11-03 15:50:50', '2022-11-10 15:50:50');

/*8번 user는 3번 권한(회원). 강제퇴장된 경우*/
INSERT INTO tbl_user(id, password, name, classify, connection_date, creation_date, withdraw_state)
VALUES ('member04', '1234', '고객이름4', '고객', '2023-05-03 15:41:33', '2019-12-05 19:23:10', 'Y');
INSERT INTO tbl_authority_list(authority_code, user_code)
VALUES (3, 8);
INSERT INTO tbl_customer(customer_code, email, member_state, phone)
VALUES (8, 'mmail8@gmail.com', 'Y', '01000000008');
INSERT INTO tbl_member(member_code, birthday, gender, email_consent, point)
VALUES (8, '1989-03-02', 'M', 'Y', 1500);
INSERT INTO tbl_withdraw(user_code, state, cause, cancel_date)
VALUES (8, 'Y', '강제탈퇴', '2023-05-06 11:50:50');

/*9번 user는 4번 권한(비회원)*/
INSERT INTO tbl_user(name, classify, creation_date)
VALUES ('비회원이름1', '고객', '2023-05-01 15:41:33');
INSERT INTO tbl_authority_list(authority_code, user_code)
VALUES (4, 9);
INSERT INTO tbl_customer(customer_code, email, member_state, phone)
VALUES (9, 'mmail9@gmail.com', 'N', '01000000009');

/*10번 user는 4번 권한(비회원)*/
INSERT INTO tbl_user(name, classify, creation_date)
VALUES ('비회원이름2', '고객', '2022-09-03 15:41:33');
INSERT INTO tbl_authority_list(authority_code, user_code)
VALUES (4, 10);
INSERT INTO tbl_customer(customer_code, email, member_state, phone)
VALUES (10, 'mmail10@gmail.com', 'N', '01000000010');

/*11번 user는 4번 권한(비회원). 10번 user와 이름, 휴대폰정보, 이메일정보가 일치하지만. 로그인 개념이 없어서 새로운 user가 생성*/
INSERT INTO tbl_user(name, classify, creation_date)
VALUES ('비회원이름2', '고객', '2022-09-05 15:41:33');
INSERT INTO tbl_authority_list(authority_code, user_code)
VALUES (4, 11);
INSERT INTO tbl_customer(customer_code, email, member_state, phone)
VALUES (11, 'mmail10@gmail.com', 'N', '01000000010');

/*메일양식*/
INSERT INTO tbl_mailform(name, title, content, creation_date)
VALUES ('가입인사', '1 in 가구 가입을 환영합니다', '<p>환영하는의미의메일내용</p>', '2010-11-05 17:12:59');

/*관리자의 메일전송내역*/
INSERT INTO tbl_mail_history(date, title, content, user_code, customer_code) /*3번 user인 관리자가 5번, 6번 user인 회원들에게 메일 전송한 내역*/
VALUES ('2022-07-01 12:00:00', '1 in 가구. 여름 대행사!', '<p>이벤트가 뭐가 있다는 의미의 내용</p>', 3, 5);
INSERT INTO tbl_mail_history(date, content, title, user_code, customer_code) /*3번 user인 관리자가 5번, 6번 user인 회원들에게 메일 전송한 내역*/
VALUES ('2022-07-01 12:00:00', '1 in 가구. 여름 대행사!', '<p>이벤트가 뭐가 있다는 의미의 내용</p>', 3, 6);

/*관리자 게시글. 2번 user인 관리자가 마지막으로*/
/*게시글타입 = 공지사항, 이용약관, 개인정보처리방침, FAQ*/
INSERT INTO tbl_admin_bulletin_board(write_date, user_code, modify_date, title, content, post_type, state)
VALUES ('2021-03-01 10:20:30', '2', '2021-05-02 10:20:30', '공지사항1', '<h1>공지사항내용입니다.</h1>', '공지사항', 'Y');
INSERT INTO tbl_admin_bulletin_board(write_date, user_code, modify_date, title, content, post_type, state)
VALUES ('2021-03-01 11:21:31', '2', '2021-05-02 10:20:30', '이용약관1', '<h1>이용약관내용입니다.</h1>', '이용약관', 'Y');
INSERT INTO tbl_admin_bulletin_board(write_date, user_code, modify_date, title, content, post_type, state)
VALUES ('2021-03-01 10:20:30', '2', '2021-05-02 10:20:30', '개인정보처리방침1', '<h1>개인정보처리방침내용입니다.</h1>', '개인정보처리방침', 'Y');
INSERT INTO tbl_admin_bulletin_board(write_date, user_code, modify_date, title, content, post_type, state)
VALUES ('2021-03-01 10:20:30', '2', '2021-05-02 10:20:30', 'FAQ1', '<h1>FAQ내용입니다.</h1>', 'FAQ', 'Y');

/*이벤트. 3번 user인 관리자*/
INSERT INTO tbl_event(writer_code, write_date, start_day, end_day, progress, status, title, content, discount)
VALUES (2, '2023-01-01', '2023-02-01', '2023-02-28', 'N', 'N', '이벤트제목입니다.', '<p>이벤트 내용입니다.</p>', 0.1);

/*1:1문의. 6번 user가 문의한 내용. 2번 user인 관리자가 답변한다.*/
INSERT INTO tbl_inquiry(member_code, secret_state, password, answer_state, write_date, modify_date, post_type, title,
                        content, state)
VALUES (6, 'Y', '123456', 'Y', '2023-05-05 10:22:33', '2023-05-05 10:30:21', '상품', '제품에관한질문입니다.(제목입니다)',
        '<p>제품에관한질문에 대한 내용입니다.</p>', 'Y');
INSERT INTO tbl_comment(inquiry_code, user_code, write_date, modify_date, content)
VALUES (1, 2, '2023-05-05 19:20:23', '2023-05-05 20:10:00', '제품에 관한 질문에 대한 답변의 내용입니다.');

/*카테고리*/
INSERT INTO tbl_category(upper_category_code, state, name) /*1*/
VALUES (null, 'Y', '침실');
INSERT INTO tbl_category(upper_category_code, state, name) /*2*/
VALUES (null, 'Y', '거실');
INSERT INTO tbl_category(upper_category_code, state, name) /*3*/
VALUES (null, 'Y', '서재');
INSERT INTO tbl_category(upper_category_code, state, name) /*4*/
VALUES (null, 'Y', '주방');
INSERT INTO tbl_category(upper_category_code, state, name) /*5*/
VALUES (1, 'Y', '침대');
INSERT INTO tbl_category(upper_category_code, state, name) /*6*/
VALUES (1, 'Y', '옷장');
INSERT INTO tbl_category(upper_category_code, state, name) /*7*/
VALUES (1, 'Y', '화장대');
INSERT INTO tbl_category(upper_category_code, state, name) /*8*/
VALUES (2, 'Y', '소파');
INSERT INTO tbl_category(upper_category_code, state, name) /*9*/
VALUES (2, 'Y', '테이블');
INSERT INTO tbl_category(upper_category_code, state, name) /*10*/
VALUES (2, 'Y', '수납장');
INSERT INTO tbl_category(upper_category_code, state, name) /*11*/
VALUES (3, 'Y', '책상');
INSERT INTO tbl_category(upper_category_code, state, name) /*12*/
VALUES (3, 'Y', '책장');
INSERT INTO tbl_category(upper_category_code, state, name) /*13*/
VALUES (3, 'Y', '의자');
INSERT INTO tbl_category(upper_category_code, state, name) /*14*/
VALUES (4, 'Y', '식탁');
INSERT INTO tbl_category(upper_category_code, state, name) /*15*/
VALUES (4, 'Y', '수납장');

/*옵션목록. 우선은 검정과 흰색만*/
INSERT INTO tbl_option(option_code, name)
VALUES ('#000000', '검정색');
INSERT INTO tbl_option(option_code, name)
VALUES ('#FFFFFF', '흰색');

/*제품*/
/*1번제품은 수납장*/
INSERT INTO tbl_product
(category_code, product_name, orderable_status, price, registration_date, modification_date, event_progressionstatus,
 delivery_charge, reviews, grade, sales_start, sales_end)
VALUES (15, '수납장이에요', 'Y', 90000, '2019-12-31 15:00:00', '2021-01-01 14:23:22', 'N', 3000, 1, 4, '2020-01-01 08:00:00',
        '2099-12-31 23:59:59');
INSERT INTO tbl_option_list(option_code, product_code, stock, exposure_status, sales_volume)
VALUES ('#000000', 1, 30, 'Y', 2);
INSERT INTO tbl_option_list(option_code, product_code, stock, exposure_status, sales_volume)
VALUES ('#FFFFFF', 1, 20, 'Y', 1);
/*2번제품은 책상*/
INSERT INTO tbl_product
(category_code, product_name, orderable_status, price, registration_date, modification_date, event_progressionstatus,
 delivery_charge, reviews, grade, sales_start, sales_end)
VALUES (11, '책상이에요', 'Y', 150000, '2019-12-31 15:00:00', '2021-01-01 14:23:22', 'N', 5000, 0, 0, '2020-01-01 08:00:00',
        '2099-12-31 23:59:59');
INSERT INTO tbl_option_list(option_code, product_code, stock, exposure_status, sales_volume)
VALUES ('#000000', 2, 40, 'Y', 1);
INSERT INTO tbl_option_list(option_code, product_code, stock, exposure_status, sales_volume)
VALUES ('#FFFFFF', 2, 40, 'Y', 1);
/*3번제품은 테이블*/
INSERT INTO tbl_product
(category_code, product_name, orderable_status, price, registration_date, modification_date, event_progressionstatus,
 delivery_charge, reviews, grade, sales_start, sales_end)
VALUES (11, '테이블이에요', 'Y', 300000, '2019-03-05 15:00:00', '2019-06-06 14:23:22', 'N', 0, 0, 0, '2019-04-01 08:00:00',
        '2099-12-31 23:59:59');
INSERT INTO tbl_option_list(option_code, product_code, stock, exposure_status, sales_volume)
VALUES ('#000000', 3, 40, 'Y', 1);

/*상품리뷰. 1번제품에만 4점짜리 리뷰가 하나 있다. 나머지 제품은 리뷰가 없으므로 별점이 0점이긴 하지만. 출력할때는 리뷰가 없다고 표시하는게 좋을거 같습니다.*/
/*리뷰는 5번회원이 리뷰 했습니다. 그러면 주문내역에서도 5번회원이 주문할겁니다.*/
INSERT INTO tbl_review(product_code, option_code, grade, title, content, write_date, member_code)
VALUES (1, '#FFFFFF', 4, '1번 흰색 제품 리뷰 제목입니다.', '1번 흰색 제품 리뷰 내용입니다.', '2022-03-05 08:00:00', 5);

/*장바구니*/
/*5번사용자, 7번사용자. 7번 사용자는 탈퇴이전(22년 11월 10일 15:50:50 이전)*/
/*customer_code라서 비회원도 가능하지만, 비회원은 세션 또는 쿠키에 저장한다.(비회원의 customer 데이터는 주문시점에 생성되기 때문)
  로그인하면 그 세션에서 자동으로 테이블에 추가되게 할 수도 있겠다.*/
INSERT INTO tbl_cart(customer_code, product_code, option_code, amount)
VALUES (7, 1, '#000000', 3);
INSERT INTO tbl_cart(customer_code, product_code, option_code, amount)
VALUES (7, 2, '#FFFFFF', 1);
INSERT INTO tbl_cart(customer_code, product_code, option_code, amount)
VALUES (7, 3, '#000000', 2);
INSERT INTO tbl_cart(customer_code, product_code, option_code, amount)
VALUES (5, 2, '#000000', 1);
INSERT INTO tbl_cart(customer_code, product_code, option_code, amount)
VALUES (5, 3, '#000000', 1);

/*이벤트제품목록*/
/*INSERT INTO tbl_event(writer_code, write_date, start_day, end_day, progress, status, title, content, discount)
VALUES (2, '2023-01-01', '2023-02-01', '2023-02-28', 'N', 'N', '이벤트제목입니다.', '<p>이벤트 내용입니다.</p>', 0.1);*/
/*위를 토대로 제품 하나를 이벤트에 넣어두기만 하는거로*/
INSERT INTO tbl_event_product_list(post_code, product_code)
VALUES (1, 1);
INSERT INTO tbl_event_product_list(post_code, product_code)
VALUES (1, 2);
INSERT INTO tbl_event_product_list(post_code, product_code)
VALUES (1, 3);

/*이미지*/
/*post_type 에는 이벤트, 1:1문의, 리뷰. 이 세가지 장소에서 있던 게시글의 이미지를 여기에 모여 놓습니다.*/
/*이 때, 파일의 경로가 저장되지만 일단 임시 값을 넣습니다.*/
INSERT INTO tbl_post_img(post_code, create_date, source_path, convert_path, source_name, convert_name, state, post_type)
VALUES (1, '2023-01-01', '원본파일경로입니다.', '변환파일경로입니다.', '원본파일이름입니다.', '변환파일이름입니다.', 'Y', '이벤트');

/*제품이미지*/
/*제품이미지는 1번제품에 대한 이미지만 넣겠습니다*/
/*image_classification 은 '썸네일이미지', '제품관점이미지', '제품설명이미지'*/
INSERT INTO tbl_product_img(product_code, create_date, source_path, convert_path, source_name, convert_name,
                            image_status, image_classification)
VALUES (1, '2019-12-31 15:00:00', '원본파일경로입니다.', '변환파일경로입니다.', '원본파일이름입니다.', '변환파일이름입니다.', 'Y', '썸네일이미지');
INSERT INTO tbl_product_img(product_code, create_date, source_path, convert_path, source_name, convert_name,
                            image_status, image_classification)
VALUES (1, '2019-12-31 15:00:00', '원본파일경로입니다.', '변환파일경로입니다.', '원본파일이름입니다.', '변환파일이름입니다.', 'Y', '제품관점이미지');
INSERT INTO tbl_product_img(product_code, create_date, source_path, convert_path, source_name, convert_name,
                            image_status, image_classification)
VALUES (1, '2019-12-31 15:00:00', '원본파일경로입니다.', '변환파일경로입니다.', '원본파일이름입니다.', '변환파일이름입니다.', 'Y', '제품관점이미지');
INSERT INTO tbl_product_img(product_code, create_date, source_path, convert_path, source_name, convert_name,
                            image_status, image_classification)
VALUES (1, '2019-12-31 15:00:00', '원본파일경로입니다.', '변환파일경로입니다.', '원본파일이름입니다.', '변환파일이름입니다.', 'Y', '제품관점이미지');
INSERT INTO tbl_product_img(product_code, create_date, source_path, convert_path, source_name, convert_name,
                            image_status, image_classification)
VALUES (1, '2019-12-31 15:00:00', '원본파일경로입니다.', '변환파일경로입니다.', '원본파일이름입니다.', '변환파일이름입니다.', 'Y', '제품설명이미지');


/*주문내역 + 주문상세내역*/
/*주문내역은 6번유저가 1번#000000 2개, 2번#000000 1개*/
/*5번유저가 1번#FFFFFF 1개, 2번#FFFFFF 1개, 3번#000000 1개*/
/*state에는 결제완료, 배송완료, 교환, 환불*/
INSERT INTO tbl_order(product_code, customer_code, sum_price, point_usage, creation_date, state)
VALUES (1, 6, 341000, 5000, '2020-01-03 08:12:03', '배송완료');
INSERT INTO tbl_order(product_code, customer_code, sum_price, point_usage, creation_date, state)
VALUES (1, 5, 548000, 0, '2021-02-05 08:12:03', '배송완료');
/*우선 들어오는 포인트 자체는 가격의 1%로*/
INSERT INTO tbl_order_details
(order_code, product_code, option_code, cost_price, reduced_price, delivery_charge, point, final_price, count)
VALUES (1, 1, '#000000', 90000, 0, 3000, 900, 93000, 2);
INSERT INTO tbl_order_details
(order_code, product_code, option_code, cost_price, reduced_price, delivery_charge, point, final_price, count)
VALUES (1, 2, '#000000', 150000, 0, 5000, 1500, 155000, 1);
INSERT INTO tbl_order_details
(order_code, product_code, option_code, cost_price, reduced_price, delivery_charge, point, final_price, count)
VALUES (2, 1, '#FFFFFF', 90000, 0, 3000, 900, 93000, 1);
INSERT INTO tbl_order_details
(order_code, product_code, option_code, cost_price, reduced_price, delivery_charge, point, final_price, count)
VALUES (2, 2, '#FFFFFF', 150000, 0, 5000, 1500, 155000, 1);
INSERT INTO tbl_order_details
(order_code, product_code, option_code, cost_price, reduced_price, delivery_charge, point, final_price, count)
VALUES (2, 3, '#000000', 300000, 0, 0, 3000, 300000, 1);
/*sum_price는 포인트 사용전 주문상세내역의 가격 총합(원가, 할인가격, 배송비, 개수 모두 고려해서)*/

/*배송내역*/
/*6번 user가 구매한 내역 하나,*/
/*5번 user가 구매한 내역 하나,*/
/*state 에는 배송중, 배송완료*/
INSERT INTO tbl_delivery(recipent_name, delivery_address, delivery_requests, recipient_phone, arrival_time, state)
VALUES ('받는사람이름입니다.', '서울시 종로구 머시기', '바닥에 놓고 가주세요', '010-9999-9999', '2020-01-03 23:12:03', '배송완료');
INSERT INTO tbl_delivery(recipent_name, delivery_address, delivery_requests, recipient_phone, arrival_time, state)
VALUES ('받는사람이름입니다.2', '서울시 종로구 머시기2', '바닥에 놓고 가주세요2', '010-9999-9998', '2020-02-05 23:12:03', '배송완료');

/*결제내역*/
/*6번 user가 구매한 내역 하나,*/
/*5번 user가 구매한 내역 하나,*/
/*state에는 환불, 교환, 결제완료, 결제전*/
/*환불이나 교환상태로 넘어간다음에 교환내역테이블이나 환불내역 테이블이 생기더라도 결제유무가 바뀌진 않는다.*/
INSERT INTO tbl_payment(details, complete_state, date, state)
VALUES ('카카오페이', 'Y', '2020-01-03 08:12:06', '결제완료');
INSERT INTO tbl_payment(details, complete_state, date, state)
VALUES ('카카오페이', 'Y', '2021-02-05 08:12:06', '결제완료');

/*주문리스트. 주문내역, 결제내역, 배송내역의 PK를 키로 가지는 테이블*/
/*6번, 5번*/
INSERT INTO tbl_order_list(order_code, delivery_code, payment_code)
VALUES (1, 1, 1);
INSERT INTO tbl_order_list(order_code, delivery_code, payment_code)
VALUES (2, 2, 2);
commit;