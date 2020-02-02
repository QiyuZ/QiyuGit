import lombok.Getter;
import lombok.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 *GitTrees may contains multiple blobs or trees
 */
@Getter
public final class GitTree extends GitGeneralObject {

    /**
     * id(hashCode) to object, easy to retrieve
     */
    private final Map<Integer, GitBlob> blobs;
    private final Map<Integer, GitTree> trees;

    /**
     * Constructor
     * @param blobs can be empty, can not be null
     * @param trees can be empty, can not be null
     */
    public GitTree(@NonNull Map<Integer, GitBlob> blobs, @NonNull Map<Integer, GitTree> trees) {
        this.blobs = blobs;
        this.trees = trees;
    }

    /**
     * convert the
     * @return byte[] can not be null
     */
    public byte[] convertToBytes() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        for (Integer blobKey : blobs.keySet()) {
            try {
                stream.write(blobs.get(blobKey).convertToBytes());
                stream.write(blobKey);
            } catch (IOException e) {
                throw new GitException(e.getMessage(), e);
            }
        }
        for (Integer treeKey : trees.keySet()) {
            try {
                stream.write(trees.get(treeKey).convertToBytes());
                stream.write(treeKey);
            } catch (IOException e) {
                throw new GitException(e.getMessage(), e);
            }
        }
        return stream.toByteArray();
    }

}
