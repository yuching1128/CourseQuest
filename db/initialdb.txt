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
    description   varchar(255) null,
    name          varchar(255) null,
    rating        float        null,
    degree_id     int          null,
    university_id int          null,
    constraint FK4ag2rc4adjiesa1d0lcvnnq82
        foreign key (university_id) references university (id),
    constraint FKdgomx5qg5fvg9cgyh0sdh0vwo
        foreign key (degree_id) references degree (id)
);