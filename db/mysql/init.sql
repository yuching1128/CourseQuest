-- create schema CourseQuest;
use CourseQuest;

-- create table coursecrn
-- (
--     id         int auto_increment
--         primary key,
--     crn_number varchar(255) null
-- );
--
-- create table degree
-- (
--     id   int auto_increment
--         primary key,
--     name varchar(255) null,
--     constraint UK_by27bbt64p1ria17hy3khpyft
--         unique (name)
-- );
--
-- create table instructor
-- (
--     id   int auto_increment
--         primary key,
--     name varchar(255) not null
-- );
--
-- create table major
-- (
--     id   int auto_increment
--         primary key,
--     name varchar(255) null,
--     constraint UK_oi0ctjbjvktdcfxws9w2exiwb
--         unique (name)
-- );
--
-- create table university
-- (
--     id   int auto_increment
--         primary key,
--     name varchar(255) null,
--     constraint UK_ru212k5vib3yvu360fuy3h1g5
--         unique (name)
-- );
--
-- create table course
-- (
--     id            int auto_increment
--         primary key,
--     description   varchar(255) null,
--     name          varchar(255) not null,
--     rating        float        null,
--     degree_id     int          null,
--     university_id int          null,
--     constraint FK4ag2rc4adjiesa1d0lcvnnq82
--         foreign key (university_id) references university (id),
--     constraint FKdgomx5qg5fvg9cgyh0sdh0vwo
--         foreign key (degree_id) references degree (id)
-- );
--
-- create table course_coursecrns
-- (
--     course_id     int not null,
--     coursecrns_id int not null,
--     primary key (course_id, coursecrns_id),
--     constraint UK_sg5l6bj5k4pbeule0v9hi7ahd
--         unique (coursecrns_id),
--     constraint FK503m64orid5mj0ivll6oi9l99
--         foreign key (coursecrns_id) references coursecrn (id),
--     constraint FKg7swl4871igbcguayn7jlwobd
--         foreign key (course_id) references course (id)
-- );
--
-- create table course_instructor
-- (
--     courses_id    int not null,
--     instructor_id int not null,
--     primary key (courses_id, instructor_id),
--     constraint FKltori8qni3ivrlovca4gd9yw3
--         foreign key (instructor_id) references instructor (id),
--     constraint FKteff0a8lukm14bmslsh3titiw
--         foreign key (courses_id) references course (id)
-- );
--
-- create table department
-- (
--     id            int auto_increment
--         primary key,
--     name          varchar(255) null,
--     university_id int          null,
--     constraint FKh2ap9lv99txektaou64wpx8b2
--         foreign key (university_id) references university (id)
-- );
--
-- create table level
-- (
--     id            int auto_increment
--         primary key,
--     name          varchar(255) null,
--     university_id int          null,
--     constraint UK_lrjnw0jty1fs19q56u0us8d0n
--         unique (name),
--     constraint FKkvg8900rbtqtvud24vcsnw6rw
--         foreign key (university_id) references university (id)
-- );
--
-- create table user
-- (
--     id             int auto_increment
--         primary key,
--     concentration  varchar(255) null,
--     email          varchar(255) null,
--     first_name     varchar(255) null,
--     last_name      varchar(255) null,
--     password       varchar(255) null,
--     phone          varchar(255) null,
--     degree_fid     int          null,
--     major_fid      int          null,
--     university_fid int          null,
--     constraint user_email_unique
--         unique (email),
--     constraint FK2e0fnf9jmfw0jmwf9m0hihdst
--         foreign key (university_fid) references university (id),
--     constraint FKfydmmlqn19qnpytrvob21vv4d
--         foreign key (major_fid) references major (id),
--     constraint FKnqnxmrttbx4tqiv2qkcv1ajcx
--         foreign key (degree_fid) references degree (id)
-- );
--
-- create table review
-- (
--     id            int auto_increment
--         primary key,
--     is_anonymous  bit          null,
--     content       varchar(255) null,
--     created_at    datetime(6)  not null,
--     delivery      int          null,
--     rating        float        null,
--     workload      int          null,
--     course_id     int          null,
--     instructor_id int          null,
--     university_id int          null,
--     user_id       int          null,
--     constraint FKd3gy0ma4syq3tkgqyje0nbhb2
--         foreign key (instructor_id) references instructor (id),
--     constraint FKiyf57dy48lyiftdrf7y87rnxi
--         foreign key (user_id) references user (id),
--     constraint FKn3q1vv5fmlyv3ouelg9ta0unb
--         foreign key (university_id) references university (id),
--     constraint FKprox8elgnr8u5wrq1983degk
--         foreign key (course_id) references course (id)
-- );
--
-- create table user_course
-- (
--     user_id   int not null,
--     course_id int not null,
--     primary key (user_id, course_id),
--     constraint UK_bca82qqx1k6y56o7mv7sy7pam
--         unique (course_id),
--     constraint FKka18m18kpimodvy8yg2icu083
--         foreign key (course_id) references course (id),
--     constraint FKpv8tt3252hb6kyej8p7e7pokl
--         foreign key (user_id) references user (id)
-- );
--
-- create table user_interested_course
-- (
--     user_id              int not null,
--     interested_course_id int not null,
--     primary key (user_id, interested_course_id),
--     constraint UK_727ygq5lux6ilf71xgkfnqyh6
--         unique (interested_course_id),
--     constraint FKexsv0pa0ljgpr17d4ry34xaxc
--         foreign key (interested_course_id) references course (id),
--     constraint FKtlxbvteqs80kpgnwhed1w0vir
--         foreign key (user_id) references user (id)
-- );







-- Degrees Data
INSERT INTO CourseQuest.degree (name) VALUES ('BS');
INSERT INTO CourseQuest.degree (name) VALUES ('MS');
INSERT INTO CourseQuest.degree (name) VALUES ('Phd');

-- University Data
INSERT INTO CourseQuest.university (name) VALUES ('Virginia Tech');
INSERT INTO CourseQuest.university (name) VALUES ('New York University');
INSERT INTO CourseQuest.university (name) VALUES ('Stanford University');
INSERT INTO CourseQuest.university (name) VALUES ('Columbia University');

-- Major Data
INSERT INTO CourseQuest.major (name) VALUES ('CS');
INSERT INTO CourseQuest.major (name) VALUES ('CE');
INSERT INTO CourseQuest.major (name) VALUES ('EE');

-- Courses Data
INSERT INTO CourseQuest.course (course_num, name, degree_id, university_id) VALUES ('CN1', 'SE','1', '1');
INSERT INTO CourseQuest.course (course_num, name, degree_id, university_id) VALUES ('CN2', 'Web','1', '1');
INSERT INTO CourseQuest.course (course_num, name, degree_id, university_id) VALUES ('CN3', 'Mobile', '2', '1');
INSERT INTO CourseQuest.course (course_num, name, degree_id, university_id) VALUES ('CN4', 'Database','1', '2');
INSERT INTO CourseQuest.course (course_num, name, degree_id, university_id) VALUES ('CN5', 'ML','2', '2');

-- Instructors Data
insert into CourseQuest.instructor (name) values ('Dr. M');
insert into CourseQuest.instructor (name) values ('Dr. K');
insert into CourseQuest.instructor (name) values ('Dr. A');

-- Course CRN
INSERT INTO CourseQuest.coursecrn (crn_number) values ('1001');
INSERT INTO CourseQuest.coursecrn (crn_number) values ('1002');
INSERT INTO CourseQuest.coursecrn (crn_number) values ('1003');
INSERT INTO CourseQuest.coursecrn (crn_number) values ('1004');
INSERT INTO CourseQuest.coursecrn (crn_number) values ('1005');

-- Department Data
INSERT INTO CourseQuest.department (name, university_id) values ('CS', 1);
INSERT INTO CourseQuest.department (name, university_id) values ('EE', 1);
INSERT INTO CourseQuest.department (name, university_id) values ('CE', 1);
INSERT INTO CourseQuest.department (name, university_id) values ('CS', 2);

-- User Data
insert into CourseQuest.user (id,  email, first_name, last_name, password, phone, degree_fid, department_fid, university_fid) values (1, 'abdf@vt.edu', 'Eugene', 'Feng', 'fasdf', '8623579779', 1, 1, 2);
insert into CourseQuest.user (id,  email, first_name, last_name, password, phone, degree_fid, department_fid, university_fid) values (2, 'asdfas@vt.edu', 'Jessica', 'L', 'sdfg', '8623579775', 1, 2, 1);
insert into CourseQuest.user (id,  email, first_name, last_name, password, phone, degree_fid, department_fid, university_fid) values (3, 'fsdfasfd@vt.edu', 'Tom', 'Du', 'gsfgagf', '1234567890', 2, 1, 2);

-- UserCourse Data
insert into CourseQuest.user_course (user_id, course_id)
values  (1, 1),
        (1, 2);
-- UserInterestedCourse Data
insert into CourseQuest.user_interested_course (user_id, interested_course_id)
values  (1, 1);

-- Course_CourseCRNs
insert into CourseQuest.course_coursecrns (course_id, coursecrns_id)
values  (1, 1),
        (2, 2),
        (3, 3),
        (4, 4),
        (5, 5);

-- Course_Instructor
insert into CourseQuest.course_instructor (courses_id, instructor_id)
values  (1, 1),
        (4, 1),
        (2, 2),
        (5, 2),
        (3, 3);
-- Review
insert into CourseQuest.review (id, is_anonymous, content, created_at, delivery, rating, workload, course_id, instructor_id, university_id, user_id)
values  (1, null, 'good', '2023-04-03 18:50:43', 0, 4.1, 0, 1, 1, 1, 1),
        (2, null, 'good', '2023-04-03 18:50:45', 0, 3, 0, 2, 2, 1, 1),
        (3, null, 'bad', '2023-04-03 18:50:47', 0, 2.5, 1, 1, 1, 1, 2);

-- Add hd
insert into CourseQuest.university (id, name, hd)
values  (1, 'Virginia Tech', 'vt.edu'),
        (2, 'New York University', 'nyu.edu'),
        (3, 'Stanford University', 'stanford.edu'),
        (4, 'Columbia University', 'columbia.edu');