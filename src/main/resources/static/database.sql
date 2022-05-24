create database 2019ncov;

create table abroad
(
    provinceName          varchar(30) not null
        primary key,
    continents            varchar(10) null,
    currentConfirmedCount int         null,
    confirmedCount        int         null,
    suspectedCount        int         null,
    curedCount            int         null,
    deadCount             int         null,
    date                  date        null,
    deadCountRank         int         null,
    deadRate              float       null
);

create index abroad_provinceName_index
    on abroad (provinceName);

create table city
(
    provinceName          varchar(255) null,
    cityName              varchar(255) null,
    confirmedCount        int          null,
    curedCount            int          null,
    deadCount             int          null,
    suspectedCount        int          null,
    modifyTime            int          null,
    currentConfirmedCount int          null,
    constraint city_pk
        unique (provinceName, cityName)
)
    charset = utf8;

create table domestic_stat
(
    currentConfirmedCount       int           null,
    currentConfirmedIncr        int           null,
    confirmedCount              int           null,
    confirmedIncr               int           null,
    suspectedCount              int           null,
    suspectedIncr               int           null,
    curedCount                  int           null,
    curedIncr                   int           null,
    deadCount                   int           null,
    deadIncr                    int           null,
    seriousCount                int           null,
    seriousIncr                 int           null,
    countRemark                 varchar(255)  null,
    dailyPic                    varchar(1000) null,
    imgUrl                      varchar(1000) null,
    date                        date          not null
        primary key,
    yesterdayConfirmedCountIncr int           null,
    yesterdaySuspectedCountIncr int           null
)
    charset = utf8;

create table global_stat
(
    currentConfirmedCount       int  null,
    confirmedCount              int  null,
    curedCount                  int  null,
    deadCount                   int  null,
    currentConfirmedIncr        int  null,
    confirmedIncr               int  null,
    curedIncr                   int  null,
    deadIncr                    int  null,
    yesterdayConfirmedCountIncr int  null,
    date                        date not null,
    constraint global_stat_date_uindex
        unique (date)
);

alter table global_stat
    add primary key (date);

create table province
(
    provinceName          varchar(255) not null
        primary key,
    provinceShortName     varchar(255) null,
    confirmedCount        int          null,
    suspectedCount        int          null,
    curedCount            int          null,
    deadCount             int          null,
    comment               varchar(255) null,
    modifyTime            int          null,
    currentConfirmedCount int          null
)
    charset = utf8;

create table timeline
(
    id           int          not null
        primary key,
    pubDate      int          null,
    pubDateStr   varchar(255) null,
    title        varchar(255) null,
    summary      varchar(255) null,
    infoSource   varchar(255) null,
    sourceUrl    varchar(255) null,
    provinceId   int          null,
    provinceName varchar(255) null,
    createTime   int          null,
    modifyTime   int          null
)
    charset = utf8;


