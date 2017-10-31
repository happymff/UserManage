package UserManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mengfeifei on 2017/10/28.
 */

public class JsonExtracter {


    public static String getParamByRex(String json, String regex) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);
        String result = null;
        while (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }

    public static String getJsonSomeString(String jsonStr, String someString) {
        JSONObject jsonObject = JSON.parseObject(jsonStr);

        //提取出error为 0
        String someString1 = (String) jsonObject.get(someString);
        System.out.println(someString + someString1);
        return someString1;
    }

    public static void getJSONArraySomeString(String jsonStr, String someStringToArray,String someString) {
        JSONObject jsonObject = JSON.parseObject(jsonStr);

        //注意：results中的内容带有中括号[]，所以要转化为JSONArray类型的对象
        JSONArray result = jsonObject.getJSONArray(someStringToArray);

        for (int i = 0; i < result.size(); i++) {
            //提取出currentCity为 青岛
            String currentCity = result.getJSONObject(i).getString(someString);
            System.out.println(someString + currentCity);
            if (25 == Integer.parseInt(currentCity))
            {
                String currentCity1 = result.getJSONObject(i).getString("name");
                System.out.println("name:" + currentCity1);
                System.out.println(jsonStr);
            }
        }
    }

}