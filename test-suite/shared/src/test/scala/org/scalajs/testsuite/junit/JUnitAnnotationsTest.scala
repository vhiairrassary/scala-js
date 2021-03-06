/*
 * Scala.js (https://www.scala-js.org/)
 *
 * Copyright EPFL.
 *
 * Licensed under Apache License 2.0
 * (https://www.apache.org/licenses/LICENSE-2.0).
 *
 * See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 */

package org.scalajs.testsuite.junit

import org.junit._
import org.junit.Assert._

object JUnitAnnotationsTest {
  @BeforeClass
  def beforeClassTest(): Unit = ()

  @AfterClass
  def afterClassTest(): Unit = ()
}

class JUnitAnnotationsTest {
  @Before
  def beforeTest(): Unit = ()

  @After
  def afterTest(): Unit = ()

  @Test
  def test1(): Unit = ()

  @Test
  def test2(): Unit = ()

  @Test
  def test3(): Unit = ()

  @Ignore
  @Test
  def testIgnore(): Unit = {
    assertTrue(false)
  }

  @Ignore("This is the @Ignore message.")
  @Test
  def testIgnoreWithMessage(): Unit = {
    assertTrue(false)
  }
}
