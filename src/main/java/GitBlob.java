import lombok.Getter;
import lombok.NonNull;

/**
 *GitBlob object
 */
@Getter
public final class GitBlob extends GitGeneralObject{

    /**
     * content of blob, assume it's a string format
     */
    private final String data;

    public GitBlob(@NonNull String data) {
        this.data = data;
    }

    public byte[] convertToBytes() {
        return getData().getBytes();
    }
}
