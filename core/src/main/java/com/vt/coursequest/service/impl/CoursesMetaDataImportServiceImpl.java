package com.vt.coursequest.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.vt.coursequest.dao.CourseCRNsRepository;
import com.vt.coursequest.dao.CourseRepository;
import com.vt.coursequest.dao.DegreeRepository;
import com.vt.coursequest.dao.InstructorRepository;
import com.vt.coursequest.dao.LevelRepository;
import com.vt.coursequest.dao.UniversityRepository;
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

	@Resource
	private UniversityRepository universityRepository;

	@Resource
	private DegreeRepository degreeRepository;

	@Resource
	private LevelRepository levelRepository;

	@Resource
	private InstructorRepository instructorRepository;

	@Resource
	private CourseCRNsRepository courseCRNsRepository;

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
			correctlyParseItAndSaveInDB(exhaustiveList, universityId);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void saveToDb(Collection<Course> courses) {
		for (Course c : courses) {
			Optional<Course> optionalCourse = courseRepository.findByCourseNum(c.getCourseNum());
			if (optionalCourse.isPresent()) {
				Course existingCourse = optionalCourse.get();

				for (CourseCRN crn : c.getCourseCRNs()) {
					existingCourse.getCourseCRNs().add(crn);
				}

				for (Instructor instructor : c.getInstructor()) {
					existingCourse.getInstructor().add(instructor);
				}
				existingCourse.setDegree(c.getDegree());
				existingCourse.setName(c.getName());
				courseRepository.save(existingCourse);
			} else {
				Course newCourse = new Course();
				newCourse.setDegree(c.getDegree());
				newCourse.setName(c.getName());
				newCourse.setCourseNum(c.getCourseNum());
				courseRepository.save(newCourse);
			}

		}
	}

	private void correctlyParseItAndSaveInDB(List<CourseMetaData> exhaustiveList, int universityId) {
		for (CourseMetaData metaCourseData : exhaustiveList) {
			Optional<Course> optionalCourse = courseRepository.findByCourseNum(metaCourseData.getCourseNo());
			Course existingCourse = null;
			if (optionalCourse.isPresent()) {
				existingCourse = optionalCourse.get();
			} else {
				Course newCourse = new Course();
				newCourse.setCourseNum(metaCourseData.getCourseNo());
				newCourse.setName(metaCourseData.getCourseTitle());
				existingCourse = courseRepository.save(newCourse);
			}

			existingCourse.setCourseNum(metaCourseData.getCourseNo());
			Degree degree = getDegree(metaCourseData.getCourseNo());
			if (null != degree) {
				Optional<Degree> optionalDegree = degreeRepository.findByName(degree.getName());
				if (optionalDegree.isPresent()) {
					existingCourse.setDegree(optionalDegree.get());
				} else {
					existingCourse.setDegree(degreeRepository.save(degree));
				}

			}
			existingCourse.setName(metaCourseData.getCourseTitle());

			Optional<University> uni = universityRepository.findById(universityId);
			if (uni.isPresent()) {
				existingCourse.setUniversity(uni.get());
			}

			Optional<CourseCRN> optionalCRN = courseCRNsRepository.findByCrnNumber(metaCourseData.getCrn());
			if (!optionalCRN.isPresent()) {
				CourseCRN crn1 = courseCRNsRepository.save(new CourseCRN(metaCourseData.getCrn()));
				existingCourse.getCourseCRNs().add(crn1);
			} else {
				existingCourse.getCourseCRNs().add(optionalCRN.get());
			}

			Optional<Instructor> optionalInstructor = instructorRepository.findByName(metaCourseData.getInstructor());

			if (!optionalInstructor.isPresent()) {
				Instructor instructor = instructorRepository.save(new Instructor(metaCourseData.getInstructor()));
				existingCourse.getInstructor().add(instructor);
			} else {
				existingCourse.getInstructor().add(optionalInstructor.get());
			}
			courseRepository.save(existingCourse);
		}
	}

	private Degree getDegree(String courseNo) {
		String degreeName = "Phd";
		if (Integer.valueOf(courseNo) <= 4000) {
			degreeName = "UG";
		} else if (Integer.valueOf(courseNo) <= 5000) {
			degreeName = "Master";
		}
		return new Degree(degreeName);
	}

	public static void main(String[] args) {
		CoursesMetaDataImportServiceImpl c = new CoursesMetaDataImportServiceImpl();
		try {
			c.importCourseMetaData(1, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void scrapCourseDescriptionMetaData(Integer universityId, boolean isFullImport) throws IOException {
		// TODO Auto-generated method stub

	}

}
