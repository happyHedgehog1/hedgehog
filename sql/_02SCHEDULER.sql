DELIMITER //
CREATE EVENT IF NOT EXISTS updateWithdrawStatusEvent
    ON SCHEDULE
        EVERY 1 DAY
            STARTS TIMESTAMP(CURRENT_DATE, '00:00:00')
    DO
    BEGIN
        UPDATE tbl_withdraw
        SET state       = 'N',
            commit_date = CURRENT_TIMESTAMP
        WHERE state = 'Y'
          AND commit_date is null
          AND cancel_date is null
          AND DATEDIFF(CURRENT_TIMESTAMP, cancel_date) >= 7;
        UPDATE tbl_user
        SET withdraw_state = 'N'
        WHERE user_code IN (SELECT user_code FROM tbl_withdraw WHERE state = 'N' AND commit_date IS NOT NULL);
    END //
DELIMITER ;

SELECT * FROM INFORMATION_SCHEMA.EVENTS WHERE EVENT_NAME = 'updateWithdrawStatusEvent';