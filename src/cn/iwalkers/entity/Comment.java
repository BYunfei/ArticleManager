package cn.iwalkers.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * 文章评论
 */
@Entity
@Table(name="comment")
public class Comment {
	@Id
	@Column(name="comment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	//评论的id
	@ManyToOne(targetEntity=User.class)	
	private User author; // 作者
	@Column(name="comment_content",nullable=false)
	private String content; // 内容
	@Column(name="comment_creatDate",nullable=false)
	private Date createDate; // 创建时间
	@ManyToOne(targetEntity=Article.class)
	@JoinTable(name="article_comment",joinColumns=@JoinColumn(referencedColumnName="comment_id"),inverseJoinColumns=@JoinColumn(referencedColumnName="article_id"))
	//@JoinColumn(referencedColumnName="article_id",nullable = false)
	private Article belong; // 所属的文章
	@OneToOne(targetEntity=Comment.class)
	private Comment father; // 父评论

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Article getBelong() {
		return belong;
	}

	public void setBelong(Article belong) {
		this.belong = belong;
	}

	public Comment getFather() {
		return father;
	}

	public void setFather(Comment father) {
		this.father = father;
	}

}
