package org.primefaces.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.test.pojo.TestObject;

@ManagedBean(name = "testView")
@RequestScoped
public class TestView implements Serializable {

    private List<TestObject> list;

    @PostConstruct
    public void init() {
        list = new ArrayList<TestObject>();

        list.add(new TestObject(true, new Object()));
        list.add(new TestObject(false, new Object()));
        list.add(new TestObject(false, new Object()));
        list.add(new TestObject(true, new Object()));
        list.add(new TestObject(false, new Object()));
    }

    public List<TestObject> getList() {
        return list;
    }

}
