package cn.iwalkers.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "user_inf")
public class User{
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //主键ID
	@Column(name = "user_username",unique=true,nullable=false)
	private String username; //管理员的用户名
	@Column(name = "user_password",nullable=false)
	private String password; //管理员的密码
	@OneToMany(targetEntity=Article.class,mappedBy="author")
	@Cascade(CascadeType.ALL)
	private Set<Article> article;

	public int getId() {
		return id;
	}

	public Set<Article> getArticle() {
		return article;
	}

	public void setArticle(Set<Article> article) {
		this.article = article;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
