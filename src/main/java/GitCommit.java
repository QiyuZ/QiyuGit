import lombok.Getter;
import lombok.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

/**
 * GitCommit contains tree as root, parents ids which could be used to check the history and committer's information
 * I used final everywhere because your requirement is immutable object
 */
@Getter
public class GitCommit extends GitGeneralObject{

    /**
     * root directory in this commit.
     */
    private final GitTree tree;

    /**
     * parents ids which could be used to check the history
     */
    private final List<String> parents;

    /**
     * committer's information
     */
    private final CommitInfo info;

    public GitCommit(@NonNull GitTree tree, @NonNull List<String> parents, @NonNull CommitInfo info) {
        this.tree = tree;
        this.parents = parents;
        this.info = info;
    }

    public byte[] convertToBytes() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            stream.write(tree.convertToBytes());
            stream.write(info.hashCode());
        } catch (IOException e) {
            throw new GitException(e.getMessage(), e);
        }
        return stream.toByteArray();
    }

    /**
     * There's a demo for you to have a test
     * The three hashCodes of different objects are unique.
     * The way to generate hashCode could be found in each objects
     */
    public static void main(String[] args) {
        GitBlob blob = new GitBlob("this is a test");
        GitTree tree = new GitTree(Collections.singletonMap(blob.hashCode(), blob), Collections.<Integer, GitTree>emptyMap());
        CommitInfo commitInfo = new CommitInfo("Qiyu", "", new Timestamp(System.currentTimeMillis()), "test");
        GitCommit commit = new GitCommit(tree, Collections.<String>emptyList(), commitInfo);
        System.out.println("Blob 's hashCode is " + blob.hashCode());
        System.out.println("Tree 's hashCode is " + tree.hashCode());
        System.out.println("Commit 's hashCode is " + commit.hashCode());

//        output
//        Blob 's hashCode is -98124263
//        Tree 's hashCode is 853089054
//        Commit 's hashCode is -2131101274
    }
}
