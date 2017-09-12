# play-pjax [![Build Status](https://travis-ci.org/ThatScalaGuy/play-pjax.svg?branch=master)](https://travis-ci.org/ThatScalaGuy/play-pjax) [![codecov](https://codecov.io/gh/ThatScalaGuy/play-pjax/branch/master/graph/badge.svg)](https://codecov.io/gh/ThatScalaGuy/play-pjax) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/480e6b888e2b42009722fe95a200b261)](https://www.codacy.com/app/sven-herrmann/play-pjax?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ThatScalaGuy/play-pjax&amp;utm_campaign=Badge_Grade)


## Requirements

* [Play Framework 2.6.0](https://github.com/playframework/playframework)    
* [jQuery](https://github.com/jquery/jquery)
* [jquery-pjax](https://github.com/defunkt/jquery-pjax)

## Compatibility Matrix

| Version | Play Version  | Scala 2.12.x | Scala 2.11.x |
|---------|---------------|--------------|--------------|
| 1.0.x   | 2.5.x         | no           | yes          |
| 1.1.x   | 2.6.x         | yes          | yes          |
| 1.2.x   | tbd           | tbd          | tbd          |

## Installation

1. Add the play filter to your application

    * Add the reslover if needed</br>

        ``` scala 
        resolvers += Resolver.jcenterRepo
        ```

    * Add the dependency to your sbt file</br>

        ``` scala
        libraryDependencies ++= Seq(
            "com.thatscalaguy" %% "play-pjax" % "1.1.0"
        )
        ```

    * Enable the module in the `application.conf`</br>

        ```
        play.modules.enabled += "com.thatscalaguy.play.filters.PjaxFilterModule"
        ```

    * Add the `PjaxFilter` to your application `HttpFilters` class</br>

        ``` scala
        class Filters @Inject()(pjaxFilter: PjaxFilter) extends HttpFilters {
          override def filters: Seq[EssentialFilter] = Seq(pjaxFilter)
        }
        ```


2. Add pjax to your views</br>
   Find a detailed instruction of pjax [here](https://github.com/defunkt/jquery-pjax#overview).

## Notes

## License

This software is licensed under the MIT license, please see the LICENSE file. 
