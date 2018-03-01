package mapleleaf.context.test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.xdog.mapleleaf.context.GenericHttpContext;
import org.xdog.mapleleaf.context.MainBeans;
import org.xdog.mapleleaf.context.XBean;
import org.xdog.mapleleaf.context.util.ContextUtil;

public class Test01 {
    
    @SuppressWarnings("static-access")
    @Test
    public void test01() {
        MainBeans ghc = new MainBeans();
        LinkedList<XBean> lists = new LinkedList<>();
        XBean x = new XBean();
        x.setName("lzw");
        x.setClassName("org.xdog.mapleleaf.context.XBean");
        x.setBean("sss");
        lists.add(x);
        ghc.setListxBean(lists);
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "dadoubi");
        x.setMap(map);
        try {
            GenericHttpContext.marshal(ghc,"mapleleaf.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        GenericHttpContext ghcc = (GenericHttpContext) GenericHttpContext.newInstance("mapleleaf.xml");
//        Object bean = ghcc.getListxBean().get(0).getBean();
//        System.out.println(bean);
//        System.out.println(bean.getName());
    }
    
    @Test
    public void test2() {
        try {
            GenericHttpContext ghc =
                    GenericHttpContext.getInstanceByXml("mapleleaf.xml", GenericHttpContext.class);
            System.out.println(ghc.getMbs().getListxBean().get(0).getClassName());
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void test3() {
        Class<?> c = GenericHttpContext.class;
        Method[] ms = c.getMethods();
        for(Method m : ms) {
            System.out.println(m.getName());
        }
        System.out.println((char)((int)'b'-32));
        System.out.println((int)'A');
        System.out.println(ContextUtil.firstToUpper("lzw"));
    }
    
    @Test
    public void test4() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Map<String, Object> map = new HashMap<>();
        map.put("className", "lzw");
        XBean x = (XBean) ContextUtil.createInstance("org.xdog.mapleleaf.context.XBean", map);
        System.out.println(x.getClassName());
    }
}
