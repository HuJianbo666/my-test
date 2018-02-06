## springboot 集成 报错


```
org.elasticsearch.client.transport.NoNodeAvailableException: None of the configured nodes are available: [{#transport#-1}{127.0.0.1}{127.0.0.1:9300}]
```
报这个错，看log

```
Received message from unsupported version: [2.0.0] minimal compatible version is: [5.6.0]
```
版本不一致，spring data is not supporting version 5.0.0 of es

醉了个醉

```
Unfortunately, Spring Boot Starter Data Elasticsearch does not support Elasticsearch version 5.x yet, they are working on it, but you can use my repository just changing the model propertly and update the repository with your own methods.

Your error is because you are using an instance of Elasticsearch 5.x and you need a 2.x version.
```

就不用springboot starter这种方式了。

## java API

https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-index.html

在这，也踩了不少坑