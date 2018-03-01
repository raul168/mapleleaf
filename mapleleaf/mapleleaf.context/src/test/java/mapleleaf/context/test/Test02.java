package mapleleaf.context.test;

import org.junit.Test;
import org.xdog.mapleleaf.context.ContextFactory;
import org.xdog.mapleleaf.context.GenericHttpContext;
import org.xdog.mapleleaf.context.XBean;

public class Test02 {
    
    @Test
    public void test01() {
        ContextFactory cf = GenericHttpContext.newInstance("mapleleaf.xml");
        XBean bean = cf.getBean("xBean", XBean.class);
        System.out.println(bean.getName());
    }
}
