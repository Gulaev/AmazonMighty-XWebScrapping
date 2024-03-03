package com.gulaev.web.entity;

import java.util.List;

public class Product {

  private long id;
  private String rating;
  private String asin;
  private String link;

  public String getAsin() {
    return asin;
  }

  public void setAsin(String asin) {
    this.asin = asin;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  private String title;
  private List<Comment> comments;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", rating='" + rating + '\'' +
        ", asin='" + asin + '\'' +
        ", link='" + link + '\'' +
        ", title='" + title + '\'' +
        ", comments=" + comments +
        '}';
  }
}
