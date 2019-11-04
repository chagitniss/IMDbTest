# IMDbTest
Automatic test watchlist feature of the beloved website imdb.com

### Prerequisites
 - Selenium 3.141.59
 - Maven 3.6.2

### Setup
In [src/test/resources/config.properties](https://github.com/chagitniss/IMDbTest/blob/master/src/test/resources/config.properties) file, you need to complete your details:
```
driverPath= Google chrome driver location On your machine
implicitlyWait= I.e. 20 (An implicit wait is to tell WebDriver to poll the DOM for a certain amount of time when trying to find an element or elements if they are not immediately available.)
email= The email address you registered with to the IMDb site
password= The passwords you registered with to the IMDb site
```
### Run   
To run the project use:
   ```
   src\test\java\watchlistTest.java
   ```
Currently supported browsers is:

- chrome
