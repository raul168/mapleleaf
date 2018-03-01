package org.xdog.mapleleaf.context;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.xdog.mapleleaf.context.util.ContextUtil;

public class XBean {
    
    private String name;
    
    private String className;
    
    private String id;
    
    private HashMap<String, Object> map;
    
    private Object bean;
    
    @XmlAttribute
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }    
    
    @XmlAttribute
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
            this.id = id; 
    }

    public HashMap<String, Object> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Object> map) {
        this.map = map;
    }
    
    @XmlTransient
    public Object getBean() {
        return bean;
    }
    
    public void setBean(Object bean) {
        this.bean = bean;
    }
    
    public XBean() {
        
    }
    
    public Object injectBean() {
        bean = ContextUtil.createInstance(className, map);
        if(id == null||"".equals(id.trim())) {
            String str = className.substring(className.lastIndexOf(".")+1);
            id = ContextUtil.firstToLower(str);
        }
        return bean;
    }
}
