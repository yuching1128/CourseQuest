package com.vt.coursequest.elasticsearch.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Dynamic;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import lombok.Data;

@Document(indexName = "comments", createIndex = true, dynamic = Dynamic.TRUE)
@Data
@Setting(shards = 1, replicas = 0)
public class CommentModel {

	@Field(type = FieldType.Integer)
	private Integer courseID = null;

	@Field(type = FieldType.Text, analyzer = "stop")
	private String content = null;

	@Field(type = FieldType.Keyword)
	private String username = null;

}
