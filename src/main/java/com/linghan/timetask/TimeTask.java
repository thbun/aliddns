package com.linghan.timetask;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.linghan.ddns.DDNS;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class TimeTask {
    @Scheduled(cron = "1 * * * * ?")
    public void configureTasks() {
        // 设置鉴权参数，初始化客户端
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-qingdao",// 地域ID
                "xxxxxxxxxxxxxx",// 您的AccessKey ID
                "xxxxxxxxxxxxxx");// 您的AccessKey Secret
        IAcsClient client = new DefaultAcsClient(profile);

        DDNS ddns = new DDNS();

        // 查询指定二级域名的最新解析记录
        DescribeDomainRecordsRequest describeDomainRecordsRequest = new DescribeDomainRecordsRequest();
        // 主域名
        describeDomainRecordsRequest.setDomainName("xxxxxxxx");
        // 主机记录
        describeDomainRecordsRequest.setRRKeyWord("www");
        // 解析记录类型
        describeDomainRecordsRequest.setType("A");
        DescribeDomainRecordsResponse describeDomainRecordsResponse = ddns.describeDomainRecords(describeDomainRecordsRequest, client);
        log_print("describeDomainRecords", describeDomainRecordsResponse);

        List<DescribeDomainRecordsResponse.Record> domainRecords = describeDomainRecordsResponse.getDomainRecords();
        // 最新的一条解析记录
        if (domainRecords.size() != 0) {
            DescribeDomainRecordsResponse.Record record = domainRecords.get(0);
            // 记录ID
            String recordId = record.getRecordId();
            // 记录值
            String recordsValue = record.getValue();
            // 当前主机公网IP
            String currentHostIP = ddns.getCurrentHostIP();
            System.out.println("-------------------------------当前主机公网IP为：" + currentHostIP + "-------------------------------");
            if (!currentHostIP.equals(recordsValue)) {
                // 修改解析记录
                UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest();
                // 主机记录
                updateDomainRecordRequest.setRR("www");
                // 记录ID
                updateDomainRecordRequest.setRecordId(recordId);
                // 将主机记录值改为当前主机IP
                updateDomainRecordRequest.setValue(currentHostIP);
                // 解析记录类型
                updateDomainRecordRequest.setType("A");
                UpdateDomainRecordResponse updateDomainRecordResponse = ddns.updateDomainRecord(updateDomainRecordRequest, client);
                log_print("updateDomainRecord", updateDomainRecordResponse);
            }
        }
    }


    private static void log_print(String functionName, Object result) {
        Gson gson = new Gson();
        System.out.println("-------------------------------" + functionName + "-------------------------------");
        System.out.println(gson.toJson(result));
    }
}
