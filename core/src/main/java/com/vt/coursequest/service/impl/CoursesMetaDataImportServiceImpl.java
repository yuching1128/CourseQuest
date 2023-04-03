package com.vt.coursequest.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
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
			Collection<Course> courses = correctlyParseItToSaveInDB(exhaustiveList, universityId);
			saveToDb(courses);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void saveToDb(Collection<Course> courses) {
		for (Course c : courses) {
			Optional<University> uni = universityRepository.findById(c.getUniversity().getId());
			if (uni.isPresent()) {
				c.setUniversity(uni.get());
			}
			Optional<Course> optionalCourse = courseRepository.findById(c.getId());
			if (optionalCourse.isPresent()) {
				Course existingCourse = optionalCourse.get();

				for (CourseCRN crn : c.getCourseCRNs()) {
					Optional<CourseCRN> optionalCRN = courseCRNsRepository.findByCrnNumber(crn.getCrnNumber());
					if (!optionalCRN.isPresent()) {
						crn = courseCRNsRepository.save(crn);
						existingCourse.getCourseCRNs().add(crn);
					} else {
						existingCourse.getCourseCRNs().add(optionalCRN.get());
					}
				}

				for (Instructor instructor : c.getInstructor()) {
					Optional<Instructor> optionalInstructor = instructorRepository.findByName(instructor.getName());
					if (!optionalInstructor.isPresent()) {
						instructor = instructorRepository.save(instructor);
						existingCourse.getInstructor().add(instructor);
					} else {
						existingCourse.getInstructor().add(optionalInstructor.get());
					}
				}

				if (null != c.getDegree()) {
					Optional<Degree> optionalDegree = degreeRepository.findByName(c.getDegree().getName());
					if (optionalDegree.isPresent()) {
						existingCourse.setDegree(optionalDegree.get());
					} else {
						existingCourse.setDegree(degreeRepository.save(c.getDegree()));
					}

				}

				existingCourse.setName(c.getName());
				courseRepository.save(existingCourse);
			} else {

				for (CourseCRN crn : c.getCourseCRNs()) {
					Optional<CourseCRN> optionalCRN = courseCRNsRepository.findByCrnNumber(crn.getCrnNumber());
					if (!optionalCRN.isPresent()) {
						c.getCourseCRNs().add(courseCRNsRepository.save(crn));
					} else {
						c.getCourseCRNs().add(optionalCRN.get());
					}
				}

				for (Instructor instructor : c.getInstructor()) {
					Optional<Instructor> optionalInstructor = instructorRepository.findByName(instructor.getName());
					if (!optionalInstructor.isPresent()) {
						instructor = instructorRepository.save(instructor);
						c.getInstructor().add(instructor);
					} else {
						c.getInstructor().add(optionalInstructor.get());
					}
				}

				if (null != c.getDegree()) {
					Optional<Degree> optionalDegree = degreeRepository.findByName(c.getDegree().getName());
					if (optionalDegree.isPresent()) {
						c.setDegree(optionalDegree.get());
					} else {
						c.setDegree(degreeRepository.save(c.getDegree()));
					}

				}

				courseRepository.save(c);
			}

		}
	}

	private Collection<Course> correctlyParseItToSaveInDB(List<CourseMetaData> exhaustiveList, int universityId) {
		Map<String, Course> map = new HashMap<>();
		for (CourseMetaData metaCourseData : exhaustiveList) {
			Course course = new Course();
			if (!map.containsKey(metaCourseData.getCourseNo())) {
				course.setId(Integer.parseInt(metaCourseData.getCourseNo()));
				course.setDegree(getDegree(metaCourseData.getCourseNo()));
				course.setName(metaCourseData.getCourseTitle());
				course.setUniversity(new University(universityId));
			} else {
				course = map.get(metaCourseData.getCourseNo());
			}
			course.getCourseCRNs().add(new CourseCRN(metaCourseData.getCrn()));
			Instructor i = new Instructor(metaCourseData.getInstructor());
			if (!course.getInstructor().contains(i)) {
				course.getInstructor().add(i);
			}
			map.put(metaCourseData.getCourseNo(), course);
		}
		return map.values();
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
