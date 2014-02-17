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
 * Created 17/02/2014 14:01:35
 *
 */
@Embeddable
@Configurable
@RooIdentifier(dbManaged = true)
public final class MultimediaTagsPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1969331384166111819L;

	@Column(name = "multimedia_id", nullable = false)
    private Long multimediaId;

	@Column(name = "word", nullable = false, length = 45)
    private String word;

	public MultimediaTagsPK(Long multimediaId, String word) {
        super();
        this.multimediaId = multimediaId;
        this.word = word;
    }

	public MultimediaTagsPK() {
        super();
    }

	public Long getMultimediaId() {
        return multimediaId;
    }

	public String getWord() {
        return word;
    }

	public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(this);
    }

	public static MultimediaTagsPK fromJsonToMultimediaTagsPK(String json) {
        return new JSONDeserializer<MultimediaTagsPK>().use(null, MultimediaTagsPK.class).deserialize(json);
    }

	public static String toJsonArray(Collection<MultimediaTagsPK> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<MultimediaTagsPK> collection, String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<MultimediaTagsPK> fromJsonArrayToMultimediaTagsPKs(String json) {
        return new JSONDeserializer<List<MultimediaTagsPK>>().use(null, ArrayList.class).use("values", MultimediaTagsPK.class).deserialize(json);
    }
}
