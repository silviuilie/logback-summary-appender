# logback-summary-appender

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

TODOs
- test
- ~~SummaryFileAppender should extend even more abstract OutputStreamAppender~~
  - see SummaryUnsynchronizedAppender
- more encoders/check speed? 
- ~~delete SimpleSummaryAsyncAppender ?~~
