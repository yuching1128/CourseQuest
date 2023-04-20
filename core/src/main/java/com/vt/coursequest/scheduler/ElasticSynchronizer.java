package com.vt.coursequest.scheduler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vt.coursequest.dao.CourseRepository;
import com.vt.coursequest.elasticsearch.dao.ICourseESRepository;
import com.vt.coursequest.elasticsearch.model.CourseModel;
import com.vt.coursequest.entity.Course;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ElasticSynchronizer {

	@Autowired
	ICourseESRepository courseESRepo;

	@Autowired
	CourseRepository courseRepo;

	@Autowired
	ElasticsearchClient esClient;

	// Fields

	// Constructor

	@Scheduled(cron = "0 */3 * * * *")
	@Transactional
	public void sync() {
		log.info("Start Syncing - {}", LocalDateTime.now());
		this.syncCourses();
		log.info(" End Syncing - {}", LocalDateTime.now());
	}

	private void syncCourses() {
//    	 Specification<CourseModel> courseSpec = (root, criteriaQuery, criteriaBuilder) ->
//         getModificationDatePredicate(criteriaBuilder, root);
		List<Course> courseList = new ArrayList<>();
		// if (courseESRepo.count() == 0) {
		courseList = courseRepo.findAll();
// else {
//	 courseList = courseRepo.findAll(courseSpec);
// }
//		for (Course course : courseList) {
//			log.info("Syncing User - {}", course.getId());
//			CourseModel courseModel = null;
//			Optional<CourseModel> esDoc = courseESRepo.findById(course.getId());
//			if (esDoc.isPresent()) {
//				courseModel = esDoc.get();
//			} else {
//				courseModel = new CourseModel();
//				courseModel.setId(course.getId());
//			}
//			courseModel.setCourseName(course.getName());
//			courseModel.setCourseNum(course.getCourseNum());
//			courseModel.setDesc(course.getDescription());
//			courseESRepo.save(courseModel);
//		}

		try {
			indexCoursesData(courseList);
		} catch (ElasticsearchException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void indexCoursesData(List<Course> courseList) throws ElasticsearchException, IOException {
		for (Course course : courseList) {
			CourseModel courseModel = new CourseModel();
			IndexResponse response = esClient.index(i -> i.index("courses").id(course.getCourseNum()).document(courseModel));
			log.debug("ES  : "+response+"...");
		}
	}

//    private static Predicate getModificationDatePredicate(CriteriaBuilder cb, Root<?> root) {
//    	 Expression<Timestamp> currentTime;
//         currentTime = cb.currentTimestamp();
//         Expression<Timestamp> currentTimeMinus = cb.literal(
//                 new Timestamp(System.currentTimeMillis() -
//                 (Constants.INTERVAL_IN_MILLISECONDE)));
//         return cb.between(root.<Date>get(Constants.MODIFICATION_DATE),
//                 currentTimeMinus,
//                 currentTime
//         );
//    }
}