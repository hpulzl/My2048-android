package util;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by admin on 2015/11/25.
 */
public class DialogUtil{
    public static AlertDialog.Builder dialogBuiler(Context context,String title,String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(msg !=null){
            builder.setMessage(msg);
        }
        if(title!=null){
            builder.setTitle(title);
        }
        return builder;
    }
}
