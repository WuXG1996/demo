package com.example.demo.config;

import com.aliyun.opensearch.DocumentClient;
import com.aliyun.opensearch.OpenSearchClient;
import com.aliyun.opensearch.SearcherClient;
import com.aliyun.opensearch.sdk.generated.OpenSearch;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by void on 2018/7/18.
 * 阿里开发搜索
 */
@Data
@Configuration
public class OpenSearchConfig {

    @Value("${opensearch.accessKeyId}")
    private String accessKey;

    @Value("${openserach.accessKeySecret}")
    private String secret;

    @Value("${opensearch.application.host}")
    private String host;

    @Value("${opensearch.application.name}")
    private String applicationName;//test

    @Value("${opensearch.application.table.name}")
    private String tableName;//t_job

    @Bean
    public SearcherClient openSearchClient(){
        //创建并构造OpenSearch对象
        OpenSearch openSearch = new OpenSearch(accessKey, secret, host);
        //创建OpenSearchClient对象，并以OpenSearch对象作为构造参数
        OpenSearchClient searchClient = new OpenSearchClient(openSearch);
        //创建查询客户端
        SearcherClient searcherClient = new SearcherClient(searchClient);
        return searcherClient;
    }

    @Bean
    public DocumentClient documentClient(){
        //创建并构造OpenSearch对象
        OpenSearch openSearch = new OpenSearch(accessKey, secret, host);
        //创建OpenSearchClient对象，并以OpenSearch对象作为构造参数
        OpenSearchClient searchClient = new OpenSearchClient(openSearch);
        //创建文档操作客户端
        DocumentClient documentClient = new DocumentClient(searchClient);
        return documentClient;
    }
}
