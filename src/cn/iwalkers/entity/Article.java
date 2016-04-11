package cn.iwalkers.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


/*
 * 文章类
 */
@Entity
@Table(name = "article")
public class Article {
	@Id
	@Column(name = "article_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	//文章的id
	@Column(name = "article_title")
	private String title;	//标题
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", referencedColumnName="user_id",nullable = false)
	private User author;	//作者，映射到用户
	@Column(name = "article_publishDate")
	private Date publishDate;	//发布时间
	@Column(name = "article_content")
	private String content;	//内容
	@OneToMany(targetEntity=Comment.class,mappedBy="belong")
	@Cascade(CascadeType.ALL)
	private Set<Comment> comment;

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
