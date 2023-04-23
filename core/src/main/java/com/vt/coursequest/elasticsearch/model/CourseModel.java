package com.vt.coursequest.elasticsearch.model;

import java.util.List;
import java.util.Set;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import lombok.Data;

@Document(indexName = "course")
@Data
@Setting(shards = 1, replicas = 0, settingPath = "static/es-settings.json")
public class CourseModel {

	private Integer id;
	private String courseName;
	private String desc;
	private String courseNum;

	private Set<String> instructors;
	private Set<String> crns;
	private Integer universityId;
	private String universityName;
	private String dept;
	private String degree;
//	@JsonIgnore
//	@JoinTypeRelations(relations = { @JoinTypeRelation(parent = "university", children = "dept"),
//			@JoinTypeRelation(parent = "dept", children = "course") })
//	private JoinField<String> relation;

	@Field(type = FieldType.Nested)
	private List<CommentModel> review;
	// getters and setters
}