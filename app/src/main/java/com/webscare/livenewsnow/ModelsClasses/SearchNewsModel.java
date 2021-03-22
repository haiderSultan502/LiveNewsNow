package com.webscare.livenewsnow.ModelsClasses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchNewsModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("post_type")
    @Expose
    private String postType;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("thumbnail_url")
    @Expose
    private List<String> thumbnailUrl = null;
    @SerializedName("featured_media")
    @Expose
    private List<String> featuredMedia = null;
    @SerializedName("content")
    @Expose
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(List<String> thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public List<String> getFeaturedMedia() {
        return featuredMedia;
    }

    public void setFeaturedMedia(List<String> featuredMedia) {
        this.featuredMedia = featuredMedia;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
