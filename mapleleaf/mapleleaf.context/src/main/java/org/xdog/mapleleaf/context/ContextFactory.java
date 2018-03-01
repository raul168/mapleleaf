package org.xdog.mapleleaf.context;

public abstract class ContextFactory {    
    
    public abstract <T> T getBean(String id,Class<T> c);
    
    public abstract Object getBean(String id);
}
