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
	@Field(type = FieldType.Keyword)
	private Integer id;
	
	@Field(type = FieldType.Text, analyzer = "pattern")
	private String courseName;
	
	@Field(type = FieldType.Text, analyzer = "pattern")
	private String desc;
	
	@Field(type = FieldType.Keyword)
	private String courseNum;

	@Field(type = FieldType.Text, analyzer = "pattern")
	private Set<String> instructors;
	
	@Field(type = FieldType.Text, analyzer = "pattern")
	private Set<String> crns;
	
	private Integer universityId;
	
	@Field(type = FieldType.Keyword)
	private String level;
	
	@Field(type = FieldType.Text, analyzer = "pattern")
	private String universityName;
	
	@Field(type = FieldType.Keyword)
	private String dept;
	
	@Field(type = FieldType.Keyword)
	private String degree;
	
	@Field(type = FieldType.Keyword)
	private Double rating;
	
//	@JsonIgnore
//	@JoinTypeRelations(relations = { @JoinTypeRelation(parent = "university", children = "dept"),
//			@JoinTypeRelation(parent = "dept", children = "course") })
//	private JoinField<String> relation;

	@Field(type = FieldType.Nested)
	private List<CommentModel> review;
	// getters and setters
}