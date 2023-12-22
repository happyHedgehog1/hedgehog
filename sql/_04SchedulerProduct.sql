DELIMITER //  --MySQL에서 사용하는 구분자를 설정, 쿼리문의 끝이라는 표식을 뭘로할지 지정 기본은 ; 인데 여기서는 // 사용한다는 의미
CREATE EVENT IF NOT EXISTS updateProductOrderableStatusEvent  --여기서부터 새로운 이벤트 생성 IF NOT EXISTS로 해당 이름의 이벤트가 존재하지않을 경우에만 이벤트 생성
    ON SCHEDULE
        EVERY 1 DAY
            STARTS TIMESTAMP(CURRENT_DATE, '00:00:00') --매일 00:00:00에 실행되도록 예약
    DO                                                 --이벤트가 실행될 때 수행할 작업 정의 UPDATE부터는 쿼리문임
    BEGIN                                              --product 테이블의 sales_start가 오늘이면 orderable_status를 Y로 바꾸고, sales_end가 오늘이면 orderable_status를 N으로 바꾼다
        UPDATE tbl_product
        SET orderable_status = 'Y'
        WHERE DATE(sales_start) = CURRENT_DATE;

        UPDATE tbl_product
        SET orderable_status = 'N'
        WHERE DATE(sales_end) = CURRENT_DATE;
    END //
DELIMITER ; -- 구분자를 다시 ; 로 설정

SELECT * FROM INFORMATION_SCHEMA.EVENTS WHERE EVENT_NAME = 'updateProductOrderableStatusEvent'; --생성된 이벤트가 정상적으로 등록되었는지 확인하기 위해 .EVENT 테이블 조회하는 쿼리