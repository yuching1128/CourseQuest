package com.vt.coursequest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author: EugeneFeng
 * @date: 3/29/23 11:08 AM
 * @description: some desc
 */

@Data
@Entity
@Table(name = "major")
public class Major {
    @JsonProperty
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    String name;
}
