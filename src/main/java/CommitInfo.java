import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * git committer information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommitInfo {
    private String committerName;
    private String committerEmail;
    private Timestamp timestamp;
    private String comment;
}
