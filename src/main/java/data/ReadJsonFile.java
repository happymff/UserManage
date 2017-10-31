package data;


import UserManager.JsonExtracter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Created by mengfeifei on 2017/10/28.
 */
public class ReadJsonFile {

    public static String readFile(String Path){
        BufferedReader reader = null;
        String laststr = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                laststr += tempString;
                laststr +="\n";
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }
    public static void main(String[] args) {
       String s= readFile("src/jsonFiles.txt");
        System.out.println(s);
        String[] s1 = s.split("\n");
        JsonExtracter jsonExtracter = new JsonExtracter();
        jsonExtracter.getJsonSomeString(s1[0],"name");
        System.out.println();
        jsonExtracter.getJSONArraySomeString(s1[1],"students","age");
    }
}