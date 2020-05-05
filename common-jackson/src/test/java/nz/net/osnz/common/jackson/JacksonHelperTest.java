package nz.net.osnz.common.jackson;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JacksonHelperTest {

    @Test
    public void mapSerialization() {
        Map<String, String> data = new HashMap<String, String>();

        data.put("one", "two");

        Assert.assertEquals("{\"one\":\"two\"}", JacksonHelper.serialize(data));
    }

    @Test
    public void allowsEmptyBeans() throws Exception {
        EmptyTestBean bean = new EmptyTestBean();
        String output = JacksonHelper.serialize(bean);
        Assert.assertEquals("{}", output);
    }

    private static class EmptyTestBean {
        // nothing here.
    }


}
