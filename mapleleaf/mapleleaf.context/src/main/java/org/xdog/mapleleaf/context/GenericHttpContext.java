package org.xdog.mapleleaf.context;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class GenericHttpContext extends ContextFactory{
    
    public static final String CHARSET_NAME = "utf-8";
        
    private MainBeans mbs;    
    
    public GenericHttpContext(MainBeans mbs) {
        super();
        this.mbs = mbs;
    }

    public GenericHttpContext(String xmlResource) {
        
    }
    
    public void exportResourceXml(String xmlResource) {
        
    }
    
    protected GenericHttpContext() {
        
    }
    
    public static JAXBContext newInstance(Class<?> c) throws JAXBException {
        return JAXBContext.newInstance(c);
    }
    
    public static void marshal(Object obj,String xmlResource) throws Exception {
        JAXBContext jaxbContext = newInstance(obj.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, CHARSET_NAME);
        String xml = "";
        try(StringWriter writer = new StringWriter()){
             jaxbMarshaller.marshal(obj, writer);
             xml = writer.toString();
        }
        try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(xmlResource))){            
            byte[] xmls = xml.getBytes(CHARSET_NAME);
            bos.write(xmls);
        }
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T getInstanceByXml(String xmlResource,Class<T> c) throws JAXBException, FileNotFoundException, IOException {
        File file = new File(xmlResource);
        JAXBContext jaxbContext = newInstance(c);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        try(InputStream is = new FileInputStream(file)){
            return (T) jaxbUnmarshaller.unmarshal(file);     
        }
    }
    
    public <T> T getBean(String id,Class<T> c) {
        XBean b = null;
        for(XBean bean:mbs.getListxBean()) {
            if(id.equals(bean.getId())) {
                b = bean;
                break;
            }
        }
        @SuppressWarnings("unchecked")
        T t = (T) b.getBean();
        return t;
    }
    
    public static ContextFactory newInstance(String xmlResource) {
        ContextFactory cf = null;
        try {
            MainBeans mainBean = getInstanceByXml(xmlResource, MainBeans.class);
            GenericHttpContext gc = new GenericHttpContext(mainBean);
            for(XBean bean:gc.mbs.getListxBean()) {
                bean.injectBean();
            }
            cf = gc;
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return cf;
    }
    
    public Object getBean(String id) {
        XBean b = null;
        for(XBean bean:mbs.getListxBean()) {
            if(id.equals(bean.getId())) {
                b = bean;
                break;
            }
        }
        Object obj = b.getBean();
        return obj;
    }

    public MainBeans getMbs() {
        return mbs;
    }

    public void setMbs(MainBeans mbs) {
        this.mbs = mbs;
    }
}
