package com.github.ahrooranr.object_measurer;


import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

public class ObjectGraphMeasurerTest {

    @Test
    public void measureTest() {

        HashSet<Object> rootObject1 = new HashSet<>(Arrays.asList(2, 3, 4, 1, "new dog", 17));
        HashSet<Object> rootObject2 = new HashSet<>(Arrays.asList(2, 3, 4, 1, "new dog"));
        rootObject2.add(17);
        rootObject2.add(15);
        rootObject2.remove(15);
        assertEquals(rootObject2, rootObject2);

        FootPrint footPrint1 = ObjectGraphMeasurer.measure(rootObject1);
        System.out.println(footPrint1);
        FootPrint footPrint2 = ObjectGraphMeasurer.measure(rootObject2);
        System.out.println(footPrint2);

        assertThat(footPrint1.toString()).isEqualTo(footPrint2.toString());
    }
}
