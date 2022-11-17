package app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Comments")
@Data
public class Comment implements Serializable{
    private static final long serialVersionUID = 2688176559336870324L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_comment_sequence")
    @SequenceGenerator(sequenceName = "comment_sequence", name = "custom_comment_sequence", allocationSize = 1)


    private long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private User user;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
	private Post post;
	
	@Column(columnDefinition = "VARCHAR(150)", nullable = false)
	private String content;
	
	@Column(name = "date_posted", columnDefinition = "DATE", nullable = false)
	private Date time;

}
