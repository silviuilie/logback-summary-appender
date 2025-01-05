# logback-summary-appender


 [![java/maven build](https://github.com/silviuilie/logback-summary-appender/actions/workflows/maven.yml/badge.svg)](https://github.com/silviuilie/logback-summary-appender/actions/workflows/maven.yml)
![Coverage Status](./doc/badges/coverage.svg)


instead of appending, aggregate logback event metrics for a given period in a simple format :

    {
        "levels": "{WARN=1, ERROR=3, DEBUG=4}",
        "totalEvents": "8",
        "threadCount": "1",
        "start": "07/09/2022 22:48:20.526",
        "end": "07/09/2022 22:49:20.556",
        "duration": "60030",
        "description": "system-name"
    }



**TODO**s
- test 
  - see SummaryUnsynchronizedAppender
- benchmark  
