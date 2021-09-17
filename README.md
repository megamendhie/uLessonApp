
# Welcome to ULesson App!
This is a test app built for ULesson

## Implementation
- I developed the app using the MVVM design pattern because it easily seperates the different parts of the app: the UI, database, networking class, and the viewmodels.

I used Hilt to inject the Database, Api Service and the Repository at where needed. It took away the need for boiler-plate codes and would be much easier to test.

I used single-activity design and implementedd all the different destinations (live lessons and my lesson) as fragments using Navigation Component. That is mainly because fragements are much lighter than activities and an app like this would be lighter, faster, and more responsive with a single-activity, the underlying MainActivity acts as Entry Point.


For data caching, I used Room Database to cache My Lessons. With the DOA, rooms makes it quite easy to query the database and perform other CRUD opeerations with least error.

I started a bit late mainly due to power issues, as such I wasn't able to write the unit tests for the app and perfect the UI. 
