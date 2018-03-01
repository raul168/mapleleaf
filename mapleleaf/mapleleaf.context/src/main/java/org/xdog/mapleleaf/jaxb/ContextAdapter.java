package org.xdog.mapleleaf.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ContextAdapter extends XmlAdapter<String, Object>{
    
    @Override
    public Object unmarshal(String name) throws Exception {
        Object obj = name;
        return obj;
    }

    @Override
    public String marshal(Object v) throws Exception {
        return (String) v;
    }

}
