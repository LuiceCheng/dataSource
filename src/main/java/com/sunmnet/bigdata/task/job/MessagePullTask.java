package com.sunmnet.bigdata.task.job;


import com.sunmnet.bigdata.task.service.IStudentInfoService;
import com.sunmnet.bigdata.task.service.IStudentSignService;
import com.sunmnet.bigdata.task.service.WeiXinOperatingAddress;
import com.sunmnet.bigdata.task.util.DateUtils;
import com.sunmnet.bigdata.task.vo.CheckInRecord;
import com.sunmnet.bigdata.task.vo.PageRequest;
import com.sunmnet.bigdata.task.vo.PageResponse;
import com.sunmnet.bigdata.task.vo.ParamsVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class MessagePullTask {

    @Autowired
    private IStudentSignService studentSignService;

    @Autowired
    private IStudentInfoService infoService;

    @Value("${task.startTime}")
    private String startTime;

    @Value("${task.endTime}")
    private String endTime;

    @Value("${task.corpID}")
    private String corpID;

    @Value("${task.secret}")
    private String secret;

    @Scheduled(cron = "${task.corn}")
    public void getMessage() throws Exception {
        int pageNum = 1;
        int pageSize = 100;
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        int selectSize;
        ParamsVo paramsVo = new ParamsVo();
        String startDate = DateUtils.dateToString(new Date(), DateUtils.PATTERN_DATE) + " " + startTime;
        String endDate = DateUtils.dateToString(new Date(), DateUtils.PATTERN_DATE) + " " + endTime;
        paramsVo.setStartTime(startDate);
        paramsVo.setEndTime(endDate);
        paramsVo.setCorpID(corpID);
        paramsVo.setSecret(secret);
        do {
            PageResponse<String> student = infoService.getStudent(pageRequest);
            selectSize = student.getRow().size();
            pageRequest.setPage(pageRequest.getPage() + 1);
            String[] userIds = (String[]) student.getRow().toArray(new String[student.getRow().size()]);
            paramsVo.setUserId(userIds);

            JSONObject checkin = WeiXinOperatingAddress.getCheckin(paramsVo);
            int errCode = (int) checkin.get("errcode");
            if (errCode == 0) {
                List<JSONObject> checkindata = (List<JSONObject>)checkin.get("checkindata");
                List<CheckInRecord> transfer = transfer(checkindata);
                if (transfer.size() > 0) {
                    int i = studentSignService.batchInsertSelective(transfer);
                    if(i>0){
                        System.out.println("成功插入数");
                    }
                }
            }
        } while (selectSize == pageSize);
    }

    public List<CheckInRecord> transfer(List<JSONObject> checkindata){
        List<CheckInRecord> records = new ArrayList<>();
        for (JSONObject object: checkindata) {
            CheckInRecord bean = (CheckInRecord) JSONObject.toBean(object, CheckInRecord.class);
            records.add(bean);
        }
        return records;
    }
}
