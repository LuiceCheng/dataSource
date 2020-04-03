package com.sunmnet.bigdata.task.service;

import com.sunmnet.bigdata.task.util.WeiXinUtil;
import com.sunmnet.bigdata.task.vo.AccessToken;
import com.sunmnet.bigdata.task.vo.ParamsVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WeiXinOperatingAddress {
    private static String accessToken="";
    //获取部门信息
    private final static String getDeptUrl = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN&id=ID";
    //获取部门用户
    private final static String getDeptUserUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID";
    //获取打卡数据
    private final static String getCheckinUrl="https://qyapi.weixin.qq.com/cgi-bin/checkin/getcheckindata?access_token=ACCESS_TOKEN";
    //获取用户信息
    private final static String getUserUrl="https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";

    public static void main(String[] args) {
        try {
            getCheckin(null);//获取打卡记录

        } catch (ParseException e) {
            e.printStackTrace();
        }
//		getUser1();//获取用户信息

        System.out.println("结束");
    }

    /*
     * 获取打卡记录
     * */
    public static JSONObject getCheckin(ParamsVo paramsVo) throws ParseException {

        AccessToken at= WeiXinUtil.getAccessToken(paramsVo.getCorpID(), paramsVo.getSecret());
        accessToken=at.getToken();
        Map<String,String[]> aa=new HashMap<>();
//        String[] strs = new String[]{"YuBaiBai"};//用户IDs

        Map<String,Object> Data=new HashMap<>();
        Data.put("opencheckindatatype", 1);
        Data.put("starttime", Date2TimeStamp(paramsVo.getStartTime()));
        Data.put("endtime", Date2TimeStamp(paramsVo.getEndTime()));
        Data.put("useridlist", paramsVo.getUserId());
//		for(int i = 0; i < 300; i++) {
        JSONObject jsonback=getCheckinW(Data);
//			System.out.println("状态:"+jsonback.get("errcode"));
//			if(jsonback.get("errcode").toString().equals("0")){
        System.out.println(jsonback);
//			}
//		}
        System.out.println("time:"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        return jsonback;
    }

    public static void getUser1() {
        AccessToken at=WeiXinUtil.getAccessToken("ww7fb9de0813c6ea1d", "9JbJhXBtuLy4tSEk6-KnybcJXNL-s05EOiGJ4_OmCt8");
        accessToken=at.getToken();
        System.out.println(getUserOneW());
    }


    public static JSONObject getCheckinW(Map<String,Object> data){
        JSONObject json=JSONObject.fromObject(data);
        return WeiXinUtil.PostMessage1(accessToken, "POST",getCheckinUrl , json.toString());
    }

    public static JSONObject getUserOneW(){
        return WeiXinUtil.GetMessage(accessToken, "GET", getUserUrl, null, null, null, "YuBaiBai");
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String Date2TimeStamp(String dateStr) {
        try {
            String format = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static List<Map<String,String>> getWeiXinDeptList(){
        JSONObject json=WeiXinUtil.GetMessage(accessToken, "POST", getDeptUrl, null,"供应商ID",null,null);
        JSONArray jsonarray=json.getJSONArray("department");
        List<Map<String,String>> allDept=new ArrayList<Map<String,String>>();
        for(int i=0;i<jsonarray.size();i++){
            Map<String,String> dept=new HashMap<String, String>();
            dept.put("id", jsonarray.getJSONObject(i).get("id").toString());
            dept.put("name", jsonarray.getJSONObject(i).get("name").toString());
            dept.put("parentid", jsonarray.getJSONObject(i).get("parentid").toString());
            allDept.add(dept);
        }
        return allDept;
    }

    public static List<Map<String,String>> getWeiXinDeptUser(String deptId){
        JSONObject json=WeiXinUtil.GetMessage(accessToken, "POST", getDeptUserUrl, null,null,deptId,null);
        JSONArray jsonarray=json.getJSONArray("userlist");
        List<Map<String,String>> allUser=new ArrayList<Map<String,String>>();
        for(int i=0;i<jsonarray.size();i++){
            Map<String,String> user=new HashMap<String, String>();
            user.put("userid", jsonarray.getJSONObject(i).get("userid").toString());
            user.put("name", jsonarray.getJSONObject(i).get("name").toString());
            allUser.add(user);
        }
        return allUser;
    }

}
