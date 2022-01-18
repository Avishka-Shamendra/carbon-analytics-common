/*
*  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.carbon.databridge.commons.binary.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.wso2.carbon.databridge.commons.binary.BinaryMessageConstants;
import org.wso2.carbon.databridge.commons.binary.BinaryMessageConverterUtil;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


/**
 * Binary Message Convert Util Testcase.
 */
public class BinaryMessageConvertUtilTest {
    private static final Logger log = LogManager.getLogger(BinaryMessageConvertUtilTest.class);

    @Test
    public void testEventBufferSize() {
        int stringSize = BinaryMessageConverterUtil.getSize("i♥apim)");
        int intSize = BinaryMessageConverterUtil.getSize(1);
        int longSize = BinaryMessageConverterUtil.getSize(1L);
        int doubleSize = BinaryMessageConverterUtil.getSize(1D);
        int floatSize = BinaryMessageConverterUtil.getSize(1F);
        int booleanSize = BinaryMessageConverterUtil.getSize(true);
        int intArraySize = BinaryMessageConverterUtil.getSize(new int[]{1, 2, 3});

        AssertJUnit.assertEquals("Expected byte length of String is 13", 13, stringSize);
        AssertJUnit.assertEquals("Expected byte length of Int is 4", 4, intSize);
        AssertJUnit.assertEquals("Expected byte length of Float is 4", 4, floatSize);
        AssertJUnit.assertEquals("Expected byte length of Long is 8", 8, longSize);
        AssertJUnit.assertEquals("Expected byte length of Double is 8", 8, doubleSize);
        AssertJUnit.assertEquals("Expected byte length of Boolean is 4", 1, booleanSize);
        AssertJUnit.assertEquals("Expected byte length of Int-Array  is 4", 4, intArraySize);
    }

    @Test
    public void testGetString() {
        byte[] bytes = "iapim)".getBytes(StandardCharsets.UTF_8);
        ByteBuffer eventByteBuffer = ByteBuffer.wrap(bytes);
        String returnString = BinaryMessageConverterUtil.getString(eventByteBuffer, 5);
        AssertJUnit.assertEquals("Expected return string is \'iapim\'", "iapim", returnString);
    }

    @Test
    public void testLoadDataWithValidByteArray() {
        String exampleString = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. ";
        InputStream stream = null;
        try {
            stream = new ByteArrayInputStream(exampleString.getBytes(BinaryMessageConstants.DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        byte[] dataArray1 = new byte[75];
        try {
            BinaryMessageConverterUtil.loadData(stream, dataArray1);
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = EOFException.class)
    public void testLoadDataWithInValidByteArray() throws IOException {
        String exampleString = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. ";
        InputStream stream = null;
        try {
            stream = new ByteArrayInputStream(exampleString.getBytes(BinaryMessageConstants.DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        byte[] dataArray1 = new byte[100];
        BinaryMessageConverterUtil.loadData(stream, dataArray1);
    }

    @Test
    public void testAssignData() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        try {
            BinaryMessageConverterUtil.assignData("Lorem Ipsum", byteBuffer);
            BinaryMessageConverterUtil.assignData(123, byteBuffer);
            BinaryMessageConverterUtil.assignData(1L, byteBuffer);
            BinaryMessageConverterUtil.assignData(1F, byteBuffer);
            BinaryMessageConverterUtil.assignData(1D, byteBuffer);
            BinaryMessageConverterUtil.assignData(true, byteBuffer);
            BinaryMessageConverterUtil.assignData(new int[]{1, 2, 3}, byteBuffer);
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }
}
