package com.moriahacky.servicetestapp.servicetestmodule.pojos;

import org.junit.Test;

import com.moriahacky.servicetestapp.servicetestmodule.pojos.SamplePojo;

import static org.fest.assertions.api.Assertions.assertThat;

public class SamplePojoTest {

    @Test
    public void testMultiply() {

        // MyClass is tested
        SamplePojo tester = new SamplePojo();
        assertThat(tester.someMethod()).isEqualTo(-1);
    }

}
