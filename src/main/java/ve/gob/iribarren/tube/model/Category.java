package ve.gob.iribarren.tube.model;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
 * Created 17/02/2014 13:56:57
 *
 */
@Entity
@Table(name = "category")
@RooJavaBean
@RooJpaEntity(versionField = "", table = "category")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "categoryMultimedias" })
public class Category {

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("categoryMultimedias").toString();
    }

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

    @OneToMany(mappedBy = "categoryId")
    private Set<CategoryMultimedia> categoryMultimedias;

    @Column(name = "name", length = 45, unique = true)
    @NotNull
    private String name;

    public Set<CategoryMultimedia> getCategoryMultimedias() {
        return categoryMultimedias;
    }

    public void setCategoryMultimedias(Set<CategoryMultimedia> categoryMultimedias) {
        this.categoryMultimedias = categoryMultimedias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
