package bg.softuni.pathfinder.model.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "comments")
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


//•	approved - Accepts boolean values
//•	created - Accepts Date and Time values
//    o	 The values should not be future dates
//•	text content - Accepts very long text values
//•	author - Accepts User Entities as values
//•	route - Accepts Route Entities as values

    @Column(nullable = false)
    private boolean approved;
    @Column(nullable = false)
    private Instant created;

    @Column(name = "text_content", columnDefinition =  "TEXT", nullable = false)
    private String content;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Route route;




    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
