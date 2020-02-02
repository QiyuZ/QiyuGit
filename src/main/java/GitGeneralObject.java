

import lombok.NonNull;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * An abstract class for git object
 */
public abstract class GitGeneralObject {

    public GitGeneralObject() {}

    /**
     * convert the content to byte array, different objects should have different way to convert
     * @return byte array, can be empty can not be null
     */
    public abstract byte[] convertToBytes();

    /**
     * get unique hashCode
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        byte[] hash = getSha1HashCode(convertToBytes());
        int hashCode = ByteBuffer.wrap(hash).getInt();
        return hashCode;
    }

    /**
     * get hashCode by using Sha1 Algorithm
     * @param bytes can not be null
     * @return hashCode as byte[]
     */
    private byte[] getSha1HashCode(@NonNull byte[] bytes) {
        try {
            return MessageDigest.getInstance("SHA-1").digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new GitException(e.getMessage(), e);
        }
    }
}
