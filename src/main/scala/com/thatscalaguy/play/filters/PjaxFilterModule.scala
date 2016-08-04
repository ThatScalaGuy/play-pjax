package com.thatscalaguy.play.filters

import play.api.{Configuration, Environment}
import play.api.inject.{Binding, Module}

/**
  * Created by sven on 8/4/16.
  */
class PjaxFilterModule extends Module {
  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = {
    Seq(bind[PjaxFilter].toSelf)
  }
}
