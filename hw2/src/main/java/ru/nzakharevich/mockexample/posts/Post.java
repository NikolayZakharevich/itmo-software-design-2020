package ru.nzakharevich.mockexample.posts;

/**
 * @author nzakharevich
 */
public class Post {
    public final long id;
    public final long date;
    public final long ownerId;
    public final long fromId;
    public final String postType;
    public final String text;

    public Post(long id, long date, long ownerId, long fromId, String postType, String text) {
        this.id = id;
        this.date = date;
        this.ownerId = ownerId;
        this.fromId = fromId;
        this.postType = postType;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }
}
