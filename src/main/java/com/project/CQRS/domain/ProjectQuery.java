package com.project.CQRS.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Document
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectQuery {
    @Id
    private Long id;
    private String title;
    private boolean closed;
    private String description;
    private double budget;
    @Enumerated(EnumType.STRING)
    private CategoryP categoryP;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;

    @OneToOne
    public User createdBy;

    @ManyToMany()
    public Set<User> members;
}
