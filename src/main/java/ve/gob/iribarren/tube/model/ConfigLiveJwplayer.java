package ve.gob.iribarren.tube.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
 * Created 17/02/2014 14:01:15
 *
 */
@Entity
@Table(name = "config_live_jwplayer")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", table = "config_live_jwplayer")
@RooDbManaged(automaticallyDelete = true)
public class ConfigLiveJwplayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "url", length = 255, unique = true)
    @NotNull
    private String url;

    @Column(name = "streamer", length = 45, unique = true)
    @NotNull
    private String streamer;

    @Column(name = "width")
    @NotNull
    private Integer width;

    @Column(name = "heigth")
    @NotNull
    private Integer heigth;

    @Column(name = "type", length = 1)
    @NotNull
    private String type;

    @Column(name = "description", length = 255)
    @NotNull
    private String description;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStreamer() {
        return streamer;
    }

    public void setStreamer(String streamer) {
        this.streamer = streamer;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeigth() {
        return heigth;
    }

    public void setHeigth(Integer heigth) {
        this.heigth = heigth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
