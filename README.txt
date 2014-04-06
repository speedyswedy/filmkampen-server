-Run locally
mvn clean install
java -jar target/dependency/jetty-runner.jar target/*.war;

-Push to Github
git add .
git commit -m "comments"
git push origin master

-Push to Heroku
git add .
git commit -m "comments"
git push heroku master

-Create new app in Heroku
heroku create




