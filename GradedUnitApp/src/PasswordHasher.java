
/**
 A utility class for generating SHA-256 hashed password from a base string.
 */
import java.security.MessageDigest;
public class PasswordHasher implements Runnable {

    private String base; // the base string to be hashed
    private static Thread thread; // the thread to run the hashing process
    private String hashedPassword; // the hashed password
    private static boolean stopRequested; // flag to stop the hashing process

    /**
     * Creates a new PasswordHasher instance.
     * @param base the base string to be hashed
     */
    public PasswordHasher(String base) {
        this.base = base;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Starts the hashing process in a new thread.
     */
    public void run() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            hashedPassword = hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            stopRequested = true;
        }
    }

    /**
     * Returns the hashed password.
     * @return the hashed password
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * Stops the hashing process.
     */
    public static void stop() {
        stopRequested = true;
        thread.interrupt();
    }
}