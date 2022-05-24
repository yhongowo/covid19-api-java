# COVID-19-API
### 介绍
- 使用SpringBoot开发的疫情数据接口，爬虫。数据源来自丁香园。
- 业务流程：使用JSoup与HtmlUnit进行爬虫，将数据处理后持久化到Mysql，启用Redis中间件作为缓存。开发Restful API 提供服务。
- 技术栈：Java, SpringBoot, MySQL, Redis
- 开发环境：JDK-8

### 接口
- /api/statistics/latest
- /api/statistics/history
- /api/province/all
- /api/abroad
- /api/timeline

使用案例：http://175.178.174.246:9100/api/statistics/latest
返回结果：


    [
        {
            "domesticStat": {
                "currentConfirmedCount": 1570311,
                "currentConfirmedIncr": 0,
                "confirmedCount": 1875548,
                "confirmedIncr": 0,
                "suspectedCount": 18493,
                "suspectedIncr": 0,
                "curedCount": 289249,
                "curedIncr": 0,
                "deadCount": 15988,
                "deadIncr": 0,
                "seriousCount": 37636,
                "seriousIncr": 0,
                "countRemark": "",
                "dailyPic": "https://img1.dxycdn.com/2020/0211/763/3395998884005602079-135.png,https://img1.dxycdn.com/2020/0211/362/3395998896890788910-135.png,https://img1.dxycdn.com/2020/0211/365/3395998905480724211-135.png,https://img1.dxycdn.com/2020/0211/364/3395998916217859778-135.png,https://img1.dxycdn.com/2020/0211/922/3395998929103046444-135.png,https://img1.dxycdn.com/2020/0211/089/3395998939840182072-135.png",
                "imgUrl": "https://img1.dxycdn.com/2020/0201/450/3394153392393266839-135.png",
                "yesterdayConfirmedCountIncr": 0,
                "yesterdaySuspectedCountIncr": 0,
                "date": "2022-05-23"
            },
            "globalStatistics": {
                "currentConfirmedCount": 195723307,
                "confirmedCount": 524717276,
                "curedCount": 322703539,
                "deadCount": 6290430,
                "currentConfirmedIncr": 0,
                "confirmedIncr": 0,
                "curedIncr": 0,
                "deadIncr": 0,
                "yesterdayConfirmedCountIncr": 0,
                "date": "2022-05-23"
            },
            "date": null
        }
    ]

### 部署
- mysql: 执行resources目录下的sql脚本


- Redis: 安装Redis即可


- 找到application-test(prod).properties, 更改为你数据库的用户密码与url,在application.properties中根据需求选择环境为test或prod
```
spring.datasource.url=jdbc:mysql://localhost:3306/2019ncov?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=2019ncov
spring.datasource.password=<password>
```