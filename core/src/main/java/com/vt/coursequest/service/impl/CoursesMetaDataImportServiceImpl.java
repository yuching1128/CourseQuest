package com.vt.coursequest.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.vt.coursequest.entity.Course;
import com.vt.coursequest.importdata.bean.CourseMetaData;
import com.vt.coursequest.service.CoursesMetaDataImportService;

@Service
public class CoursesMetaDataImportServiceImpl implements CoursesMetaDataImportService {

	private static final String ADDRESS_FILE = "src/main/resources/static/data.csv";
	
	@Autowired
    private Environment environment;

	@Override
	public void importCourseMetaData(Integer universityId, boolean isFullImport) throws IOException {

		//String fileName = environment.getProperty("application.metadata-file-path");
		Map<String, String> mapping = new HashMap<>(); // csv columnname as key and CourseMetaData property name as value
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

			CsvToBean csvToBean = new CsvToBean();
			csvToBean.setCsvReader(csvReader);
			csvToBean.setMappingStrategy(strategy);
			// call the parse method of CsvToBean
			// pass strategy, csvReader to parse method
			List<CourseMetaData> exhaustiveList = csvToBean.parse();

			for (CourseMetaData course : exhaustiveList) {
				System.out.println(course.toString());
			}
			List<Course> courses = correctlyParseItToSaveInDB(exhaustiveList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private List<Course> correctlyParseItToSaveInDB(List<CourseMetaData> exhaustiveList) {
		return null;
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

}
