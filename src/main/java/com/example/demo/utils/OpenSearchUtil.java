package com.example.demo.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.opensearch.DocumentClient;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchClientException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchResult;
import com.aliyun.opensearch.sdk.generated.document.Command;
import com.example.demo.pojo.OpenSearchError;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by void on 2018/7/25.
 */
public class OpenSearchUtil {

    private static Logger logger = LoggerFactory.getLogger(OpenSearchUtil.class);

    /**
     * 保存或更新一条记录(全量更新)
     *
     * @param appName        应用名
     * @param tableName      表名
     * @param documentClient 文档客户端对象
     * @param obj            数据对象
     * @return 推送结果
     * @throws OpenSearchClientException
     * @throws OpenSearchException
     */
    public static void saveOrUpdateData(String appName, String tableName, DocumentClient documentClient, Object obj) throws OpenSearchClientException, OpenSearchException {
        JSONArray jsonArray = new JSONArray();
        Map<String, Object> map = new HashMap<>();
        map.put("fields", obj);
        map.put("cmd", Command.ADD.toString());
        jsonArray.add(map);

        //执行推送操作
        String str = jsonArray.toJSONString();
        logger.debug(str);
        OpenSearchResult osr = documentClient.push(str, appName, tableName);
        if(osr.getResult().equalsIgnoreCase("true")){
            logger.debug("用户方全量推送无报错！\n以下为getTraceInfo推送请求Id:{}", osr.getTraceInfo().getRequestId());
        }else{
            logger.debug("用户方全量推送报错！"+osr.getTraceInfo());
        }
    }

    /**
     * 保存或更新多条记录(全量更新)
     *
     * @param appName        应用名
     * @param tableName      表名
     * @param documentClient 文档客户端对象
     * @param list            数据list
     * @return 推送结果
     * @throws OpenSearchClientException
     * @throws OpenSearchException
     */
    public static void saveOrUpdateList(String appName, String tableName, DocumentClient documentClient, List list) throws OpenSearchClientException, OpenSearchException {
        JSONArray jsonArray = new JSONArray();

        for(Object obj : list){
            Map<String, Object> map = ImmutableMap.of("fields", obj, "cmd", Command.ADD.toString());
            jsonArray.add(map);
        }

        //执行推送操作
        String str = jsonArray.toJSONString();
        logger.debug(str);
        OpenSearchResult osr = documentClient.push(str, appName, tableName);
        if(osr.getResult().equalsIgnoreCase("true")){
            logger.debug("用户方全量推送无报错！\n以下为getTraceInfo推送请求Id:{}", osr.getTraceInfo().getRequestId());
        }else{
            logger.debug("用户方全量推送报错！"+osr.getTraceInfo());
        }
    }

    /**
     * 保存或更新多条记录(全量更新)
     *
     * @param appName        应用名
     * @param tableName      表名
     * @param documentClient 文档客户端对象
     * @param list            数据list
     * @return 推送结果
     * @throws OpenSearchClientException
     * @throws OpenSearchException
     */
    public static void addList(String appName, String tableName, DocumentClient documentClient, List list) throws OpenSearchClientException, OpenSearchException {
        JSONArray jsonArray = new JSONArray();

        for(Object obj : list){
            Map<String, Object> map = ImmutableMap.of("fields", obj, "cmd", Command.ADD.toString());
            jsonArray.add(map);
        }

        //执行推送操作
        String str = jsonArray.toJSONString();
        logger.debug(str);
        OpenSearchResult osr = documentClient.push(str, appName, tableName);
        if(osr.getResult().equalsIgnoreCase("true")){
            logger.debug("用户方全量推送无报错！\n以下为getTraceInfo推送请求Id:{}", osr.getTraceInfo().getRequestId());
        }else{
            logger.debug("用户方全量推送报错！"+osr.getTraceInfo());
        }
    }

    /**
     * 修改数据(增量更新)
     *
     * @param appName
     * @param tableName
     * @param documentClient
     * @param obj
     * @throws OpenSearchClientException
     * @throws OpenSearchException
     */
    public static void updateSelectiveData(String appName, String tableName, DocumentClient documentClient, Object obj) throws OpenSearchClientException, OpenSearchException {
        JSONArray jsonArray = new JSONArray();
        Map<String, Object> map = new HashMap<>();
        map.put("fields", obj);
        map.put("cmd", Command.UPDATE.toString());
        jsonArray.add(map);

        //执行推送操作
        String str = jsonArray.toJSONString();
        logger.debug(str);
        OpenSearchResult osr = documentClient.push(str, appName, tableName);
        if(osr.getResult().equalsIgnoreCase("true")){
            logger.debug("用户方增量推送无报错！\n以下为getTraceInfo推送请求Id:{}", osr.getTraceInfo().getRequestId());
        }else{
            logger.debug("用户方增量推送报错！"+osr.getTraceInfo());
        }
    }

    /**
     * 修改多条数据(增量更新)
     *
     * @param appName
     * @param tableName
     * @param documentClient
     * @param list
     * @throws OpenSearchClientException
     * @throws OpenSearchException
     */
    public static void updateSelectiveList(String appName, String tableName, DocumentClient documentClient, List list) throws OpenSearchClientException, OpenSearchException {
        JSONArray jsonArray = new JSONArray();

        for(Object obj : list){
            Map<String, Object> map = ImmutableMap.of("fields", obj, "cmd", Command.UPDATE.toString());
            jsonArray.add(map);
        }

        //执行推送操作
        String str = jsonArray.toJSONString();
        logger.debug(str);
        OpenSearchResult osr = documentClient.push(str, appName, tableName);
        if(osr.getResult().equalsIgnoreCase("true")){
            logger.debug("用户方全量推送无报错！\n以下为getTraceInfo推送请求Id:{}", osr.getTraceInfo().getRequestId());
        }else{
            logger.debug("用户方全量推送报错！"+osr.getTraceInfo());
        }
    }

    /**
     * 删除一条记录
     *
     * @param appName        应用名
     * @param tableName      表名
     * @param documentClient 文档客户端对象
     * @param obj            数据对象(只需要有主键信息)
     * @return 推送结果
     * @throws OpenSearchClientException
     * @throws OpenSearchException
     */
    public static void deleteData(String appName, String tableName, DocumentClient documentClient, Object obj) throws OpenSearchClientException, OpenSearchException {
        JSONArray jsonArray = new JSONArray();
        Map<String, Object> map = new HashMap<>();
        map.put("fields", obj);
        map.put("cmd", Command.DELETE.toString());
        jsonArray.add(map);

        //执行推送操作
        OpenSearchResult osr = documentClient.push(jsonArray.toJSONString(), appName, tableName);
        if(osr.getResult().equalsIgnoreCase("true")){
            logger.debug("用户方删除推送无报错！\n以下为getTraceInfo推送请求Id:{}", osr.getTraceInfo().getRequestId());
        }else{
            logger.debug("用户方删除推送报错！"+osr.getTraceInfo());
        }
    }

    /**
     * 判断阿里接口返回是否报错,打印报错信息
     * @param obj
     * @param vo
     */
    public static void analyseReturn(JSONObject obj, Object vo){
        String status = (String) obj.get("status");
        //阿里接口返回查询失败
        if(status.equals("FAIL")){
            List<OpenSearchError> errors =  JSONArray.parseArray(obj.get("errors").toString(), OpenSearchError.class);
            for (OpenSearchError error : errors) {
                logger.warn("开放搜索OpenSearch接口报错,错误编码:{},错误描述:{}", error.getCode(), error.getMessage());
                logger.info(obj.toJSONString());
                Exception e = new Exception("调用堆栈");
                e.printStackTrace();
                logger.info(JSONObject.toJSONString(vo));
            }
        }
    }
}
