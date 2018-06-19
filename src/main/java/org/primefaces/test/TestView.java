package org.primefaces.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.test.pojo.TestObject;

@ManagedBean(name = "testView")
@RequestScoped
public class TestView implements Serializable {

    private List<TestObject> list;

    public static class NoopCallback implements ContextCallback {

        @Override
        public void invokeContextCallback(FacesContext context, UIComponent target) {
        }

    }

    public String generateOutputWithSideEffect(TestObject object) {
        if (object == null) {
            return null;
        }

        if (object.getFlag()) {
            ContextCallback noopCallback = new NoopCallback();

            FacesContext context = FacesContext.getCurrentInstance();
            context.getViewRoot().invokeOnComponent(context, "thirdForm", noopCallback);
        }

        return Boolean.toString(object.getFlag());
    }

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
