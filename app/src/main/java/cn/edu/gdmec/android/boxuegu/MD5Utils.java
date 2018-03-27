package cn.edu.gdmec.android.boxuegu;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/3/11.
 */

public class MD5Utils {
    public static String md5(String text){
        try {
            MessageDigest digest=MessageDigest.getInstance("md5");
            byte[] result=digest.digest(text.getBytes());
            StringBuilder stringBuilder=new StringBuilder();
            for(byte b:result){
                int number=b & 0xff;
                String hex=Integer.toHexString(number);
                if(hex.length()==1){
                    stringBuilder.append("0"+hex);
                }else {
                    stringBuilder.append(hex);
                }
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
