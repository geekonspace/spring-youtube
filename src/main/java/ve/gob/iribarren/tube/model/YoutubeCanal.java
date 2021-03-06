package ve.gob.iribarren.tube.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

/**
 *
 * @author Williams Rivas
 * Created 17/02/2014 14:01:41
 *
 */
@Entity
@Table(name = "youtube_canal")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", table = "youtube_canal")
@RooDbManaged(automaticallyDelete = true)
public class YoutubeCanal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 255, unique = true)
    @NotNull
    private String name;

    @Column(name = "id_channel", length = 255, unique = true)
    @NotNull
    private String idChannel;

    @Column(name = "max_results")
    @NotNull
    @Min(8)
    @Max(16)
    private Integer maxResults;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdChannel() {
        return this.idChannel;
    }

    public void setIdChannel(String idChannel) {
        this.idChannel = idChannel;
    }

	public Integer getMaxResults() {
        return this.maxResults;
    }

	public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }
}
