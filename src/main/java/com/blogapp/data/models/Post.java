package com.blogapp.data.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blog_post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 50, unique = true)
    private String title;

    @Column(length = 500,nullable = false)
    private String content;

    private String coverImageUrl;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn
    private Author author;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateModified;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Comment>comment;

    public void updateContent(String content){
        this.content+=content;
    }
    public void addComment(Comment comment){
        if(this.comment==null){
            this.comment=new ArrayList<>();
        }
        this.comment.add(comment);
    }

}
