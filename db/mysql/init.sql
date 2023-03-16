create schema CourseQuest;
use CourseQuest;

create table degree
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

create table university
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

create table course
(
    id            int auto_increment
        primary key,
    crn_number    varchar(255) null,
    description   longtext null,
    name          varchar(255) null,
    rating        float        null,
    degree_id     int          null,
    university_id int          null,
    constraint FK4ag2rc4adjiesa1d0lcvnnq82
        foreign key (university_id) references university (id),
    constraint FKdgomx5qg5fvg9cgyh0sdh0vwo
        foreign key (degree_id) references degree (id)
);

INSERT INTO CourseQuest.degree (name) VALUES ('bachelor');
INSERT INTO CourseQuest.degree (name) VALUES ('master');
INSERT INTO CourseQuest.degree (name) VALUES ('doctor');

INSERT INTO CourseQuest.university (name) VALUES ('Virginia Tech');
INSERT INTO CourseQuest.university (name) VALUES ('New York University');
INSERT INTO CourseQuest.university (name) VALUES ('Stanford University');
INSERT INTO CourseQuest.university (name) VALUES ('Columbia University');

INSERT INTO CourseQuest.course (crn_number, description, name, rating, degree_id, university_id) VALUES ('CRN10001', 'This course teaches you about the power of the Software Development Lifecycle (SDLC) and software development methodologies like Agile. Explore fundamental programming principles and foundations of design, architecture, and deployment. Investigate skills a software engineer needs and identify job opportunities with hands-on projects. You will also learn about programming basics and software development tools and stacks. ', 'Software Engineering', 3.8, 1, 1);
INSERT INTO CourseQuest.course (crn_number, description, name, rating, degree_id, university_id) VALUES ('CRN10002', 'In computer science, a data structure is a way of organizing and storing data in a computer program so that it can be accessed and used efficiently. Data structures provide a means of managing large amounts of data, enabling efficient searching, sorting, insertion, and deletion of data.', 'Data Structure', 4.1, 2, 1);
INSERT INTO CourseQuest.course (crn_number, description, name, rating, degree_id, university_id) VALUES ('CRN10003', 'At 65+ hours, this Web Development course is without a doubt the most comprehensive web development course available online. Even if you have zero programming experience, this course will take you from beginner to mastery.', 'Web Development', 4.2, 3, 1);
