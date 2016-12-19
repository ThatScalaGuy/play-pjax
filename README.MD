# play-pjax

## Requirements

* [Play Framework 2.5.0](https://github.com/playframework/playframework)    
* [jQuery](https://github.com/jquery/jquery)
* [jquery-pjax](https://github.com/defunkt/jquery-pjax)

## Installation

1. Add the play filter to your application

    * Add the reslover if needed</br>

        ``` scala 
        resolvers += Resolver.jcenterRepo
        ```

    * Add the dependency to your sbt file</br>

        ``` scala
        libraryDependencies ++= Seq(
            "com.thatscalaguy" %% "play-pjax" % "1.0"
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
