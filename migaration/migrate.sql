CREATE TABLE `cvdatabase`.`address` (
         `id` INT NOT NULL AUTO_INCREMENT,
         `type` INT NULL COMMENT '\'Loại địa chỉ\'',
         `code` VARCHAR(45) NULL COMMENT '\'mã địa chỉ\'',
         `name` VARCHAR(100) NULL COMMENT '\'Tên\'',
         `created_id` INT NULL,
         `created_time` TIME NULL,
         `updated_time` TIME NULL,
         PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Thông tin địa chỉ, sẽ mapping với công ty, phục vụ tìm kiếm';


CREATE TABLE `cvdatabase`.`process` (
         `id` INT NOT NULL AUTO_INCREMENT,
         `code` VARCHAR(45) NOT NULL COMMENT 'Mã tiến trình',
         `meaning` VARCHAR(100) NULL COMMENT 'mô tả',
         PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
ALTER TABLE `cvdatabase`.`process`
    ADD COLUMN `created_id` INT NULL AFTER `meaning`,
ADD COLUMN `created_time` TIME NULL AFTER `created_id`,
ADD COLUMN `updated_time` TIME NULL AFTER `created_time`;


CREATE TABLE `cvdatabase`.`user_type` (
           `id` INT NOT NULL AUTO_INCREMENT,
           `code` VARCHAR(45) NOT NULL COMMENT 'mã type',
           `meaning` VARCHAR(100) NULL COMMENT 'ý nghĩa',
           `created_id` INT NULL,
           `created_time` TIME NULL,
           `updated_time` TIME NULL,
           PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



CREATE TABLE `cvdatabase`.`candidate_level` (
                 `id` INT NOT NULL AUTO_INCREMENT,
                 `name` VARCHAR(45) NOT NULL COMMENT 'tên level; fresher,middle,v..v\n',
                 `meaning` VARCHAR(100) NULL COMMENT 'ý nghĩa',
                 `created_id` INT NULL,
                 `created_time` TIME NULL,
                 `updated_time` TIME NULL,
                 PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `cvdatabase`.`company` (
         `id` INT NOT NULL,
         `code` VARCHAR(45) NOT NULL COMMENT 'mã công ty',
         `name` VARCHAR(200) NOT NULL COMMENT 'tên công ty',
         `province_code` VARCHAR(45) NULL COMMENT 'mã phường xã',
         `district_code` VARCHAR(45) NULL COMMENT 'mã quận huyện\n',
         `precinct_code` VARCHAR(45) NULL COMMENT 'mã phường xã',
         `infomation` VARCHAR(500) NULL COMMENT 'mô tả công ty',
         `star` INT NULL COMMENT 'số sao đánh giá',
         `created_id` INT NULL,
         `created_time` TIME NULL,
         `updated_time` TIME NULL,
         PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table company
    change infomation information varchar(500) null;

CREATE TABLE `cvdatabase`.`user` (
        `id` INT NOT NULL,
        `role_id` INT NOT NULL,
        `company_id` INT NULL COMMENT 'cho agency\n',
        `type_id` INT NULL COMMENT '1: agency \n2: candidate',
        `level_id` INT NULL COMMENT 'cho candidate\n',
        `description` VARCHAR(500) NULL,
        `star` INT NULL COMMENT 'Đánh giá \ncho agency\n',
        `infomation` VARCHAR(500) NULL,
        `birth_date` TIME NOT NULL,
        `name` VARCHAR(100) NOT NULL,
        `experience_year` INT NULL COMMENT 'số năm kinh nghiệm, cho candidate',
        `cv_link` VARCHAR(500) NULL COMMENT 'link cv của candidate',
        `username` VARCHAR(100) NOT NULL,
        `password` VARCHAR(100) NOT NULL,
        `status` TINYINT(2) NULL COMMENT 'trạng thái\n1: hoạt động\n2: không hoạt động\n',
        PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table user
    modify id int auto_increment;

alter table user
    change infomation information varchar(500) null;

ALTER TABLE `cvdatabase`.`user`
    ADD COLUMN `created_id` INT NULL AFTER `status`,
ADD COLUMN `created_time` TIME NULL AFTER `created_id`,
ADD COLUMN `updated_time` TIME NULL AFTER `created_time`;

CREATE TABLE `cvdatabase`.`user_role` (
           `id` INT NOT NULL AUTO_INCREMENT,
           `user_id` INT NULL,
           `role_id` INT NULL,
           `created_id` INT NULL,
           `created_time` TIME NULL,
           `updated_time` TIME NULL,
           PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `cvdatabase`.`role` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `code` VARCHAR(45) NOT NULL,
        `name` VARCHAR(45) NOT NULL,
        `status` INT NULL,
        `created_id` INT NULL,
        `created_time` TIME NULL,
        `updated_time` TIME NULL,
        PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `cvdatabase`.`review` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `description` VARCHAR(200) NULL,
        `star` INT NULL,
        `object_id` INT NULL,
        `review_user_id` INT NULL,
        `type` INT NULL COMMENT '1: review company\n2: review agency\n',
        `created_id` INT NULL,
        `created_time` TIME NULL,
        `updated_time` TIME NULL,
        PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `cvdatabase`.`deal` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `agency_id` INT NOT NULL,
        `candidate_id` INT NOT NULL,
        `status` INT NULL,
        `process_id` INT NOT NULL COMMENT 'thông tin tiến trình của deal',
        `created_id` INT NULL,
        `created_time` TIME NULL,
        `updated_time` TIME NULL,
        `dealcol` VARCHAR(45) NULL,
        PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


create table `cvdatabase`.`access_token`
(
    access_token   varchar(500) not null,
    user_id        long         null,
    expire_at      TIMESTAMP    null,
    expire_in      TIMESTAMP    null,
    first_issue_at TIMESTAMP    null,
    created_time   TIMESTAMP    null,
    updated_time   TIMESTAMP    null,
    deleted        binary       null,
    constraint access_token_pk
        primary key (access_token)
);
alter table access_token
    modify user_id INT null;


alter table user
    modify role_id INT null;

alter table user
    modify company_id INT null;

