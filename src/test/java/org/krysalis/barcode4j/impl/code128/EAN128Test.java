/*
 * Copyright 2007 Jeremias Maerki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* $Id: EAN128Test.java,v 1.7 2008-05-01 08:10:30 jmaerki Exp $ */

package org.krysalis.barcode4j.impl.code128;

import org.krysalis.barcode4j.ChecksumMode;

import junit.framework.TestCase;

/**
 * Tests for EAN 128.
 */
public class EAN128Test extends TestCase {

  public void testAI() throws Exception {
    EAN128AI ret = null;
    ret = EAN128AI.parseSpec("230", "n1");
    assertEquals("(230)n1 (fixed)", ret.toString());

    final char FNC1 = Code128LogicImpl.FNC_1;
    final char GS = EAN128Bean.DEFAULT_GROUP_SEPARATOR;
    final char CD = EAN128Bean.DEFAULT_CHECK_DIGIT_MARKER;
    EAN128LogicImpl impl;

    // Test FNC1 position and basic stuff
    impl = new EAN128LogicImpl(ChecksumMode.CP_AUTO, null);
    impl.setMessage("8100712345" + GS + "2112345678");
    assertEquals("(8100)712345(21)12345678", impl.getHumanReadableMsg());
    assertEquals(FNC1 + "8100712345" + FNC1 + "2112345678", impl.getCode128Msg());

    // Test check digit generation (cd0)
    impl = new EAN128LogicImpl(ChecksumMode.CP_AUTO, "(420)n5(91)n2+n9+n2-8+cd0");
    impl.setMessage("42012345" + FNC1 + "910112345678912345678" + CD);
    assertEquals("(420)12345(91)01123456789123456780", impl.getHumanReadableMsg());
    assertEquals(FNC1 + "42012345" + FNC1 + "9101123456789123456780", impl.getCode128Msg());

    // Test if GS is optional after a variable length field which
    // is redefined to fixed length in a template (FNC1 is added)
    impl = new EAN128LogicImpl(ChecksumMode.CP_AUTO, "(10)n2(420)n5");
    impl.setMessage("1012" + "42012345");
    assertEquals("(10)12(420)12345", impl.getHumanReadableMsg());
    assertEquals(FNC1 + "1012" + FNC1 + "42012345", impl.getCode128Msg());
    impl.setMessage("1012" + GS + "42012345");
    assertEquals("(10)12(420)12345", impl.getHumanReadableMsg());
    assertEquals(FNC1 + "1012" + FNC1 + "42012345", impl.getCode128Msg());

    // Test if GS is optional after real fixed length field (FNC1 is not added)
    impl = new EAN128LogicImpl(ChecksumMode.CP_AUTO, null);
    impl.setMessage("0112345678901231" + GS + "1001234");
    assertEquals("(01)12345678901231(10)01234", impl.getHumanReadableMsg());
    assertEquals(FNC1 + "0112345678901231" + "1001234", impl.getCode128Msg());
    impl.setMessage("0112345678901231" + "1001234");
    assertEquals("(01)12345678901231(10)01234", impl.getHumanReadableMsg());
    assertEquals(FNC1 + "0112345678901231" + "1001234", impl.getCode128Msg());


    // Test length redefinition of fixed length field not allowed
    try {
      impl = new EAN128LogicImpl(ChecksumMode.CP_AUTO, "(00)n19");
      assertTrue("Exception expected", false);
    } catch (Exception e) {
    } ;

    // Test date Template
    impl = new EAN128LogicImpl(ChecksumMode.CP_AUTO, "(11)d");
    impl.setMessage("11071231");
    assertEquals("(11)071231", impl.getHumanReadableMsg());
    assertEquals(FNC1 + "11071231", impl.getCode128Msg());

    // Test missing length in Template
    try {
      impl = new EAN128LogicImpl(ChecksumMode.CP_AUTO, "(10)n1-");
      assertTrue("Exception expected", false);
    } catch (Exception e) {
    } ;
  }

  public void testSupportRequests() throws Exception {
    final char FNC1 = Code128LogicImpl.FNC_1;
    final char GS = EAN128Bean.DEFAULT_GROUP_SEPARATOR;
    final char CD = EAN128Bean.DEFAULT_CHECK_DIGIT_MARKER;
    EAN128LogicImpl impl;

    /*
     * impl = new EAN128LogicImpl(ChecksumMode.CP_AUTO, "(415)n12+cd(8020)n-24(3900)n-14(96)n8");
     * impl.setMessage("415770105500005" + CD + FNC1
     * + "80209115675110080555" + FNC1
     * + "390000021170" + FNC1
     * + "9620080402");
     * //impl.setMessage("42012345" + FNC1 + "910112345678912345678" + CD);
     * assertEquals("(415)7701055000054(8020)9115675110080555(3900)00021170(96)20080402",
     * impl.getHumanReadableMsg());
     * assertEquals(FNC1 + "4157701055000054"
     * + "80209115675110080555" + FNC1
     * + "390000021170" + FNC1
     * + "9620080402", impl.getCode128Msg());
     */
  }

}
