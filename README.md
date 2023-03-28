# CourseQuest


## Project Description

Courses you choose in each semester are career-defining and what you specialize in the long run. Choosing courses for a given program can be a tedious task for students, especially with only a generic course description and no details on what the course involves. Students tend to reach out to other students through an informal channel who have already taken the course that they are interested in through various sources. VTCourseGuide can be a formal channel for students to connect and discuss the class course and have an overview, and get guidance regarding the classes from the students who have already taken it previously. Also, students will be allowed to rate and write reviews on the courses taken for other students to view.

Why we chose this:
Currently, there are no such applications available to help and guide students. Our product could improve students’ experiences in learning and help them choose the right class based on their interests.

## Team Members
- Gary Fang
- Raghavi Vannala  
- Swati Lamba   
- Yu-Ching Lin
- Yuechen Feng

## Stakeholders
- University students and staff

## Minimum Viable Product (MVP):
- VT students are able to login to the application.
- The web application allows Virginia Tech students to rate (0 to 5 stars) and review courses they have taken.
- All the VT students can search and view the reviews based on the courses.
- Reviewers can choose to be anonymous while writing a review.
- Students who have taken the classes previously will have the option to volunteer(mentor) to have a chat/audio/video session with the prospective - students(mentee). The mentor and a mentee will be paired using a matching algorithm, taking into account various data points such as major, courses, area of interests, etc. Once matched, the web app will generate an open-source audio/video chatting link for them to communicate with each other.


## Potential Enhancement
- Utilize a large language model (ChatGPT) to provide course recommendations based on a student's major, year of study, courses are taken, etc.
- Offer users the ability to verify their account (firstly with VT email id).
- Enhancing the above feature with SSO (Duo verification).
- Option to report an improper review.
- Deletion of improper reviews by administrators.

## Tech Stack
- Work in progress

## Project Management
- [JIRA Board](https://coursequest.atlassian.net/jira/software/projects/COUR/boards/1)


## Test and Deploy
- Work in progress

## Git Merge Strategy
- Create a new branch from develop branch
- Naming convention for the branch : COUR-[story-id]-brief-description 
- Work on this branch for your story commits
- Once you are done with all changes, create a merge request to develop branch with reviewer as the peer reviewer.
- Add appropriate comments if any for merge request.
- Either delete it manually or enable the tick mark to delete automatically after merge request is approved.
- When code is stable, merge the develop branch to main branch with team's review.

## Local Development Setup - frontend
- Install node.js (check if installed on machine by typing in 'node -v')
- Install git 
- Install Webstorm IDE
- CD into an appropriate folder location and git clone this repo
- Open the newly cloned repo in Webstorm
- Install the required packages through 'npm install'
- Install JSON-server for mocked backend API fetching.
- Run JSON-server 'cd mock-server' then 'json-server --watch db.json --routes routes.json'. Local server will be 'http://localhost:4000/'
- Check if can start up web application by running 'npm start' 

## Local Development Setup - backend + database
- MySQL Installation/Setup
  - Install MySQL on your local machine
  - Create new database via DataGrip (ensure to use your MySQL username and password)
  - Create tables and queries for by running these SQL scripts (located in coursequest -> db -> mysql -> init.sql)
- SpringBoot Setup
  - In Coursequest -> core -> scr -> main -> resources -> application.yml, update lines 4 and 5 to your MySQL username and password
  - Run the maven and java commands below to build the project 
  - Open localhost:8080 on browser. 
  
## Maven command(at the folder: coursequest) 
# To run the integrated project on local at port 8080 i.e. localhost:8080
- mvn clean install generate-resources process-resources validate
- java -jar core/target/course-quest.jar

# Spring Boot using DockerCompose connecting to MySQL Docker container

1. Run the command: mvn clean install generate-resources process-resources validate

2. Start dockerDesktop 

3. Run: 
- docker-compose build
- docker-compose up -d
- Test with localhost:8080

4. Stop docker container
`docker-compose down`


## Useful Docker commands
- `docker images`
- `docker container ls`
- `docker logs <container_name>`
- `docker container rm <container_name>`
- `docker image rm <image_name>`
