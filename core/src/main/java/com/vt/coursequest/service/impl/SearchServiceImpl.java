package com.vt.coursequest.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.coursequest.elasticsearch.dto.CourseListDTO;
import com.vt.coursequest.elasticsearch.dto.SearchDTO;
import com.vt.coursequest.elasticsearch.model.CourseModel;
import com.vt.coursequest.entity.Course;
import com.vt.coursequest.entity.Degree;
import com.vt.coursequest.entity.Department;
import com.vt.coursequest.entity.University;
import com.vt.coursequest.service.SearchService;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

	@Autowired
	private ElasticsearchClient esClient;

	@Override
	public CourseListDTO getSearchResults(SearchDTO searchDto, Integer pageNum, Integer pageSize)
			throws ElasticsearchException, IOException {
		List<Course> courses = new ArrayList<>();
		SearchRequest searchRequest = null;
		SearchResponse<CourseModel> response = null;
		List<Query> qList = new ArrayList<>();

		if (null != searchDto.getFullTextSearch() && !searchDto.getFullTextSearch().isBlank()) {
			Query query3 = Query.of(q -> q.multiMatch(
					t -> t.fields("instructors", "crns", "courseName", "courseNum", "universityName", "dept", "degree")
							.query(searchDto.getFullTextSearch())));
			qList.add(query3);
		}
		if (null != searchDto.getLevel() && !searchDto.getLevel().isBlank()) {
			Query query1 = Query.of(q -> q.match(t -> t.field("level").query(searchDto.getLevel())));
			qList.add(query1);
		}
		if (null != searchDto.getDept() && !searchDto.getDept().isBlank()) {
			Query query2 = Query.of(q -> q.match(t -> t.field("dept").query(searchDto.getDept())));
			qList.add(query2);

		}
		searchRequest = SearchRequest.of(e -> e.index("course").query(b -> b.bool(bq -> bq.must(qList)))
				.allowPartialSearchResults(true).from(pageNum-1).size(pageSize));
		response = esClient.search(searchRequest, CourseModel.class);
		TotalHits total = response.hits().total();
		boolean isExactResult = total.relation() == TotalHitsRelation.Eq;
		Integer totalResultCourses = (int) total.value();
		if (isExactResult) {
			log.info("There are " + total.value() + " results");
		} else {
			log.info("There are more than " + total.value() + " results");
		}

		List<Hit<CourseModel>> hits = response.hits().hits();

		for (Hit<CourseModel> hit : hits) {
			CourseModel esCourseModel = hit.source();
			log.info("Found course " + esCourseModel.getCourseName() + ", score " + hit.score());
			Course courseObj = new Course();
			courseObj.setId(esCourseModel.getId());
			courseObj.setRating(esCourseModel.getRating());
			courseObj.setName(esCourseModel.getCourseName());
			courseObj.setDescription(esCourseModel.getDesc());
			courseObj.setUniversity(new University(esCourseModel.getUniversityId()));
			courseObj.setDept(new Department(esCourseModel.getDept(), new University(esCourseModel.getUniversityId())));
			courseObj.setCourseNum(esCourseModel.getCourseNum());
			courseObj.setDegree(new Degree(esCourseModel.getDegree()));
			courses.add(courseObj);
		}

		
		return new CourseListDTO(courses, totalResultCourses);
	}

}
