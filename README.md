
# Welcome to ULesson App!
This is a test app built for ULesson

## Implementation
- I developed the app using the MVVM design pattern because it easily seperates the different parts of the app: the UI, database, networking class, and the viewmodels.

To fetch data from the remote source, I utilized Retrofit library mainly because it takes away the need to write boiler plate codes and laso provide converter for easy deserialization.

All major components are inected vith Hilt DI library. 

I used single-activity design and implementedd all the different destinations (live lessons and my lesson as fragmen)
