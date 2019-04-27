package com.example.demo.ali;

import com.aliyun.opensearch.DocumentClient;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchClientException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchException;
import com.aliyun.opensearch.sdk.generated.search.Config;
import com.example.demo.config.OpenSearchConfig;
import com.example.demo.domain.to.UserTo;
import com.example.demo.utils.OpenSearchUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author void
 * @date 2019/4/22 16:32
 * @desc
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OpenSearchTest {

    @Autowired
    private OpenSearchConfig config;
    @Autowired
    private DocumentClient documentClient;

    @Test
    public void test() throws OpenSearchClientException, OpenSearchException {
        List<UserTo> list = new ArrayList<>();
        list.add(new UserTo(1L, "user1", new Date()));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1980);
        list.add(new UserTo(2L, "user2", c.getTime()));

        c.set(Calendar.YEAR, 1970);
        list.add(new UserTo(3L, "user3", c.getTime()));

        c.set(Calendar.YEAR, 1940);
        list.add(new UserTo(4L, "user4", c.getTime()));
        OpenSearchUtil.addList(config.getApplicationName(), config.getTableName(), documentClient, list);
    }

}
