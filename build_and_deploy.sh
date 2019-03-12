sudo /etc/init.d/tomcat7 stop
./gradlew clean war
sudo rm /usr/share/tomcat7/webapps/fineract-provider.war
sudo cp /build/libs/fineract-provider.war /usr/share/tomcat7/webapps
sudo /etc/init.d/tomcat7 start
