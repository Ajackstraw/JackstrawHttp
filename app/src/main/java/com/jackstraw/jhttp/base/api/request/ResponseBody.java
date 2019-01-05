package com.jackstraw.jhttp.base.api.request;

import com.jackstraw.jackhttp.response.BaseResponseBody;
import com.jackstraw.jackhttp.response.ResponseInterface;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author pink-jackstraw
 * @date 2019/01/04
 * @describe
 */
public class ResponseBody implements ResponseInterface {

    @Override
    public BaseResponseBody resultResponseBody(String result) {
        try {
            JSONObject json = new JSONObject(result);
            String errorCode = json.getString("code");
            if (errorCode.equals("200")) {
                String strResult = json.getString("data");
            } else {
                String message = json.getString("message");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
