# DROP SCHEMA IF EXISTS `oneinfurniture`;
# CREATE SCHEMA IF NOT EXISTS `oneinfurniture`;
/*윗부분은 진짜로 큰일 났을때 root 계정에서 실행한 다음 아래를 실행*/
use `oneinfurniture`;
DROP TABLE IF EXISTS `tbl_admin_bulletin_board`;
DROP TABLE IF EXISTS `tbl_authority_list`;
DROP TABLE IF EXISTS `tbl_authority`;
DROP TABLE IF EXISTS `tbl_cart`;
DROP TABLE IF EXISTS `tbl_comment`;
DROP TABLE IF EXISTS `tbl_event_product_list`;
DROP TABLE IF EXISTS `tbl_exchange`;
DROP TABLE IF EXISTS `tbl_mail_history`;
DROP TABLE IF EXISTS `tbl_mailform`;
DROP TABLE IF EXISTS `tbl_order_details`;
DROP TABLE IF EXISTS `tbl_order_list`;
DROP TABLE IF EXISTS `tbl_order`;
DROP TABLE IF EXISTS `tbl_delivery`;
DROP TABLE IF EXISTS `tbl_post_img`;
DROP TABLE IF EXISTS `tbl_event`;
DROP TABLE IF EXISTS `tbl_product_img`;
DROP TABLE IF EXISTS `tbl_review`;
DROP TABLE IF EXISTS `tbl_option_list`;
DROP TABLE IF EXISTS `tbl_option`;
DROP TABLE IF EXISTS `tbl_product`;
DROP TABLE IF EXISTS `tbl_category`;
DROP TABLE IF EXISTS `tbl_refund`;
DROP TABLE IF EXISTS `tbl_inquiry`;
DROP TABLE IF EXISTS `tbl_member`;
DROP TABLE IF EXISTS `tbl_payment`;
DROP TABLE IF EXISTS `tbl_customer`;
DROP TABLE IF EXISTS `tbl_certified`;
DROP TABLE IF EXISTS `tbl_withdraw`;
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_admin_bulletin_board`
(
    `post_code`   INTEGER             NOT NULL auto_increment,
    `write_date`  DATETIME            NOT NULL,
    `content`     VARCHAR(3000)       NOT NULL,
    `title`       VARCHAR(100),
    `views`       INTEGER DEFAULT 0   NOT NULL,
    `post_type`   VARCHAR(30)         NOT NULL,
    `user_code`   INTEGER             NOT NULL,
    `state`       CHAR(1) DEFAULT 'Y' NOT NULL,
    `modify_date` DATETIME,
    PRIMARY KEY (`post_code`)
);


CREATE TABLE `tbl_authority`
(
    `authority_code` INTEGER     NOT NULL auto_increment,
    `name`           VARCHAR(30) NOT NULL,
    PRIMARY KEY (`authority_code`)
);


CREATE TABLE `tbl_authority_list`
(
    `authority_code` INTEGER NOT NULL,
    `user_code`      INTEGER NOT NULL,
    PRIMARY KEY (`authority_code`, `user_code`)
);


CREATE TABLE `tbl_cart`
(
    `cart_code`     INTEGER           NOT NULL auto_increment,
    `amount`        INTEGER DEFAULT 0 NOT NULL,
    `customer_code` INTEGER           NOT NULL,
    `option_code`   VARCHAR(30)       NOT NULL,
    `product_code`  INTEGER           NOT NULL,
    PRIMARY KEY (`cart_code`)
);


CREATE TABLE `tbl_category`
(
    `category_code`       INTEGER             NOT NULL auto_increment,
    `state`               CHAR(1) DEFAULT 'N' NOT NULL,
    `name`                VARCHAR(50)         NOT NULL,
    `upper_category_code` INTEGER,
    PRIMARY KEY (`category_code`)
);


CREATE TABLE `tbl_certified`
(
    `certification_number` INTEGER NOT NULL auto_increment,
    `code`                 INTEGER NOT NULL,
    PRIMARY KEY (`certification_number`)
);


CREATE TABLE `tbl_comment`
(
    `comment_code` INTEGER       NOT NULL auto_increment,
    `inquiry_code` INTEGER       NOT NULL,
    `user_code`    INTEGER       NOT NULL,
    `write_date`   DATETIME      NOT NULL,
    `modify_date`  DATETIME,
    `content`      VARCHAR(1000) NOT NULL,
    PRIMARY KEY (`comment_code`)
);


CREATE TABLE `tbl_customer`
(
    `customer_code`        INTEGER             NOT NULL,
    `member_state`         CHAR(1) DEFAULT 'Y' NOT NULL,
    `email`                VARCHAR(50)         NOT NULL,
    `phone`                VARCHAR(20),
    `certification_number` INTEGER,
    PRIMARY KEY (`customer_code`)
);


CREATE TABLE `tbl_delivery`
(
    `delivery_code`     INTEGER     NOT NULL auto_increment,
    `delivery_address`  VARCHAR(50) NOT NULL,
    `delivery_requests` VARCHAR(100),
    `arrival_time`      DATETIME,
    `recipent_name`     VARCHAR(50) NOT NULL,
    `recipient_phone`   VARCHAR(20),
    `state`             VARCHAR(20) NOT NULL,
    PRIMARY KEY (`delivery_code`)
);


CREATE TABLE `tbl_event`
(
    `post_code`   INTEGER             NOT NULL auto_increment,
    `writer_code` INTEGER             NOT NULL,
    `start_day`   DATETIME,
    `end_day`     DATETIME,
    `progress`    CHAR(1) DEFAULT 'N' NOT NULL,
    `views`       INTEGER DEFAULT 0   NOT NULL,
    `status`      CHAR(1) DEFAULT 'N' NOT NULL,
    `title`       VARCHAR(100)        NOT NULL,
    `content`     VARCHAR(3000)       NOT NULL,
    `write_date`  DATETIME            NOT NULL,
    `modify_date` DATETIME,
    `discount`    FLOAT   DEFAULT 0.0 NOT NULL,
    PRIMARY KEY (`post_code`)
);


CREATE TABLE `tbl_event_product_list`
(
    `post_code`    INTEGER NOT NULL,
    `product_code` INTEGER NOT NULL,
    PRIMARY KEY (`post_code`, `product_code`)
);


CREATE TABLE `tbl_exchange`
(
    `exchange_code` INTEGER      NOT NULL auto_increment,
    `date`          DATETIME,
    `cause`         VARCHAR(255) NOT NULL,
    `payment_code`  INTEGER      NOT NULL,
    `inquiry_code`  INTEGER,
    PRIMARY KEY (`exchange_code`)
);


CREATE TABLE `tbl_inquiry`
(
    `inquiry_code` INTEGER             NOT NULL auto_increment,
    `secret_state` CHAR(1) DEFAULT 'N' NOT NULL,
    `answer_state` CHAR(1) DEFAULT 'N' NOT NULL,
    `write_date`   DATETIME            NOT NULL,
    `post_type`    VARCHAR(10)         NOT NULL,
    `title`        VARCHAR(100)        NOT NULL,
    `content`      VARCHAR(3000)       NOT NULL,
    `password`     VARCHAR(100),
    `state`        CHAR(1) DEFAULT 'N' NOT NULL,
    `modify_date`  DATETIME,
    `member_code`  INTEGER             NOT NULL,
    PRIMARY KEY (`inquiry_code`)
);


CREATE TABLE `tbl_mail_history`
(
    `mail_code`     INTEGER       NOT NULL auto_increment,
    `content`       VARCHAR(3000) NOT NULL,
    `date`          DATETIME      NOT NULL,
    `title`         VARCHAR(100)  NOT NULL,
    `form_code`     INTEGER,
    `user_code`     INTEGER       NOT NULL,
    `customer_code` INTEGER       NOT NULL,
    PRIMARY KEY (`mail_code`)
);


CREATE TABLE `tbl_mailform`
(
    `form_code`     INTEGER       NOT NULL auto_increment,
    `name`          VARCHAR(100)  NOT NULL,
    `content`       VARCHAR(3000) NOT NULL,
    `creation_date` DATETIME      NOT NULL,
    `modify_date`   DATETIME,
    `title`         VARCHAR(50)   NOT NULL,
    PRIMARY KEY (`form_code`)
);


CREATE TABLE `tbl_member`
(
    `member_code`   INTEGER             NOT NULL,
    `birthday`      DATE                NOT NULL,
    `gender`        CHAR(1) DEFAULT 'M' NOT NULL,
    `email_consent` CHAR(1) DEFAULT 'Y' NOT NULL,
    `point`         INTEGER DEFAULT 0   NOT NULL,
    `cumulative_amount` INTEGER DEFAULT 0 NOT NULL,
    PRIMARY KEY (`member_code`)
);


CREATE TABLE `tbl_option`
(
    `option_code` VARCHAR(30) NOT NULL,
    `name`        VARCHAR(30) NOT NULL,
    PRIMARY KEY (`option_code`)
);


CREATE TABLE `tbl_option_list`
(
    `product_code`    INTEGER             NOT NULL,
    `option_code`     VARCHAR(30)         NOT NULL,
    `stock`           INTEGER DEFAULT 0   NOT NULL,
    `exposure_status` CHAR(1) DEFAULT 'Y' NOT NULL,
    `sales_volume`    INTEGER DEFAULT 0   NOT NULL,
    PRIMARY KEY (`option_code`, `product_code`)
);


CREATE TABLE `tbl_order`
(
    `order_code`    INTEGER           NOT NULL auto_increment,
    `customer_code` INTEGER           NOT NULL,
    `product_code`  INTEGER           NOT NULL,
    `creation_date` DATETIME          NOT NULL,
    `point_usage`   INTEGER DEFAULT 0 NOT NULL,
    `state`         VARCHAR(30)       NOT NULL,
    PRIMARY KEY (`order_code`)
);


CREATE TABLE `tbl_order_details`
(
    `order_details_code` INTEGER           NOT NULL auto_increment,
    `count`              INTEGER           NOT NULL,
    `product_code`       INTEGER           NOT NULL,
    `order_code`         INTEGER           NOT NULL,
    `delivery_charge`    INTEGER DEFAULT 0 NOT NULL,
    `point`              INTEGER           NOT NULL,
    `reduced_price`      INTEGER           NOT NULL,
    `cost_price`         INTEGER           NOT NULL,
    `final_price`        INTEGER           NOT NULL,
    `option_code`        VARCHAR(30)       NOT NULL,
    PRIMARY KEY (`order_details_code`)
);


CREATE TABLE `tbl_order_list`
(
    `order_code`    INTEGER NOT NULL,
    `delivery_code` INTEGER NOT NULL,
    `payment_code`  INTEGER NOT NULL,
    PRIMARY KEY (`order_code`)
);


CREATE TABLE `tbl_payment`
(
    `payment_code`   INTEGER             NOT NULL auto_increment,
    `details`        VARCHAR(30)         NOT NULL,
    `complete_state` CHAR(1) DEFAULT 'N' NOT NULL,
    `date`           DATETIME            NOT NULL,
    `state`          VARCHAR(30),
    PRIMARY KEY (`payment_code`)
);


CREATE TABLE `tbl_post_img`
(
    `post_img_code` INTEGER             NOT NULL auto_increment,
    `post_code`     INTEGER             NOT NULL,
    `create_date`   DATETIME            NOT NULL,
    `modify_date`   DATETIME,
    `source_path`   VARCHAR(255)        NOT NULL,
    `convert_path`  VARCHAR(255)        NOT NULL,
    `state`         CHAR(1) DEFAULT 'Y' NOT NULL,
    `post_type`     VARCHAR(30)         NOT NULL,
    `source_name`   VARCHAR(255)        NOT NULL,
    `convert_name`  VARCHAR(255)        NOT NULL,
    PRIMARY KEY (`post_img_code`)
);


CREATE TABLE `tbl_product`
(
    `product_code`            INTEGER              NOT NULL auto_increment,
    `category_code`           INTEGER              NOT NULL,
    `product_name`            VARCHAR(30)          NOT NULL,
    `orderable_status`        CHAR(1) DEFAULT 'Y'  NOT NULL,
    `price`                   INTEGER              NOT NULL,
    `registration_date`       DATETIME             NOT NULL,
    `modification_date`       DATETIME,
    `event_progressionstatus` CHAR(1) DEFAULT 'N'  NOT NULL,
    `delivery_charge`         INTEGER DEFAULT 3000 NOT NULL,
    `sales_start`             DATETIME,
    `reviews`                 INTEGER DEFAULT 0    NOT NULL,
    `grade`                   INTEGER DEFAULT 0    NOT NULL,
    `sales_end`               DATETIME,
    PRIMARY KEY (`product_code`)
);


CREATE TABLE `tbl_product_img`
(
    `img_code`             INTEGER             NOT NULL auto_increment,
    `image_classification` VARCHAR(30)         NOT NULL,
    `source_path`          VARCHAR(255)        NOT NULL,
    `convert_path`         VARCHAR(255)        NOT NULL,
    `source_name`          VARCHAR(255)        NOT NULL,
    `convert_name`         VARCHAR(255)        NOT NULL,
    `image_status`         CHAR(1) DEFAULT 'N' NOT NULL,
    `create_date`          DATETIME            NOT NULL,
    `modify_date`          DATETIME,
    `product_code`         INTEGER             NOT NULL,
    PRIMARY KEY (`img_code`)
);


CREATE TABLE `tbl_refund`
(
    `refund_code`  INTEGER      NOT NULL auto_increment,
    `date`         DATETIME,
    `cause`        VARCHAR(255) NOT NULL,
    `payment_code` INTEGER      NOT NULL,
    `inquiry_code` INTEGER,
    PRIMARY KEY (`refund_code`)
);


CREATE TABLE `tbl_review`
(
    `review_code`  INTEGER           NOT NULL auto_increment,
    `product_code` INTEGER           NOT NULL,
    `grade`        INTEGER DEFAULT 5 NOT NULL,
    `content`      VARCHAR(3000)     NOT NULL,
    `write_date`   DATETIME          NOT NULL,
    `option_code`  VARCHAR(30)       NOT NULL,
    `member_code`  INTEGER           NOT NULL,
    `state`        CHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY (`review_code`)
);


CREATE TABLE `tbl_user`
(
    `user_code`       INTEGER  NOT NULL auto_increment,
    `id`              VARCHAR(30),
    `password`        VARCHAR(100),
    `classify`        VARCHAR(30),
    `name`            VARCHAR(30),
    `connection_date` DATETIME,
    `creation_date`   DATETIME NOT NULL,
    `withdraw_state`  CHAR(1) default 'N',
    PRIMARY KEY (`user_code`)
);

# alter table tbl_user
# modify column withdraw_state char(1) default 'N';


CREATE TABLE `tbl_withdraw`
(
    `withdraw_code` INTEGER                    NOT NULL auto_increment,
    `user_code`     INTEGER             NOT NULL,
    `apply_date`    DATETIME,
    `state`         CHAR(1) DEFAULT 'N' NOT NULL,
    `cause`         VARCHAR(3000)        NOT NULL,
    `commit_date`   DATETIME,
    `cancel_date`   DATETIME,
    PRIMARY KEY (`withdraw_code`)
);



ALTER TABLE `tbl_admin_bulletin_board`
    ADD CONSTRAINT `tbl_admin_bulletin_board_FK` FOREIGN KEY (`user_code`) REFERENCES `tbl_user` (`user_code`);
ALTER TABLE `tbl_authority_list`
    ADD CONSTRAINT `tbl_authority_list_FK` FOREIGN KEY (`authority_code`) REFERENCES `tbl_authority` (`authority_code`);
ALTER TABLE `tbl_authority_list`
    ADD CONSTRAINT `tbl_authority_list_FK1` FOREIGN KEY (`user_code`) REFERENCES `tbl_user` (`user_code`);
ALTER TABLE `tbl_cart`
    ADD CONSTRAINT `tbl_cart_FK` FOREIGN KEY (`customer_code`) REFERENCES `tbl_customer` (`customer_code`);
ALTER TABLE `tbl_cart`
    ADD CONSTRAINT `tbl_cart_FK1` FOREIGN KEY (`option_code`, `product_code`) REFERENCES `tbl_option_list` (`option_code`, `product_code`);
ALTER TABLE `tbl_category`
    ADD CONSTRAINT `tbl_category_FK` FOREIGN KEY (`upper_category_code`) REFERENCES `tbl_category` (`category_code`);
ALTER TABLE `tbl_comment`
    ADD CONSTRAINT `tbl_comment_FK` FOREIGN KEY (`inquiry_code`) REFERENCES `tbl_inquiry` (`inquiry_code`);
ALTER TABLE `tbl_comment`
    ADD CONSTRAINT `tbl_comment_FK1` FOREIGN KEY (`user_code`) REFERENCES `tbl_user` (`user_code`);
ALTER TABLE `tbl_customer`
    ADD CONSTRAINT `tbl_customer_FK` FOREIGN KEY (`certification_number`) REFERENCES `tbl_certified` (`certification_number`);
ALTER TABLE `tbl_customer`
    ADD CONSTRAINT `tbl_customer_FK1` FOREIGN KEY (`customer_code`) REFERENCES `tbl_user` (`user_code`);
ALTER TABLE `tbl_event`
    ADD CONSTRAINT `tbl_event_FK` FOREIGN KEY (`writer_code`) REFERENCES `tbl_user` (`user_code`);
ALTER TABLE `tbl_event_product_list`
    ADD CONSTRAINT `tbl_event_product_list_FK` FOREIGN KEY (`post_code`) REFERENCES `tbl_event` (`post_code`);
ALTER TABLE `tbl_event_product_list`
    ADD CONSTRAINT `tbl_event_product_list_FK1` FOREIGN KEY (`product_code`) REFERENCES `tbl_product` (`product_code`);
ALTER TABLE `tbl_exchange`
    ADD CONSTRAINT `tbl_exchange_FK` FOREIGN KEY (`inquiry_code`) REFERENCES `tbl_inquiry` (`inquiry_code`);
ALTER TABLE `tbl_exchange`
    ADD CONSTRAINT `tbl_exchange_FK1` FOREIGN KEY (`payment_code`) REFERENCES `tbl_payment` (`payment_code`);
ALTER TABLE `tbl_inquiry`
    ADD CONSTRAINT `tbl_inquiry_FK` FOREIGN KEY (`member_code`) REFERENCES `tbl_member` (`member_code`);
ALTER TABLE `tbl_mail_history`
    ADD CONSTRAINT `tbl_mail_history_FK` FOREIGN KEY (`customer_code`) REFERENCES `tbl_customer` (`customer_code`);
ALTER TABLE `tbl_mail_history`
    ADD CONSTRAINT `tbl_mail_history_FK1` FOREIGN KEY (`form_code`) REFERENCES `tbl_mailform` (`form_code`);
ALTER TABLE `tbl_mail_history`
    ADD CONSTRAINT `tbl_mail_history_FK2` FOREIGN KEY (`user_code`) REFERENCES `tbl_user` (`user_code`);
ALTER TABLE `tbl_member`
    ADD CONSTRAINT `tbl_member_FK` FOREIGN KEY (`member_code`) REFERENCES `tbl_customer` (`customer_code`);
ALTER TABLE `tbl_option_list`
    ADD CONSTRAINT `tbl_option_list_FK1` FOREIGN KEY (`product_code`) REFERENCES `tbl_product` (`product_code`);
ALTER TABLE `tbl_option_list`
    ADD CONSTRAINT `tbl_option_list_FK` FOREIGN KEY (`option_code`) REFERENCES `tbl_option` (`option_code`);
ALTER TABLE `tbl_order`
    ADD CONSTRAINT `tbl_order_FK` FOREIGN KEY (`customer_code`) REFERENCES `tbl_customer` (`customer_code`);
ALTER TABLE `tbl_order`
    ADD CONSTRAINT `tbl_order_FK1` FOREIGN KEY (`product_code`) REFERENCES `tbl_product` (`product_code`);
ALTER TABLE `tbl_order_details`
    ADD CONSTRAINT `tbl_order_details_FK1` FOREIGN KEY (`order_code`) REFERENCES `tbl_order` (`order_code`);
ALTER TABLE `tbl_order_details`
    ADD CONSTRAINT `tbl_order_details_FK` FOREIGN KEY (`option_code`, `product_code`) REFERENCES `tbl_option_list` (`option_code`, `product_code`);
ALTER TABLE `tbl_order_list`
    ADD CONSTRAINT `tbl_order_list_FK` FOREIGN KEY (`delivery_code`) REFERENCES `tbl_delivery` (`delivery_code`);
ALTER TABLE `tbl_order_list`
    ADD CONSTRAINT `tbl_order_list_FK1` FOREIGN KEY (`order_code`) REFERENCES `tbl_order` (`order_code`);
ALTER TABLE `tbl_order_list`
    ADD CONSTRAINT `tbl_order_list_FK2` FOREIGN KEY (`payment_code`) REFERENCES `tbl_payment` (`payment_code`);
ALTER TABLE `tbl_post_img`
    ADD CONSTRAINT `tbl_post_img_FK` FOREIGN KEY (`event_code`) REFERENCES `tbl_event` (`post_code`);
ALTER TABLE `tbl_post_img`
    ADD CONSTRAINT `tbl_post_img_FK1` FOREIGN KEY (`inquiry_code`) REFERENCES `tbl_inquiry` (`inquiry_code`);
ALTER TABLE `tbl_post_img`
    ADD CONSTRAINT `tbl_post_img_FK2` FOREIGN KEY (`review_code`) REFERENCES `tbl_review` (`review_code`);
ALTER TABLE `tbl_product`
    ADD CONSTRAINT `tbl_product_FK` FOREIGN KEY (`category_code`) REFERENCES `tbl_category` (`category_code`);
ALTER TABLE `tbl_product_img`
    ADD CONSTRAINT `tbl_product_img_FK` FOREIGN KEY (`product_code`) REFERENCES `tbl_product` (`product_code`);
ALTER TABLE `tbl_refund`
    ADD CONSTRAINT `tbl_refund_FK` FOREIGN KEY (`inquiry_code`) REFERENCES `tbl_inquiry` (`inquiry_code`);
ALTER TABLE `tbl_refund`
    ADD CONSTRAINT `tbl_refund_FK1` FOREIGN KEY (`payment_code`) REFERENCES `tbl_payment` (`payment_code`);
ALTER TABLE `tbl_review`
    ADD CONSTRAINT `tbl_review_FK1` FOREIGN KEY (`option_code`, `product_code`) REFERENCES `tbl_option_list` (`option_code`, `product_code`);
ALTER TABLE `tbl_review`
    ADD CONSTRAINT `tbl_review_FK` FOREIGN KEY (`member_code`) REFERENCES `tbl_member` (`member_code`);
ALTER TABLE `tbl_withdraw`
    ADD CONSTRAINT `tbl_withdraw_FK` FOREIGN KEY (`user_code`) REFERENCES `tbl_user` (`user_code`);
commit;