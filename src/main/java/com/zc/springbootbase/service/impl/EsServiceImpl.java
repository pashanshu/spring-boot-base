package com.zc.springbootbase.service.impl;

import com.zc.springbootbase.service.EsService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanchang
 * @version 1.0
 * @date 2020/5/12 16:34
 */
@Slf4j
@Service
public class EsServiceImpl implements EsService {
    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public void getFromEs() {
        SearchRequest searchRequest = new SearchRequest("test02*");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("username", "zhanchang001"));
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);

        try {
            SearchResponse response = restHighLevelClient.search(searchRequest);
            Arrays.stream(response.getHits().getHits())
                    .forEach(i -> {
                        log.info(i.getIndex());
                        log.info((i.getSourceAsString()));
                        log.info(i.getId());
                    });
            log.info(String.valueOf(response.getHits().totalHits));

        } catch (IOException e) {
            log.error("met some error");
        }


    }
}
