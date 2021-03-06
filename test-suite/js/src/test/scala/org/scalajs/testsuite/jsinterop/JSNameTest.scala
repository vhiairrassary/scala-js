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

package org.scalajs.testsuite.jsinterop

import scala.scalajs.js
import scala.scalajs.js.annotation._

import org.junit.Assert._
import org.junit.Test

class JSNameTest {
  import JSNameTest._

  @Test def defsThatAreProperties(): Unit = {
    val obj = js.Dynamic.literal(jsDef = 1).asInstanceOf[PropDefFacade]
    assertEquals(1, obj.internalDef)
  }

  @Test def vals(): Unit = {
    val obj = js.Dynamic.literal(jsVal = "hi").asInstanceOf[PropValFacade]
    assertEquals("hi", obj.internalVal)
  }

  @Test def vars(): Unit = {
    val obj = js.Dynamic.literal(jsVar = 0.1).asInstanceOf[PropVarFacade]
    assertEquals(0.1, obj.internalVar, 0.0)
    obj.internalVar = 0.2
    assertEquals(0.2, obj.internalVar, 0.0)
  }

  @Test def defsThatArePropertiesInScalaJSDefinedTrait_Issue2197(): Unit = {
    val obj = js.Dynamic.literal(jsDef = 1).asInstanceOf[PropDefSJSDefined]
    assertEquals(1, obj.internalDef)
  }

  @Test def valsInScalaJSDefinedTrait_Issue2197(): Unit = {
    val obj = js.Dynamic.literal(jsVal = "hi").asInstanceOf[PropValSJSDefined]
    assertEquals("hi", obj.internalVal)
  }

  @Test def varsInScalaJSDefinedTrait_Issue2197(): Unit = {
    val obj = js.Dynamic.literal(jsVar = 0.1).asInstanceOf[PropVarSJSDefined]
    assertEquals(0.1, obj.internalVar, 0.0)
    obj.internalVar = 0.2
    assertEquals(0.2, obj.internalVar, 0.0)
  }

  @Test def namesEndingInUnderscoreEquals(): Unit = {
    val d = js.Dynamic.literal("a_=" -> 1)
    val f = d.asInstanceOf[UndEqNamed]

    assertEquals(1, f.a)
    f.a = 2
    assertEquals(2, d.selectDynamic("a_="))
    assertEquals(2, f.a)
  }
}

object JSNameTest {

  @js.native
  trait PropDefFacade extends js.Any {
    @JSName("jsDef")
    def internalDef: Int = js.native
  }

  @js.native
  trait PropValFacade extends js.Any {
    @JSName("jsVal")
    val internalVal: String = js.native
  }

  @js.native
  trait PropVarFacade extends js.Any {
    @JSName("jsVar")
    var internalVar: Double = js.native
  }

  trait PropDefSJSDefined extends js.Any {
    @JSName("jsDef")
    def internalDef: Int
  }

  trait PropValSJSDefined extends js.Any {
    @JSName("jsVal")
    val internalVal: String
  }

  trait PropVarSJSDefined extends js.Any {
    @JSName("jsVar")
    var internalVar: Double
  }

  @js.native
  trait UndEqNamed extends js.Any {
    @JSName("a_=")
    def a: Int = js.native

    @JSName("a_=")
    def a_=(x: Int): Unit = js.native
  }

}
