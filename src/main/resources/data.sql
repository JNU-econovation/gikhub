drop table if exists users CASCADE;
drop table if exists comment CASCADE;
drop table if exists money_info CASCADE;
drop table if exists participant CASCADE;
drop table if exists talk_talk CASCADE;
drop table if exists together_team CASCADE;


create table users (
    user_id bigint generated by default as identity,
    email varchar(255),
    real_name varchar(255),
    password varchar(255),
    phone_no varchar(255),
    nick_name varchar(255),
    primary key (user_id)
);

create table comment (
    comment_id bigint generated by default as identity,
    created_date timestamp,
    modified_date timestamp,
    content varchar(255),
    talk_talk_id bigint,
    user_id bigint,
    primary key (comment_id)
);

create table money_info (
    money_info_id bigint generated by default as identity,
    goal_money integer not null,
    delivery_fee integer not null,
    current_money integer not null,
    together_team varchar(255),
    primary key (money_info_id)
);

create table participant (
    participant_id bigint generated by default as identity,
    user_id bigint,
    primary key (participant_id)
);

create table talk_talk (
    talk_talk_id bigint generated by default as identity,
    created_date timestamp,
    modified_date timestamp,
    title varchar(255),
    content varchar(255),
    comment_count int,
    user_id bigint,
    primary key (talk_talk_id)
);

create table together_team (
    team_id bigint generated by default as identity,
    room_type varchar(255),
    store_name varchar(255),
    team_dead_line timestamp,
    receive varchar(255),
    max_user integer not null,
    user_id bigint,
    primary key (team_id)
);

INSERT INTO USERS (email, real_name, password, phone_no, nick_name) VALUES ('minl741@naver.com', '김지민', 'Rrar741!', '010121212', 'moly');
INSERT INTO USERS (email, real_name, password, phone_no, nick_name) VALUES ('merge@fairy.com', '머지요정', 'adfasSfw12!', '01012345678', 'merge');
INSERT INTO comment (created_date, modified_date, content, talk_talk_id, user_id) VALUES ('2021-01-01 00:00:01', '2022-01-01 00:00:01', '댓글 내용 크크', '1','0');
INSERT INTO money_info (goal_money, delivery_fee, current_money, together_team) VALUES ('12000', '3000', '6000', '0');
INSERT INTO participant (participant_id) VALUES ('0');
INSERT INTO talk_talk (created_date, modified_date, title, content, comment_count, user_id) VALUES ('2021-01-01 00:00:01', '2022-01-01 00:00:01', '글의 제목은', '댓글 내용은 사실', '0', '1');
INSERT INTO together_team (room_type, store_name, team_dead_line, receive, max_user, user_id) VALUES ('같이 먹어요', '한우촌', '2023-01-01 00:00:01', '9D동', '3', '0');