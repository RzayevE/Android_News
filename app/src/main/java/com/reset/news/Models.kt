package com.reset.news

class HomeFeed(val articles: List<Article>)

class Article(
    val title: String, val urlToImage: String,
    val description: String, val publishedAt: String, val author: String, val source: Source
)

class Source(val name: String)


//"articles": [
//{
//    "source": {
//    "id": null,
//    "name": "Ria.ru"
//},
//    "author": "",
//    "title": "ФСБ задержала в Курской области главу отдела полиции за госизмену - РИА НОВОСТИ",
//    "description": "ФСБ сообщила о задержании начальника отдела полиции одного из районов Курской области по подозрению в госизмене. РИА Новости, 22.06.2020",
//    "url": "https://ria.ru/20200622/1573298085.html",
//    "urlToImage": "https://cdn25.img.ria.ru/images/sharing/article/1573298085.jpg?15732984611592828258",
//    "publishedAt": "2020-06-22T12:45:45Z",
//    "content": "https://ria.ru/20200622/1573298085.html\r\n/html/head/meta[@name='og:title']/@content\r\n/html/head/meta[@name='og:description']/@content\r\nhttps://cdn25.img.ria.ru/images/07e4/06/16/1573298465_0:0:1024:5… [+807 chars]"
//},