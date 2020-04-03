package com.sunmnet.bigdata.task.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sunmnet.bigdata.task.vo.AccessToken;
import net.sf.json.JSONObject;


public class WeiXinUtil {
	
	WeiXinUtil(){
		
	}
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param output
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject HttpRequest(String request, String RequestMethod,String output) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 建立连接
			URL url = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod(RequestMethod);
			if (output != null) {
				OutputStream out = connection.getOutputStream();
				out.write(output.getBytes("UTF-8"));
				out.close();
			}
			// 流处理
			InputStream input = connection.getInputStream();
			InputStreamReader inputReader = new InputStreamReader(input,"UTF-8");
			BufferedReader reader = new BufferedReader(inputReader);
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			// 关闭连接、释放资源
			reader.close();
			inputReader.close();
			input.close();
			input = null;
			connection.disconnect();
			String test=buffer.toString();
			jsonObject = JSONObject.fromObject(test);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	

	// 获取access_token的接口地址（GET）
	public final static String access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CorpID&corpsecret=SECRET";

	/**
	 * 获取access_token
	 * 
	 * @param CorpID
	 *            企业Id
	 * @param SECRET
	 *            管理组的凭证密钥，每个secret代表了对应用、通讯录、接口的不同权限；不同的管理组拥有不同的secret
	 * @return
	 */
	public static AccessToken getAccessToken(String corpID, String secret) {
		AccessToken accessToken = null;
		String requestUrl = access_token_url.replace("CorpID", corpID).replace("SECRET", secret);
		JSONObject jsonObject = HttpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				System.out.println("获取token成功:"+ jsonObject.getString("access_token") + "；————失效时间："+ jsonObject.getInt("expires_in"));
			} catch (Exception e) {
				accessToken = null;
				// 获取token失败
				String error = String.format("获取token失败 errcode:{} errmsg:{}",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
				System.out.println(error);
			}
		}
		return accessToken;
	}
	
	
	/**
	* 数据提交与请求通用方法
	* 
	* @param access_token
	*            凭证
	* @param RequestMethod
	*            请求方式
	* @param RequestURL
	*            请求地址
	* @param json
	*            请求的json数据
	* @param id
	*            部门ID
	* @param departmentId
	*            获取部门下人员的部门ID
	* @param userId
	*            获取当前的用户ID
	* */
	public static JSONObject PostMessage1(String access_token, String RequestmMethod,String RequestURL,String json) {
//		System.out.println("json:"+json);
		RequestURL = RequestURL.replace("ACCESS_TOKEN", access_token);
		
		JSONObject jsonobject = WeiXinUtil.HttpRequest(RequestURL, RequestmMethod,json);
		if (null != jsonobject) {
			if (0 != jsonobject.getInt("errcode")) {
				jsonobject.getInt("errcode");
				String error = String.format("操作失败 errcode:{%s} errmsg:{%s}",jsonobject.getInt("errcode"),jsonobject.getString("errmsg"));
				System.out.println(RequestURL+error);
			}
		}
		return jsonobject;
	}
	
	/**
	* 数据提交与请求通用方法
	* 
	* @param access_token
	*            凭证
	* @param RequestMethod
	*            请求方式
	* @param RequestURL
	*            请求地址
	* @param json
	*            请求的json数据
	* @param id
	*            部门ID
	* @param departmentId
	*            获取部门下人员的部门ID
	* @param userId
	*            获取当前的用户ID
	* */
	public static JSONObject PostMessage(String access_token, String RequestmMethod,String RequestURL,String json,String id,String departmentId,String userId,String tagId) {
		RequestURL = RequestURL.replace("ACCESS_TOKEN", access_token);
		if(id!=null){
			RequestURL = RequestURL.replace("ID", id);
		}
		if(departmentId!=null){
			RequestURL = RequestURL.replace("DEPARTMENT_ID", departmentId);
		}
		if(userId!=null){
			RequestURL = RequestURL.replace("USERID", userId);
		}
		if(tagId!=null){
			RequestURL = RequestURL.replace("TAGID", tagId);
		}
		JSONObject jsonobject = WeiXinUtil.HttpRequest(RequestURL, RequestmMethod,json);
		if (null != jsonobject) {
			if (0 != jsonobject.getInt("errcode")) {
				jsonobject.getInt("errcode");
				String error = String.format("操作失败 errcode:{%s} errmsg:{%s}",jsonobject.getInt("errcode"),jsonobject.getString("errmsg"));
				System.out.println(RequestURL+error);
			}
		}
		return jsonobject;
	}
	
	/**
	* 数据提交与请求通用方法
	* 
	* @param access_token
	*            凭证
	* @param RequestMethod
	*            请求方式
	* @param RequestURL
	*            请求地址
	* @param json
	*            请求的json数据
	* @param id
	*            部门ID
	* @param departmentId
	*            获取部门下人员的部门ID
	* @param userId
	*            获取当前的用户ID
	* */
	public static JSONObject GetMessage(String access_token, String RequestMethod,String RequestURL, String json,String id,String departmentId,String userId) {
		RequestURL = RequestURL.replace("ACCESS_TOKEN", access_token);
		if(id!=null){
			RequestURL = RequestURL.replace("ID", id);
		}
		if(departmentId!=null){
			RequestURL = RequestURL.replace("DEPARTMENT_ID", departmentId);
		}
		if(userId!=null){
			RequestURL = RequestURL.replace("USERID", userId);
		}
		JSONObject jsonobject = WeiXinUtil.HttpRequest(RequestURL,RequestMethod,json);
		if (null != jsonobject) {
			if (0 != jsonobject.getInt("errcode")) {
				jsonobject.getInt("errcode");
				String error = String.format("操作失败 errcode:{%s} errmsg:{%s}",jsonobject.getInt("errcode"),jsonobject.getString("errmsg"));
				System.out.println(error);
			}
		}
		return jsonobject;
	}
}
