package org.xdog.mapleleaf.context;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xBeans")
public class MainBeans {
    
    private LinkedList<XBean> listxBean;
    
    @XmlElement(name = "xBean")
    public LinkedList<XBean> getListxBean() {
        return listxBean;
    }

    public void setListxBean(LinkedList<XBean> listxBean) {
        this.listxBean = listxBean;
    }
}
