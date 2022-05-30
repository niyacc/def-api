package cn.niya.api.util;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

/**
 * @date: 2022/05/30
 * @Description: Api Result return to web with JSON data type
 * example: view xxx api success return json type as {
 *     status: 100,
 *     msg: "ok"
 *
 * }
 */
public class JsonResult implements Serializable {


    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static ObjectMapper getMAPPER() {
        return MAPPER;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 响应业务状态
     */
    private long status;

    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应中的数据
     */
    private Object data;

    public static JsonResult build(Long status, String msg, Object data) {
        return new JsonResult(status, msg, data);
    }

    public static JsonResult ok() {
        return new JsonResult(null);
    }
    public static JsonResult ok(Object data) {
        return new JsonResult(data);
    }


    public static JsonResult unAuth() {
        return new JsonResult(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMsg(), "");
    }

    public static JsonResult error() {
        return new JsonResult(ResultCode.EMO.getCode(), ResultCode.EMO.getMsg(), null);
    }

    public static JsonResult error(Object msg) {
        return new JsonResult(ResultCode.EMO.getCode(), ResultCode.EMO.getMsg(), msg);
    }

    public JsonResult() {
    }

    public JsonResult(Long status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult(Object data) {
        this.status = ResultCode.OK.getCode();
        this.msg = ResultCode.OK.getMsg();
        this.data = data;
    }

    /**
     * 将json结果集转化为Result对象
     *
     * @param jsonData json数据 传的是Result的对象的Json字符串
     * @param clazz    Result中的object类型
     * @return
     */
    public static JsonResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, JsonResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isObject()) {
                obj = MAPPER.readValue(data.traverse(), clazz);
            } else if (data.isTextual()) {
                obj = MAPPER.readValue(data.asText(), clazz);
            }
            return build((long) jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static JsonResult format(String json) {
        try {
            return MAPPER.readValue(json, JsonResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData 传的是Result的对象的Json字符串
     *                 json数据
     * @param clazz    集合中的类型
     * @return
     */
    public static JsonResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build((long) jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }


}

enum ResultCode{


    OK(100 , "ok"),
    UNAUTHORIZED(401 , "auth is invalid"),
    EMO(0 , "fail");


    private long code;

    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
