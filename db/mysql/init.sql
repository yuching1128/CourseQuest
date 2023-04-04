create schema CourseQuest;
use CourseQuest;

create table coursecrn
(
    id         int auto_increment
        primary key,
    crn_number varchar(255) null
);

create table degree
(
    id   int auto_increment
        primary key,
    name varchar(255) null,
    constraint UK_by27bbt64p1ria17hy3khpyft
        unique (name)
);

create table instructor
(
    id   int auto_increment
        primary key,
    name varchar(255) not null
);

create table major
(
    id   int auto_increment
        primary key,
    name varchar(255) null,
    constraint UK_oi0ctjbjvktdcfxws9w2exiwb
        unique (name)
);

create table university
(
    id   int auto_increment
        primary key,
    name varchar(255) null,
    constraint UK_ru212k5vib3yvu360fuy3h1g5
        unique (name)
);

create table course
(
    university_id int          not null
        primary key,
    description   varchar(255) null,
    name          varchar(255) not null,
    rating        float        null,
    degree_id     int          null,
    constraint FK4ag2rc4adjiesa1d0lcvnnq82
        foreign key (university_id) references university (id),
    constraint FKdgomx5qg5fvg9cgyh0sdh0vwo
        foreign key (degree_id) references degree (id)
);

create table course_coursecrns
(
    course_university_id int not null,
    coursecrns_id        int not null,
    primary key (course_university_id, coursecrns_id),
    constraint UK_sg5l6bj5k4pbeule0v9hi7ahd
        unique (coursecrns_id),
    constraint FK503m64orid5mj0ivll6oi9l99
        foreign key (coursecrns_id) references coursecrn (id),
    constraint FKjnmg2hil9djxoyml1udh9w9f
        foreign key (course_university_id) references course (university_id)
);

create table course_instructor
(
    courses_university_id int not null,
    instructor_id         int not null,
    primary key (courses_university_id, instructor_id),
    constraint FK4yrginwid5ipfrsjcwbgddcmm
        foreign key (courses_university_id) references course (university_id),
    constraint FKltori8qni3ivrlovca4gd9yw3
        foreign key (instructor_id) references instructor (id)
);

create table department
(
    id            int auto_increment
        primary key,
    name          varchar(255) null,
    university_id int          null,
    constraint FKh2ap9lv99txektaou64wpx8b2
        foreign key (university_id) references university (id)
);

create table level
(
    id            int auto_increment
        primary key,
    name          varchar(255) null,
    university_id int          null,
    constraint UK_lrjnw0jty1fs19q56u0us8d0n
        unique (name),
    constraint FKkvg8900rbtqtvud24vcsnw6rw
        foreign key (university_id) references university (id)
);

create table user
(
    id             int auto_increment
        primary key,
    concentration  varchar(255) null,
    email          varchar(255) null,
    first_name     varchar(255) null,
    last_name      varchar(255) null,
    password       varchar(255) null,
    phone          varchar(255) null,
    degree_fid     int          null,
    major_fid      int          null,
    university_fid int          null,
    constraint user_email_unique
        unique (email),
    constraint FK2e0fnf9jmfw0jmwf9m0hihdst
        foreign key (university_fid) references university (id),
    constraint FKfydmmlqn19qnpytrvob21vv4d
        foreign key (major_fid) references major (id),
    constraint FKnqnxmrttbx4tqiv2qkcv1ajcx
        foreign key (degree_fid) references degree (id)
);

create table review
(
    id            int auto_increment
        primary key,
    is_anonymous  bit          null,
    content       varchar(255) null,
    created_at    datetime(6)  not null,
    delivery      int          null,
    rating        float        null,
    workload      int          null,
    course_id     int          null,
    instructor_id int          null,
    university_id int          null,
    user_id       int          null,
    constraint FKd3gy0ma4syq3tkgqyje0nbhb2
        foreign key (instructor_id) references instructor (id),
    constraint FKiyf57dy48lyiftdrf7y87rnxi
        foreign key (user_id) references user (id),
    constraint FKn3q1vv5fmlyv3ouelg9ta0unb
        foreign key (university_id) references university (id),
    constraint FKprox8elgnr8u5wrq1983degk
        foreign key (course_id) references course (university_id)
);

create table user_course
(
    user_id              int not null,
    course_university_id int not null,
    primary key (user_id, course_university_id),
    constraint UK_ojhtxuq944ptfxntlhrxvcshw
        unique (course_university_id),
    constraint FK4xq18kasyvpo8xmpnpfcr754m
        foreign key (course_university_id) references course (university_id),
    constraint FKpv8tt3252hb6kyej8p7e7pokl
        foreign key (user_id) references user (id)
);

create table user_interested_course
(
    user_id                         int not null,
    interested_course_university_id int not null,
    primary key (user_id, interested_course_university_id),
    constraint UK_7g14fn7qjihowhstipo6iy3vd
        unique (interested_course_university_id),
    constraint FKcjdnskifislkh57b59bfquop2
        foreign key (interested_course_university_id) references course (university_id),
    constraint FKtlxbvteqs80kpgnwhed1w0vir
        foreign key (user_id) references user (id)
);





-- Degrees Data
INSERT INTO CourseQuest.degree (name) VALUES ('bachelor');
INSERT INTO CourseQuest.degree (name) VALUES ('master');
INSERT INTO CourseQuest.degree (name) VALUES ('doctor');

-- Universities Data
INSERT INTO CourseQuest.university (name) VALUES ('Virginia Tech');
INSERT INTO CourseQuest.university (name) VALUES ('New York University');
INSERT INTO CourseQuest.university (name) VALUES ('Stanford University');
INSERT INTO CourseQuest.university (name) VALUES ('Columbia University');

-- Courses Data
INSERT INTO CourseQuest.course (crn_number, description, name, rating, degree_id, university_id) VALUES ('CRN10001', 'This course teaches you about the power of the Software Development Lifecycle (SDLC) and software development methodologies like Agile. Explore fundamental programming principles and foundations of design, architecture, and deployment. Investigate skills a software engineer needs and identify job opportunities with hands-on projects. You will also learn about programming basics and software development tools and stacks. ', 'Software Engineering', 3.8, 1, 1);
INSERT INTO CourseQuest.course (crn_number, description, name, rating, degree_id, university_id) VALUES ('CRN10002', 'In computer science, a data structure is a way of organizing and storing data in a computer program so that it can be accessed and used efficiently. Data structures provide a means of managing large amounts of data, enabling efficient searching, sorting, insertion, and deletion of data.', 'Data Structure', 4.1, 2, 1);
INSERT INTO CourseQuest.course (crn_number, description, name, rating, degree_id, university_id) VALUES ('CRN10003', 'At 65+ hours, this Web Development course is without a doubt the most comprehensive web development course available online. Even if you have zero programming experience, this course will take you from beginner to mastery.', 'Web Development', 4.2, 3, 1);

-- Instructors Data
insert into CourseQuest.instructor (id, name) values (1, 'Dr. M');
insert into CourseQuest.instructor (id, name) values (2, 'Dr. K');
insert into CourseQuest.instructor (id, name) values (3, 'Dr. A');

-- User Data
insert into CourseQuest.user (id, first_name, last_name, password, phone) values (1, 'Eugene', 'Feng', 'sdfgsdfg', '8623579779');
insert into CourseQuest.user  (id, first_name, last_name, password, phone) values (2, 'Flora', 'Lin', 'sfgsdfg', '15151973561');
insert into CourseQuest.user  (id, first_name, last_name, password, phone) values (3, 'Jack', 'Lu', 'sdfg', '123');
insert into CourseQuest.user  (id, first_name, last_name, password, phone) values (4, 'Jake', 'Ma', 'sfg', '2323');

-- Reviews Data
insert into CourseQuest.review (id, content, created_at, delivery, is_anonymous, rating, workload, course_id, instructor_id, university_id, user_id) values (1, 'Good Course', '2023-03-23 08:04:28', 0, true, 4.1, 1, 1, 1, 1, 1);
insert into CourseQuest.review (id, content, created_at, delivery, is_anonymous, rating, workload, course_id, instructor_id, university_id, user_id) values (2, 'Nice Course', '2023-03-23 08:04:31', 1, true, 4.2, 0, 2, 2, 1, 1);
insert into CourseQuest.review (id, content, created_at, delivery, is_anonymous, rating, workload, course_id, instructor_id, university_id, user_id) values (3, 'Bad Course', '2023-03-23 08:04:32', 0, true, 4.4, 1, 1, 1, 1, 2);
insert into CourseQuest.review (id, content, created_at, delivery, is_anonymous, rating, workload, course_id, instructor_id, university_id, user_id) values (4, 'Bad Bad', '2023-03-23 08:04:33', 0, false, 5, 1, 1, 1, 1, 3);


INSERT INTO `CourseQuest`.`department` (`id`, `university_id`, `name`) VALUES ('1', '1', 'CS');

INSERT INTO `CourseQuest`.`department` (`id`, `university_id`, `name`) VALUES ('2', '1', 'CE');
INSERT INTO `CourseQuest`.`department` (`id`, `university_id`, `name`) VALUES ('3', '1', 'Civil');

INSERT INTO `CourseQuest`.`level` (`id`, `university_id`, `name`) VALUES ('1', '1', 'UG');

INSERT INTO `CourseQuest`.`level` (`id`, `university_id`, `name`) VALUES ('2', '1', 'Master');
INSERT INTO `CourseQuest`.`level` (`id`, `university_id`, `name`) VALUES ('3', '1', 'Phd');

INSERT INTO CourseQuest.coursecrn (crn_number, coursecrns_id) VALUES ('CRN1001', 1);
INSERT INTO CourseQuest.coursecrn (crn_number, coursecrns_id) VALUES ('CRN1001', 1);
INSERT INTO CourseQuest.coursecrn (crn_number, coursecrns_id) VALUES ('CRN2001', 2);
INSERT INTO CourseQuest.coursecrn (crn_number, coursecrns_id) VALUES ('CRN2001', 2);
INSERT INTO CourseQuest.coursecrn (crn_number, coursecrns_id) VALUES ('CRN3001', 3);
INSERT INTO CourseQuest.coursecrn (crn_number, coursecrns_id) VALUES ('CRN3001', 3);
INSERT INTO CourseQuest.coursecrn (crn_number, coursecrns_id) VALUES ('CRN4001', 4);
INSERT INTO CourseQuest.coursecrn (crn_number, coursecrns_id) VALUES ('CRN4002', 4);
