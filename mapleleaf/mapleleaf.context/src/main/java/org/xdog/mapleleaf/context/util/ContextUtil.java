package org.xdog.mapleleaf.context.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ContextUtil {

    public static Object createInstance(String className) {
        Object obj = null;
        try {
                Class<?> c = Class.forName(className);
                return obj = c.newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        return obj;
    }
    
    public static Object injectObject(Object object,Map<String, Object> map) {
        Class<?> c = object.getClass();
        Method[] methods = c.getDeclaredMethods();
        Set<Entry<String, Object>> sets = map.entrySet();
        for(Entry<String, Object> e : sets) {
            String methodName = "set" + firstToUpper(e.getKey());
            ok:
            for(int index = 0;index++ < methods.length;) {
                Method m = methods[index-1];
                if(methodName.equals(m.getName())) {
                    try {
                        m.invoke(object, e.getValue());
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                    break ok;
                }
            }
        }
        return object;      
    }
    
    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String firstToUpper(String str) {
        char fr = str.charAt(0);
        char Ufr = (char)(fr - 32);
        String UpperStr = Ufr + str.substring(1);
        return UpperStr;
    }
    
    public static String firstToLower(String str) {
        char fr = str.charAt(0);
        char Ufr = (char)(fr + 32);
        String UpperStr = Ufr + str.substring(1);
        return UpperStr;
    }
    
    public static Object createInstance(String className,Map<String, Object> map) {
        Object obj = createInstance(className);
        obj = injectObject(obj, map);
        return obj;
    }
}
