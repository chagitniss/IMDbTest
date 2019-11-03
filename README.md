# IMDbTest
Automatic test watchlist feature of the beloved website imdb.com

### Setup
- In file src/test/resources/config.properties, You need to complete your details:
     ```
     driverPath=<google chrome driver location>
     implicitlyWait=<i.e. 20> (An implicit wait is to tell WebDriver to poll the DOM for a certain amount of time when trying to find an element or elements if they are not immediately available.)
     email=<The email address you registered with to the IMDb site>
     password=<The passwords you registered with to the IMDb site>
     ```
