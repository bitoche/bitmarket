package ru.bitoche.basemarket.features;

import java.util.Date;

public class Logger {
    public static void log( Class<?> whoIs, String string){
        var newDate = new Date();

        System.out.println("[MYLOG]\t"
                +newDate.getHours()+":"+newDate.getMinutes()+":"+newDate.getSeconds()+":"+System.currentTimeMillis() +"\t||\t"
                +whoIs.getSimpleName()+"\t||\t"
                +string);
    }
}
