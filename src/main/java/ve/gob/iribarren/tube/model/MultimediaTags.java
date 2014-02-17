package ve.gob.iribarren.tube.model;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

/**
 *
 * @author Williams Rivas
 * Created 17/02/2014 14:01:27
 *
 */
@Entity
@Table(name = "multimedia_tags")
@RooJavaBean
@RooJpaEntity(identifierType = MultimediaTagsPK.class, versionField = "", table = "multimedia_tags")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "multimediaId" })
public class MultimediaTags {

    @EmbeddedId
    private MultimediaTagsPK id;

    public MultimediaTagsPK getId() {
        return this.id;
    }

    public void setId(MultimediaTagsPK id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "multimedia_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Multimedia multimediaId;

    public Multimedia getMultimediaId() {
        return multimediaId;
    }

    public void setMultimediaId(Multimedia multimediaId) {
        this.multimediaId = multimediaId;
    }

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("multimediaId").toString();
    }
}
