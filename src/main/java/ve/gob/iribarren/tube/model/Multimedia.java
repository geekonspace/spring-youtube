package ve.gob.iribarren.tube.model;
import java.util.Calendar;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

/**
 *
 * @author Williams Rivas
 * Created 17/02/2014 14:01:22
 *
 */
@Entity
@Table(name = "multimedia")
@RooJavaBean
@RooJpaEntity(versionField = "", table = "multimedia")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "categoryMultimedias", "multimediaTagss" })
public class Multimedia {

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("categoryMultimedias", "multimediaTagss").toString();
    }

    @OneToMany(mappedBy = "multimediaId")
    private Set<CategoryMultimedia> categoryMultimedias;

    @OneToMany(mappedBy = "multimediaId")
    private Set<MultimediaTags> multimediaTagss;

    @Column(name = "title", length = 100)
    @NotNull
    private String title;

    @Column(name = "original_filename", length = 255)
    @NotNull
    private String originalFilename;

    @Column(name = "filename", length = 255, unique = true)
    @NotNull
    private String filename;

    @Column(name = "mime_type", length = 255)
    @NotNull
    private String mimeType;

    @Column(name = "type", length = 1)
    @NotNull
    private String type;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "plays_count")
    @NotNull
    private Long playsCount;

    @Column(name = "created", updatable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar created = java.util.Calendar.getInstance();

    @Column(name = "updated")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar updated;

    @Column(name = "created_by", length = 255)
    @NotNull
    private String createdBy;

    @Column(name = "updated_by", length = 255)
    @NotNull
    private String updatedBy;

    @Column(name = "active")
    @NotNull
    private boolean active;

    public Set<CategoryMultimedia> getCategoryMultimedias() {
        return categoryMultimedias;
    }

    public void setCategoryMultimedias(Set<CategoryMultimedia> categoryMultimedias) {
        this.categoryMultimedias = categoryMultimedias;
    }

    public Set<MultimediaTags> getMultimediaTagss() {
        return multimediaTagss;
    }

    public void setMultimediaTagss(Set<MultimediaTags> multimediaTagss) {
        this.multimediaTagss = multimediaTagss;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
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

    public Long getPlaysCount() {
        return playsCount;
    }

    public void setPlaysCount(Long playsCount) {
        this.playsCount = playsCount;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getUpdated() {
        return updated;
    }

    public void setUpdated(Calendar updated) {
        this.updated = updated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
}
