package org.primefaces.test.pojo;

public class TestObject {

    private boolean flag;
    private Object innerObject;

    public TestObject(boolean flag, Object innerObject) {
        this.flag = flag;
        this.innerObject = innerObject;
    }

    public boolean getFlag() {
        return flag;
    }

    public Object getInnerObject() {
        return innerObject;
    }

}
