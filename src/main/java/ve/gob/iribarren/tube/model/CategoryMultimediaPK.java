package ve.gob.iribarren.tube.model;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jpa.identifier.RooIdentifier;

/**
 * 
 * @author Williams Rivas
 * Created 17/02/2014 14:01:09
 *
 */
@Embeddable
@Configurable
@RooIdentifier(dbManaged = true)
public final class CategoryMultimediaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7466946885000165121L;

	@Column(name = "multimedia_id", nullable = false)
    private Long multimediaId;

	@Column(name = "category_id", nullable = false)
    private Long categoryId;

	public CategoryMultimediaPK(Long multimediaId, Long categoryId) {
        super();
        this.multimediaId = multimediaId;
        this.categoryId = categoryId;
    }

	public CategoryMultimediaPK() {
        super();
    }
	
	public Long getMultimediaId() {
        return multimediaId;
    }

	public Long getCategoryId() {
        return categoryId;
    }

	public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(this);
    }

	public static CategoryMultimediaPK fromJsonToCategoryMultimediaPK(String json) {
        return new JSONDeserializer<CategoryMultimediaPK>().use(null, CategoryMultimediaPK.class).deserialize(json);
    }

	public static String toJsonArray(Collection<CategoryMultimediaPK> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<CategoryMultimediaPK> collection, String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<CategoryMultimediaPK> fromJsonArrayToCategoryMultimediaPKs(String json) {
        return new JSONDeserializer<List<CategoryMultimediaPK>>().use(null, ArrayList.class).use("values", CategoryMultimediaPK.class).deserialize(json);
    }

}
