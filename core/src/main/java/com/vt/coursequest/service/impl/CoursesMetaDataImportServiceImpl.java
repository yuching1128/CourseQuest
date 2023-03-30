package com.vt.coursequest.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.vt.coursequest.dao.CourseRepository;
import com.vt.coursequest.entity.Course;
import com.vt.coursequest.entity.CourseCRN;
import com.vt.coursequest.entity.Degree;
import com.vt.coursequest.entity.Instructor;
import com.vt.coursequest.entity.University;
import com.vt.coursequest.importdata.bean.CourseMetaData;
import com.vt.coursequest.service.CoursesMetaDataImportService;

@Service
public class CoursesMetaDataImportServiceImpl implements CoursesMetaDataImportService {

	private static final String ADDRESS_FILE = "src/main/resources/static/data.csv";

	@Autowired
	private Environment environment;

	@Resource
	private CourseRepository courseRepository;

	@Override
	public void importCourseMetaData(Integer universityId, boolean isFullImport) throws IOException {

		// String fileName = environment.getProperty("application.metadata-file-path");
		Map<String, String> mapping = new HashMap<>(); // csv columnname as key and CourseMetaData property name as
														// value
		mapping.put("Term", "term");
		mapping.put("Course No.", "courseNo");
		mapping.put("Course Title", "courseTitle");
		mapping.put("Instructor", "instructor");
		mapping.put("CRN", "crn");
		mapping.put("Credits", "credits");

		// HeaderColumnNameTranslateMappingStrategy
		// for Student class
		HeaderColumnNameTranslateMappingStrategy<CourseMetaData> strategy = new HeaderColumnNameTranslateMappingStrategy<CourseMetaData>();
		strategy.setType(CourseMetaData.class);
		strategy.setColumnMapping(mapping);
		// Create csvtobean and csvreader object
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new FileReader(ADDRESS_FILE));

			CsvToBean<CourseMetaData> csvToBean = new CsvToBean<>();
			csvToBean.setCsvReader(csvReader);
			csvToBean.setMappingStrategy(strategy);
			// call the parse method of CsvToBean
			// pass strategy, csvReader to parse method
			List<CourseMetaData> exhaustiveList = csvToBean.parse();
			Collection<Course> courses = correctlyParseItToSaveInDB(exhaustiveList, universityId);
			saveToDb(courses);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void saveToDb(Collection<Course> courses) {
		courseRepository.saveAll(courses);
	}

	private Collection<Course> correctlyParseItToSaveInDB(List<CourseMetaData> exhaustiveList, int universityId) {
		Map<String, Course> map = new HashMap<>();
		for (CourseMetaData metaCourseData : exhaustiveList) {
			Course course = new Course();
			if (!map.containsKey(metaCourseData.getCourseTitle())) {
				course.setId(Integer.parseInt(metaCourseData.getCourseNo()));
				course.setDegree(getDegree(metaCourseData.getCourseNo()));
				course.setName(metaCourseData.getCourseTitle());
				course.setUniversity(new University(universityId));
			} else {
				course = map.get(metaCourseData.getCourseTitle());
			}
			course.getCourseCRNs().add(new CourseCRN(metaCourseData.getCrn()));
			course.getInstructor().add(new Instructor(metaCourseData.getInstructor()));
			map.put(metaCourseData.getCourseTitle(), course);
		}
		return map.values();
	}

	private Degree getDegree(String courseNo) {
		// TODO Auto-generated method stub
		return new Degree("test");
	}

	public static void main(String[] args) {
		CoursesMetaDataImportServiceImpl c = new CoursesMetaDataImportServiceImpl();
		try {
			c.importCourseMetaData(1, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void scrapCourseDescriptionMetaData(Integer universityId, boolean isFullImport) throws IOException {
		// TODO Auto-generated method stub

	}

}
