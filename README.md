# weatherApp

I am quite new to Android Development, but thought it might be fun and a bit of a challenge to try to create this work-test in Android environment. But since I have yet to learn a few of the android features, a more experienced developer might sneer at this. But hey, I tried my best! Some parts of the code is instancing unessecary objects for readability, hope that helps

The app is not optimized for different phones, but I hope that is ok. I tested the application on a Google Nexus virutual device and my Hueawei pro mate 9. So those two  devices do at least work.

EDIT: I have noticed some bugs, like the paramenter "lol", and some countries wont't get caught in try/catch statment.


**Problems I encountered during development:**
  - I had no Idea the main thread did not allow for getting online and fetching information from a server\database. The solution I used     might be seen as a bit of a hack, explicitly telling the thread to go online instead of creating another one that did it. I needed a      quick solution, and learned a little thing on the go.





**Android specific things I want to add:**
  - More use of MVC pattern, as it is right now I have the business logic in the Activities.
  - Use of Fragments.
  - I have not used any log messages for debugging/ future development, no LogCat for me.



**Things I wanted to add to the app:**
  - Being able to change units, using a State Pattern
  - More modularity to the app, so the user can choose what to see.
  - add more private methods to for readability of the business logic.
  - pictures of the current weather. (could not find where to find the picture codes in the OWM API I used)
  - more tests, more use of test-driven development as it stands right now I mainly used prints to see what my output were as stub           tests.
  - More structured styles and fonts in the values folder.
  - More structured javadoc/ documentation (sorry if it's a mess).

**Things I did think of during development**
  - The API I used had exceptional tools for error handling, so I think I managed to catch exceptions and give user a Toast if their input      is bad
  - I used Stringbuilder for efficiency when parising my strings for a little bit of efficiency.
  - Although I want more tests, I was aware of the bugs and ran those few I had quite a lot. I also did alot of top-down testing from       the Android simulator.




API's used to create this app:

OWMs Java API :  https://bitbucket.org/aksinghnet/owm-japis/src
OpenWeatherAPI:  http://openweathermap.org/api
