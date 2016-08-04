package com.thatscalaguy.utils

import org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl

import scala.xml.XML

/**
  * Created by sven on 8/4/16.
  */
object HTML {

  import scala.xml.Source._

  private[this] val parser = (new SAXFactoryImpl).newSAXParser

  def loadString(string: String) = XML.loadXML(fromString(string), parser)

}
