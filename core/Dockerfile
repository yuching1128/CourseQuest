FROM eclipse-temurin:11
LABEL Team CourseQuest
VOLUME /tmp
COPY target/course-quest.jar course-quest.jar
CMD ["java", "-jar","-Dspring.profiles.active=prod", "course-quest.jar"]