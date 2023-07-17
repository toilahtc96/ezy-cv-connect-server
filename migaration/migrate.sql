CREATE TABLE `cvdatabase`.`address` (
                                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                                        `type` INT NULL COMMENT 'Loại địa chỉ',
                                        `code` VARCHAR(45) NULL COMMENT 'mã địa chỉ',
                                        `name` VARCHAR(100) NULL COMMENT 'Tên',
                                        `parent_id` BIGINT NULL,
                                        `created_id` INT NULL,
                                        `created_time` TIME NULL,
                                        `updated_time` TIME NULL,
                                        PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COMMENT = 'Thông tin địa chỉ, sẽ mapping với công ty, phục vụ tìm kiếm';


CREATE TABLE `cvdatabase`.`step` (
                                     `id` INT NOT NULL AUTO_INCREMENT,
                                     `code` VARCHAR(45) NOT NULL COMMENT 'Mã tiến trình',
                                     `meaning` VARCHAR(100) NULL COMMENT 'mô tả',
                                     PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
ALTER TABLE `cvdatabase`.step
    ADD COLUMN `created_id` INT NULL AFTER `meaning`,
ADD COLUMN `created_time` TIME NULL AFTER `created_id`,
ADD COLUMN `updated_time` TIME NULL AFTER `created_time`;
alter table `cvdatabase`.step add column status ENUM('ACTIVED','DISABLED') null;
alter table  `cvdatabase`.step change code code int not null;

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



CREATE TABLE `cvdatabase`.`level` (
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `name` VARCHAR(45) NOT NULL COMMENT 'tên level; fresher,middle,v..v\n',
                                      `meaning` VARCHAR(100) NULL COMMENT 'ý nghĩa',
                                      `created_id` INT NULL,
                                      `created_time` TIME NULL,
                                      `updated_time` TIME NULL,
                                      PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
alter table `level`  add column status ENUM('ACTIVED','DISABLED') null;


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
                                     `birth_date` TIME NULL,
                                     `name` VARCHAR(100) NOT NULL,
                                     `experience_year` INT NULL COMMENT 'số năm kinh nghiệm, cho candidate',
                                     `cv_link` VARCHAR(500) NULL COMMENT 'link cv của candidate',
                                     `username` VARCHAR(100) NOT NULL,
                                     `password` VARCHAR(100) NOT NULL,
                                     status ENUM('ACTIVED','DISABLED') null,
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
                                     `status` ENUM('ACTIVED','DISABLED') null,
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
alter table `review`  add column status ENUM('ACTIVED','DISABLED') null;

CREATE TABLE `cvdatabase`.`progress` (
                                         `id` INT NOT NULL AUTO_INCREMENT,
                                         `agency_id` INT NOT NULL,
                                         `candidate_id` INT NOT NULL,
                                         `status` ENUM('ACTIVED','DISABLED') null,
                                         `step_id` INT NOT NULL COMMENT 'thông tin tiến trình',
                                         `created_id` INT NULL,
                                         `created_time` TIME NULL,
                                         `updated_time` TIME NULL,
                                         PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


create table `cvdatabase`.`access_token`
(
    access_token   varchar(500) not null,
    user_id       INT         null,
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

alter table address add column status ENUM('ACTIVED','DISABLED') null;
alter table company add column status ENUM('ACTIVED','DISABLED') null;
alter table user_type add column status ENUM('ACTIVED','DISABLED') null;
alter table user add column avatar_url varchar(500);
create table voucher(
                        id  int auto_increment
                            primary key,
                        title varchar(500) not null,
                        voucher_type enum('PERCENT', 'CASH'),
                        `value` double not null,
                        start_time time null,
                        end_time time null,
                        status       enum ('ACTIVED', 'DISABLED') null,
                        created_id   int                          null,
                        created_time time                         null,
                        updated_time time                         null
);

alter table voucher change column end_time end_time   datetime                         null;

alter table voucher change column start_time start_time   datetime                         null;
create table job(
                    id  int auto_increment
                        primary key,
                    job_type_id INT null,
                    company_id INT null,
                    quantity INTEGER null,
                    range_salary_min BIGINT null,
                    range_salary_max BIGINT null,
                    information BLOB(60000) null,
                    level_id INT null,
                    career_id INT null,
                    custom_range bit null,
                    working_form_id INT null,
                    tags VARCHAR(1000) null,
                    thumbnail VARCHAR(1000) null,
                    title VARCHAR(1000) null,
                    voucher_id INT null,
                    reason_for_choosing VARCHAR(1000) null,
                    status       enum ('ACTIVED', 'DISABLED') null,
                    created_id   int                          null,
                    created_time time                         null,
                    updated_time time                         null
);
alter table progress add column job_id int null;
create index job_tag on job(tags);

alter table step change column  code
    code         enum ('INIT', 'SEND_CV_TO_AGENCY', 'SEND_CV_TO_COMPANY', 'SUCCESS', 'FAIL','DISCUSSION_WITH_AGENCY','PROVIDE_INTERVIEW_DATE') not null;

alter table step add column `order` int null;
commit;

INSERT INTO cvdatabase.user_type (id, code, meaning, created_id, created_time, updated_time, status) VALUES (1, 'ADMIN', 'ADMIN', 1, null, null, 'ACTIVED');
INSERT INTO cvdatabase.user_type (id, code, meaning, created_id, created_time, updated_time, status) VALUES (2, 'AGENCY', 'AGENCY', 1, null, null, 'ACTIVED');
INSERT INTO cvdatabase.role (id, code, name, status, created_id, created_time, updated_time) VALUES (1, 'USER', 'USER', 'ACTIVED', 1, null, null);
INSERT INTO cvdatabase.role (id, code, name, status, created_id, created_time, updated_time) VALUES (2, 'ADMIN', 'ADMIN', 'ACTIVED', 1, null, null);

create table working_form(
                             id  int auto_increment
                                 primary key,
                             name varchar(500) not null,
                             description text null,
                             status enum ('ACTIVED', 'DISABLED') null,
                             created_id int null
);
commit;
create index  name_working_form on working_form(name);
commit;

create table career(
                             id  int auto_increment
                                 primary key,
                             name varchar(500) not null,
                             description text null,
                             status enum ('ACTIVED', 'DISABLED') null,
                             created_id int null
);
commit;
create index  name_career on career(name);
commit;

create table job_type(
                       id  int auto_increment
                           primary key,
                       name varchar(500) not null,
                       description text null,
                       status enum ('ACTIVED', 'DISABLED') null,
                       created_id int null
);
commit;
create index  name_job_type on job_type(name);
commit;

alter table address change type type         enum('PROVINCE','DISTRICT','PRECINCT')                          null comment 'Loại địa chỉ';
commit ;

# setup data
# step
INSERT INTO cvdatabase.step (id, code, meaning, created_id, created_time, updated_time, status, `order`) VALUES (1, 'SEND_CV_TO_AGENCY', 'Send cv to agency', 1, null, null, 'ACTIVED', 1);
INSERT INTO cvdatabase.step (id, code, meaning, created_id, created_time, updated_time, status, `order`) VALUES (2, 'SEND_CV_TO_COMPANY', 'send cv to company', 1, null, null, 'ACTIVED', 2);
INSERT INTO cvdatabase.step (id, code, meaning, created_id, created_time, updated_time, status, `order`) VALUES (3, 'SUCCESS', 'SUCCESS', 1, null, null, 'ACTIVED', 3);
INSERT INTO cvdatabase.step (id, code, meaning, created_id, created_time, updated_time, status, `order`) VALUES (4, 'FAIL', 'FAIL', 1, null, null, 'ACTIVED', 4);
