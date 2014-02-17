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
 * Created 17/02/2014 13:57:31
 *
 */
@Entity
@Table(name = "category_multimedia")
@RooJavaBean
@RooJpaEntity(identifierType = CategoryMultimediaPK.class, versionField = "", table = "category_multimedia")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "multimediaId", "categoryId" })
public class CategoryMultimedia {

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("multimediaId", "categoryId").toString();
    }

    @EmbeddedId
    private CategoryMultimediaPK id;

    public CategoryMultimediaPK getId() {
        return this.id;
    }

    public void setId(CategoryMultimediaPK id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "multimedia_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Multimedia multimediaId;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Category categoryId;

    public Multimedia getMultimediaId() {
        return multimediaId;
    }

    public void setMultimediaId(Multimedia multimediaId) {
        this.multimediaId = multimediaId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }
}
